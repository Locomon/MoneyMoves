import React, { Component } from 'react';
import SplitPane, { Pane } from 'react-split-pane';
import { Card, CardHeader, CardBody, CardFooter, Row, Col, Button } from 'reactstrap';
import ReactECharts from 'echarts-for-react';
import axios from 'axios';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import '../../App.css'; 
import 'bootstrap/dist/css/bootstrap.min.css';
class InstrumentGraphContainer extends Component {
	constructor(props) {
		super(props);
		this.state = {
			loadedSymbol: '',
			timeseries: [],
			filteredTimeseries: [],
			loading: false,
			zoomStart: 80,
			zoomEnd: 100,
			fromDate: new Date(new Date().setDate(new Date().getDate() - 30)), // 30 days ago
			toDate: new Date()
		};
		this.handleFromDateChange = this.handleFromDateChange.bind(this);
		this.handleToDateChange = this.handleToDateChange.bind(this);
		this.handleLoadTimeseries = this.handleLoadTimeseries.bind(this);
	}
	
	handleFromDateChange(fromDate) {
		const { timeseries, toDate } = this.state;
		const filteredTimeseries = this.getFilteredTimeseries(timeseries, fromDate, toDate);
		this.setState({ fromDate: fromDate, filteredTimeseries: filteredTimeseries});		
	}

	handleToDateChange(toDate) {
		const { timeseries, fromDate } = this.state;
		const filteredTimeseries = this.getFilteredTimeseries(timeseries, fromDate, toDate);
		this.setState({ toDate: toDate, filteredTimeseries: filteredTimeseries});		
	}

	handleLoadTimeseries() {
		const { selectedInstrument } = this.props;
		if (!selectedInstrument) return;

		this.setState({ loading: true });

		const url = `http://localhost:8080/getEnrichedTimeseries?symbol=${selectedInstrument.symbol}`;
		axios.get(url).then(res => {
			const loadedSymbol = res.data.symbol;
			const tsMap = res.data.enrichedTimeseriesDatapointMap;

			// Convert map to array, sort by date
			const timeseriesArray =
				Object.entries(tsMap).map(([date, data]) => ({
					date,
					open: data.open,
					close: data.close,
					low: data.low,
					high: data.high,
					volume: data.volume,
					rsi: data.rsi,
					support: data.support1,
					support2: data.support2,
					resistance: data.resistance1,
					resistance2: data.resistance2,
					sma5: data.sma5,
					sma14: data.sma14,
					sma60: data.sma60,
					macd: data.macd,
					vwap: data.vwap
				})).sort((a, b) => new Date(a.date) - new Date(b.date)); // Ensure correct order
			const { fromDate, toDate } = this.state;
			const filteredTimeseries = this.getFilteredTimeseries(timeseriesArray, fromDate, toDate);
			this.setState({ loadedSymbol: loadedSymbol, timeseries: timeseriesArray
													  , filteredTimeseries: filteredTimeseries
													  , loading: false });
		}).catch(err => {
			console.error("Error loading timeseries:", err);
			this.setState({ loading: false });
		});
	}

	
	getFilteredTimeseries(data, fromDate, toDate) {
	  const from = fromDate ? new Date(fromDate) : null;
	  const to = toDate ? new Date(toDate) : null;

	  if (to) to.setHours(23, 59, 59, 999);  // end of day
	  if (from) from.setHours(0, 0, 0, 0);   // start of day

	  return data.filter(entry => {
	    const [year, month, day] = entry.date.split("-").map(Number);
	    const d = new Date(year, month - 1, day); // local midnight
	    return (!from || d >= from) && (!to || d <= to);
	  });
	}

	getCandlestickChartOptions() {
		const { filteredTimeseries } = this.state;
		// Transform data
		const categoryData = []; // dates
		const values = [];	   // [open, close, low, high]

		filteredTimeseries.forEach(entry => {
			categoryData.push(entry.date); // Assuming format: YYYY-MM-DD
			values.push([entry.open, entry.close, entry.low, entry.high]);
		});

		return {
			tooltip: {
				trigger: 'axis',
				axisPointer: {
					type: 'cross'
				}
			},
			xAxis: {
				type: 'category',
				data: categoryData,
				scale: true,
				boundaryGap: false,
				axisLine: { onZero: false },
				splitLine: { show: false },
				min: 'dataMin',
				max: 'dataMax'
			},
			yAxis: {
				scale: true,
				splitArea: {
					show: true
				}
			},
			series: [
				{
					name: 'Candlestick',
					type: 'candlestick',
					data: values,
					itemStyle: {
						color: '#00da3c',	  // green for down — should be up
						color0: '#ec0000',	 // red for up — should be down
						borderColor: '#008F28',
						borderColor0: '#8A0000'
					}
				},
				{
					name: 'VWAP',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.vwap ?? null),
					lineStyle: { color: '#000000' },
					showSymbol: false
				},
				{
					name: 'Support',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.support ?? null),
					lineStyle: { color: '#00BFFF', type: 'dashed' },
					showSymbol: false
				},
				{
					name: 'Support2',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.support2 ?? null),
					lineStyle: { color: '#00BFFF', type: 'dashed' },
					showSymbol: false
				},
				{
					name: 'Resistance',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.resistance ?? null),
					lineStyle: { color: '#FF6347', type: 'dashed' },
					showSymbol: false
				},
				{
					name: 'Resistance2',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.resistance2 ?? null),
					lineStyle: { color: '#FF6347', type: 'dashed' },
					showSymbol: false
				},
				{
					name: 'sma5',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.sma5 ?? null),
					lineStyle: { color: '#BB6347', type: 'dotted' },
					showSymbol: false
				},
				{
					name: 'sma14',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.sma14 ?? null),
					lineStyle: { color: '#FF6347', type: 'dotted' },
					showSymbol: false
				},
				{
					name: 'sma60',
					type: 'line',
					data: filteredTimeseries.map(entry => entry.sma60 ?? null),
					lineStyle: { color: '#000000', type: 'dotted' },
					showSymbol: false
				}				
			]
		};
	}
	
	getRSIChartOptions() {
		const { filteredTimeseries } = this.state;
		const dates = filteredTimeseries.map(entry => entry.date);
		const rsi14 = filteredTimeseries.map(entry => entry.rsi ?? null);

		return {
			title: { text: 'RSI(14)', left: 0 },
			tooltip: { trigger: 'axis' },
			xAxis: { type: 'category', data: dates
			},
			yAxis: { min: 0, max: 100, splitLine: { show: true } },
			series: [{ type: 'line', data: rsi14, smooth: true, lineStyle: { color: '#5470C6' } }]
		};
	}

	getMACDChartOptions() {
		const { filteredTimeseries }= this.state;
		const dates = filteredTimeseries.map(entry => entry.date);
		const macd = filteredTimeseries.map(entry => entry.macd ?? null);

		return {
			title: { text: 'MACD', left: 0 },
			tooltip: { trigger: 'axis' },
			xAxis: { type: 'category', data: dates
			},
			yAxis: { min: -5, max: 5, splitLine: { show: true } },
			series: [{ type: 'line', data: macd, smooth: true, lineStyle: { color: '#5470C6' } }]
		};
	}
	

	getVolumeChartOptions() {
		const { filteredTimeseries } = this.state;
		const dates = filteredTimeseries.map(entry => entry.date);
		const volumes = filteredTimeseries.map(entry => entry.volume);

		return {
			title: {
				text: 'Volume',
				left: 0
			},
			tooltip: {
				trigger: 'axis'
			},
			xAxis: {
				type: 'category',
				data: dates
			},
			yAxis: {
				type: 'value'
			},
			series: [{
				type: 'bar',
				data: volumes,
				itemStyle: { color: '#91cc75' }
			}]
		};
	}

	render() {
		const { selectedInstrument } = this.props;
		const { timeseries, loading, loadedSymbol } = this.state;
		const title = loadedSymbol ? 'Timeseries Data for ' + loadedSymbol
								   : 'Timeseries Data'	;
		return (
			<Card className="card-full-height">
				<CardHeader>
					<Row className="mt-2">
						<Col md={1} className="d-flex justify-content-start">
							<Button	color="primary"
									onClick={this.handleLoadTimeseries}
									disabled={!selectedInstrument || loading}>
								{loading ? 'Loading...' : 'Load Data'}
							</Button>
						</Col>
						<Col md={2}>
							<b>From: </b>
							<DatePicker	selected={this.state.fromDate}
										onChange={this.handleFromDateChange}
										placeholderText="From"
										maxDate={this.state.toDate}/>
						</Col>
						<Col md={2}>
							<b>To: </b>
							<DatePicker	selected={this.state.toDate}
										onChange={this.handleToDateChange}
										placeholderText="To"
										minDate={this.state.fromDate}/>
						</Col>
						<Col  className="d-flex justify-content-start"><h4>{title}</h4></Col>
						<Col>

						</Col>
					</Row>
				</CardHeader>
				<CardBody className="card-body-flexible">
					<SplitPane	split="vertical"
								defaultSize="50%"
								className="custom-split-pane "
								style={{height:"100%"}}
								paneStyle={{display:'flex', flexDirection: 'column'}}>
						<Pane className="Vertical-Pane">
						{timeseries.length > 0 ? (
							<ReactECharts	option={this.getCandlestickChartOptions()}
											style={{ height: '100%', width: '100%' }}/>
						) : (
					  		<div style={{ color: '#666' }}>Chart will display here after loading data.</div>
						)}
				  		</Pane>
				   		<Pane className="Vertical-Pane">				  
							<div className="chart-half">
							{timeseries.length > 0 ? (
								<ReactECharts	option={this.getVolumeChartOptions()}
												style={{ height: '100%', width: '100%' }}/>
						  	) : (
								<div style={{ color: '#666' }}>Chart will display here after loading data.</div>
					  		)}
							</div>
							<div className="chart-half">
								{timeseries.length > 0 ? (
									<ReactECharts	option={this.getRSIChartOptions()}
													style={{ height: '100%', width: '100%' }}/>
							  	) : (
								<div style={{ color: '#666' }}>Chart will display here after loading data.</div>
							  	)}
							</div>
				  		</Pane>
					</SplitPane>
				</CardBody>
			</Card>
		);
	}
}

export default InstrumentGraphContainer;
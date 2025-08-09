import React, { Component } from 'react';
import SplitPane, { Pane } from 'react-split-pane';
import { Button, Card, CardHeader, CardBody, CardFooter, Row, Col } from 'reactstrap';
import '../../App.css';
import InstrumentGraphContainer from './InstrumentGraphContainer.js';
import TickerList from './TickerList.js';
import InstrumentFundamentalsTabContainer from './InstrumentFundamentalsTabContainer.js';
import axios from 'axios';

class InstrumentDataContainer extends Component {
	
	constructor(props) {
		super(props);
		this.handleInstrumentSelect = this.handleInstrumentSelect.bind(this);
		this.handleLoadFundamentals = this.handleLoadFundamentals.bind(this);
	}
	
	componentDidMount() {
		this.setState({selectedInstrument:null});	
	}
	
	handleInstrumentSelect(instrument) {
		console.log("Instrument=" + instrument.symbol+":" + instrument.companyName);
		this.handleLoadFundamentals(instrument);
		this.setState({selectedInstrument:instrument})
	}
	
	handleLoadFundamentals(instrument) {
		const url = `http://localhost:8080/getFundamentalsContainer?symbol=${instrument.symbol}`;
		axios.get(url).then(res => {
			var data = res.data;
			console.log("=====");
		}).catch(error => {
			console.log("Error=" + error);
		});
	}
	
	render() {
		const selectedInstrument = this.state && this.state.selectedInstrument ? this.state.selectedInstrument : "";
		const loading = this.state && this.state.loading ? this.state.loading : false;
		return (
			<SplitPane	split="vertical"
						defaultSize="25%"
						className="custom-split-pane "
						style={{height:"100%"}}
						paneStyle={{display:'flex', flexDirection: 'column'}}>
			 			
				<Pane className="Vertical-Pane">
					<TickerList handleInstrumentSelect={this.handleInstrumentSelect}/>
				</Pane>
				<Pane className="Vertical-Pane">
					<Card className="card-full-height">
						<CardHeader>
							<Row className="mt-2">
								<Col>
									<div style={{ fontWeight: 'bold' }}>
										{selectedInstrument
											? `${selectedInstrument.symbol}: ${selectedInstrument.companyName}`
											: 'Please select an Instrument'}
									</div>
								</Col>
								<Col>
									{ selectedInstrument && 
										<Button
										  color="primary"
										  //onClick={this.handleLoadFundamentals(selectedInstrument)}
										  disabled={!selectedInstrument}
										>
										  {loading ? 'Loading...' : 'Load Fundamentals Data'}
										</Button>
									}
								</Col>
							</Row>
						</CardHeader>
						<SplitPane	split="horizontal"
									defaultSize="65%"
									primary="second">								
							<Pane className="Horizontal-Pane">
								<InstrumentFundamentalsTabContainer selectedInstrument={selectedInstrument}/>	
							</Pane>
							<Pane className="Horizontal-Pane">
								<InstrumentGraphContainer selectedInstrument={selectedInstrument}/>
							</Pane>
						</SplitPane>
					</Card>
				</Pane>
			</SplitPane>		
		);
	}
}
export default InstrumentDataContainer;
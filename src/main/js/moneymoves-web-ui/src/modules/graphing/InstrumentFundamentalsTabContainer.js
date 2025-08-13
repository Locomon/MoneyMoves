import React, { Component } from 'react';
import {
  Nav, NavItem, NavLink,
  TabContent, TabPane,
  Card, CardHeader, CardBody, CardFooter, Row, Col, Button
} from 'reactstrap';
import classnames from 'classnames';
import '../../App.css';
import BalanceContainer from './tabs/BalanceContainer.js';
import EstimatesContainer from './tabs/EstimatesContainer.js'
import FinancialsContainer from './tabs/FinancialsContainer.js'
import ValuationContainer from './tabs/ValuationContainer.js'
import GrowthContainer from './tabs/GrowthContainer.js'
import axios from 'axios';
import * as XLSX from 'xlsx';


class InstrumentFundamentalsTabContainer extends Component {
	constructor(props) {
		super(props);
		this.state = {
			loadedInstrument: null,
			fundamentals: null,
			loading: false,
			activeTab: 'valuation'
		};
		this.handleLoadFundamentals = this.handleLoadFundamentals.bind(this);
	}

	toggleTab = (tab) => {
		if (this.state.activeTab !== tab) {
			this.setState({ activeTab: tab });
		}
	};
	

	handleLoadFundamentals(instrument) {
		const url = `http://localhost:8080/getFundamentalsContainer?symbol=${instrument.symbol}`;
		axios.get(url).then(res => {
			var data = res.data;
			console.log("=====");
  			this.setState({ fundamentals: res.data, loading: false, loadedInstrument: instrument});
  		}).catch(error => {
			console.log("Error=" + error);
		});
	}
	
	exportToExcel = () => {
		
		const { fundamentals, selectedColumns, loadedInstrument } = this.state;
		if (!fundamentals) return;
		const workbook = XLSX.utils.book_new();
		const instrumentValuation = fundamentals.instrumentValuation;
		const valuationRows = 
			Object.entries(instrumentValuation).map(([col, value]) => [col, value ?? '-']);
		const valuationWorksheet = XLSX.utils.json_to_sheet(valuationRows);
		XLSX.utils.book_append_sheet(workbook, valuationWorksheet, 'ValuationSheet');

		const instrumentFinancialsMap = fundamentals.instrumentFinancialsMap;
		const financialsRows = Object.entries(instrumentFinancialsMap).map(
			([key, data]) => {
				const row = {};
				Object.keys(data).forEach((col) => { row[col] = data[col] ?? '-'; });
				return row;
			});
		const financialsWorksheet = XLSX.utils.json_to_sheet(financialsRows);
		XLSX.utils.book_append_sheet(workbook, financialsWorksheet, 'FinancialsSheet');
	
		const instrumentBalanceMap = fundamentals.instrumentBalanceMap;
	 	const balanceRows = Object.entries(instrumentBalanceMap).map(
			([key, data]) => {
				const row = {};
				Object.keys(data).forEach((col) => { row[col] = data[col] ?? '-'; });
				return row;
			});
		const balanceWorksheet = XLSX.utils.json_to_sheet(balanceRows);
		XLSX.utils.book_append_sheet(workbook, balanceWorksheet, 'BalanceSheet');
		
		const instrumentGrowthMap = fundamentals.instrumentGrowthMap;
		const growthRows = Object.entries(instrumentGrowthMap).map(
			([key, data]) => {
				const row = {};
				Object.keys(data).forEach((col) => { row[col] = data[col] ?? '-'; });
				return row;
			});
		const growthWorksheet = XLSX.utils.json_to_sheet(growthRows);
		XLSX.utils.book_append_sheet(workbook, growthWorksheet, 'GrowthSheet');

			
		XLSX.writeFile(workbook, 'Fundamentals_' + loadedInstrument.symbol + '.xlsx');
	};

	render() {
		const { selectedInstrument } = this.props;
		const { activeTab, fundamentals, loading } = this.state;

		return (
			<Card className="card-full-height">
				<CardHeader>
					<Row>
						<Col>
						<Nav tabs>
							<NavItem>
								<NavLink	className={classnames({ active: activeTab === 'valuation' })}
											onClick={() => this.toggleTab('valuation')}>
									Valuation
								</NavLink>
							</NavItem>
							<NavItem>
								<NavLink	className={classnames({ active: activeTab === 'financials' })}
											onClick={() => this.toggleTab('financials')}>
									Financials
								</NavLink>
							</NavItem>
							<NavItem>
								<NavLink	className={classnames({ active: activeTab === 'balance' })}
											onClick={() => this.toggleTab('balance')}>
									Balance Sheet
								</NavLink>
							</NavItem>
							<NavItem>
								<NavLink	className={classnames({ active: activeTab === 'growth' })}
											onClick={() => this.toggleTab('growth')}>
									Growth
								</NavLink>
							</NavItem>
						</Nav>
	
						</Col>

						<Col className="d-flex justify-content-center"><h4>Fundamentals Data </h4></Col>
						<Col className="d-flex justify-content-end">
							{ selectedInstrument && 
								<Button
								  color="primary"
								  onClick={() => this.handleLoadFundamentals(selectedInstrument)}
								  disabled={!selectedInstrument}
								>
								  {loading ? 'Loading...' : 'Load Fundamentals Data'}
								</Button>
							}
							{fundamentals && 
							<button
							  onClick={this.exportToExcel}
							  style={{ marginLeft: '10px' }}
							>
							  📤 Export
							</button>
							}
						</Col>
					</Row>
				</CardHeader>
				<CardBody style={{ margin: 0, padding: 0 }}>

					<TabContent activeTab={activeTab}>
						<TabPane tabId="valuation">
							<ValuationContainer selectedInstrument={selectedInstrument}
												fundamentals={fundamentals}/>
						</TabPane>
						<TabPane tabId="financials">
							<FinancialsContainer selectedInstrument={selectedInstrument}
												 fundamentals={fundamentals}/>
						</TabPane>
						<TabPane tabId="balance">
							<BalanceContainer	selectedInstrument={selectedInstrument}
												fundamentals={fundamentals}/>
						</TabPane>
						<TabPane tabId="growth">
							<GrowthContainer selectedInstrument={selectedInstrument}
											 fundamentals={fundamentals}/>
						</TabPane>

					</TabContent>
				</CardBody>

			</Card>
		);
	}
}

export default InstrumentFundamentalsTabContainer;

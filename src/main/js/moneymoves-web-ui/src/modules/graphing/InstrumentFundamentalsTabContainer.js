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

class InstrumentFundamentalsTabContainer extends Component {
	constructor(props) {
		super(props);
		this.state = {
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
  			this.setState({ fundamentals: res.data, loading: false });
  		}).catch(error => {
			console.log("Error=" + error);
		});
	}


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
						</Col>
					</Row>
				</CardHeader>
				<CardBody>

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

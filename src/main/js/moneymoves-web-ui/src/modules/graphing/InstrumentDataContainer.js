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
		this.state = {
			selectedInstrument: null,
			loading: false
		}
		this.handleInstrumentSelect = this.handleInstrumentSelect.bind(this);
	}
	
	handleInstrumentSelect(instrument) {
		console.log("Instrument=" + instrument.symbol+":" + instrument.companyName);
		this.setState({selectedInstrument:instrument})
	}
	
	render() {
		const { loading, selectedInstrument} = this.state;
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
									<h2 className="d-flex justify-content-center">
										{selectedInstrument
											? `${selectedInstrument.symbol}: ${selectedInstrument.companyName}`
											: 'Please select an Instrument'}
									</h2>

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
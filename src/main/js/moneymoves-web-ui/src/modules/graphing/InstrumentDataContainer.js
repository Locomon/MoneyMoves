import React, { Component } from 'react';
import SplitPane, { Pane } from 'react-split-pane';
import { Card, CardHeader, CardBody, CardFooter, Row, Col } from 'reactstrap';
import '../../App.css';
import InstrumentGraphContainer from './InstrumentGraphContainer.js';
import TickerList from './TickerList.js';
import InstrumentFundamentalsTabContainer from './InstrumentFundamentalsTabContainer.js';

class InstrumentDataContainer extends Component {
	
	constructor(props) {
		super(props);
		this.handleInstrumentSelect = this.handleInstrumentSelect.bind(this);
	}
	
	componentDidMount() {
		this.setState({selectedInstrument:"TEST"});	
	}
	
	handleInstrumentSelect = (instrument) => {
		console.log("Instrument=" + instrument.symbol+":" + instrument.companyName);
		this.setState({selectedInstrument:instrument})
	}
	
	render() {
		const selectedInstrument = this.state && this.state.selectedInstrument ? this.state.selectedInstrument : "";
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
							<Row>
								<div style={{ fontWeight: 'bold' }}>
									{selectedInstrument
										? `${selectedInstrument.symbol}: ${selectedInstrument.companyName}`
										: 'Please select an Instrument'}
								</div>
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
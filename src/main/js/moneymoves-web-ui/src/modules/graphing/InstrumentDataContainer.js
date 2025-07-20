import React, { Component } from 'react';
import SplitPane, { Pane } from 'react-split-pane';
import { Card, CardHeader, CardBody, CardFooter } from 'reactstrap';
import '../../App.css';
import InstrumentGraphContainer from './InstrumentGraphContainer.js';
import TickerList from './TickerList.js';


class InstrumentDataContainer extends Component {
	
	constructor(props) {
		super(props);
		this.handleInstrumentSelect = this.handleInstrumentSelect.bind(this);
		this.setState({selectedInstrument:"TEST"});	
	}
	
	componentDidMount() {
	}
	
	handleInstrumentSelect = (instrument) => {
		console.log("Instrument=" + instrument.symbol+":" + instrument.name);
		this.setState({selectedInstrument:instrument})
	}
	
	render() {
		const selectedInstrument = this.state && this.state.selectedInstrument ? this.state.selectedInstrument : "";
		return (
			<SplitPane	split="vertical"
						defaultSize="25%"
						minSize="10%"
						maxSize="50%"
						className="custom-split-pane "
						style={{height:"100%"}}
						paneStyle={{display:'flex', flexDirection: 'column'}}>
						
			      <Pane style={{ display: 'flex', flexDirection: 'column' }}>
					<TickerList handleInstrumentSelect={this.handleInstrumentSelect}/>
				</Pane>
			      <Pane style={{ display: 'flex', flexDirection: 'column' }}>
					<InstrumentGraphContainer selectedInstrument={selectedInstrument}/>
				</Pane>
			</SplitPane>		
		);
	}
}
export default InstrumentDataContainer;
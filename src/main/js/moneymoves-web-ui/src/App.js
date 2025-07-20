import React, { Component } from 'react';
import SplitPane, { Pane } from 'react-split-pane';
import { Card, CardHeader, CardBody, CardFooter } from 'reactstrap';
import './App.css'; // You can style your app here
import InstrumentDataContainer from './modules/graphing/InstrumentDataContainer.js';
// Main App component
class App extends Component {

	render() {
		return (
			<div className="App">
				<div className="header">
					<b> MoneyMoves</b>
				</div>
				<div className="mainbody">
					<InstrumentDataContainer/>
				</div>
				<div className="footer">
					Test
				</div>
			</div>
		);
	}
}

export default App;

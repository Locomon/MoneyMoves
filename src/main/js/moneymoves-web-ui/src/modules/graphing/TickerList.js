import React, { Component } from 'react';
import SplitPane, { Pane } from 'react-split-pane';
import { Card, CardHeader, CardBody, CardFooter, Row, Button } from 'reactstrap';
import axios from 'axios';
import { FaFolder, FaFolderOpen } from 'react-icons/fa';

import '../../App.css'; 

class TickerList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			iexSymbolMap: new Map(),
			loadingKeys: new Set(),
			searchTerm:''
		};
	}


	handleClick = () => {
		const url = 'http://localhost:8080/getInstrumentTypes';
		axios.get(url).then(res => {
			const instrumentTypes = res.data; // e.g., ["Equities", "Options"]
			const iexSymbolMap = new Map();

			// Pre-fill keys with `undefined` to indicate not-yet-loaded children
			instrumentTypes.forEach(type=> iexSymbolMap.set(type, {"children": new Map(), "isExpanded":false}));

			this.setState({
				iexSymbolMap: iexSymbolMap
			});
		});
	};
	
	handleSearchChange = (e) => {
	  this.setState({ searchTerm: e.target.value.toLowerCase() });
	};


	toggleInstrumentExpand = (instrumentType) => {
		const iexSymbolMap = this.state.iexSymbolMap;
		const instrumentContainer = iexSymbolMap.get(instrumentType);
		instrumentContainer.isExpanded = !instrumentContainer.isExpanded;
		if(instrumentContainer.isExpanded && instrumentContainer.children.size === 0) {
			const url = 'http://localhost:8080/getExchangesForInstrument?instrumentType='+instrumentType;
			axios.get(url).then(res =>  {
				console.log("resData=" + res.data);
				const exchanges = res.data;
				const exchangeSymbolMap = new Map();
				exchanges.forEach(exchange => exchangeSymbolMap.set(exchange, {"children":[], "isExpanded":false}));
				instrumentContainer.children=exchangeSymbolMap;
				this.setState({});
			});
		} else {
			this.setState({});	
		}
	};
	
	toggleExchangeExpand = (instrumentType, exchange) => {
		const iexSymbolMap = this.state.iexSymbolMap;
		const instrumentContainer = iexSymbolMap.get(instrumentType);
		if (!instrumentContainer) { return; }
		const exchangeContainer = instrumentContainer.children.get(exchange);
		exchangeContainer.isExpanded = !exchangeContainer.isExpanded;
		if (exchangeContainer.isExpanded && exchangeContainer.children.length === 0) {
			const url = 'http://localhost:8080/getInstrumentSet?instrumentType='+instrumentType+'&exchange='+exchange;
			axios.get(url).then(res => {
				const symbols = res.data;
				exchangeContainer.children = symbols;
				this.setState({});
			});
		} else {
			this.setState({});	
		}
			
	};
	
	handleInstrumentSelect = (instrument) => {
		this.props.handleInstrumentSelect(instrument);
	}


	render() {
		const iexSymbolMap = this.state.iexSymbolMap;
		
		return(
			<Card className="card-full-height">
				<CardHeader>
					<Row><div>Instrument Master List</div></Row>
					<Row><div>

					<input
					  type="text"
					  placeholder="Search symbols..."
					  value={this.state.searchTerm}
					  onChange={this.handleSearchChange}
					  style={{ width: '100%', marginBottom: '10px', padding: '5px' }}
					/>
					
					</div></Row>
				</CardHeader>
				<CardBody className="card-body-flexible">
					<ul>
						{Array.from(iexSymbolMap.entries()).map(entry => {
							const instrumentType = entry[0];
							const instrumentValue = entry[1];
							const isInstrumentExpanded = instrumentValue.isExpanded;
							const FolderIcon = isInstrumentExpanded ? FaFolderOpen : FaFolder;
							return (
								<li key={instrumentType}>
									<span onClick={() => this.toggleInstrumentExpand(instrumentType)} style={{ cursor: 'pointer' }}>
									  <FolderIcon style={{ marginRight: '8px' }} />
									  {instrumentType}
									</span>
									{isInstrumentExpanded && instrumentValue.children && instrumentValue.children.size > 0 && (
									<ul style={{ paddingLeft: '20px' }}>
										{
										Array.from(instrumentValue.children.entries()).map(([exchangeKey, exchangeValue]) => {
											const isExchangeExpanded = exchangeValue.isExpanded;
											const ExchangeFolderIcon = isExchangeExpanded ? FaFolderOpen : FaFolder;
											return (<li key={instrumentType + "-" + exchangeKey}>
												<span onClick={() => this.toggleExchangeExpand(instrumentType, exchangeKey)} style={{ cursor: 'pointer' }}>
													<ExchangeFolderIcon style={{ marginRight: '8px' }} />
													{exchangeKey}											
												</span>
												{
													isExchangeExpanded && exchangeValue.children && exchangeValue.children.length > 0 && (
														<ul style={{ paddingLeft: '40px' }}>
															{exchangeValue.children
																		  .filter(instrument => instrument.symbol.toLowerCase().startsWith(this.state.searchTerm))
																		  .map(instrument => {
																return (<li key={instrumentType + "-" + exchangeKey + "-" + instrument.symbol}
																			onClick={() => this.handleInstrumentSelect(instrument)}
																			style={{ cursor: 'pointer' }}>
																			{instrument.symbol}
																		</li>);
															})}
														</ul>
													)}
											</li>
										 );
									 	})}
									</ul>
									)}
								</li>
							);
						})}
					</ul>
				</CardBody>
				<CardFooter>
					<Row>
						<Button className="custom-button" onClick={this.handleClick}>Load List</Button>
					</Row>
				</CardFooter>
			</Card>
		);
	}
}
export default TickerList;
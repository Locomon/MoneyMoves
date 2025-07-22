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
			sectorIndustryMap: new Map(),
			loadingKeys: new Set(),
			searchTerm:''
		};
	}


	handleClick = () => {
		const url = 'http://localhost:8080/getSectors';
		axios.get(url).then(res => {
			const sectors = res.data; // 
			const sectorIndustryMap = new Map();

			// Pre-fill keys with `undefined` to indicate not-yet-loaded children
			sectors.forEach(type=> sectorIndustryMap.set(type, {"children": new Map(), "isExpanded":false}));

			this.setState({
				sectorIndustryMap: sectorIndustryMap
			});
		});
	};
	
	handleSearchChange = (e) => {
	  this.setState({ searchTerm: e.target.value.toLowerCase() });
	};

	toggleSectorExpand = (sector) => {
		const sectorIndustryMap = this.state.sectorIndustryMap;
		const sectorContainer = sectorIndustryMap.get(sector);
		sectorContainer .isExpanded = !sectorContainer .isExpanded;
		if(sectorContainer .isExpanded && sectorContainer .children.size === 0) {
			const url = 'http://localhost:8080/getIndustriesForSector?sector='+sector;
			axios.get(url).then(res =>  {
				console.log("resData=" + res.data);
				const industries = res.data;
				const industrySymbolMap = new Map();
				industries.forEach(industry => industrySymbolMap.set(industry, {"children":[], "isExpanded":false}));
				sectorContainer.children=industrySymbolMap;
				this.setState({});
			});
		} else {
			this.setState({});	
		}
	};
	
	toggleIndustryExpand = (sector, industry) => {
		const sectorIndustryMap = this.state.sectorIndustryMap;
		const sectorContainer = sectorIndustryMap.get(sector);
		if (!sectorContainer) { return; }
		const industryContainer = sectorContainer.children.get(industry);
		industryContainer.isExpanded = !industryContainer.isExpanded;
		if (industryContainer.isExpanded && industryContainer.children.length === 0) {
			const url = 'http://localhost:8080/getInstrumentSet?sector='+sector+'&industry='+industry;
			axios.get(url).then(res => {
				const symbols = res.data;
				industryContainer.children = symbols;
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
		const sectorIndustryMap = this.state.sectorIndustryMap;
		
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
						{Array.from(sectorIndustryMap.entries()).map(entry => {
							const sectorType = entry[0];
							const sectorValue = entry[1];
							const isSectorExpanded = sectorValue.isExpanded;
							const FolderIcon = isSectorExpanded ? FaFolderOpen : FaFolder;
							return (
								<li key={sectorType}>
									<span onClick={() => this.toggleSectorExpand(sectorType)} style={{ cursor: 'pointer' }}>
									  <FolderIcon style={{ marginRight: '8px' }} />
									  {sectorType}
									</span>
									{isSectorExpanded && sectorValue.children && sectorValue.children.size > 0 && (
									<ul style={{ paddingLeft: '20px' }}>
										{
										Array.from(sectorValue.children.entries()).map(([industryKey, industryValue]) => {
											const isIndustryExpanded = industryValue.isExpanded;
											const IndustryFolderIcon = isIndustryExpanded ? FaFolderOpen : FaFolder;
											return (<li key={sectorType + "-" + industryKey}>
												<span onClick={() => this.toggleIndustryExpand(sectorType, industryKey)} style={{ cursor: 'pointer' }}>
													<IndustryFolderIcon style={{ marginRight: '8px' }} />
													{industryKey}											
												</span>
												{
													isIndustryExpanded && industryValue.children && industryValue.children.length > 0 && (
														<ul style={{ paddingLeft: '40px' }}>
															{industryValue.children
																		  .filter(instrument => instrument.symbol.toLowerCase().startsWith(this.state.searchTerm))
																		  .map(instrument => {
																return (<li key={sectorType + "-" + industryKey + "-" + instrument.symbol}
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
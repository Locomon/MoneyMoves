import React, { Component } from 'react';
import SplitPane, { Pane } from 'react-split-pane';
import { Card, CardHeader, CardBody, CardFooter, Row, Col, Button } from 'reactstrap';
import axios from 'axios';
import { FaFolder, FaFolderOpen } from 'react-icons/fa';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGear } from '@fortawesome/free-solid-svg-icons';
import '../../App.css'; 

class TickerList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			sectorIndustryMap: new Map(),
			searchTerm:'',
			filterToggled:false,
			priceMin: '', priceMax: '',
			betaMin: '', betaMax:'',
			marketCapMin:'', marketCapMax:'',
			country: '',
			industry: '',
			exchange: ''
		};

		this.handleClick = this.handleClick.bind(this);
		this.toggleSectorExpand = this.toggleSectorExpand.bind(this);
		this.toggleIndustryExpand = this.toggleIndustryExpand.bind(this);
		
		this.toggleFilter = this.toggleFilter.bind(this);
		this.handleInstrumentSelect = this.handleInstrumentSelect.bind(this);
		this.handleSearchChange = this.handleSearchChange.bind(this);
		this.handlePriceMinChange = this.handlePriceMinChange.bind(this);
		this.handlePriceMaxChange = this.handlePriceMaxChange.bind(this);
		this.handleBetaMinChange = this.handleBetaMinChange.bind(this);
		this.handleBetaMaxChange = this.handleBetaMaxChange.bind(this);
		this.handleMarketCapMinChange = this.handleMarketCapMinChange.bind(this);
		this.handleMarketCapMaxChange = this.handleMarketCapMaxChange.bind(this);
		this.handleCountryChange = this.handleCountryChange.bind(this);
		this.handleIndustryChange = this.handleIndustryChange.bind(this);
		this.handleExchangeChange = this.handleExchangeChange.bind(this);
	}

	handleSearchChange(e) { this.setState({ searchTerm: e.target.value.toLowerCase() }); };
	handlePriceMinChange(e) { this.setState({ priceMin: e.target.value.toLowerCase() });};
	handlePriceMaxChange(e) { this.setState({ priceMax: e.target.value.toLowerCase() });};
	handleBetaMinChange(e) { this.setState({ betaMin: e.target.value.toLowerCase() });};
	handleBetaMaxChange(e) { this.setState({ betaMax: e.target.value.toLowerCase() });};
	handleMarketCapMinChange(e) { this.setState({ marketCapMin: e.target.value.toLowerCase() }); };
	handleMarketCapMaxChange(e) { this.setState({ marketCapMax: e.target.value.toLowerCase() });}
	handleCountryChange(e) { this.setState({ country: e.target.value.toLowerCase() });}
	handleIndustryChange(e) { this.setState({ industry: e.target.value.toLowerCase() });}
	handleExchangeChange(e) { this.setState({ exchange: e.target.value.toLowerCase() });}

		
	toggleFilter() {
		this.setState({filterToggled: !this.state.filterToggled});
	}
	
	handleClick() {
		const url = 'http://localhost:8080/getSectorIndustrySymbolMap';
		axios.get(url).then(res => {
			const data = res.data;
			const sectorIndustryMap = new Map();

			Object.entries(data).forEach(([sector, industries]) => {
				const industryMap = new Map();

				Object.entries(industries).forEach(([industry, instrumentMap]) => {
					// Convert instrument map to array of instrument objects
					const instruments = Array.isArray(instrumentMap)
						? instrumentMap // In case it's already an array
						: Object.values(instrumentMap); // Convert Map-like object to array

					industryMap.set(industry, {
						children: instruments,
						isExpanded: false
					});
				});

				sectorIndustryMap.set(sector, {
					children: industryMap,
					isExpanded: false
				});
			});

			this.setState({ sectorIndustryMap });
		});
	};

	toggleSectorExpand (sector) {
		const { sectorIndustryMap } = this.state;
		const sectorData = sectorIndustryMap.get(sector);
		sectorData.isExpanded = !sectorData.isExpanded;
		this.setState({ sectorIndustryMap });
	};

	toggleIndustryExpand (sector, industry) {
		const { sectorIndustryMap } = this.state;
		const sectorData = sectorIndustryMap.get(sector);
		if (!sectorData) return;

		const industryData = sectorData.children.get(industry);
		industryData.isExpanded = !industryData.isExpanded;
		this.setState({ sectorIndustryMap });
	};
	
	handleInstrumentSelect (instrument) {
		this.props.handleInstrumentSelect(instrument);
	}
	
	generateInstrumentPredicate() {
		const { sectorIndustryMap, searchTerm, priceMin, priceMax, betaMin, betaMax
										 , marketCapMin, marketCapMax, country, industry, exchange, isEtf, isFund} = this.state;
		return (instrument) =>
			instrument.symbol.toLowerCase().startsWith(searchTerm) &&
			((priceMin == "" || priceMax=="") || (Number(priceMin) <= instrument.price && instrument.price <= Number(priceMax))) &&
			((betaMin == "" || betaMax=="") || (Number(betaMin) <= instrument.beta && instrument.beta <= Number(betaMax)) ) &&
			((marketCapMin == "" || marketCapMax=="") || (Number(marketCapMin) <= instrument.marketCap && instrument.marketCap <= Number(marketCapMax)) ) &&
			(country == "" || (instrument.country != null && country === (instrument.country.toLowerCase()))) &&
			(industry == "" || (instrument.industry != null && industry === (instrument.industry.toLowerCase()))) &&
			(exchange == "" || (instrument.exchange != null && exchange === (instrument.exchangeShortName.toLowerCase())));
	}


	render() {
		const { sectorIndustryMap, searchTerm, priceMin, priceMax, betaMin, betaMax
								 , marketCapMin, marketCapMax, country, industry, exchange, isEtf, isFund} = this.state;
		const instrumentPredicate = this.generateInstrumentPredicate();
		return (
			<Card className="card-full-height">
				<CardHeader>
					<Row><div>Instrument Master List</div></Row>
					<Row>
							<input
								type="text"
								placeholder="Search symbols..."
								value={searchTerm}
								onChange={this.handleSearchChange}
								style={{ width: '80%', marginBottom: '10px', padding: '5px' }}
							/>
							<Button onClick={this.toggleFilter} style={{ marginLeft: '10px', marginBottom: '10px', width: '15%'}}>
								<FontAwesomeIcon icon={faGear} />
							</Button>
					</Row>
					<Row>
						<Button className="custom-button" onClick={this.handleClick}>Load List</Button>
					</Row>
				</CardHeader>
				{this.state.filterToggled ?
				<CardBody className="card-body-flexible">
					<Row><Col>Price</Col><Col>
						<input	type="text"
								placeholder="Min"
								value={priceMin}
								onChange={this.handlePriceMinChange}
								style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
						</Col><Col>
						<input	type="text"
								placeholder="Max"
								value={priceMax}
								onChange={this.handlePriceMaxChange}
								style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
						</Col></Row>
					<Row><Col>Beta</Col><Col>
							<input	type="text"
									placeholder="Min"
									value={betaMin}
									onChange={this.handleBetaMinChange}
									style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
							</Col><Col>
							<input	type="text"
									placeholder="Max"
									value={betaMax}
									onChange={this.handleBetaMaxChange}
									style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
						</Col></Row>
					<Row><Col>Market Cap</Col><Col>
						<input	type="text"
								placeholder="Min"
								value={marketCapMin}
								onChange={this.handleMarketCapMinChange}
								style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
						</Col><Col>
						<input	type="text"
								placeholder="Max"
								value={marketCapMax}
								onChange={this.handleMarketCapMaxChange}
								style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
					</Col></Row>
					<Row><Col>Country</Col><Col>
						<input type="text"
							placeholder="Country"
							value={country}
							onChange={this.handleCountryChange}
							style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
					</Col></Row>
					<Row><Col>Industry</Col><Col>
						<input type="text"
							placeholder="Industry"
							value={industry}
							onChange={this.handleIndustryChange}
							style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
					</Col></Row>
					<Row><Col>Exchange</Col><Col>
						<input type="text"
							placeholder="Exchange"
							value={exchange}
							onChange={this.handleExchangeChange}
							style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
					</Col></Row>
					<Row><Col>Is ETF</Col><Col>
						<input type="checkbox"
							value={isEtf}
							onChange={this.handleIsETFChange}
							style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
					</Col></Row>
					<Row><Col>Is Fund</Col><Col>
						<input type="checkbox"
							value={isFund}
							onChange={this.handleIsFundChange}
							style={{ width: '100%', marginBottom: '10px', padding: '5px' }}/>
					</Col></Row>
				</CardBody>: 
				<CardBody className="card-body-flexible">
					<ul style={{ listStyleType: 'none', paddingLeft: '0' }}>
						{Array.from(sectorIndustryMap.entries()).map(([sectorType, sectorValue]) => {
							// Filter industries that contain at least one matching instrument
							const filteredIndustries = Array.from(sectorValue.children.entries()).filter(([_, industryValue]) =>
								industryValue.children.some(instrumentPredicate)
							);

							// Skip this sector if no industries match
							if (filteredIndustries.length === 0) return null;

							const isSectorExpanded = sectorValue.isExpanded;
							const FolderIcon = isSectorExpanded ? FaFolderOpen : FaFolder;

							return (
								<li key={sectorType}>
									<span
										onClick={() => this.toggleSectorExpand(sectorType)}
										style={{ cursor: 'pointer' }}
									>
										<FolderIcon style={{ marginRight: '8px' }} />
										{sectorType}
									</span>
									{isSectorExpanded && (
										<ul style={{ listStyleType: 'none', paddingLeft: '20px' }}>
											{filteredIndustries.map(([industryKey, industryValue]) => {
												const filteredInstruments = industryValue.children.filter(instrumentPredicate);

												// Skip industry if no instruments match (shouldn’t happen now, but safe)
												if (filteredInstruments.length === 0) return null;

												const isIndustryExpanded = industryValue.isExpanded;
												const IndustryFolderIcon = isIndustryExpanded ? FaFolderOpen : FaFolder;

												return (
													<li key={`${sectorType}-${industryKey}`}>
														<span
															onClick={() => this.toggleIndustryExpand(sectorType, industryKey)}
															style={{ cursor: 'pointer' }}
														>
															<IndustryFolderIcon style={{ marginRight: '8px' }} />
															{industryKey}
														</span>
														{isIndustryExpanded && (
															<ul style={{ listStyleType: 'none', paddingLeft: '40px' }}>
																{filteredInstruments.map(instrument => (
																	<li
																		key={`${sectorType}-${industryKey}-${instrument.symbol}`}
																		onClick={() => this.handleInstrumentSelect(instrument)}
																		style={{ cursor: 'pointer' }}
																	>
																		{instrument.symbol}
																	</li>
																))}
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
				</CardBody>}
			</Card>
		);
	}

}
export default TickerList;
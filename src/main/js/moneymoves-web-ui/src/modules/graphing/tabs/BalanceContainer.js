import React, { Component } from 'react';
import { Card, CardHeader, CardBody, CardFooter, Row, Col, Modal, ModalHeader, ModalBody } from 'reactstrap';
import * as XLSX from 'xlsx';
import '../../../App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

class BalanceContainer extends Component {
	constructor(props) {
		super(props);
		this.state = {
			symbol: null,
			instrumentBalanceMap: null,
			availableColumns: [],
			columnFlags: [],
			showSettings: false
		};
	}

	componentDidUpdate(prevProps) {
		if (this.props.fundamentals !== prevProps.fundamentals) {
			if (
				this.props.fundamentals != null &&
				this.props.fundamentals.instrumentBalanceMap != null
			) {
				const instrumentBalanceMap = this.props.fundamentals.instrumentBalanceMap;
				const firstKey = Object.keys(instrumentBalanceMap)[0];
				if (firstKey != null) {
					const availableColumns = Object.keys(instrumentBalanceMap[firstKey]);
					const columnFlags = [];
					availableColumns.forEach(column => columnFlags.push(true));
					this.setState({
						symbol: this.props.selectedInstrument.symbol,
						instrumentBalanceMap: instrumentBalanceMap,
						availableColumns: availableColumns,
						columnFlags: columnFlags
					});
				}
			}
		}
	}

	toggleColumn = (index) => {
		const { columnFlags } = this.state;
		columnFlags[index] = !columnFlags[index];
		this.setState({});
	};


	render() {
		const { symbol, instrumentBalanceMap, availableColumns, columnFlags, showSettings } = this.state;
		const tableStyle = { borderCollapse: 'collapse', width: '100%' };
		const thStyle = { border: '1px solid #ccc', backgroundColor: '#f2f2f2', padding: '8px', textAlign: 'left' };
		const tdStyle = { border: '1px solid #ccc', padding: '8px' };
		if (!instrumentBalanceMap) {
			return (
				<Card className="card-full-height">
					<CardHeader className="d-flex justify-content-center">
						<h5>The table will display here after loading data.</h5>
					</CardHeader>
				</Card>
			);
		}
		
		return (
			<div>
				<Modal	isOpen={showSettings}
						toggle={() => this.setState({ showSettings: !this.state.showSettings })}
						className="modal-right">					
					<ModalHeader toggle={() => this.setState({ showSettings: false })}>
						Column Settings
					</ModalHeader>
					<ModalBody style = {{maxHeight: 'calc(100vh - 200px)', // adjust 200px for header/footer height
										 overflowY: 'auto'}}>
						{availableColumns.map((col, index) => (
							<label key={col} style={{ display: 'block' }}>
								<input	type="checkbox"
										checked={columnFlags[index] == true}
										onChange={() => this.toggleColumn(index)}/>
								{' '}
								{col}
							</label>
						))}
					</ModalBody>
				</Modal>
				<Card className="card-full-height">
					<CardHeader>
						<Row>
							<Col className="d-flex justify-content-end">
								<h5 style={{ margin: 0 }}>Balance Sheet for: {symbol}</h5>
							</Col>
							<Col className="d-flex justify-content-end">
							{/* Settings Button */}
								<button onClick={() => this.setState({ showSettings: true }) }>
									⚙ Settings
								</button>
							</Col>
						{/* Settings Popup */}
						
						</Row>
					</CardHeader>
					{/* Scrollable Table */}
					<CardBody style={{ margin: 0, padding: 0, height: '100%', overflowY: 'auto' }}>
						<table style={tableStyle}>
							<thead>
								<tr>
									{availableColumns.filter((col, index) => columnFlags[index] == true)
													 .map((col) => (
										<th key={col} style={thStyle}>{col}</th>
									))}
								</tr>
							</thead>
							<tbody>
								{Object.entries(instrumentBalanceMap).map(([instrumentKey, instrumentData]) => (
									<tr key={instrumentKey}>
										{availableColumns.filter((col, index) => columnFlags[index] == true)
														 .map((col) => (
											<td key={col} style={tdStyle}>
												{instrumentData[col] !== undefined ? instrumentData[col]: '-'}
											</td>
										))}
									</tr>
								))}
							</tbody>
						</table>
					</CardBody>
				</Card>
			</div>
		);
	}
}

export default React.memo(BalanceContainer);

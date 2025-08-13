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
			selectedColumns: [],
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
					const columns = Object.keys(instrumentBalanceMap[firstKey]);
					this.setState({
						symbol: this.props.selectedInstrument.symbol,
						instrumentBalanceMap: instrumentBalanceMap,
						availableColumns: columns,
						selectedColumns: columns
					});
				}
			}
		}
	}

	toggleColumn = (col) => {
		this.setState((prev) => {
			const isSelected = prev.selectedColumns.includes(col);
			return {
				selectedColumns: isSelected ? prev.selectedColumns.filter((c) => c !== col)
											: [...prev.selectedColumns, col]
			};
		});
	};


	render() {
		const { symbol, instrumentBalanceMap, selectedColumns, availableColumns, showSettings } = this.state;
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
			<Modal
								   isOpen={showSettings}
								   toggle={() => this.setState({ showSettings: !this.state.showSettings })}
								 >
								   <ModalHeader toggle={() => this.setState({ showSettings: false })}>
								     Column Settings
								   </ModalHeader>
								   <ModalBody>
								     {availableColumns.map((col) => (
								       <label key={col} style={{ display: 'block' }}>
								         <input
								           type="checkbox"
								           checked={selectedColumns.includes(col)}
								           onChange={() => this.toggleColumn(col)}
								         />{' '}
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
				<CardBody style={{ margin: 0, padding: 0, height:'100%' }}>
					<table style={tableStyle}>
						<thead>
							<tr>
								{selectedColumns.map((col) => (
									<th key={col} style={thStyle}>{col}</th>
								))}
							</tr>
						</thead>
						<tbody>
							{Object.entries(instrumentBalanceMap).map(([instrumentKey, instrumentData]) => (
								<tr key={instrumentKey}>
									{selectedColumns.map((col) => (
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

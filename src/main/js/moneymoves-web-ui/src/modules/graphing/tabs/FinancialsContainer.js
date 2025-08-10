import React, { Component } from 'react';

class FinancialsContainer extends Component {
	
	constructor(props) {
		super(props);
		this.state = {
			symbol: null,
			instrumentFinancialsMap: null,
			availableColumns: [],
			selectedColumns: []
		};
	}
	
	componentDidUpdate(prevProps) {
		if (this.props.fundamentals !== prevProps.fundamentals) {
			if (this.props.fundamentals != null && this.props.fundamentals.instrumentFinancialsMap != null) {
				const instrumentFinancialsMap = this.props.fundamentals.instrumentFinancialsMap;
				const firstKey = (Object.keys(instrumentFinancialsMap))[0];
				if (firstKey != null) {
					const columns = Object.keys(instrumentFinancialsMap[firstKey]);
					this.setState({
						symbol: this.props.selectedInstrument.symbol,
						instrumentFinancialsMap: instrumentFinancialsMap,
						availableColumns: columns,
						selectedColumns: columns
					});
				}
			}
		}
	}
	
	
	render() {
		const {selectedInstrument} = this.props;
		const {instrumentFinancialsMap, selectedColumns, availableColumns, symbol} = this.state;
		if (instrumentFinancialsMap != null) {
			const tableStyle = {
				borderCollapse: 'collapse',
				width: '100%',
			};

			const thStyle = {
				border: '1px solid #ccc',
				backgroundColor: '#f2f2f2',
				padding: '8px',
				textAlign: 'left',
			};

			const tdStyle = {
				border: '1px solid #ccc',
				padding: '8px',
			};

			return (
				<div>
				<h5>Financials Sheet for: {symbol}</h5>
				<table style={tableStyle}>
					<thead>
						<tr>
							{selectedColumns.map((col) => (
								<th key={col} style={thStyle}>{col}</th>
							))}
						</tr>
					</thead>
					<tbody>
						{Object.entries(instrumentFinancialsMap).map(([instrumentKey, instrumentData]) => (
							<tr key={instrumentKey}>
								{selectedColumns.map((col) => (
									<td key={col} style={tdStyle}>
										{/* Render data if exists, else fallback */}
										{instrumentData[col] !== undefined ? instrumentData[col] : '-'}
									</td>
								))}
							</tr>
						))}
					</tbody>
				</table>
				</div>
			);		
		} else {
			return (
				<div>
					<h5>The table will display here after loading data.</h5>
				</div>
			);		
		}
	}
}

// Wrap with React.memo for shallow props comparison to avoid re-render on unrelated changes
export default React.memo(FinancialsContainer);
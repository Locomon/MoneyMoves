import React, { Component } from 'react';

class GrowthContainer extends Component {
	
	constructor(props) {
		super(props);
		this.state = {
			symbol: null,
			instrumentGrowthMap: null,
			availableColumns: [],
			selectedColumns: []
		};
	}
	
	componentDidUpdate(prevProps) {
		if (this.props.fundamentals !== prevProps.fundamentals) {
			if (this.props.fundamentals != null && this.props.fundamentals.instrumentGrowthMap != null) {
				const instrumentGrowthMap = this.props.fundamentals.instrumentGrowthMap;
				const firstKey = (Object.keys(instrumentGrowthMap))[0];
				if (firstKey != null) {
					const columns = Object.keys(instrumentGrowthMap[firstKey]);
					this.setState({
						symbol: this.props.selectedInstrument.symbol,
						instrumentGrowthMap: instrumentGrowthMap,
						availableColumns: columns,
						selectedColumns: columns
					});
				}
			}
		}
	}
	
	
	render() {
		const {selectedInstrument} = this.props;
		const {instrumentGrowthMap, selectedColumns, availableColumns, symbol} = this.state;
		if (instrumentGrowthMap != null) {
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
				<h5>Growth Sheet for: {symbol}</h5>
				<table style={tableStyle}>
					<thead>
						<tr>
							{selectedColumns.map((col) => (
								<th key={col} style={thStyle}>{col}</th>
							))}
						</tr>
					</thead>
					<tbody>
						{Object.entries(instrumentGrowthMap).map(([instrumentKey, instrumentData]) => (
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
export default React.memo(GrowthContainer);
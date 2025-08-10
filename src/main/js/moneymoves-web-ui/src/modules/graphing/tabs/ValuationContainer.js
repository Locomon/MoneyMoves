import React, { Component } from 'react';

class ValuationContainer extends Component {
	
	constructor(props) {
		super(props);
		this.state = {
			symbol: null,
			instrumentValuation: null,
		};
	}
	
	componentDidUpdate(prevProps) {
		if (this.props.fundamentals !== prevProps.fundamentals) {
			if (this.props.fundamentals != null && this.props.fundamentals.instrumentValuation != null) {
				const instrumentValuation = this.props.fundamentals.instrumentValuation;
				const firstKey = (Object.keys(instrumentValuation))[0];
				if (firstKey != null) {
					this.setState({
						symbol: this.props.selectedInstrument.symbol,
						instrumentValuation: instrumentValuation
					});
				}
			}
		}
	}
	
	
  render() {
	const {selectedInstrument} = this.props;
	const {instrumentValuation, symbol} = this.state;
	if (instrumentValuation != null) {
		const tableStyle = {
		  borderCollapse: 'collapse',
		  width: '100%',
		};

		const thStyle = {
		  border: '1px solid #ccc',
		  backgroundColor: '#f2f2f2',
		  padding: '8px',
		  textAlign: 'right',
		  width: '20%',  // smaller width for key column
		};

		const tdStyle = {
		  border: '1px solid #ccc',
		  padding: '8px',
		};
		

		return (
		  <div>
		    <h5>Valuation Metrics for: {symbol}</h5>
			<table style={tableStyle}>
			  <tbody>
			    {Object.entries(instrumentValuation).map(([valuationKey, valuationData]) => (
			      <tr key={valuationKey}>
					<td key={valuationKey + "-0"} style={thStyle}><b>{valuationKey}</b></td>
					<td key={valuationKey + "-1"} style={tdStyle}>{valuationData}</td>
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
export default React.memo(ValuationContainer);
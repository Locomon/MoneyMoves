import React, { Component } from 'react';

class ValuationContainer extends Component {
  render() {
    const { selectedInstrument } = this.props;
    return (
      <div>
        <h5>Valuation Fundamentals</h5>
        <p>Displaying valuation data for: {selectedInstrument?.symbol}</p>
      </div>
    );
  }
}

export default ValuationContainer;

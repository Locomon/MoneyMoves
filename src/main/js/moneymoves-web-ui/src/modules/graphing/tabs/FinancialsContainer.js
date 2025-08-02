import React, { Component } from 'react';

class FinancialsContainer extends Component {
  render() {
    const { selectedInstrument } = this.props;
    return (
      <div>
        <h5>Financials</h5>
        <p>Displaying income statement data for: {selectedInstrument?.symbol}</p>
      </div>
    );
  }
}

export default FinancialsContainer;

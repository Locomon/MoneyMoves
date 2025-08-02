import React, { Component } from 'react';

class BalanceContainer extends Component {
  render() {
    const { selectedInstrument } = this.props;
    return (
      <div>
        <h5>Balance Sheet</h5>
        <p>Displaying balance sheet data for: {selectedInstrument?.symbol}</p>
      </div>
    );
  }
}

export default BalanceContainer;

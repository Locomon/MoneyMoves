import React, { Component } from 'react';

class EstimatesContainer extends Component {
  render() {
    const { selectedInstrument } = this.props;
    return (
      <div>
        <h5>Estimates</h5>
        <p>Displaying earnings/revenue estimates for: {selectedInstrument?.symbol}</p>
      </div>
    );
  }
}

export default EstimatesContainer;

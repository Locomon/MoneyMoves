import React, { Component } from 'react';

class GrowthContainer extends Component {
  render() {
    const { selectedInstrument } = this.props;
    return (
      <div>
        <h5>Growth Metrics</h5>
        <p>Displaying growth data for: {selectedInstrument?.symbol}</p>
      </div>
    );
  }
}

export default GrowthContainer;

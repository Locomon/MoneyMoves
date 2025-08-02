import React, { Component } from 'react';
import {
  Nav, NavItem, NavLink,
  TabContent, TabPane,
  Card, CardHeader, CardBody, Row
} from 'reactstrap';
import classnames from 'classnames';
import '../../App.css';
import BalanceContainer from './tabs/BalanceContainer.js';
import EstimatesContainer from './tabs/EstimatesContainer.js'
import FinancialsContainer from './tabs/FinancialsContainer.js'
import ValuationContainer from './tabs/ValuationContainer.js'
import GrowthContainer from './tabs/GrowthContainer.js'


class InstrumentFundamentalsTabContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      activeTab: 'valuation'
    };
  }

  toggleTab = (tab) => {
    if (this.state.activeTab !== tab) {
      this.setState({ activeTab: tab });
    }
  };

  render() {
    const { selectedInstrument } = this.props;
    const { activeTab } = this.state;

    return (
      <Card className="card-full-height">
        <CardHeader>
		<Row>
          <Nav tabs>
            <NavItem>
              <NavLink
                className={classnames({ active: activeTab === 'valuation' })}
                onClick={() => this.toggleTab('valuation')}
              >
                Valuation
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({ active: activeTab === 'financials' })}
                onClick={() => this.toggleTab('financials')}
              >
                Financials
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({ active: activeTab === 'balance' })}
                onClick={() => this.toggleTab('balance')}
              >
                Balance Sheet
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({ active: activeTab === 'growth' })}
                onClick={() => this.toggleTab('growth')}
              >
                Growth
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({ active: activeTab === 'estimates' })}
                onClick={() => this.toggleTab('estimates')}
              >
                Estimates
              </NavLink>
            </NavItem>
          </Nav>
		  </Row>
        </CardHeader>

        <CardBody>
          <TabContent activeTab={activeTab}>
            <TabPane tabId="valuation">
              <ValuationContainer selectedInstrument={selectedInstrument}/>
            </TabPane>
            <TabPane tabId="financials">
              <FinancialsContainer selectedInstrument={selectedInstrument}/>
            </TabPane>
            <TabPane tabId="balance">
              <BalanceContainer selectedInstrument={selectedInstrument}/>
            </TabPane>
            <TabPane tabId="growth">
              <GrowthContainer selectedInstrument={selectedInstrument}/>
            </TabPane>
            <TabPane tabId="estimates">
              <EstimatesContainer selectedInstrument={selectedInstrument}/>
            </TabPane>
          </TabContent>
        </CardBody>
      </Card>
    );
  }
}

export default InstrumentFundamentalsTabContainer;

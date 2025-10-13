import React, { Component } from 'react';
import {
  Navbar,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  NavbarToggler,
  Collapse
} from 'reactstrap';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import InstrumentDataContainer from './modules/graphing/InstrumentDataContainer.js';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { isOpen: false };
  }

  toggle = () => this.setState({ isOpen: !this.state.isOpen });

  render() {
    return (
      <Router>
        <div className="App">
          {/* ✅ Semantic header */}
          <header>
            <Navbar color="dark" dark expand="md" className="shadow-sm">
              <NavbarBrand tag={Link} to="/">
                MoneyMoves
              </NavbarBrand>
              <NavbarToggler onClick={this.toggle} />
              <Collapse isOpen={this.state.isOpen} navbar>
                <Nav className="me-auto" navbar>
                  <NavItem>
                    <NavLink tag={Link} to="/data">
                      Data
                    </NavLink>
                  </NavItem>
                </Nav>
              </Collapse>
            </Navbar>
          </header>

          {/* ✅ Main app content */}
          <main className="flex-grow-1">
            <Routes>
              <Route path="/data" element={<InstrumentDataContainer />} />
              <Route
                path="*"
                element={<div className="full-height">Welcome to MoneyMoves!</div>}
              />
            </Routes>
          </main>

          {/* ✅ Footer */}
          <footer className="footer bg-light text-center py-2">
            Test
          </footer>
        </div>
      </Router>
    );
  }
}

export default App;

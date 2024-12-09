// src/components/Navbar.js
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './app.css';

const Navbar = () => {
  // State for login status and role
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('isLoggedIn') === 'true');
  const [role, setRole] = useState(localStorage.getItem('role'));
console.log(localStorage)
  // Sync state with localStorage changes
  useEffect(() => {
    const updateAuthState = () => {
      setIsLoggedIn(localStorage.getItem('isLoggedIn') === 'true');
      setRole(localStorage.getItem('role'));
      console.log(localStorage)
    };

    window.addEventListener('storage', updateAuthState);

    return () => {
      window.removeEventListener('storage', updateAuthState);
    };
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('role');
    setIsLoggedIn(false);
    setRole(null);
    window.location.href = '/login'; // Redirect to login
  };

  return (
   
    <div className="navbar" style={{marginBottom:"2px"}}>
      <div className="logo-container">
        <img
          src="https://freedesignfile.com/upload/2021/09/Service-logo-design-template-vector.jpg"
          alt="logo"
          className="logo"
        />
        <span className="app-name">MotoMate</span>
      </div>
      <ul className="nav-links">
        {/* Navbar for unauthenticated users */}
        {!isLoggedIn ? (
          <>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/about">About</Link></li>
            <li><Link to="/care">Maintenance Guide</Link></li>
            <li><Link to="/register">Register</Link></li>
            <li><Link to="/login">Login</Link></li>
          </>
        ) : (
          <>
            {/* Navbar for authenticated users */}
            {role === 'USER' && (
              <>
                <li><Link to="/home">Home</Link></li>
                <li><Link to="/profile">My Profile</Link></li>
                <li><Link to="/addBike">Add Bike</Link></li>
                <li><Link to="/bikes">View Bikes</Link></li>
                <li><Link to="/bookService">Book Service</Link></li>
                <li><Link to="/wallet">My Wallet</Link></li>
                <li><Link to="/myservices">My Booking</Link></li>
              </>
            )}
            {role === 'ADMIN' && (
              <>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/users">All Users</Link></li>
                <li><Link to="/bookings">All Bookings</Link></li>
              </>
            )}
            <li>
              <button className="bt" onClick={handleLogout}>
                Logout
              </button>
            </li>
          </>
        )}
      </ul>
    </div>
    
  );
};

export default Navbar;

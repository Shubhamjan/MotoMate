// src/components/Footer.js
import React from 'react';
import './footer.css';
import { FaFacebook, FaTwitter, FaInstagram } from 'react-icons/fa'; // Import social media icons

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-column">
        <p className="footer-heading">Follow Us</p>
        <ul className="social-links">
          <li>
            <a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer">
              <FaFacebook size={30} color="#ecf0f1" /> {/* Facebook icon */}
            </a>
          </li>
          <li>
            <a href="https://twitter.com" target="_blank" rel="noopener noreferrer">
              <FaTwitter size={30} color="#ecf0f1" /> {/* Twitter icon */}
            </a>
          </li>
          <li>
            <a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer">
              <FaInstagram size={30} color="#ecf0f1" /> {/* Instagram icon */}
            </a>
          </li>
        </ul>
      </div>
      <div className="footer-bottom">
        <p>&copy; 2024 MOTOMATE. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;

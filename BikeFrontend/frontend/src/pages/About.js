import React from 'react';
import './About.css'; // Import the CSS file for styling

const About = () => {
  return (
    <div className="about-section-container">
      <div className="about-section-content">
        <h1 className="about-section-title">About Us</h1>
        <p>
          Welcome to <strong>Online Bike Service</strong>, where convenience meets quality. We are proud to be one of the leading platforms for bike maintenance and repair services. With years of experience in the automotive industry, our team is committed to ensuring your two-wheeler runs smoothly and efficiently.
        </p>
        <p>
          Our platform bridges the gap between professional bike mechanics and busy bike owners like you. Whether it's a routine servicing or an urgent repair, we have got you covered. With our user-friendly online booking system, expert team, and affordable pricing, we take the stress out of bike maintenance.
        </p>
        <h2 className="about-section-why-choose-title">Our Values</h2>
        <p>
          At <strong>Online Bike Service</strong>, we believe in delivering excellence through:
        </p>
        <ul className="about-section-list">
          <li>Uncompromised quality in services</li>
          <li>Transparent pricing with no hidden charges</li>
          <li>Commitment to timely delivery</li>
          <li>Fostering trust and reliability with our customers</li>
        </ul>
        <h2 className="about-section-why-choose-title">Our Services</h2>
        <ul className="about-section-list">
          <li>General bike maintenance and tune-ups</li>
          <li>Engine oil replacement and lubrication</li>
          <li>Brake and clutch repairs</li>
          <li>Wheel alignment and tire replacement</li>
          <li>Battery check and replacement</li>
        </ul>
        <p>
          Whether you're a daily commuter, a weekend rider, or an adventure enthusiast, we are here to ensure your bike is always road-ready!
        </p>
      </div>
      <div className="about-section-image">
        <img
          src="https://i.pinimg.com/564x/b1/db/cc/b1dbccf5b65df457310bbdf8f6ab3fa8.jpg" // Replace with your actual image URL
          alt="Bike Service"
          className="about-section-img"
        />
      </div>
    </div>
  );
};

export default About;

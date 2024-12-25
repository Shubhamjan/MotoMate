import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../../pages/Navbar'; // Use the user-specific Navbar
import './userHome.css';
import { Carousel } from 'react-bootstrap';
import CarouselComponent from '../../pages/CarouselComponent';

const UserHome = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    const role = localStorage.getItem('role');

    if (!isLoggedIn || role !== 'USER') {
      // Redirect to login if not authenticated or not a USER
      navigate('/login');
    }
  }, [navigate]);

  return (
    <div>
      <CarouselComponent/>
    </div>
  );
};

export default UserHome;

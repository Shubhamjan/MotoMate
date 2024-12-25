// src/components/CarouselComponent.js
import React from 'react';
import { Carousel } from 'react-bootstrap';
import './carousel.css'; // Optional for custom styling
import ProductCards from '../components/Products/ProductCards';

const CarouselComponent = () => {
  return (
    <>
    <Carousel>
      <Carousel.Item>
        <img
          className="d-block wi"
          src="https://png.pngtree.com/thumb_back/fh260/background/20240527/pngtree-automobile-mechanic-repairing-motorcycle-in-bike-repair-shop-image_15732728.jpg"
          alt="First slide"
        />
        <Carousel.Caption>
          <h1 style={{fontSize:"50px",fontWeight:"bolder"}}>Expert Bike Service</h1>
          <p style={{color:"white",fontSize:"30px"}}>Quality maintenance for your bike.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block wi"
          src="https://purposebuiltmoto.com/cdn/shop/files/purpose-built-moto-motorcycle-workshop08.jpg?v=1691637361&width=3000"
          alt="Second slide"
        />
        <Carousel.Caption>
          <h1 style={{fontSize:"50px",fontWeight:"bolder"}}>Reliable and Affordable</h1>
          <p style={{color:"white",fontSize:"30px"}}>Keeping your ride smooth and safe.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block wi"
          src="https://purposebuiltmoto.com/cdn/shop/files/purpose-built-moto-motorcycle-workshop01.jpg?v=1691637362&width=3000"
          alt="Third slide"
        />
        <Carousel.Caption>
          <h1 style={{fontSize:"50px",fontWeight:"bolder"}}>Convenient Booking</h1>
          <p style={{color:"white",fontSize:"30px"}}>Book your service online easily.</p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
    <hr/>
    <ProductCards/>
    </>
  );
};

export default CarouselComponent;

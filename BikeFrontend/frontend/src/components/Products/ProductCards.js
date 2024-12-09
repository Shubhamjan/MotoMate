import React from 'react';
import { Card, CardBody, CardImg, CardTitle, CardText, Button } from 'reactstrap';
import './ProductCards.css'; // Optional: Add a CSS file for additional styles.

const ProductCards = () => {
  const products = [
    {
      id: 1,
      image: 'https://www.mrftyres.com/images/tyres/patterns/Shakthi-Life-Plus-Front-R.png', // Replace with your product image URL
      title: 'MRF TYRE',
      description: "MRF Tyres: Durable, superior grip, and perfect for all vehicles. Experience unmatched performance, safety, and mileage with India’s leading tyre brand!"
    },
    {
      id: 2,
      image: 'https://5.imimg.com/data5/SELLER/Default/2022/1/GS/BU/EA/89987832/castrol-activ-bike-engine-oil-500x500.jpg', // Replace with your product image URL
      title: 'CASTROL OIL',
      description: "Castrol Oil: Superior engine protection, efficiency, and trusted performance to extend engine life and maximize power!",
    },
    {
      id: 3,
      image: 'https://tiimg.tistatic.com/fp/1/007/027/amaron-pro-bike-rider-vrla-battery-086.jpg', // Replace with your product image URL
      title: 'AMARON BATTERY',
      description: "Amaron Batteries: Reliable power, extended life, and low maintenance. Engineered for durability to keep your vehicle running smoothly!",
    },
  ];

  return (
    <div className="d-flex justify-content-center flex-wrap">
      {products.map((product) => (
        <Card key={product.id} className="m-3" style={{ width: '18rem' }}>
          <CardImg
            top
            src={product.image}
            alt={product.title}
            style={{ height: '200px', objectFit: 'cover' }}
          />
          <CardBody style={{backgroundColor:"white"}}>
            {/* <CardTitle className='ctitle'>{product.title}</CardTitle> */}
            <CardTitle className='ctitle' style={{color:"black",backgroundColor:"white"}}>{product.title}</CardTitle>
            <CardText className='text'>{product.description}</CardText>
            <Button className='btn' style={{backgroundColor:"rgb(44, 162, 158)"}}>Learn More</Button>
          </CardBody>
        </Card>
      ))}
    </div>
  );
};

export default ProductCards;

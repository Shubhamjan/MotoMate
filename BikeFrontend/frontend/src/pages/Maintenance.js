import React from 'react';
import './Maintenance.css'; // Updated file with new class names

const Maintenance = () => {
    return (
        <div className="mnt-container">
            <h1 className="mnt-title">Bike Maintenance Guidelines</h1>
            <p className="mnt-intro">
                Keeping your bike in top condition is essential for safety, performance, and longevity. Follow these guidelines to ensure your ride is always smooth and hassle-free!
            </p>

            <div className="mnt-section">
                <div className="mnt-item">
                    <img
                        src="https://mybikeclinic.com/img/carousel-bg-2.jpg" // Replace with actual image URL
                        alt="Regular Servicing"
                        className="mnt-img"
                    />
                    <div className="mnt-info">
                        <h2>1. Regular Servicing</h2>
                        <p>
                            Ensure your bike gets regular servicing to avoid unexpected breakdowns. Change the engine oil, clean the air filter, and check critical components like brakes, clutch, and chain tension.
                        </p>
                    </div>
                </div>

                <div className="mnt-item">
                    <img
                        src="https://www.telletire.com/wp-content/uploads/2021/05/AdobeStock_55858595-scaled.jpeg"
                        alt="Tire Pressure Check"
                        className="mnt-img"
                    />
                    <div className="mnt-info">
                        <h2>2. Tire Pressure Check</h2>
                        <p>
                            Proper tire pressure improves mileage and ensures better road grip. Check and maintain the recommended pressure levels regularly for a smoother ride.
                        </p>
                    </div>
                </div>

                <div className="mnt-item">
                    <img
                        src="https://dmlauto.com/wp-content/uploads/2013/03/brake-repair_DML.jpg"
                        alt="Brake Inspection"
                        className="mnt-img"
                    />
                    <div className="mnt-info">
                        <h2>3. Brake Inspection</h2>
                        <p>
                            Inspect your bike's brakes frequently for wear and tear. Replace worn-out brake pads immediately to avoid accidents and ensure responsive braking.
                        </p>
                    </div>
                </div>

                <div className="mnt-item">
                    <img
                        src="https://www.motousher.com/wp-content/uploads/2024/02/Motorcycle_Chain_Grunge_Brush.jpeg"
                        alt="Lubricate Chain"
                        className="mnt-img"
                    />
                    <div className="mnt-info">
                        <h2>4. Lubricate the Chain</h2>
                        <p>
                            A well-lubricated chain reduces wear and tear while enhancing bike performance. Use high-quality chain lubricants and avoid over-lubrication to prevent dirt accumulation.
                        </p>
                    </div>
                </div>

                <div className="mnt-item">
                    <img
                        src="https://www.sunautoservice.com/wp-content/uploads/2019/04/iStock-106539729-1-1024x685.jpg"
                        alt="Battery Care"
                        className="mnt-img"
                    />
                    <div className="mnt-info">
                        <h2>5. Battery Care</h2>
                        <p>
                            Regularly check the battery terminals for corrosion and clean them when necessary. Ensure the electrolyte levels are sufficient for a long-lasting battery life.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Maintenance;

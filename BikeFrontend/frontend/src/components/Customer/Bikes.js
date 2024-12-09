import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { BASE_URL } from '../../services/config';
import { toast } from 'react-toastify';
import './Bikes.css';

const Bikes = () => {
    const [bikes, setBikes] = useState([]);
    const userId = localStorage.getItem('userId'); // Retrieve the user's ID from local storage
    // console.log(userId)
    // const userId=1
    useEffect(() => {
        const fetchBikes = async () => {
            try {
                if (!userId) {
                    toast.error('User ID is not available. Please log in again.');
                    return;
                }

                const response = await axios.get(`${BASE_URL}/v1/user/bikes/${userId}`);
                setBikes(response.data);
                console.log(response.data)
            } catch (error) {
                toast.error('Failed to fetch bikes. Please try again.');
            }
        };

        fetchBikes();
    }, [userId]);

    return (
        <div className="bikes-container">
            <h2 className="bikes-title">Your Bikes</h2>
            {bikes.length > 0 ? (
                <table className="bikes-table">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Model</th>
                            <th>Registration Number</th>
                            <th>Company</th>
                        </tr>
                    </thead>
                    <tbody>
                        {bikes.map((bike, index) => (
                            <tr key={bike.id}>
                                <td>{index + 1}</td>
                                <td>{bike.model}</td>
                                <td>{bike.registrationNumber}</td>
                                <td>{bike.company}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p className="no-bikes">No bikes found!</p>
            )}
        </div>
    );
};

export default Bikes;

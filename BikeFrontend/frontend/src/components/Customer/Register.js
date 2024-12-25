import React, { useState } from 'react';
import axios from 'axios';
import { BASE_URL } from '../../services/config';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import './Register.css';

const Register = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        gender: '',
        mobileNumber: '',
        address: '',
        role: 'USER', // Default role
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${BASE_URL}/v1/user/register`, formData);
            toast.success('Registration successful!');
        } catch (error) {
            toast.error('Registration failed. Please try again.');
        }
    };

    return (
        <div className="register-page">
            <div className="register-cont">
                {/* Left Section with Image */}
                <div className="register-image">
                    <img
                        src="https://img.freepik.com/premium-vector/online-registration-illustration-design-concept-websites-landing-pages-other_108061-938.jpg"
                        alt="Registration Illustration"
                        className="register-img"
                    />
                </div>

                {/* Right Section with Form */}
                <div className="register-form-wrapper">
                    <div className="register-form-container">
                        <div  className="tit">
                        <h2>Create Account</h2>
                        </div>
                        
                        <form onSubmit={handleSubmit} className="register-form">
                            <div className="form-row">
                                <div className="form-group">
                                    <label>First Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="firstName"
                                        value={formData.firstName}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>
                                <div className="form-group">
                                    <label>Last Name</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="lastName"
                                        value={formData.lastName}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>
                            </div>
                            <div className="form-group">
                                <label>Email</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Password</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    name="password"
                                    value={formData.password}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Gender</label>
                                <select
                                    className="form-control"
                                    name="gender"
                                    value={formData.gender}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="">Select Gender</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label>Mobile Number</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    name="mobileNumber"
                                    value={formData.mobileNumber}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Address</label>
                                <textarea
                                    className="form-control"
                                    name="address"
                                    value={formData.address}
                                    onChange={handleChange}
                                    required
                                ></textarea>
                            </div>
                            <div className="form-group d-flex justify-content-between">
                                <button type="submit" className="Red">
                                    Register
                                </button>
                                <Link to="/login" >
                                    Already have an account? Login
                                </Link>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Register;

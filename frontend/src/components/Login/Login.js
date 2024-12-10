import React, { useState } from 'react';
import axios from 'axios';
import { BASE_URL } from '../../services/config';
import { useNavigate, Link } from 'react-router-dom'; // Import Link for navigation
import { toast } from 'react-toastify';
import { useAuth } from '../../services/AuthContext'; // Import the useAuth hook
import 'react-toastify/dist/ReactToastify.css';
import './Login.css';

const Login = () => {
    const { login } = useAuth(); // Access login method from context
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        role: 'USER',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const loginUrl =
            formData.role === 'ADMIN'
                ? `${BASE_URL}/v1/admin/login`
                : `${BASE_URL}/v1/user/login`;

        try {
            const response = await axios.post(loginUrl, {
                email: formData.email,
                password: formData.password,
            });

            if (response.status === 200) {
                const { id, role } = response.data; // Extract user ID and role from API response
                console.log(response.data)
                console.log(formData.role)
                if (role !== formData.role) {
                    toast.error('Role mismatch! Please select the correct role.');
                    return;
                }
                login(role, id); // Pass role and userId to login function
                toast.success('Login successful!');
                navigate(role === 'ADMIN' ? '/admin-home' : '/home');
            } else {
                toast.error('Invalid credentials, please try again.');
            }
        } catch (error) {
            toast.error('Login failed. Please check your credentials.');
        }
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center align-items-center">
                <div className="col-md-8" style={{ width: "100%" }}>
                    <div className="card">
                        <div className="row g-0">
                            <div className="col-md-5 d-none d-md-block">
                                <img
                                    src="https://img.freepik.com/free-vector/login-concept-illustration_114360-739.jpg"
                                    alt="Login Visual"
                                    className="img-fluid login-image"
                                />
                            </div>
                            <div className="col-md-7">
                                <div className="card-body">
                                    <div className="titl">
                                    <h2>Login</h2>
                                        </div>
                                    <form onSubmit={handleSubmit}>
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
                                            <label>Role</label>
                                            <div>
                                                <div className="form-check form-check-inline">
                                                    <input
                                                        className="form-check-input"
                                                        type="radio"
                                                        name="role"
                                                        value="USER"
                                                        checked={formData.role === 'USER'}
                                                        onChange={handleChange}
                                                    />
                                                    <label className="form-check-label">User</label>
                                                </div>
                                                <div className="form-check form-check-inline">
                                                    <input
                                                        className="form-check-input"
                                                        type="radio"
                                                        name="role"
                                                        value="ADMIN"
                                                        checked={formData.role === 'ADMIN'}
                                                        onChange={handleChange}
                                                    />
                                                    <label className="form-check-label">Admin</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="form-group d-flex justify-content-between align-items-center">
                                            <button type="submit" className="btn-block">
                                                Login
                                            </button>
                                            <Link to="/reset" className="forgot-password-link ml-3">
                                                Forgot Password?
                                            </Link>
                                        </div>

                                    </form>

                                    {/* Add the Forgot Password link
                                    <div className="text-center mt-3">
                                        <Link to="/reset" className="forgot-password-link">
                                            Forgot Password?
                                        </Link>
                                    </div> */}
                                    {/* End Forgot Password link */}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;

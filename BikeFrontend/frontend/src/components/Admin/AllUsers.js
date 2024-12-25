import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { BASE_URL } from '../../services/config';
import './AllUsers.css';

const AllUsers = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/v1/admin/customers`);
                if (Array.isArray(response.data)) {
                    setUsers(response.data);
                } else {
                    console.error('Response is not a list:', response.data);
                }
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };
        fetchUsers();
    }, []);

    return (
        <div className="all-users-container">
            <h1 className="title">Customer Details</h1>
            {users.length > 0 ? (
                <table className="user-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Mobile</th>
                            <th>Address</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>
                                    {user.firstName} {user.lastName}
                                </td>
                                <td>{user.mobileNumber}</td>
                                <td>{user.address}</td>
                                <td>{user.email}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p className="no-data">No users available.</p>
            )}
        </div>
    );
};

export default AllUsers;

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { BASE_URL } from '../../services/config'; // Adjust path if necessary
import { useAuth } from '../../services/AuthContext'; // Import useAuth
import './MyProfile.css'; // Import the custom CSS for profile styling

const MyProfile = () => {
    const { isLoggedIn, role } = useAuth(); // Access isLoggedIn and role from context
    const [profileData, setProfileData] = useState(null);
    const [loading, setLoading] = useState(true);

    // Retrieve userId from localStorage
    const userId = localStorage.getItem('userId'); // Assuming the userId is stored in localStorage
    // console.log(localStorage)
    // console.log("User ID is"+userId)
    useEffect(() => {
        const fetchProfileData = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/v1/user/getProfile/${userId}`);
                console.log(response.data)
                setProfileData(response.data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching profile data', error);
                setLoading(false);
            }
        };

        if (isLoggedIn && userId) {
            fetchProfileData();
        }
    }, [isLoggedIn, userId]);

    if (!isLoggedIn) {
        return <div className="message">Please log in to view your profile.</div>;
    }

    if (loading) {
        return <div className="loading">Loading...</div>;
    }

    if (!profileData) {
        return <div className="message">Profile not found</div>;
    }

    return (
        <div className="profile-container">
            <div className="card profile-card">
                <div className="row g-0">
                    <div className="col-md-5 profile-image">
                        <img
                            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkuHGQcoh2XCa6j_kBji17CIrfC0YMdzKaeyH7nVWmLTK91zTcEeisGgAl_YEZnItoioE&usqp=CAU"height="400px" width="400px" // Placeholder; replace with user image URL if available
                            alt="Profile"
                            className="img-fluid profile-img"
                        />
                    </div>
                    <div className="col-md-7">
                        <div className="card-body">
                            <h3 className="card-title">
                                {profileData.firstName} {profileData.lastName}
                            </h3>
                            <p><strong>Email:</strong> {profileData.email}</p>
                            <p><strong>Gender:</strong> {profileData.gender}</p>
                            <p><strong>Role:</strong> {role}</p>
                            <p><strong>Address:</strong> {profileData.address || 'Not provided'}</p>
                            <p><strong>Mobile:</strong> {profileData.mobileNumber || 'Not provided'}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MyProfile;

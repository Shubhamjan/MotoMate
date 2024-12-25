import React, { createContext, useState, useContext } from 'react';

// Create a Context for authentication
const AuthContext = createContext();

// Custom hook to access the AuthContext
export const useAuth = () => {
    return useContext(AuthContext);
};

// Provider component to wrap the app and provide auth state
export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('isLoggedIn') === 'true');
    const [role, setRole] = useState(localStorage.getItem('role'));
    const [userId, setUserId] = useState(localStorage.getItem('userId'));

    const login = (role, id) => {
        setIsLoggedIn(true);
        setRole(role);
        setUserId(id);
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('role', role);
        localStorage.setItem('userId', id);
    };

    const logout = () => {
        setIsLoggedIn(false);
        setRole(null);
        setUserId(null);
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('role');
        localStorage.removeItem('userId');
    };

    return (
        <AuthContext.Provider value={{ isLoggedIn, role, userId, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

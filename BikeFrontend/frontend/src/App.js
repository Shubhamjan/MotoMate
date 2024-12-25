import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './pages/Navbar';
import Footer from './pages/Footer';
import CarouselComponent from './pages/CarouselComponent';
import Register from './components/Customer/Register';
import Login from './components/Login/Login';
import UserHome from './components/Customer/UserHome';
import AdminHome from './components/Admin/AdminHome';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
// import { AuthProvider, useAuth } from './services/AuthContext'; // As originally written
import { AuthProvider,useAuth } from './services/AuthContext';
import MyProfile from './components/Customer/MyProfile';
import Bikes from './components/Customer/Bikes';
import AddBike from './components/Customer/AddBike';
import MyWallet from './components/Customer/MyWallet';
import BookService from './components/Customer/BookService';
import MyBookings from './components/Customer/MyBookings';
import AllUsers from './components/Admin/AllUsers';
import AllBookings from './components/Admin/AllBookings';
import UpdateDetails from './components/Admin/UpdateDetails';
import ProductCards from './components/Products/ProductCards';
import ResetPassword from './components/Login/ResetPassword';
import About from './pages/About';
import Maintenance from './pages/Maintenance';
// import BikeCare from './pages/BikeCare';



const App = () => {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
};

const AppContent = () => {
  const { isLoggedIn, role } = useAuth(); // Access auth state from context

  return (
    <Router>
      {/* Conditionally render Navbar based on login status */}
      {isLoggedIn && role === 'USER' && <Navbar />}
      {isLoggedIn && role === 'ADMIN' && <Navbar/>}
      {!isLoggedIn && <Navbar/>}
      {/* <hr/> */}
      <div className="main-content">
      {/* <CarouselComponent /> Always show the carousel */}
       
        <Routes>
          {/* Public Routes */}
          <Route path="/" element={<CarouselComponent/>} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/product" element={<ProductCards />}/>
          {/* Protected Routes */}
          <Route path="/admin-home" element={<AdminHome />} />
          <Route path="bookings" element={<AllBookings/>}/>
          <Route path="userdetail/:bookingId" element={<UpdateDetails />} />
          <Route path="users" element={<AllUsers/>}/>
          <Route path="/home" element={<UserHome />} />
          <Route path="/profile" element={<MyProfile/>}/>
          <Route path="/bikes" element={<Bikes/>}/>
          <Route path="/addBike" element={<AddBike/>}/>
          <Route path="/wallet" element={<MyWallet/>}/>
          <Route path="/bookService" element={<BookService/>}/>
          <Route path="/myservices" element={<MyBookings/>}/>
           <Route path="/about" element={<About/>}/>
          <Route path="/care" element={<Maintenance/>}/> 
          <Route path="/reset" element={<ResetPassword/>}/>
         

        </Routes>
      </div>

      {/* Always show footer */}
     
      <Footer />

      {/* Toast Notifications */}
      <ToastContainer position="top-right" autoClose={3000} hideProgressBar />
    </Router>
  );
};

export default App;

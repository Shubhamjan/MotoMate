import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button, Table } from 'reactstrap';
import './Mybookings.css';  // Import the CSS file
import { BASE_URL } from '../../services/config';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const MyBookings = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  // Retrieve userId from localStorage
  const userId = localStorage.getItem('userId');

  useEffect(() => {
    const fetchBookings = async () => {
      if (!userId) {
        toast.error('User not found!');
        return;
      }

      try {
        const response = await axios.get(`${BASE_URL}/v1/booking/get/${userId}`);
        setBookings(response.data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching bookings", error);
        setLoading(false);
        toast.error('Error fetching bookings!');
      }
    };
    
    fetchBookings();
  }, [userId]);

  const handleCancelBooking = async (bookingId) => {
    try {
      // Send cancellation request to the server
      const response = await axios.put(`${BASE_URL}/v1/booking/cancel/Cancelled/${bookingId}`);
      
      if (response.status === 200) {
        // Update the booking status to 'Cancelled' in the frontend if the request is successful
        setBookings(bookings.map(booking => 
          booking.bookingId === bookingId ? { ...booking, bookingStatus: 'Cancelled' } : booking
        ));
        toast.success('Booking cancelled successfully');
      }
    } catch (error) {
      console.error("Error cancelling booking", error);
      toast.error('Cannot cancel booking');
    }
  };

  if (loading) {
    return <div className="loading-spinner">Loading...</div>;
  }

  return (
    <div className="my-bookings-container">
      <h2 className='head-book'>My Bookings</h2>
      {/* <p className='head-book'>My Bookings</p> */}
      <table striped align='center'>
        <thead>
          <tr>
            <th>Booking ID</th>
            <th>Model</th>
            <th>Registration Number</th>
            <th>Company</th>
            <th>Service Date</th>
            <th>Booked Date</th>
            <th>Booking Status</th>
            <th>Service Status</th>
            <th>Service Fee</th>
            <th>Payment Mode</th>
            <th>Payment Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map((booking) => (
            <tr key={booking.bookingId}>
              <td>{booking.bookingId}</td>
              <td>{booking.model}</td>
              <td>{booking.registrationNumber}</td>
              <td>{booking.company}</td>
              <td>{booking.serviceDate}</td>
              <td>{booking.bookedDate}</td>
              <td>{booking.bookingStatus}</td>
              <td>{booking.serviceStatus}</td>
              <td>{booking.serviceFee}</td>
              <td>{booking.paymentMode}</td>
              <td>{booking.paymentStatus}</td>
              <td>
                <Button 
                  color="danger" 
                  onClick={() => handleCancelBooking(booking.bookingId)}
                >
                  Cancel
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MyBookings;

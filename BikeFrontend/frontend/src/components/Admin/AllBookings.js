import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./AllBookings.css";
import { BASE_URL } from "../../services/config";

const AllBookings = () => {
  const [bookings, setBookings] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch bookings data
    const fetchBookings = async () => {
      try {
        const response = await axios.get(`${BASE_URL}/v1/booking/getAll`);
        setBookings(response.data);
        console.log(response.data);
      } catch (error) {
        console.error("Error fetching bookings:", error);
      }
    };

    fetchBookings();
  }, []);

  const handleAction = (
    bookingId,
    bookedDate,
    bookingStatus,
    serviceStatus,
    serviceFee,
    paymentMode,
    paymentStatus
  ) => {
    navigate(
      `/userdetail/${bookingId}?bookedDate=${bookedDate}&bookingStatus=${bookingStatus}&serviceStatus=${serviceStatus}&serviceFee=${serviceFee}&paymentMode=${paymentMode}&paymentStatus=${paymentStatus}`
    );
  };

  return (
    <div className="all-bookings-container">
      <h2>All Bookings</h2>
      <table className="tabl" align="center">
        <thead>
          <tr>
            <th>Booking Id</th>
            <th>Model</th>
            <th>Registration Number</th>
            <th>Company</th>
            <th>Customer Name</th>
            <th>Contact Number</th>
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
              <td>{booking.fullName}</td>
              <td>{booking.contactNumber}</td>
              <td>{booking.serviceDate}</td>
              <td>{booking.bookedDate}</td>
              <td>{booking.bookingStatus}</td>
              <td>{booking.serviceStatus}</td>
              <td>{booking.serviceFee.toFixed(2)}</td>
              <td>{booking.paymentMode}</td>
              <td>{booking.paymentStatus}</td>
              <td>
                <button
                  className="action-button"
                  onClick={() =>
                    handleAction(
                      booking.bookingId,
                      booking.bookedDate,
                      booking.bookingStatus,
                      booking.serviceStatus,
                      booking.serviceFee,
                      booking.paymentMode,
                      booking.paymentStatus
                    )
                  }
                >
                  Action
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AllBookings;

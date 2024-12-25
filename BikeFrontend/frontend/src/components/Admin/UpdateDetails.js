import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useSearchParams } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./UpdateDetails.css"; // Import custom CSS
import { BASE_URL } from "../../services/config";

const UpdateDetails = () => {
  const { bookingId } = useParams(); // Booking ID from URL
  const [searchParams] = useSearchParams();

  // Extract query parameters
  const serviceDateFromParams = searchParams.get("bookedDate");
  const paymentModeFromParams = searchParams.get("paymentMode");
  const bookingStatusFromParams = searchParams.get("bookingStatus");
  const serviceStatusFromParams = searchParams.get("serviceStatus");
  const serviceFeeFromParams = searchParams.get("serviceFee");
  const paymentStatusFromParams = searchParams.get("paymentStatus");
  console.log(serviceDateFromParams)
  // Initialize state with query parameters
  const [bookingStatus, setBookingStatus] = useState(bookingStatusFromParams || "");
  const [serviceStatus, setServiceStatus] = useState(serviceStatusFromParams || "");
  const [serviceFee, setServiceFee] = useState(serviceFeeFromParams || "");
  const [date, setDate] = useState(serviceDateFromParams || ""); // Use date from params
  const [paymentMode, setPaymentMode] = useState(paymentModeFromParams || "Wallet");
  const [paymentStatus, setPaymentStatus] = useState(paymentStatusFromParams || "");
  const [serviceAmount, setServiceAmount] = useState("500");
  const [lastPaymentStatus, setLastPaymentStatus] = useState(paymentStatusFromParams || "Pending");

  // Format date as dd-mm-yyyy
  const formatDate = (isoDate) => {
    if (!isoDate) return "";
    const [year, month, day] = isoDate.split("-");
    return `${day}-${month}-${year}`;
  };

  // Convert dd-mm-yyyy to yyyy-mm-dd for input value
  const unformatDate = (formattedDate) => {
    if (!formattedDate) return "";
    const [day, month, year] = formattedDate.split("-");
    return `${year}-${month}-${day}`;
  };

  const handleBookingUpdate = async () => {
    try {
      const isoDate = unformatDate(date); // Convert dd-mm-yyyy to yyyy-mm-dd
      await axios.put(`${BASE_URL}/v1/admin/update/bookingStatus`, {
        bookingId,
        status: bookingStatus,
        date: isoDate || new Date().toISOString().split("T")[0], // Use provided or current date
      });
      toast.success("Booking status updated successfully!");
    } catch (error) {
      toast.error("Failed to update booking status.");
      console.error(error);
    }
  };

  const handleServiceUpdate = async () => {
    try {
      await axios.put(`${BASE_URL}/v1/admin/update/serviceStatus`, {
        bookingId,
        serviceStatus,
        serviceFee,
      });
      toast.success("Service status updated successfully!");
    } catch (error) {
      toast.error("Failed to update service status.");
      console.error(error);
    }
  };

  const handleDeductAmount = async () => {
    try {
      await axios.put(`${BASE_URL}/v1/admin/transaction`, {
        bookingId,
        amount: serviceFee || serviceAmount, // Use service fee or default amount
      });
      toast.success("Amount deducted successfully!");

      // Reflect changes automatically
      setPaymentStatus("Completed");
      setLastPaymentStatus("Completed");
    } catch (error) {
      toast.error("failed transaction");
      console.error(error);
    }
  };




  const handlePaymentUpdate = async () => {
    try {
      await axios.put(`${BASE_URL}/v1/admin/update/paymentStatus`, {
        bookingId,
        status: paymentStatus,
      });
      toast.success("Payment status updated successfully!");
    } catch (error) {
      toast.error("Failed to update payment status.");
      console.error(error);
    }
  };

  return (
    <div className="update-details-container">
      <h2 className="page-title">Update Booking Details</h2>
      <div className="cards-container">
        {/* Booking Status Card */}
        {/* Booking Status Card */}
        <div className="my-wallet-card">
          <h3 className="my-wallet-card-title">Update Booking Status</h3>
          <div className="my-wallet-section">
            <label htmlFor="bookingStatus" className="my-wallet-label">Status:</label>
            <select
              id="bookingStatus"
              value={bookingStatus}
              onChange={(e) => setBookingStatus(e.target.value)}
              className="my-wallet-input"
            >
              <option value="">Select Status</option>
              <option value="Approved">Approved</option>
              <option value="Pending">Pending</option>
              <option value="Cancelled">Cancelled</option>
            </select>

            <label htmlFor="date" className="my-wallet-label">Select Date:</label>
            <input
              type="date"
              id="date"
              value={unformatDate(date)} // Convert to yyyy-mm-dd for input value
              onChange={(e) => setDate(formatDate(e.target.value))} // Convert to dd-mm-yyyy
              className="my-wallet-input"
            />

            <p>Present Status: {bookingStatusFromParams}</p>
            <p>Service Date: {formatDate(serviceDateFromParams)}</p> {/* Reflecting the service date */}
            <button className="my-wallet-button" onClick={handleBookingUpdate}>
              Update Booking
            </button>
          </div>
        </div>


        {/* Service Status Card */}
        <div className="my-wallet-card">
          <h3 className="my-wallet-card-title">Update Service Status</h3>
          <div className="my-wallet-section">
            <label htmlFor="serviceStatus" className="my-wallet-label">Service Status:</label>
            <select
              id="serviceStatus"
              value={serviceStatus}
              onChange={(e) => setServiceStatus(e.target.value)}
              className="my-wallet-input"
            >
              <option value="">Select Service Status</option>
              <option value="Completed">Completed</option>
              <option value="Processing">Processing</option>
              <option value="Pending">Pending</option>
            </select>
            <label htmlFor="serviceFee" className="my-wallet-label">Service Fee:</label>
            <input
              type="number"
              id="serviceFee"
              value={serviceFee}
              onChange={(e) => setServiceFee(e.target.value)}
              className="my-wallet-input"
              placeholder="Enter Service Fee"
            />
            <p>Present Service Status: {serviceStatusFromParams}</p>
            <p>Present Service Fee: ₹{serviceFeeFromParams}</p>
            <button className="my-wallet-button" onClick={handleServiceUpdate}>
              Update Service
            </button>
          </div>
        </div>

        {/* Payment Status Card */}
<div className="my-wallet-card">
  <h3 className="my-wallet-card-title">Payment Status</h3>
  <div className="my-wallet-section">
    {/* Display Payment Mode */}
    <p>Payment Mode: {paymentMode}</p>

    {/* Payment Status Dropdown */}
    <label htmlFor="paymentStatus" className="my-wallet-label">Payment Status:</label>
    <select
      id="paymentStatus"
      value={paymentStatus}
      onChange={(e) => setPaymentStatus(e.target.value)}
      className="my-wallet-input"
      disabled={paymentMode === "Wallet"} // Disable if payment mode is Wallet
    >
      <option value="">Select Payment Status</option>
      <option value="Completed">Completed</option>
      <option value="Pending">Pending</option>
    </select>

    {/* Service Charges Display */}
    <p>Service Charges: ₹{serviceFee}</p>
    <p>Last Payment Status: {lastPaymentStatus}</p>

    {/* Deduct Amount Button */}
    <button
      className="my-wallet-button"
      onClick={handleDeductAmount}
      disabled={paymentMode === "Cash"} // Disable button if payment mode is Cash
    >
      Deduct Amount
    </button>

    {/* Update Payment Status Button */}
    <button
      className="my-wallet-button"
      onClick={handlePaymentUpdate}
      disabled={paymentMode === "Wallet"} // Disable button if payment mode is Wallet
    >
      Update Payment Status
    </button>
  </div>
</div>


      </div>
    </div>
  );
};

export default UpdateDetails;

import React, { useState } from "react";
import axios from "axios";
import { toast } from "react-toastify"; // Import toast for notifications
import "react-toastify/dist/ReactToastify.css";
import "./ResetPassword.css";

const BASE_URL = "http://localhost:8080/api/v1"; // Update this if the base URL changes

const ResetPassword = () => {
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [message, setMessage] = useState("");
  const [showResetForm, setShowResetForm] = useState(false);
  const [isOtpVerified, setIsOtpVerified] = useState(false);

  // Handle sending the OTP
  const handleSendOtp = async () => {
    try {
      const response = await axios.post(`${BASE_URL}/send-otp/${email}`);
      toast.success("OTP sent successfully to your email!"); // Success toast
      setShowResetForm(true); // Show reset form
    } catch (error) {
      toast.error(
        error.response?.data || "Failed to send OTP. Please try again."
      ); // Error toast
    }
  };

  // Handle verifying the OTP
  const handleVerifyOtp = async () => {
    try {
      const response = await axios.post(`${BASE_URL}/verify-otp`, {
        email,
        otp,
      });
      toast.success("OTP verified successfully!"); // Success toast
      setIsOtpVerified(true); // Allow resetting password
    } catch (error) {
      toast.error(
        error.response?.data || "Failed to verify OTP. Please try again."
      ); // Error toast
      setIsOtpVerified(false);
    }
  };

  // Handle resetting the password
  const handleResetPassword = async () => {
    if (!isOtpVerified) {
      toast.error("Please verify the OTP before resetting your password."); // Error toast
      return;
    }

    try {
      const response = await axios.post(`${BASE_URL}/reset-password`, {
        email,
        otp,
        newPass: newPassword,
      });
      setMessage(response.data);
      toast.success("Password reset successfully!"); // Success toast
    } catch (error) {
      setMessage(
        error.response?.data || "Failed to reset password. Please try again."
      );
      toast.error(
        error.response?.data || "Failed to reset password. Please try again."
      ); // Error toast
    }
  };

  return (
    <div className="reset-password-container">
      {/* Removed ToastContainer here to avoid duplicates */}
      <h1>Password Reset</h1>

      {!showResetForm && (
        <div className="forget-section">
          <p>Enter your email to receive the OTP:</p>
          <input
            type="email"
            placeholder="Enter your email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button onClick={handleSendOtp}>Send OTP</button>
        </div>
      )}

      {showResetForm && (
        <div className="reset-section">
          <p>Enter the OTP and your new password:</p>
          <input
            type="text"
            placeholder="Enter the OTP"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
          />
          <button onClick={handleVerifyOtp}>Verify OTP</button>

          {isOtpVerified && (
            <>
              <input
                type="password"
                placeholder="Enter your new password"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
              />
              <button onClick={handleResetPassword}>Reset Password</button>
            </>
          )}
        </div>
      )}

      {message && <p className="response-message">{message}</p>}
    </div>
  );
};

export default ResetPassword;

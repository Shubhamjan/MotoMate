// frontend/src/pages/ToastContainer.js

import React from 'react';
import { ToastContainer as OriginalToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'; // Ensure you import the CSS
import './ToastContainer.css'; // Import your custom CSS for styling

const CustomToastContainer = () => {
  return (
    <OriginalToastContainer
      position="top-right"
      autoClose={3000} // Automatically close after 3 seconds
      hideProgressBar={false}
      newestOnTop={false}
      closeOnClick
      rtl={false}
      pauseOnFocusLoss
      draggable
      pauseOnHover
      theme="light"
      toastClassName="custom-toast" // Apply custom styles to the toast container
      bodyClassName="custom-toast-body" // Apply custom styles to the body
    />
  );
};

export default CustomToastContainer;

import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CarouselComponent from "../../pages/CarouselComponent"; // Ensure correct path
import { toast } from "react-toastify";

const AdminHomePage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";
    const role = localStorage.getItem("role");
    console.log("Admin home page with "+role)
    if (!isLoggedIn || role !== "ADMIN") {
      // Redirect to login if not authenticated or not an ADMIN
      toast.error("Unauthorized access. Please log in as admin.", {
        position: "top-right",
        autoClose: 3000,
      });
      navigate("/login");
    }
  }, [navigate]);

  return (
    <div>
      <CarouselComponent /> {/* Renders the CarouselComponent */}
    </div>
  );
};

export default AdminHomePage;

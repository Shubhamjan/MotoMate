import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, CardBody, CardTitle, Form, FormGroup, Label, Input, Button } from "reactstrap";
import { toast } from "react-toastify";
import "bootstrap/dist/css/bootstrap.min.css";
import { BASE_URL } from "../../services/config";
import './BookService.css';

const BookService = () => {
  const [bikeList, setBikeList] = useState([]);
  const [selectedBike, setSelectedBike] = useState("");
  const [bookingDate, setBookingDate] = useState("");
  const [paymentMode, setPaymentMode] = useState(""); // New state for payment mode
  const storedUserId = localStorage.getItem("userId"); // Fetch userId from localStorage

  // Fetch bike list when component loads
  useEffect(() => {
    const fetchBikeList = async () => {
      try {
        const response = await axios.get(`${BASE_URL}/v1/user/bikes/${storedUserId}`);
        setBikeList(response.data);
      } catch (error) {
        toast.error("Failed to fetch bike list.", {
          position: "top-right",
          autoClose: 3000,
        });
      }
    };
    fetchBikeList();
  }, [storedUserId]);

  const handleBookBike = async (e) => {
    e.preventDefault();
    if (!selectedBike || !bookingDate || !paymentMode) {
      toast.error("Please fill all the fields.", {
        position: "top-right",
        autoClose: 3000,
      });
      return;
    }

    const bookingRequest = {
      uid: storedUserId,
      registrationId: selectedBike,
      date: bookingDate,
      paymentMode: paymentMode, // Include payment mode in the request
    };

    try {
      await axios.post(`${BASE_URL}/v1/booking/bikebook`, bookingRequest);
      toast.success("Bike booked successfully!", {
        position: "top-right",
        autoClose: 3000,
      });
      setSelectedBike("");
      setBookingDate("");
      setPaymentMode(""); // Reset payment mode
    } catch (error) {
      toast.error(
        error.response?.data?.message || "Failed to book bike.",
        { position: "top-right", autoClose: 3000 }
      );
    }
  };

  return (
    <div className="container mt-5">
      <Card className="">
        <CardBody>
          <CardTitle tag="h4" className="card-title">
            Book a Service
          </CardTitle>
          <Form onSubmit={handleBookBike}>
            <FormGroup>
              <Label for="bike">Select Bike</Label>
              <Input
                type="select"
                id="bike"
                value={selectedBike}
                onChange={(e) => setSelectedBike(e.target.value)}
              >
                <option value="">-- Select Bike --</option>
                {bikeList.map((bike) => (
                  <option key={bike.id} value={bike.registrationNumber}>
                    {bike.registrationNumber}
                  </option>
                ))}
              </Input>
            </FormGroup>
            <FormGroup>
              <Label for="date">Select Date</Label>
              <Input
                type="date"
                id="date"
                value={bookingDate}
                onChange={(e) => setBookingDate(e.target.value)}
              />
            </FormGroup>
            <FormGroup>
              <Label for="paymentMode">Select Payment Mode</Label> {/* New field */}
              <Input
                type="select"
                id="paymentMode"
                value={paymentMode}
                onChange={(e) => setPaymentMode(e.target.value)}
              >
                <option value="">-- Select Payment Mode --</option>
                <option value="Cash">Cash</option>
                <option value="Wallet">Wallet</option>
              </Input>
            </FormGroup>
            <Button className="bt">
              Book Bike Service
            </Button>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};

export default BookService;

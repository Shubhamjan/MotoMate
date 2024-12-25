import React, { useState, useEffect } from "react";
import axios from "axios";
import { BASE_URL } from "../../services/config";
import {
    Card,
    CardBody,
    CardTitle,
    CardText,
    Form,
    FormGroup,
    Label,
    Input,
    Button,
} from "reactstrap";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import './AddBike.css';
const AddBike = () => {
    const [bike, setBike] = useState({
        model: "",
        registrationNumber: "",
        company: "",
    });

    const [userId, setUserId] = useState(null);

    // Fetch userId from localStorage
    useEffect(() => {
        const storedUserId = localStorage.getItem("userId");
        if (storedUserId) {
            setUserId(storedUserId);
        } else {
            toast.error("User ID not found. Please log in again.", {
                className: "custom-toast",
                bodyClassName: "custom-toast-body",
                position: "top-right",
                autoClose: 3000,
            });
        }
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setBike({ ...bike, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!userId) {
            toast.error("User ID is missing.", {
                className: "custom-toast",
                bodyClassName: "custom-toast-body",
                position: "top-right",
                autoClose: 3000,
            });
            return;
        }

        try {
            await axios.post(`${BASE_URL}/v1/user/addBike/${userId}`, bike);
            toast.success("Bike added successfully!", {
                className: "custom-toast",
                bodyClassName: "custom-toast-body",
                position: "top-right",
                autoClose: 3000,
            });
            setBike({ model: "", registrationNumber: "", company: "" }); // Clear the form
        } catch (error) {
            toast.error(
                error.response?.data?.message ||
                "An error occurred while adding the bike.",
                {
                    className: "custom-toast",
                    bodyClassName: "custom-toast-body",
                    position: "top-right",
                    autoClose: 3000,
                }
            );
        }
    };

    // Return a loading message or a placeholder while waiting for userId
    if (userId === null) {
        return <div>Loading...</div>;
    }

    return (
        <div className="add-bike-container" style={{ maxWidth: "500px", margin: "0 auto", marginTop: "50px" }}>
            <Card>
                <CardBody>
                    <CardTitle className="tit">Add a New Bike</CardTitle>
                    <CardText>Fill in the details below to add a new bike.</CardText>
                    <Form onSubmit={handleSubmit}>
                        <FormGroup>
                            <Label for="model">Bike Model</Label>
                            <Input
                                type="text"
                                name="model"
                                id="model"
                                placeholder="Enter bike model"
                                value={bike.model}
                                onChange={handleChange}
                                required
                            />
                        </FormGroup>
                        <FormGroup>
                            <Label for="registrationNumber">Registration Number</Label>
                            <Input
                                type="text"
                                name="registrationNumber"
                                id="registrationNumber"
                                placeholder="Enter registration number"
                                value={bike.registrationNumber}
                                onChange={handleChange}
                                required
                            />
                        </FormGroup>
                        <FormGroup>
                            <Label for="company">Company</Label>
                            <Input
                                type="text"
                                name="company"
                                id="company"
                                placeholder="Enter bike company"
                                value={bike.company}
                                onChange={handleChange}
                                required
                            />
                        </FormGroup>
                        <Button
                            type="submit"
                            color="primary"
                            block
                            style={{
                                width: 'auto', // Adjust width automatically
                                height: '50px', // Set a specific height for the button
                                fontSize: '16px', // Adjust font size
                                padding: '10px', // Adjust padding inside the button
                                borderRadius: '5px' // Optionally add border radius for rounded corners
                            }}
                        >
                            Add Bike
                        </Button>
                    </Form>
                </CardBody>
            </Card>
        </div>
    );
};

export default AddBike;

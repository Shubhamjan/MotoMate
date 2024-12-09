import React, { useState, useEffect } from "react";
import axios from "axios";
import { BASE_URL } from "../../services/config";
import { Card, CardBody, Form, FormGroup, Input, Button, Row, Col } from "reactstrap";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./MyWallet.css"; // Import CSS file

const MyWallet = () => {
    const [walletBalance, setWalletBalance] = useState(0);
    const [amountToAdd, setAmountToAdd] = useState("");
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        const storedUserId = localStorage.getItem("userId");
        if (storedUserId) {
            setUserId(storedUserId);
            fetchWalletBalance(storedUserId);
        } else {
            toast.error("User ID not found. Please log in again.", {
                position: "top-right",
                autoClose: 3000,
            });
        }
    }, []);

    const fetchWalletBalance = async (userId) => {
        try {
            const response = await axios.get(`${BASE_URL}/v1/user/getWallet/${userId}`);
            setWalletBalance(response.data.balance);
        } catch (error) {
            toast.error(
                error.response?.data?.message || "Failed to fetch wallet balance.",
                { position: "top-right", autoClose: 3000 }
            );
        }
    };

    const handleAddMoney = async (e) => {
        e.preventDefault();

        if (!amountToAdd || isNaN(amountToAdd) || amountToAdd <= 0) {
            toast.error("Please enter a valid amount to add.", {
                position: "top-right",
                autoClose: 3000,
            });
            return;
        }

        try {
            await axios.post(
                `${BASE_URL}/v1/user/addMoney/${userId}/${parseFloat(amountToAdd)}`
            );

            toast.success("Money added to wallet successfully!", {
                position: "top-right",
                autoClose: 3000,
            });

            setAmountToAdd("");
            fetchWalletBalance(userId);
        } catch (error) {
            toast.error(
                error.response?.data?.message || "Failed to add money to wallet.",
                { position: "top-right", autoClose: 3000 }
            );
        }
    };

    return (
        <div className="my-wallet-container">
            <Card className="my-wallet-card" style={{height:"25%"}}>
                <CardBody>
                    <div className="my-card-title">My Wallet</div>
                    <Row>
                        <Col sm="12" className="my-wallet-section">
                            {/* Wallet Balance Section */}
                            <div className="my-wallet-label">Wallet Balance</div>
                            <div className="my-wallet-balance">₹ {walletBalance.toFixed(2)}</div>
                        </Col>
                    </Row>
                    <Row>
                        {/* Add Money Section */}
                        <Col sm="12" className="my-wallet-section">
                            <div className="my-wallet-label">Add Money</div>
                            <Form className="my-wallet-form" onSubmit={handleAddMoney}>
                                <FormGroup>
                                    <Input
                                        type="number"
                                        name="amount"
                                        placeholder="Enter amount"
                                        className="my-wallet-input"
                                        value={amountToAdd}
                                        onChange={(e) => setAmountToAdd(e.target.value)}
                                        required
                                    />
                                </FormGroup>
                                <Button type="submit" className="butt" style={{backgroundColor:"#52a44f"}}>
                                    Add Money
                                </Button>
                                {/* <input type="button">Add Money> */}
                            </Form>
                        </Col>
                    </Row>
                </CardBody>
            </Card>
        </div>
    );
};

export default MyWallet;

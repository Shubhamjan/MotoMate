# Online Bike Service Booking System

The **Online Bike Service Booking System** is a software solution that allows customers to book servicing appointments for their bikes, manage their bookings, and handle payments through a wallet or cash mode. The system provides functionalities for both **Administrators** and **Customers** to manage and streamline the bike servicing process efficiently.

---

## Technologies Used
1. **Spring Boot (REST APIs)** - For backend development.
2. **React.js (Frontend)** - For creating an interactive user interface.
3. **MySQL** - For managing the database.
4. **Maven** - For project management and build automation.
5. **Bootstrap** - For styling and responsive design.

---

## User Modules
The system includes two main user modules:

1. **Administrator Module**  
   - Manage customer bookings.  
   - Approve or cancel bookings.  
   - Update servicing statuses and fees.

2. **Customer Module**  
   - Add bikes to the system.  
   - Book servicing appointments.  
   - Manage wallet and payments.

---

## Functional Modules

### 1. **User Authentication Module**  
   - Secure registration and login for Admin and Customer users.  
   - Forgot Password functionality for account recovery.  

### 2. **Bike Module**  
   - Add a bike to the system.  
   - View "My Bikes".  
   - Admin can view all bikes.  

### 3. **Booking Module**  
   - Book a servicing appointment.  
   - View booking status and history.  
   - Cancel bookings.  
   - Admin can view all customer bookings and update booking statuses.  

### 4. **Servicing Module**  
   - View servicing details.  
   - Add servicing fees.  
   - Update servicing status (e.g., "Completed", "Pending").  

### 5. **Payment Module**  
   - View the customer wallet.  
   - Add money to the wallet.  
   - Pay from the wallet after servicing.  
   - Pay via cash mode.

---

## Project Overview  

The **Online Bike Service Booking System** allows customers to add their bikes, manage wallets, and book servicing appointments. Admins manage customer bookings, approve or reject appointments, and update servicing statuses post-service completion.

### Workflow:
1. **Customer Actions:**  
   - Add a bike to the system.  
   - Schedule servicing.  
   - Choose to pay via wallet or cash after the service.  

2. **Admin Actions:**  
   - View and approve/reject customer bookings based on availability.  
   - Update the servicing status and fees after service completion.  

3. **Post-Service Actions:**  
   - Wallet deductions or cash payments are processed.  
   - Servicing status is updated to "Completed".  

Both Admins and Customers can view updated statuses and details at any time.

---

## Additional Features  
1. **Forgot Password:** Recover account using a secure method.  
2. **Profile Management:** Customers can view.  
3. **Add Bikes:** Customers can register their bikes in the system.  
4. **Cash Payment Mode:** In addition to wallet payments, customers can pay via cash.  

---

## Setup Instructions  

### Prerequisites:
- Java Development Kit (JDK 17+)
- Node.js (for React)
- MySQL Database
- Maven (for backend dependencies)

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/username/repo.git

2.Backend Setup:

   Navigate to the backend directory
   
   cd backend
   Configure the database in application.properties.
   
   Build and run the application:

3.Frontend Setup

   Navigate to the frontend directory
   
   cd frontend
   
   Install dependencies
   
   npm install
   
   Start the development server
   
   npm start
   
4.Access the application in your browser at

   http://localhost:3000


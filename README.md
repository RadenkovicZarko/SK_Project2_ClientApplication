# Rental Service System

## Overview

This system comprises three services designed to facilitate vehicle rental operations. It includes a User Service for login and token-based authentication, a Vehicle Rental Service for browsing and booking vehicles, and a Notification Service for email alerts related to reservations and reminders.

## Components Overview
API Gateway: Acts as the entry point for clients. It routes incoming requests to the appropriate microservices through Eureka. The gateway can handle cross-cutting concerns such as security, monitoring, and rate limiting.

Eureka: A service discovery tool that allows microservices to register themselves and to discover other services via a simple DNS-like service. It helps in load balancing and failover of middle-tier servers.

ClientApplication: The front-end application that interacts with the backend through the API Gateway. This could be a web or mobile application.

Microservices (ClientService, RentalService, NotificationService): Independent services that handle different business logic. Each service performs its specific functionality:

ClientService: Manages client-related operations (e.g., client registration, data management).
RentalService: Handles rental transactions or operations.
NotificationService: Manages sending notifications, such as emails, to users. It uses a message broker to decouple message sending from the rest of the application logic.
Message Broker: Facilitates asynchronous communication between services, particularly for the NotificationService to send emails. It ensures that the NotificationService is decoupled from other services, enhancing the system's resilience and scalability.


## Client Application

The Client Application is the front-end component of our microservices-based architecture, designed to offer a seamless and intuitive user experience for car rental services. This application allows users to interact with our platform, providing functionalities such as user registration, login, car reservations, and more.



## Getting Started

### Prerequisites

- Git
- Java (JDK 11 or later)
- MariaDB
- An IDE such as IntelliJ IDEA or Eclipse (optional)

### Installation Steps

1. **Clone the Repositories**: Clone each of the following repositories:

   ```bash
   git clone [Repo URL for SK_Project2_ClientService]
   git clone [Repo URL for SK_Project2_ClientApplication]
   git clone [Repo URL for SK_Project2_RentingService]
   git clone [Repo URL for SK_Project2_NotificationService]
   git clone [Repo URL for SK_Project2_Eureka]
   git clone [Repo URL for SK_Project2_Gateway]



2. **Database Setup**:

Install MariaDB on your PC.
Create three databases named servis, servis2, and servis3.
Ensure MariaDB is running on its default port (usually 3306).

3. **Initial Configuration**:

In the servis database, insert the following roles:
INSERT INTO role (name) VALUES ('ROLE_CLIENT');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_MANAGER');


4. **Service Initialization Order**:

Start the services in the following order to ensure proper registration and functionality:
4.1. ureka Service Discovery
4.2. API Gateway
4.3. Message Broker within the ClientNotificationService
4.4. All other services (ClientService, RentingService, etc.)


5. **GUI Application Usage**:

Before using the GUI application, make sure all services are up and running.
You can register as a user or manager through the GUI application.
To add cars for renting, use insert statements in the servis2 database or manage through the GUI as a manager or admin.
Note: Admin users must be inserted directly into the database and cannot be registered through the GUI.



### Additional Information
Admin User Setup: Detailed instructions on how to insert an admin user into the database.
Contribution Guidelines: If you wish to contribute to the project, please follow our guidelines.






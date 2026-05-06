# Coffee POS System

A Java-based **Point of Sale (POS) system** designed for coffee shops.  
This application allows staff to manage drinks and desserts, process customer orders, and handle payments through a simple and interactive desktop interface.

---

##  Features

###  Customer Side (POS Interface)
- Browse **Drinks** and **Desserts**
- Select:
  - Drink size (S, M, L)
  - Hot / Cold options
- Add items to cart
- View total price dynamically
- Remove items from cart
- Payment processing with change calculation

---

### Admin Panel
- Secure login system
- Add new:
  - Drinks (with Hot/Cold options)
  - Desserts
- Remove menu items
- Update item prices
- View full menu list

---

##  Project Structure
```bash
Coffee POS System/
│
├── src/
│ └── pack1/
│ ├── Admin.java
│ ├── Menu.java
│ ├── Drink.java
│ ├── Dessert.java
│ ├── POS.java
│ ├── LoginFrame.java
│ ├── ManagmentFrame.java
│
├── lib/
│ ├── miglayout-core-11.4.2.jar
│ ├── miglayout-swing-11.4.2.jar
│
├── .classpath
└── README.md
```

---

##  System Design Overview

- **Menu (Base Class)**  
  Handles shared attributes like name and price.

- **Drink / Dessert (Inheritance)**  
  Extend Menu with specific behaviors:
  - Drinks include hot/cold state handling
  - Desserts are simple menu items

- **POS (Main System)**  
  - Handles UI
  - Manages cart and total calculation
  - Controls menu display

- **Admin System**
  - `LoginFrame` → Authentication
  - `ManagmentFrame` → Menu control (CRUD operations)

---
### Database Schema

| Table Name | Purpose |
|------------|---------|
| `admin` | Stores admin login information |
| `drinks` | Stores drink menu items and prices |
| `desserts` | Stores dessert menu items and prices |
| `orders` | Stores customer order totals |
| `order_items` | Stores individual items inside each order |

---

##  Technologies Used

- **Java (JDK 21)**
- **Java Swing (GUI)**
- **MigLayout** (UI Layout Manager)
- **OOP Principles**
  - Inheritance
  - Encapsulation
  - Object Interaction

---

## How to Run

### Requirements
- Java JDK 21+
- MySQL Server
- IntelliJ IDEA / Eclipse
- MigLayout `.jar` files
- MySQL Connector/J `.jar`

---

## Steps

### 1. Clone the project
```bash
git clone https://github.com/your-username/coffee-pos-system.git
cd coffee-pos-system
```

### 2. Create the database
Run in MySQL:

```sql
CREATE DATABASE coffee_shop_pos;
```

Then run the table queries from the Database Schema section.

---

### 3. Check MySQL connection
Make sure MySQL is running on:

```text
localhost:3306
```

Database connection file:

```text
src/pack1/DBcon.java
```

Connection used:

```java
jdbc:mysql://localhost:3306/coffee_shop_pos
```

---

### 4. Add libraries
Add these `.jar` files to the project:

```text
miglayout-core-11.4.2.jar
miglayout-swing-11.4.2.jar
mysql-connector-j-x.x.x.jar
```

---

### 5. Run the project
Run:

```bash
pack1.POS
```


---

## Admin Login

```text
Username: admin
Password: 1234
```
---
##  Team & Roles

| Member Name | Role | Responsibilities |
|------------|------|-----------------|
| Nadim R. S. Naser | Project Leader | Sprint planning, coordination, progress tracking |
| Adnan Hassan Adnan Morahly | Backend Developer | Server logic, database connection |
| Amro Hariri | Frontend Developer | UI design, dashboard pages |
| Mohamed Bassam Aljundi | Tester (QA) | Test cases, bug reporting, validation |
| Mabruka Taher Elmasri | Documentation Specialist | Diagrams, final report |


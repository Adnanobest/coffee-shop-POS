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

###  Requirements
- Java JDK 21 or higher
- IDE (Eclipse, IntelliJ IDEA, or VS Code)
- MigLayout libraries:
  - `miglayout-core-11.4.2.jar`
  - `miglayout-swing-11.4.2.jar`

---

###  Steps to Run the Application
1. **Download the CoffeeShopPOS.jar file**<a href="https://github.com/Adnanobest/coffee-shop-POS/blob/master/coffe%20shop%20POS/CoffeeShopPOS.jar">download</a>

2. **Double click on CoffeeShopPOS.jar to run the application**

**Or follow the following to build the project yourself**

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/coffee-pos-system.git
   cd coffee-pos-system
2. **Open the project in your IDE**
3. **Add external libraries**
Add the following 
- .jar files to your project:
  - -miglayout-core-11.4.2.jar
  - miglayout-swing-11.4.2.jar
- In most IDEs:
  - Right-click project → Add Libraries / Dependencies
4. **Build the project**
- Ensure all files compile without errors
5. **Run the application**
```bash
pack1.POS
```


**Access Admin Panel**
- Click the Admin button in the application
- Login using:
```bash
Username: admin
Password: 1234
```
##  Team & Roles

| Member Name | Role | Responsibilities |
|------------|------|-----------------|
| Nadim R. S. Naser | Project Leader | Sprint planning, coordination, progress tracking |
| Adnan Hassan Adnan Morahly | Backend Developer | Server logic, database connection |
| Amro Hariri | Frontend Developer | UI design, dashboard pages |
| Mohamed Bassam Aljundi | Tester (QA) | Test cases, bug reporting, validation |
| Mabruka Taher Elmasri | Documentation Specialist | Diagrams, final report |


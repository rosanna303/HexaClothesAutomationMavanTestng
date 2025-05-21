# HexaClothesAutomationMavanTestng


## 🚀 Project Overview
This project automates the login and purchase process on the [HexaClothes website](https://hexaclothes.netlify.app/) using:

- **Maven** for build and dependency management  
- **TestNG** as the testing framework  
- **Selenium WebDriver** for browser automation  

The project follows test automation best practices, including cross-browser testing, test parameterization, code reuse, and result validation using assertions and screenshots.

---

## 📂 Project Structure & Execution

- The project follows the standard **Maven** directory structure.
- **TestNG** is used for:
  - Defining and organizing test cases
  - Managing test execution through `testng.xml`
  - Generating test reports
- **Selenium WebDriver** is used for interaction with web elements.
- Screenshots are automatically captured for any **failed test cases**.
- Code reusability is implemented through **inheritance** and **modular design**.

---

## 🌐 Cross-Browser Testing

- **Cross-browser testing** was implemented for the **Login functionality** using Chrome, Firefox, and Edge.
- Other modules (cart, checkout, payment) were executed using **Chrome only**, for efficient resource usage and scalability planning.
- Cross-browser support is achieved via **TestNG parameters** and `@BeforeTest`, allowing dynamic browser launching based on values defined in `testng.xml`.

---

## 🔐 Login Functionality – Testing Scenarios

| Username | Password | Expected Result | Actual Result |
|----------|----------|------------------|----------------|
| ✅ Valid | ✅ Valid | Login Successful | ✅ Passed |
| ✅ Valid | ❌ Invalid | Login Failure | ❌ Failed |
| ❌ Invalid | ✅ Valid | Login Failure | ❌ Failed |
| ❌ Invalid | ❌ Invalid | Login Failure | ❌ Failed |

> ❗ **Issue Identified**:  
> For invalid credential combinations, the system still allowed login instead of displaying an error. This is a **functional defect**.  
> Since login tests were run across **3 browsers**, the 3 negative test scenarios led to **9 failed test cases** (3 × 3).

---

## 🛒 User Navigation & Order Placement

After successful login, the following flow was automated:

1. Redirect to the **Home Page**
2. Navigate to **Women's Wear**
3. Add **2 items** to the cart
4. Proceed to **Checkout**
5. Fill in **Shipping & Payment** details
6. Click **Place Order**

---

## 🧪 Order Placement Testing – Issue Identified

> 🔍 **Expected Behavior**:  
> After placing the order, a confirmation message like `"Successfully submitted your order"` should appear.

> ❌ **Actual Behavior**:  
> The app **redirects to the home page** without confirmation.

- To confirm this issue, a home page element was verified using an assertion.
- If redirection happens instead of showing a success message, the test **fails intentionally**.
- This added **1 more failed test case**, confirming the defect.

---

## 📊 Test Summary

| Category | Count |
|----------|-------|
| ✅ Passed Test Cases | 4 |
| ❌ Failed Test Cases | 10 |

### Breakdown:
- **Passed**
  - `validUsernameValidPassword` test → executed on 3 browsers = 3 passes
  - `validCredential` test in CartAndPlaceOrder → 1 pass
- **Failed**
  - 9 tests from invalid login scenarios across 3 browsers
  - 1 test from order placement scenario

---

## 📌 Final Notes

This project reflects:
- A real-world end-to-end automation flow
- Cross-browser compatibility checks
- Identification and documentation of application defects
- Use of Java + Selenium + TestNG + Maven following best practices

---

## 👤 Author
Rosanna Jose Arikadan  
[GitHub Profile](https://github.com/rosanna303/) | 
[LinkedIn](https://linkedin.com/in/rosanna-jose/)


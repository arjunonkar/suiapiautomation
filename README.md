# 🧪 UI Automation Framework

![CI Status](https://github.com/arjunonkar/suiapiautomation/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-blue)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Allure](https://img.shields.io/badge/Reporting-Allure-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📌 Project Overview

This project is a **production-ready UI Automation Framework** built using:

- Java
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- Maven
- Allure Reporting
- GitHub Actions (CI/CD)

It follows **Page Object Model (POM)** architecture and supports automated CI execution with live hosted reports.

---

## 🌍 Live Test Report

👉 **Allure Report:**  
https://arjunonkar.github.io/suiapiautomation/

---

## 🏗 Architecture Design

This framework follows:

- Page Object Model (POM)
- Base Test Layer
- Driver Factory Pattern
- Explicit Wait Strategy
- CI/CD Deployment Flow

## 🧩 Framework Architecture Diagram

![Architecture Diagram](https://raw.githubusercontent.com/arjunonkar/suiapiautomation/main/docs/images/architecture.png)

### 🔄 Execution Flow

Feature File
↓
Step Definitions
↓
Page Classes
↓
WebDriver
↓
Application Under Test

---

## 📁 Project Structure

suiapiautomation
│
├── src/test/java
│ ├── stepdefinitions
│ ├── pages
│ ├── runners
│
├── src/test/resources
│ ├── features
│
├── .github/workflows
│ └── ci.yml
│
├── pom.xml
└── README.md


---

## ⚙️ Key Features

- ✅ Page Object Model (POM)
- ✅ Explicit Wait Strategy
- ✅ Screenshot on Failure
- ✅ Headless Execution (CI Compatible)
- ✅ Allure Reporting
- ✅ GitHub Pages Auto Deployment
- ✅ Nightly Scheduled Execution
- ✅ Manual Workflow Trigger
- ✅ CI Status Badge

---

## 🚀 How To Run Locally

### Run Tests

```bash
mvn clean test
allure serve target/allure-results

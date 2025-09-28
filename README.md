🏥 Hospital Management System – Android App

A complete Hospital Management System mobile application built in Java (Android Studio).
The app provides patients a digital platform to find doctors, book appointments, view health articles, buy medicines, and check labs details.

🚀 Features

🔐 Authentication

  User Signup & Login with SQLite Database
  
  SharedPreferences for session management

👨‍⚕️ Find Doctor

  Browse doctors by specialization (General Physician, Dentist, Cardiologist, etc.)
  
  Doctor details page

🧪 Lab Tests

  View lab tests like Blood Test, X-Ray, CBC, Diabetes Test, etc.
  
  Labs data fetched from Firebase Realtime Database

💊 Medicine Store

  Browse medicines with price, description & quantity controls
  
  Add to Cart, update quantity, and remove items
  
  Place order with delivery details

🧾 Order System

  Order summary page with medicine list & total bill
  
  Firebase stores orders with delivery info
  
  Generate & Download Order Slip as image

📰 Health Articles

  Integrated with GNews API via Retrofit
  
  Fetch & display daily health-related articles

🎨 UI/UX

  Custom splash screen with gradient background
  
  Custom toasts for feedback

🛠️ Tech Stack

Language: Java

Framework: Android SDK (Material3)

Database:

SQLite (Login/Signup)

Firebase Realtime Database (Lab & Orders)

API: Retrofit + GNews API for health news

Storage: Local image saving for Order Slips

Tools: Android Studio, Gradle

<img width="422" height="881" alt="Screenshot (68)" src="https://github.com/user-attachments/assets/92c2f5b6-ddb1-4575-ab88-59414b13124e" />
<img width="428" height="878" alt="Screenshot (69)" src="https://github.com/user-attachments/assets/798445e7-8f09-4bda-b426-af4055220ef1" />
<img width="432" height="878" alt="Screenshot (70)" src="https://github.com/user-attachments/assets/e201e812-2659-4b56-b3d8-f10ce162f84e" />
<img width="428" height="871" alt="Screenshot (71)" src="https://github.com/user-attachments/assets/cd83ba56-3bda-493e-a880-a594bd366820" />
<img width="413" height="881" alt="Screenshot (72)" src="https://github.com/user-attachments/assets/f1acd767-849a-44bd-b6e6-137b863b3aaf" />
<img width="422" height="875" alt="Screenshot (74)" src="https://github.com/user-attachments/assets/7e842999-efef-4553-a825-d18df95176ad" />
<img width="423" height="879" alt="Screenshot (75)" src="https://github.com/user-attachments/assets/cb851cdb-465d-43c5-bedf-658adf9329e9" />
<img width="423" height="882" alt="Screenshot (76)" src="https://github.com/user-attachments/assets/a118e692-efcb-44fa-a0c8-26d8fd6c4afc" />
<img width="425" height="877" alt="Screenshot (77)" src="https://github.com/user-attachments/assets/d60e3c95-3e44-4525-ab4d-56ab72b4775d" />
<img width="423" height="876" alt="Screenshot (78)" src="https://github.com/user-attachments/assets/2ebb098c-c532-42a2-bbaa-a88bab9e6698" />
<img width="426" height="879" alt="Screenshot (79)" src="https://github.com/user-attachments/assets/fb9212a7-265f-40d9-a154-6c97bdc81051" />
<img width="432" height="887" alt="Screenshot (80)" src="https://github.com/user-attachments/assets/31e74e82-af8e-4156-b5ed-e3b3cb390900" />
<img width="421" height="881" alt="Screenshot (81)" src="https://github.com/user-attachments/assets/e5f43586-c839-4f37-9bdf-689908ff9c36" />
<img width="425" height="885" alt="Screenshot (82)" src="https://github.com/user-attachments/assets/e3847b52-10f2-4912-87b3-b737d6155afd" />
<img width="430" height="882" alt="Screenshot (83)" src="https://github.com/user-attachments/assets/3d20f0da-c307-4a98-9af3-a2fcf1b7ba8c" />
<img width="421" height="881" alt="Screenshot (84)" src="https://github.com/user-attachments/assets/b9c3aeea-c909-40f0-9ec7-a5614d859eac" />

Project Structure
📦 HospitalManagementSystem
 ┣ 📂 app/src/main/java/com/academics/hospitalmanagementsystem
 ┃ ┣ 📜 LoginActivity.java
 ┃ ┣ 📜 SignupActivity.java
 ┃ ┣ 📜 HomeActivity.java
 ┃ ┣ 📜 FindDoctorActivity.java
 ┃ ┣ 📜 DoctorDetailsActivity.java
 ┃ ┣ 📜 LabTestActivity.java
 ┃ ┣ 📜 LabTestDetailActivity.java
 ┃ ┣ 📜 HealthArticleActivity.java
 ┃ ┣ 📜 BuyMedicineActivity.java
 ┃ ┣ 📜 CartViewActivity.java
 ┃ ┣ 📜 OrderFormActivity.java
 ┃ ┣ 📜 OrderSlipActivity.java
 ┃ ┣ 📜 adapters (MedicineAdapter, CartAdapter, etc.)
 ┃ ┣ 📜 utils (ToastHelper.java, etc.)
 ┣ 📂 res/layout  (XML UI files)
 ┣ 📂 res/drawable (icons, gradients, images)
 ┣ 📂 res/values   (themes, strings, colors)


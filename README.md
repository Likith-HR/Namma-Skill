# Namma-Skill – Vocational Training Opportunity Tracker

**Namma-Skill** is a professional Android application developed to bridge the information gap between government vocational training centers and rural youth. The platform centralizes course discovery, simplifies the application process through an "Interest Ping" mechanism, and provides real-time location-based center tracking.

## 🚀 Key Features

### 1. Course Finder & Filtering
*   **Multi-Category Browsing**: Specialized filters for trades including **Electrician, Sewing, Coding, and Mobile Repair**.
*   **Smart Filtering**: Ability to filter programs by duration (**Short-term vs. Long-term**) to fit the user's schedule.
*   **Detailed Insights**: Each course card highlights critical details like **Eligibility** (e.g., 10th Pass) and a **Job Guarantee** badge.

### 2. Intelligent "Interest Ping" System
*   **One-Click Application**: Replaces complex forms with a simple "I am interested" flow.
*   **Automated Candidate Summary**: Instantly generates a professional summary for the user upon submission.
*   **Lead Generation**: User contact details are synced in real-time to Firebase Firestore for immediate callback coordination by trainers.

### 3. "Complete" Center Map (GPS & Search)
*   **Real-time GPS Detection**: Automatically identifies the user's current district to show the nearest training facilities.
*   **Manual Search**: Allows users to manually type towns or districts to find centers across the region.
*   **Direct Communication**: Integrated **"Call Center"** and **"Locate"** buttons to initiate phone calls or open Google Maps navigation instantly.

### 4. Smart Notifications
*   **Topic-Based Alerts**: Users are automatically subscribed to Firebase Cloud Messaging (FCM) topics based on their interest pings.
*   **Batch Updates**: System-ready to push alerts for new batch openings or enrollment deadlines in the user's favorite trades.

### 5. Localized Success Stories
*   **Community Trust**: Features simulated success stories of local candidates with photo-based evidence to motivate new users.

## 🛠️ Tech Stack

*   **Language**: 100% Kotlin
*   **UI Framework**: Material Design 3 with ViewBinding for robust layout management.
*   **Navigation**: Jetpack Navigation Component for a seamless single-activity architecture.
*   **Database**: Firebase Firestore for real-time, offline-capable data synchronization.
*   **Cloud Messaging**: Firebase FCM for targeted push notifications.
*   **Location Services**: Google Play Services Location API & Geocoding for district detection.

## 📱 Application Flow
1.  **Interests**: First-time users select their vocational preferences.
2.  **Discovery**: Browse courses pulling from both a local demo set and live Firebase data.
3.  **Engagement**: Tap "I am interested" to view full curriculum and eligibility details.
4.  **Submission**: Submit an "Interest Ping" to register for a trainer callback.
5.  **Confirmation**: View the auto-generated **Candidate Summary**.
6.  **Navigation**: Use the Center Map to find and contact physical ITIs/centers.

## 🔧 Setup & Installation
1.  Clone the repository: `git clone https://github.com/Likith-HR/Namma-Skill.git`
2.  Place your `google-services.json` file in the `app/` directory.
3.  Enable **Firestore** and **Cloud Messaging** in your Firebase Console.
4.  Build the project using **Gradle 8.5** and **Android Studio Giraffe+**.

---
*This project was developed as a core component of the Internship Evaluation Portfolio.*

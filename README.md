# Namma-Skill – Vocational Training Opportunity Tracker

**Namma-Skill** is a professional Android application developed to bridge the information gap between government vocational training centers and rural youth. The platform centralizes course discovery, simplifies the engagement process through an "Interest Ping" mechanism, and provides real-time location-based center tracking.

## 🚀 Key Features

### 1. Course Finder & Filtering
*   **Trade-Based Discovery**: Specialized filters for vocational trades including **Electrician, Sewing, Coding, and Mobile Repair**.
*   **Flexible Duration**: Ability to filter programs by duration (**Short-term vs. Long-term**) to accommodate various schedules.
*   **Comprehensive Details**: Each course highlights critical information such as **Eligibility** (e.g., 10th Pass) and a prominent **Job Guarantee** badge for verified programs.

### 2. Intelligent "Interest Ping" System
*   **Streamlined Application**: Replaces complex application forms with a simple "I am interested" flow to reduce barriers for rural users.
*   **Automated Candidate Summary**: Instantly generates a professional profile summary upon submission, providing immediate confirmation.
*   **Real-time Lead Sync**: User contact details are synchronized in real-time to **Firebase Firestore**, enabling training centers to perform immediate callbacks.

### 3. Comprehensive Center Map
*   **GPS-Powered Detection**: Leverages Google Location Services to automatically identify the user's current district and filter for the nearest training facilities.
*   **Manual Search Integration**: A responsive search bar allows users to manually explore centers in other districts or towns.
*   **Direct Connectivity**: Includes **"Call Center"** buttons to initiate phone contact with trainers and **"Locate"** buttons for one-tap Google Maps navigation.

### 4. Localized Success Stories
*   **Community Trust**: Showcases simulated success stories of local candidates with photo-based evidence to build motivation and trust within the rural community.

## 🛠️ Tech Stack

*   **Language**: Kotlin
*   **Architecture**: Single-Activity Architecture using **Jetpack Navigation**, ViewBinding, and Material Design 3.
*   **Backend**: **Firebase Firestore** for dynamic, real-time data synchronization and persistent local caching.
*   **Location Services**: **Google Play Services Location API** and Geocoding for accurate district detection.

## 📱 User Flow
1.  **Discovery**: Browse the course catalog with real-time updates from Firebase.
2.  **Details**: Review trade curriculum, eligibility criteria, and job placement assurance.
3.  **Engagement**: Tap "I am interested" to open the Interest Ping form.
4.  **Submission**: Provide basic profile details to receive a trainer callback.
5.  **Summary**: Review the auto-generated **Candidate Summary** confirmation.
6.  **Navigation**: Locate physical centers using GPS or manual search and contact them directly.

## 🔧 Installation
1.  Clone the repository: `git clone https://github.com/Likith-HR/Namma-Skill.git`
2.  Ensure a valid `google-services.json` file is present in the `app/` directory.
3.  Build using **Android Studio Giraffe (or newer)** with **Gradle 8.5**.

---
*Developed as a core component of the Internship Evaluation Portfolio.*

# Notifications-R 🚀
### Mastering the Art of System Communication on Android

![Kotlin](https://img.shields.io/badge/kotlin-v2.2.10-blue.svg?logo=kotlin)
![Compose](https://img.shields.io/badge/Jetpack_Compose-2026.02.01-green.svg?logo=jetpackcompose)
![API Level](https://img.shields.io/badge/Target_SDK-37-orange.svg)
![Hilt](https://img.shields.io/badge/DI-Hilt-yellow.svg)

**Notifications-R** is more than just a demo app; it is a specialized laboratory for Android developers to explore and master the evolving landscape of user notifications. From legacy foundation to the bleeding edge of **Android 16 Live Updates**, this project provides a clean, documented implementation of every critical notification pattern used in world-class applications today.

---

## 🌟 The Essence of Notifications in Android Development

In the modern mobile ecosystem, notifications are the **pulse** of your application. They are the primary bridge between your app's background logic and the user's immediate attention. 

Understanding notifications is critical because:
*   **User Retention**: Well-timed, relevant notifications keep users coming back.
*   **Contextual Awareness**: They provide value without forcing the user to open the app (e.g., progress tracking, quick replies).
*   **System Trust**: Properly managing Notification Channels and permissions is the key to respecting user privacy and battery life.
*   **Future-Proofing**: With Android 16, notifications are becoming "Live Updates," changing how we think about persistent user journeys like navigation and delivery.

---

## 🛠 Feature Showreel

### 1. The Foundation: Simple & Clickable ✉️
Standard messaging patterns that bridge the gap between "something happened" and "take me to the content."
*   **Essence**: Information density and deep-linking.
*   **Implementation**: `NotificationCompat.Builder` & `PendingIntent` for `NotificationDetailActivity`.

### 2. Interaction: Actionable Notifications ⚡
Empower users to act instantly from the lock screen or shade.
*   **Essence**: Reducing friction. Why open the app to "Mark as Read" when you can do it in one tap?
*   **Implementation**: Action buttons tied to a `BroadcastReceiver` for zero-UI logic execution.

### 3. Presence: Sticky Media Style 🎵
Persistent, ongoing communication for background processes.
*   **Essence**: Reliability. Ensures the system doesn't kill critical background tasks and keeps the user informed of persistent states.
*   **Implementation**: `.setOngoing(true)` + `MediaStyle` + Audio feedback via `ToneGenerator`.

### 4. The Future: Progress-Centric Live Updates 📍
The pinnacle of Android 16 notification design.
*   **Essence**: Dynamic storytelling. Instead of a static alert, you provide a living, breathing progress tracker for "Journeys" (Rideshare, Delivery, Navigation).
*   **Implementation**: The new `Notification.ProgressStyle` (API 36+), simulating real-time updates via background threading and `Segment` logic.

---

## 🏗 Technical Architecture

This project is built using a modern, scalable architecture:
*   **Jetpack Compose**: Declarative UI for a clean, modern dashboard.
*   **Dagger Hilt**: Standardized dependency injection for robust component management.
*   **Accompanist Permissions**: Seamless handling of Android 13+ runtime notification permissions.
*   **Version Catalog (TOML)**: centralized dependency management for a clean, reproducible build environment.
*   **Clean Separation**: Notification logic is encapsulated within `NotificationUtility`, ensuring your Activities stay focused on the UI.

---

## 🚀 Getting Started

### Prerequisites
*   **Android Studio Ladybug** (or later)
*   **Android 16 (API 37)** SDK installed
*   An Emulator/Device running **Android 16+** to see the `ProgressStyle` features.

### Installation
1.  Clone the repository:
    ```bash
    git clone https://github.com/Fleur41/Notifications-R.git
    ```
2.  Open the project in Android Studio.
3.  Sync Gradle and run the `:app` module.

---

## 💡 Learning Outcomes
By exploring this codebase, you will learn:
1.  How to handle **Runtime Permissions** for notifications elegantly.
2.  How to design **Notification Channels** for different priority levels.
3.  How to simulate **Real-time Data Updates** in the notification shade.
4.  How to bridge **BroadcastReceivers** with notification actions for background processing.

---

## 📝 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
**Crafted with precision by [Fleur41](https://github.com/Fleur41)** 💻

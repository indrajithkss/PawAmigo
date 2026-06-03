# 🐾 PawAmigo – Pet Care & Social Companion App

A modern Android application that helps pet owners manage their pets, discover nearby pet lovers, schedule playdates, and engage with a social community.

Built using **Kotlin, Firebase Authentication, Cloud Firestore, Glide, and Android Jetpack Components**.

---

## ✨ Key Features

### 🏠 Smart Pet Dashboard

* Daily pet care tracking
* Feed reminders
* Walk reminders
* Vet visit notifications
* Motivational pet quotes
* Smooth animated UI

### 🐕 Pet Registration

Register and manage pets with:

* Pet name
* Breed
* Age
* Pet profile photo

Data is securely stored in Firebase Firestore.

---

### 👤 Owner Registration

Store owner information including:

* Name
* Email
* Contact Number
* Experience Level

Integrated with Firebase Authentication and Firestore.

---

### 🔍 Nearby Pet Owners

Discover nearby pet owners through an interactive interface:

* Pet profile image
* Pet name & breed
* Distance display
* Connect button
* Expandable for future chat functionality

---

### 💬 Community Feed

A social-media-inspired experience for pet lovers.

Features include:

* Create posts
* Share pet photos
* Like posts
* Comment system
* Double-tap like animation
* Instagram-style feed

Core Files:

* CommunityFragment.kt
* PostAdapter.kt
* CreatePostActivity.kt
* CommentsActivity.kt

---

### 🎉 Playdate Scheduling

Create and manage pet meetups.

Features:

* Create playdates
* Browse upcoming playdates
* Custom playdate cards
* Interactive scheduling UI

Core Files:

* Playdate.kt
* PlaydateActivity.kt
* PlaydateAdapter.kt

---

### 🔐 Authentication & Security

* Firebase Authentication
* Email & Password Login
* Persistent sessions
* Secure user management
* Release & Debug SHA support

---

## 📸 Application Screenshots
<img width="334" height="733" alt="Screenshot 2025-11-19 224642" src="https://github.com/user-attachments/assets/ece59191-c0ca-429f-8d2b-9690f84df1bd" />
<img width="336" height="722" alt="Screenshot 2025-11-19 224617" src="https://github.com/user-attachments/assets/860a15d1-3445-4d93-8401-c34aff1fc278" />
<img width="333" height="720" alt="Screenshot 2025-11-19 225149" src="https://github.com/user-attachments/assets/74e3b57a-ce96-46c2-837e-5ca12ede2148" />
<img width="338" height="724" alt="Screenshot 2025-11-19 225326" src="https://github.com/user-attachments/assets/92bc9a53-291f-4767-8d2a-a3cc60e1d2dd" />
<img width="341" height="634" alt="Screenshot 2025-11-19 225427" src="https://github.com/user-attachments/assets/f8f285ae-d1fa-48e7-b57a-71ea7f148156" />
<img width="339" height="640" alt="Screenshot 2025-11-19 225447" src="https://github.com/user-attachments/assets/93dd632e-088d-4987-8ec8-4fc60be28e4e" />
<img width="341" height="723" alt="Screenshot 2025-11-19 225503" src="https://github.com/user-attachments/assets/0baf2c4c-5f4b-4ece-9cfe-867fa7c82cef" />

---

## 🏗 Project Architecture

```text
PawAmigo
│
├── Authentication
├── Owner Management
├── Pet Management
├── Community Feed
├── Playdate Scheduling
└── Firebase Backend
```

---

## 🛠 Technology Stack

### Android Development

* Kotlin
* Android Studio
* XML Layouts
* Material Design Components

### Firebase

* Firebase Authentication
* Cloud Firestore

### Libraries

* Glide
* Android Jetpack Components

### Build Tools

* Gradle (Kotlin DSL)

---

## 📂 Project Structure

```bash
app/
├── manifests/
│   └── AndroidManifest.xml
│
├── java/com/app/pawamigo/
│   ├── community/
│   ├── fragments/
│   ├── ui/
│   ├── model/
│   └── utils/
│
├── res/
│   ├── anim/
│   ├── drawable/
│   ├── layout/
│   ├── menu/
│   ├── values/
│   └── xml/
│
├── build.gradle.kts
├── settings.gradle.kts
├── libs.versions.toml
└── proguard-rules.pro
```

---

## 🔥 Firebase Configuration

Place your Firebase configuration file here:

```bash
app/google-services.json
```

### Enabled Firebase Services

✅ Firebase Authentication

✅ Cloud Firestore

❌ Firebase Storage (Not Used)

Images are stored locally and loaded using Glide.

---

## 🔑 App Signing

Supported Configurations:

* Debug Signing
* Release Signing

Required Keys:

```text
debug.keystore
my-release-key.jks
```

Make sure SHA-1 and SHA-256 fingerprints are added in Firebase Console.

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/indrajithkss/PawAmigo.git
```

### Open in Android Studio

Recommended:

* Android Studio Hedgehog
* Android Studio Iguana
* Newer versions

### Configure Firebase

Add:

```bash
app/google-services.json
```

### Sync Project

```text
File → Sync Project with Gradle Files
```

### Run Application

```text
Select Device → Run ▶
```

---

## 🎯 Future Enhancements

* Real-time messaging
* Friend requests
* Pet adoption marketplace
* Veterinary appointment booking
* Push notifications
* Firebase Storage integration
* Location-based recommendations

---

## 🤝 Contributing

Contributions, improvements, and feature suggestions are welcome.

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

---

## 👨‍💻 Developers

### Indrajith K S

GitHub:
https://github.com/indrajithkss

### Ihsana S Ibrahim

Co-Developer
---


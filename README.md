PawAmigo – Pet Care & Social Companion App

PawAmigo is a modern Android application built to simplify pet care and connect pet owners through a social and interactive platform.
The app offers daily pet management tools, community interaction, playdate scheduling, and owner discovery — all wrapped in a smooth, animated, user-friendly interface.
Developed using Kotlin, Firebase Authentication, Firestore, Glide, and Android Jetpack components.

 Features
1. Home Dashboard
Displays daily pet care activities
Built-in reminders (Feed, Walk, Vet Visit)
Motivational pet quotes
Clean card-based UI with animations

🔍 2. Nearby Pet Owners
Discover nearby pet owners with:
Pet name
Breed
Distance
Profile image
“Connect” button (UI — extendable for future messaging)

💬 3. Community Feed
A full social feed experience including:
Create posts (text + locally loaded images, no Firebase Storage required)
Instagram-like feed UI
Double-tap like animation
Comment system
Smooth image loading using Glide
Key Files:
CommunityFragment.kt, PostAdapter.kt, CreatePostActivity.kt, CommentsActivity.kt, Utils.kt

4. Pet Registration
Collects and stores:
Pet name
Breed
Age
Photo (local image)
Saved in Firebase Firestore
File: PetRegistrationActivity.kt
👤 5. Owner Registration
Captures:
Owner name
Email
Contact number
Experience level
Integrated with Firebase Authentication + Firestore
File: OwnerRegistrationActivity.kt

🔐 6. Login / Signup
Email & Password authentication
Firebase release + debug SHA-1 keys fully configured
Secure session management
File: LoginActivity.kt

🎉 7. Playdate Management
Create and view playdates
Playdate listing with custom adapter
Interactive UI for scheduling
Files: Playdate.kt, PlaydateActivity.kt, PlaydateAdapter.kt

🧱 Project Structure
app/
 ├── manifests/
 │    └── AndroidManifest.xml
 │
 ├── java/com/app/pawamigo/
 │    ├── community/
 │    ├── fragments/
 │    ├── ui/
 │    │     └── onboarding/
 │    └── model + utils files
 │
 ├── res/
 │    ├── anim/
 │    ├── color/
 │    ├── drawable/
 │    ├── layout/
 │    ├── menu/
 │    ├── mipmap/
 │    ├── values/
 │    └── xml/
 │
 ├── build.gradle.kts        (Module)
 ├── build.gradle.kts        (Project)
 ├── settings.gradle.kts
 ├── proguard-rules.pro
 └── libs.versions.toml

🔧 Tech Stack
Category	  Technology
Language	  Kotlin
UI	XML,    Material Components
Backend	    Firebase Authentication, Firebase Firestore
Images	    Glide
Build	Gradle (Kotlin DSL)
Architecture	Activity + Fragment-based
Animations	Custom fade, double-tap like
🗄 Firebase Integration
✔ Enabled Services

Firebase Authentication
Cloud Firestore
❌ Not Used
Firebase Storage (images handled locally)

📌 Required
Place your Firebase config here:
app/google-services.json
🔑 App Signing
Both Debug and Release signing configurations are supported.
Debug key: debug.keystore
Release key: my-release-key.jks
Ensure SHA-1 and SHA-256 fingerprints from the release key are added in your Firebase project settings.

▶ How to Run the Project
1. Clone the Repository
git clone https://github.com/indrajithkss/PawAmigo.git

2. Open in Android Studio
Recommended versions: Hedgehog, Iguana, or above.

3. Add Firebase Config
Place your config file in:

app/google-services.json

4. Sync Project
Go to: File → Sync Project with Gradle Files

5. Run the App
Select a device → Click Run ▶

📸 Screenshots
<img width="334" height="733" alt="Screenshot 2025-11-19 224642" src="https://github.com/user-attachments/assets/ece59191-c0ca-429f-8d2b-9690f84df1bd" />
<img width="336" height="722" alt="Screenshot 2025-11-19 224617" src="https://github.com/user-attachments/assets/860a15d1-3445-4d93-8401-c34aff1fc278" />
<img width="333" height="720" alt="Screenshot 2025-11-19 225149" src="https://github.com/user-attachments/assets/74e3b57a-ce96-46c2-837e-5ca12ede2148" />
<img width="338" height="724" alt="Screenshot 2025-11-19 225326" src="https://github.com/user-attachments/assets/92bc9a53-291f-4767-8d2a-a3cc60e1d2dd" />
<img width="341" height="634" alt="Screenshot 2025-11-19 225427" src="https://github.com/user-attachments/assets/f8f285ae-d1fa-48e7-b57a-71ea7f148156" />
<img width="339" height="640" alt="Screenshot 2025-11-19 225447" src="https://github.com/user-attachments/assets/93dd632e-088d-4987-8ec8-4fc60be28e4e" />
<img width="341" height="723" alt="Screenshot 2025-11-19 225503" src="https://github.com/user-attachments/assets/0baf2c4c-5f4b-4ece-9cfe-867fa7c82cef" />


🤝 Contributing

Contributions, improvements, and feature additions are welcome!
Feel free to open issues or submit pull requests.

👨‍💻 Developer

Indrajith KS
Ihsana S Ibrahim
GitHub: https://github.com/indrajithkss

📄 License

MIT License — free for personal, academic, and open-source usage.

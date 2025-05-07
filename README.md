# 🌤️ Klima App

A simple weather app built with **Kotlin**, **Jetpack Compose**, and **OpenWeatherMap** to provide weather updates.

---

## 📱 Minimum Requirements

- **Android Version:** Android 12 (API level 31) and above

## ✨ Features
- User authentication with login and signup
- Current weather data using OpenWeatherMap API
- Weather list screen to view previously fetched and saved weather data
- Location-based weather fetching

---

## 🧰 Tools & Technologies
### 🧑‍💻 Programming Language
- Kotlin

### 🛠 Development Environment
- Android Studio **Meerkat | 2024.3.1 Patch 2**

### 🧱 Architecture & Patterns
- MVVM (Model-View-ViewModel)
- Clean Architecture

### 🎨 UI & UX
- Jetpack Compose
- Material3
- Compose Navigation

### 🗄 Data Handling
- Room Database
- Preference DataStore
- Retrofit & OkHttp
- OpenWeatherMap API

### 🧪 Testing
- JUnit
- MockK

### ⚙️ Dependency Injection
- Koin

---

## 🔑 API Key Setup

To use the OpenWeatherMap API, follow these steps:

1. **Get your API key:**
    - Sign up at [OpenWeatherMap](https://openweathermap.org/api) and generate an API key.

2. **Create an `apikey.properties` file:**
    - In the **root directory** of the project (same level as `gradle.properties`), create a file named:

      ```
      apikey.properties
      ```
3. **Add your API key to the file:**

   ```properties
   OPEN_WEATHER_API_KEY="your_api_key"

## 🏃 Build & Run
1. Open the project in Android Studio.
2. Let Gradle sync.
3. Click Run to build and launch the app.
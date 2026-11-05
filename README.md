# Android Jetpack Compose Job Board App

A production-ready Android application built in Kotlin using **Jetpack Compose (Material Design 3)** and **Clean Architecture + MVVM**.

---

## 🏗 Architecture Overview

The app strictly adheres to **Clean Architecture** and **Unidirectional Data Flow (UDF)**:

```
┌────────────────────────────────────────────────────────┐
│ UI Layer (Jetpack Compose + Material 3)                │
│  - JobListScreen (SearchBar, FilterChips, LazyColumn)  │
│  - JobDetailScreen (Sticky Apply Bar, Details)         │
│  - ApplyBottomSheet (Real-time Regex/Length Validation)│
└───────────────────────────▲────────────────────────────┘
                            │ StateFlow / UiState
┌───────────────────────────┴────────────────────────────┐
│ ViewModel (MVVM)                                       │
│  - JobListViewModel (Search debounce, StateFlow)       │
│  - JobDetailViewModel (Job detail by ID, Bookmarks)    │
│  - ApplyViewModel (Live validation, Submissions)       │
└───────────────────────────▲────────────────────────────┘
                            │ Coroutines & Result<T>
┌───────────────────────────┴────────────────────────────┐
│ Domain Layer (Pure Kotlin - Zero Android Framework)    │
│  - Models: Job, JobApplication, LocationType, JobType  │
│  - Use Cases: GetJobsUseCase, SubmitApplicationUseCase │
│  - Repository Interface: JobRepository                 │
└───────────────────────────▲────────────────────────────┘
                            │
┌───────────────────────────┴────────────────────────────┐
│ Data Layer                                             │
│  - Local Data Source: JobMockDataSource (In-memory)    │
│  - Remote Data Source: JobApiService (Retrofit 2.11)   │
│  - Repository Impl: JobRepositoryImpl                  │
└────────────────────────────────────────────────────────┘
```

---

## 🚀 How to Set Up in Android Studio

### Prerequisites
1. **Android Studio**: Ladybug (2024.2.1+) or Iguana / Jellyfish.
2. **JDK**: Version 17 or 21 (bundled with Android Studio).
3. **Android SDK**: API 35 (Android 15) with minimum SDK 24 (Android 7.0).

### Step-by-Step Instructions

1. **Download or Clone the Project:**
   - Click the **"Download Android Project (.zip)"** button in this tool.
   - Extract the ZIP file into a folder on your computer (e.g. `~/AndroidProjects/JobBoardApp`).

2. **Open in Android Studio:**
   - Launch Android Studio.
   - Select **File -> Open...** and browse to the extracted `JobBoardApp` directory.
   - Wait for the initial **Gradle Sync** to finish. Android Studio will download all dependencies declared in `libs.versions.toml`.

3. **Select or Create an Emulator:**
   - Open **Device Manager** in Android Studio.
   - Create a virtual device (recommended: **Pixel 8** or **Pixel 9 Pro** with Android 15 / API 35 system image).

4. **Run the App:**
   - Select the `app` run configuration.
   - Click the green **Run (▶)** button or press `Shift + F10`.

---

## 🧪 Testing Validation & Features

1. **Job List Screen:**
   - Type in the Search Bar (e.g., "Stripe", "Lead", "Compose") — the list updates with 300ms debounce.
   - Click the Filter Chips (Remote, On-site, Hybrid, Full-time, Contract) to filter positions.
   - If no jobs match, a Material 3 Empty State appears with a "Clear All Filters" action.

2. **Job Detail Screen:**
   - Click on any job card to navigate with type-safe routing.
   - Review Responsibilities and Requirements bullet points.
   - Click the sticky **"Apply Now"** button anchored to the bottom navigation bar.

3. **Application Form Screen (Bottom Sheet):**
   - **Full Name:** Type fewer than 3 characters to see the inline error message.
   - **Email:** Type an invalid email to trigger the regex validation check.
   - **Phone:** Requires at least 10 numeric digits.
   - **Submit Button:** Remains disabled until all 3 required fields pass validation.
   - On submission, an animated confirmation screen confirms receipt!

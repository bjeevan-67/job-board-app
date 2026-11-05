# 💼 Job Board Android App

A modern, responsive Android application for searching and applying to job openings. Built with **Kotlin** and **Jetpack Compose**, following **Clean Architecture** and **MVVM** principles.

---

## ✨ Features

- **Job Listings Feed:** Browse available job openings with key details including role title, company name, location, job type tag, and salary range.
- **Dynamic Filtering & Search:** Search jobs by keywords and filter listings by **Location** (*Remote, On-site, Hybrid*) or **Job Type** (*Full-time, Part-time, Contract*).
- **Detailed Job View:** Full breakdown of job descriptions, required qualifications, and company responsibilities with a sticky bottom bar to apply.
- **Interactive Application Form:** Built-in application form with real-time field validation for:
  - Full Name (length check)
  - Email Address (regex validation)
  - Phone Number (digit length check)
- **Inline Validation & Feedback:** Form disables submit button until input criteria are met and provides clear visual feedback upon submission.

---

## 🛠️ Tech Stack & Architecture

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material Design 3
- **Architecture:** MVVM (Model-View-ViewModel) + Repository Pattern
- **Async & State Management:** Kotlin Coroutines & `StateFlow` / `SharedFlow`
- **Navigation:** Jetpack Navigation Compose with type-safe routing
- **Data Source:** Scalable Repository supporting mock local data and HTTP REST API integration (Retrofit/Ktor ready)

---

## 📁 Project Structure

```text
app/src/main/java/com/example/jobboard/
├── data/              # Data sources, Data Models, Repositories
│   ├── model/         # Job & Application data entities
│   ├── repository/    # JobRepository interface & implementations
│   └── source/        # Mock & Remote API data sources
├── domain/            # Business logic & Use cases (optional)
├── ui/                # UI Layer (Jetpack Compose)
│   ├── components/    # Reusable UI widgets (JobCard, FilterChip, etc.)
│   ├── screens/       # JobListScreen, JobDetailScreen, ApplyFormScreen
│   ├── theme/         # Color, Type, Shape, Theme definitions
│   └── viewmodel/     # State holders (JobViewModel, ApplyViewModel)
└── navigation/        # Compose NavGraph definitions

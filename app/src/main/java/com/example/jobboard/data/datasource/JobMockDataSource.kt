package com.example.jobboard.data.datasource

import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType
import kotlinx.coroutines.delay

class JobMockDataSource {
    // Thread-safe in-memory cache simulating room database or local storage
    private val jobsList = mutableListOf(
        Job(
            id = "job-101",
            title = "Senior Android Engineer (Jetpack Compose)",
            company = "Stripe Mobile Core",
            companyInitial = "S",
            locationType = LocationType.REMOTE,
            locationCity = "San Francisco, CA (Remote)",
            jobType = JobType.FULL_TIME,
            salary = "$165,000 - $210,000 / yr",
            postedDate = "2 days ago",
            experienceLevel = "Senior (5+ yrs)",
            department = "Mobile Core",
            description = "Join our Mobile Platform team to architect resilient payment SDKs and consumer-facing financial checkout flows. You will spearhead our migration to 100% Jetpack Compose and modern reactive architecture.",
            responsibilities = listOf(
                "Architect and implement responsive, high-performance UI components using Jetpack Compose and Material 3.",
                "Design clean, decoupled domain logic following Clean Architecture and unidirectional data flow (MVVM).",
                "Optimize coroutine execution scopes, StateFlow lifecycles, and cold flow pipelines for sub-16ms frame rendering.",
                "Mentor junior and mid-level Android engineers through rigorous code reviews and architecture design docs."
            ),
            requirements = listOf(
                "5+ years of production Android development with modern Kotlin.",
                "Deep practical expertise with Jetpack Compose, Compose compiler stability, and Canvas/Custom Layouts.",
                "Thorough understanding of Kotlin Coroutines, Flow, StateFlow, and lifecycle-aware collection.",
                "Experience writing robust unit tests (MockK, Turbine, JUnit5) and UI automation tests with ComposeTestRule."
            ),
            isBookmarked = true
        ),
        Job(
            id = "job-102",
            title = "Lead Mobile Architect",
            company = "Revolut FinTech",
            companyInitial = "R",
            locationType = LocationType.HYBRID,
            locationCity = "New York, NY",
            jobType = JobType.FULL_TIME,
            salary = "$190,000 - $240,000 / yr",
            postedDate = "Just now",
            experienceLevel = "Staff / Lead (7+ yrs)",
            department = "Core Architecture",
            description = "Revolut is scaling to 50M+ global customers. We need a visionary Mobile Architect to establish cross-team engineering standards, modularization boundaries, and high-throughput real-time trading interfaces.",
            responsibilities = listOf(
                "Define multi-module project boundaries and dependency graph health across 60+ feature modules.",
                "Standardize network caching layers, Room database migrations, and offline-first data sync engines.",
                "Lead CI/CD optimization reducing build times with Gradle build cache and Kotlin incremental compilation.",
                "Collaborate directly with Product, Security, and Compliance stakeholders."
            ),
            requirements = listOf(
                "7+ years of building large-scale Android applications with 1M+ MAU.",
                "Proven track record designing Clean Architecture systems with multi-module Gradle setups.",
                "Expertise in dependency injection (Dagger/Hilt or Koin) and architectural governance.",
                "Passion for developer experience, build tooling, and static analysis (Detekt, ktlint, ArchUnit)."
            )
        ),
        Job(
            id = "job-103",
            title = "Android Jetpack Compose Specialist",
            company = "Cash App Ecosystem",
            companyInitial = "C",
            locationType = LocationType.REMOTE,
            locationCity = "Seattle, WA (Remote)",
            jobType = JobType.CONTRACT,
            salary = "$90 - $125 / hr",
            postedDate = "1 day ago",
            experienceLevel = "Senior (4+ yrs)",
            department = "Design Systems",
            description = "We are seeking a design-obsessed Android developer to implement intricate micro-interactions, custom gesture choreography, and design system components in Jetpack Compose.",
            responsibilities = listOf(
                "Translate Figma design specifications into pixel-perfect Compose composables with delightful spring animations.",
                "Build design token parsers and accessible Material 3 component libraries for multiple squad teams.",
                "Profile UI jank using Android Studio CPU Profiler and Macrobenchmark.",
                "Document composable APIs with interactive previews and Compose Preview Parameter Providers."
            ),
            requirements = listOf(
                "4+ years developing polished consumer Android applications.",
                "Exceptional command of Jetpack Compose animation APIs: updateTransition, Animatable, LookaheadScope.",
                "High sensitivity to typography, touch target hitboxes, accessibility semantics, and fluid gestures.",
                "Strong communication and autonomous execution skills in remote team settings."
            )
        ),
        Job(
            id = "job-104",
            title = "Mobile SDK Engineer",
            company = "Datadog Telemetry",
            companyInitial = "D",
            locationType = LocationType.ON_SITE,
            locationCity = "Boston, MA",
            jobType = JobType.FULL_TIME,
            salary = "$150,000 - $185,000 / yr",
            postedDate = "3 days ago",
            experienceLevel = "Mid-Senior (3+ yrs)",
            department = "Observability SDK",
            description = "Build the next-generation lightweight monitoring and crash-reporting SDK used by thousands of top Android apps. Focus on zero-allocation performance, background battery optimization, and low-overhead tracking.",
            responsibilities = listOf(
                "Develop ultra-low overhead bytecode instrumentation and automatic lifecycle tracking for Android SDKs.",
                "Manage NDK crash captures and ANR tracing using Android platform APIs.",
                "Ensure binary size footprint remains strictly under 150KB.",
                "Maintain 99.999% crash-free stability across Android API levels 24 through 35."
            ),
            requirements = listOf(
                "Strong Kotlin and Java fundamentals with understanding of Android internals and Dalvik/ART runtime.",
                "Experience with binary size reduction, Proguard/R8 optimization, and API backward compatibility.",
                "Understanding of WorkManager, JobScheduler, and background power management constraints."
            )
        ),
        Job(
            id = "job-105",
            title = "Junior Android Developer",
            company = "Duolingo Learning",
            companyInitial = "D",
            locationType = LocationType.HYBRID,
            locationCity = "Pittsburgh, PA",
            jobType = JobType.PART_TIME,
            salary = "$45 - $60 / hr",
            postedDate = "4 days ago",
            experienceLevel = "Junior (1-2 yrs)",
            department = "Gamification & Lessons",
            description = "Support our interactive lesson squads by writing modern Kotlin Compose UI tests, implementing streak badges, and contributing to accessibility features for language learners worldwide.",
            responsibilities = listOf(
                "Collaborate with senior engineers to implement animated reward screens in Compose.",
                "Fix bug tickets, improve talkback accessibility labels, and maintain unit tests with JUnit 5.",
                "Participate in daily standups, sprint retrospectives, and engineering book clubs."
            ),
            requirements = listOf(
                "BS in Computer Science or equivalent practical mobile portfolio.",
                "Solid understanding of Kotlin syntax, object-oriented concepts, and basic Coroutines.",
                "Familiarity with Git, Android Studio, and basic Compose layouts (Row, Column, Box, LazyColumn)."
            )
        ),
        Job(
            id = "job-106",
            title = "Freelance Android UI/UX Engineer",
            company = "Apex Design Collective",
            companyInitial = "A",
            locationType = LocationType.REMOTE,
            locationCity = "Global Remote",
            jobType = JobType.CONTRACT,
            salary = "$80 - $110 / hr",
            postedDate = "5 days ago",
            experienceLevel = "Mid (3+ yrs)",
            department = "Client Solutions",
            description = "Deliver bespoke, high-impact prototypes and proof-of-concept Android apps for international clients. Rapid prototyping and Material 3 design mastery required.",
            responsibilities = listOf(
                "Turn product wireframes into functioning Jetpack Compose applications within tight sprint cycles.",
                "Integrate RESTful endpoints using Retrofit and OkHttp with mock interceptors.",
                "Package deliverable source code with clean architecture and reproducible Gradle wrappers."
            ),
            requirements = listOf(
                "Demonstrated portfolio of published Google Play Store applications or GitHub open source work.",
                "Proficiency with Retrofit, Kotlinx Serialization, and Navigation Compose.",
                "Strong client empathy and reliable communication."
            )
        )
    )

    suspend fun getJobs(): List<Job> {
        delay(400) // Simulate fast local/cached query response
        return jobsList.toList()
    }

    suspend fun getJobById(id: String): Job? {
        delay(200)
        return jobsList.find { it.id == id }
    }

    suspend fun toggleBookmark(jobId: String): Boolean {
        delay(150)
        val index = jobsList.indexOfFirst { it.id == jobId }
        if (index != -1) {
            val current = jobsList[index]
            val updated = current.copy(isBookmarked = !current.isBookmarked)
            jobsList[index] = updated
            return updated.isBookmarked
        }
        return false
    }
}

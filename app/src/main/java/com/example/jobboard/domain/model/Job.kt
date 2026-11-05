package com.example.jobboard.domain.model

enum class LocationType(val displayName: String) {
    ALL("All Locations"),
    REMOTE("Remote"),
    ON_SITE("On-site"),
    HYBRID("Hybrid")
}

enum class JobType(val displayName: String) {
    ALL("All Types"),
    FULL_TIME("Full-time"),
    PART_TIME("Part-time"),
    CONTRACT("Contract")
}

data class Job(
    val id: String,
    val title: String,
    val company: String,
    val companyInitial: String,
    val locationType: LocationType,
    val locationCity: String,
    val jobType: JobType,
    val salary: String,
    val postedDate: String,
    val experienceLevel: String,
    val department: String,
    val description: String,
    val responsibilities: List<String>,
    val requirements: List<String>,
    val isBookmarked: Boolean = false
)

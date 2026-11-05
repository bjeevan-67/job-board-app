package com.example.jobboard.data.model

import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("company") val company: String,
    @SerialName("location_type") val locationType: String,
    @SerialName("location_city") val locationCity: String,
    @SerialName("job_type") val jobType: String,
    @SerialName("salary") val salary: String,
    @SerialName("posted_date") val postedDate: String,
    @SerialName("experience_level") val experienceLevel: String,
    @SerialName("department") val department: String,
    @SerialName("description") val description: String,
    @SerialName("responsibilities") val responsibilities: List<String>,
    @SerialName("requirements") val requirements: List<String>
) {
    fun toDomain(): Job {
        return Job(
            id = id,
            title = title,
            company = company,
            companyInitial = company.firstOrNull()?.uppercase() ?: "J",
            locationType = when (locationType.lowercase()) {
                "remote" -> LocationType.REMOTE
                "on-site", "onsite" -> LocationType.ON_SITE
                "hybrid" -> LocationType.HYBRID
                else -> LocationType.REMOTE
            },
            locationCity = locationCity,
            jobType = when (jobType.lowercase()) {
                "full-time", "fulltime" -> JobType.FULL_TIME
                "part-time", "parttime" -> JobType.PART_TIME
                "contract" -> JobType.CONTRACT
                else -> JobType.FULL_TIME
            },
            salary = salary,
            postedDate = postedDate,
            experienceLevel = experienceLevel,
            department = department,
            description = description,
            responsibilities = responsibilities,
            requirements = requirements,
            isBookmarked = false
        )
    }
}

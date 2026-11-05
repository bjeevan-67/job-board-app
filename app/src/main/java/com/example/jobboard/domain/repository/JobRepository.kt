package com.example.jobboard.domain.repository

import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.model.JobApplication
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    /**
     * Emits a reactive stream of jobs filtered by search query, location, and job type.
     */
    fun getJobs(
        query: String = "",
        locationType: LocationType = LocationType.ALL,
        jobType: JobType = JobType.ALL
    ): Flow<Result<List<Job>>>

    /**
     * Retrieves a single job by its unique identifier.
     */
    suspend fun getJobById(id: String): Result<Job>

    /**
     * Toggles bookmark state for local persistence.
     */
    suspend fun toggleBookmark(jobId: String): Result<Boolean>

    /**
     * Submits an application for a specific job posting.
     */
    suspend fun submitApplication(application: JobApplication): Result<Unit>
}

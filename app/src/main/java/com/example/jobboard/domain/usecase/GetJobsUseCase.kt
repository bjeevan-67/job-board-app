package com.example.jobboard.domain.usecase

import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType
import com.example.jobboard.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow

class GetJobsUseCase(
    private val repository: JobRepository
) {
    operator fun invoke(
        query: String = "",
        locationType: LocationType = LocationType.ALL,
        jobType: JobType = JobType.ALL
    ): Flow<Result<List<Job>>> {
        return repository.getJobs(query, locationType, jobType)
    }
}

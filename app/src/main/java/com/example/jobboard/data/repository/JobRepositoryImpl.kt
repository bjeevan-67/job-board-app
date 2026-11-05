package com.example.jobboard.data.repository

import com.example.jobboard.data.datasource.JobApiService
import com.example.jobboard.data.datasource.JobMockDataSource
import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.model.JobApplication
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType
import com.example.jobboard.domain.repository.JobRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class JobRepositoryImpl(
    private val localDataSource: JobMockDataSource = JobMockDataSource(),
    private val remoteApiService: JobApiService? = null,
    private val useRemoteDataSource: Boolean = false,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : JobRepository {

    override fun getJobs(
        query: String,
        locationType: LocationType,
        jobType: JobType
    ): Flow<Result<List<Job>>> = flow {
        try {
            val allJobs: List<Job> = if (useRemoteDataSource && remoteApiService != null) {
                // Fetch from remote API and map DTOs to Domain
                remoteApiService.fetchJobs(
                    query = query.ifBlank { null },
                    locationType = if (locationType != LocationType.ALL) locationType.name else null,
                    jobType = if (jobType != JobType.ALL) jobType.name else null
                ).map { it.toDomain() }
            } else {
                // Fetch from pre-populated local data source
                localDataSource.getJobs()
            }

            // Apply unified client filtering
            val filtered = allJobs.filter { job ->
                val matchesQuery = query.isBlank() ||
                        job.title.contains(query, ignoreCase = true) ||
                        job.company.contains(query, ignoreCase = true) ||
                        job.locationCity.contains(query, ignoreCase = true) ||
                        job.department.contains(query, ignoreCase = true)

                val matchesLocation = locationType == LocationType.ALL || job.locationType == locationType
                val matchesJobType = jobType == JobType.ALL || job.jobType == jobType

                matchesQuery && matchesLocation && matchesJobType
            }

            emit(Result.success(filtered))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(ioDispatcher)

    override suspend fun getJobById(id: String): Result<Job> = withContext(ioDispatcher) {
        try {
            if (useRemoteDataSource && remoteApiService != null) {
                val dto = remoteApiService.fetchJobDetail(id)
                Result.success(dto.toDomain())
            } else {
                val job = localDataSource.getJobById(id)
                if (job != null) {
                    Result.success(job)
                } else {
                    Result.failure(NoSuchElementException("Job with ID '$id' was not found."))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun toggleBookmark(jobId: String): Result<Boolean> = withContext(ioDispatcher) {
        try {
            val newState = localDataSource.toggleBookmark(jobId)
            Result.success(newState)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun submitApplication(application: JobApplication): Result<Unit> = withContext(ioDispatcher) {
        try {
            // Simulate network latency for submission
            delay(800)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

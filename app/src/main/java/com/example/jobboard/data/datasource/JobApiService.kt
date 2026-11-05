package com.example.jobboard.data.datasource

import com.example.jobboard.data.model.JobDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JobApiService {
    @GET("api/v1/jobs")
    suspend fun fetchJobs(
        @Query("search") query: String? = null,
        @Query("location_type") locationType: String? = null,
        @Query("job_type") jobType: String? = null
    ): List<JobDto>

    @GET("api/v1/jobs/{id}")
    suspend fun fetchJobDetail(
        @Path("id") jobId: String
    ): JobDto
}

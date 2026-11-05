package com.example.jobboard.domain.usecase

import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.repository.JobRepository

class GetJobByIdUseCase(
    private val repository: JobRepository
) {
    suspend operator fun invoke(id: String): Result<Job> {
        return repository.getJobById(id)
    }
}

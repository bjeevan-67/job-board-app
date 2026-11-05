package com.example.jobboard.domain.usecase

import com.example.jobboard.domain.model.JobApplication
import com.example.jobboard.domain.repository.JobRepository

class SubmitApplicationUseCase(
    private val repository: JobRepository
) {
    suspend operator fun invoke(application: JobApplication): Result<Unit> {
        // Domain validation guarantee
        if (application.fullName.trim().length < 3) {
            return Result.failure(IllegalArgumentException("Full Name must have at least 3 characters"))
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(application.email).matches()) {
            return Result.failure(IllegalArgumentException("Invalid email format"))
        }
        val digitsOnly = application.phoneNumber.filter { it.isDigit() }
        if (digitsOnly.length < 10) {
            return Result.failure(IllegalArgumentException("Phone number must have at least 10 digits"))
        }

        return repository.submitApplication(application)
    }
}

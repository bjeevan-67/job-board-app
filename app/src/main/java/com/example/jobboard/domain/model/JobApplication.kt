package com.example.jobboard.domain.model

data class JobApplication(
    val jobId: String,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val portfolioUrl: String,
    val coverLetter: String,
    val submittedAt: Long = System.currentTimeMillis()
)

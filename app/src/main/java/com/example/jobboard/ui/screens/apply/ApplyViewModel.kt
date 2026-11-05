package com.example.jobboard.ui.screens.apply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.domain.model.JobApplication
import com.example.jobboard.domain.repository.JobRepository
import com.example.jobboard.domain.usecase.SubmitApplicationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ApplyViewModel(
    private val jobId: String,
    private val repository: JobRepository,
    private val submitUseCase: SubmitApplicationUseCase = SubmitApplicationUseCase(repository)
) : ViewModel() {

    private val _formState = MutableStateFlow(ApplyFormUiState())
    val formState: StateFlow<ApplyFormUiState> = _formState.asStateFlow()

    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun onFullNameChange(name: String) {
        val error = when {
            name.isBlank() -> "Full name is required"
            name.trim().length < 3 -> "Name must be at least 3 characters"
            else -> null
        }
        _formState.update { it.copy(fullName = name, fullNameError = error) }
    }

    fun onEmailChange(email: String) {
        val error = when {
            email.isBlank() -> "Email address is required"
            !emailRegex.matches(email.trim()) -> "Please enter a valid email address (e.g., name@domain.com)"
            else -> null
        }
        _formState.update { it.copy(email = email, emailError = error) }
    }

    fun onPhoneChange(phone: String) {
        val digits = phone.filter { it.isDigit() }
        val error = when {
            phone.isBlank() -> "Phone number is required"
            digits.length < 10 -> "Phone number must contain at least 10 digits"
            else -> null
        }
        _formState.update { it.copy(phone = phone, phoneError = error) }
    }

    fun onPortfolioChange(url: String) {
        _formState.update { it.copy(portfolioUrl = url) }
    }

    fun onCoverLetterChange(notes: String) {
        _formState.update { it.copy(coverLetter = notes) }
    }

    fun submitApplication() {
        val state = _formState.value
        if (!state.isSubmitEnabled) return

        viewModelScope.launch {
            _formState.update { it.copy(isSubmitting = true, submissionError = null) }
            val application = JobApplication(
                jobId = jobId,
                fullName = state.fullName.trim(),
                email = state.email.trim(),
                phoneNumber = state.phone.trim(),
                portfolioUrl = state.portfolioUrl.trim(),
                coverLetter = state.coverLetter.trim()
            )

            submitUseCase(application).fold(
                onSuccess = {
                    _formState.update { it.copy(isSubmitting = false, isSuccess = true) }
                },
                onFailure = { error ->
                    _formState.update {
                        it.copy(isSubmitting = false, submissionError = error.localizedMessage ?: "Submission failed")
                    }
                }
            )
        }
    }
}

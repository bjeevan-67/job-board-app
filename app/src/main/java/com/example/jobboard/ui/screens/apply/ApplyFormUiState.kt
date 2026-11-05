package com.example.jobboard.ui.screens.apply

data class ApplyFormUiState(
    val fullName: String = "",
    val fullNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val phone: String = "",
    val phoneError: String? = null,
    val portfolioUrl: String = "",
    val coverLetter: String = "",
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val submissionError: String? = null
) {
    /**
     * Submit button is enabled ONLY when all required fields have text,
     * have been validated, and no inline error messages exist.
     */
    val isSubmitEnabled: Boolean
        get() = fullName.trim().length >= 3 &&
                fullNameError == null &&
                email.isNotBlank() &&
                emailError == null &&
                phone.isNotBlank() &&
                phoneError == null &&
                !isSubmitting
}

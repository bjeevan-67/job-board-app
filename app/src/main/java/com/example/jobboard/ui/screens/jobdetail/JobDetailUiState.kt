package com.example.jobboard.ui.screens.jobdetail

import com.example.jobboard.domain.model.Job

sealed interface JobDetailUiState {
    data object Loading : JobDetailUiState
    data class Success(val job: Job, val isBookmarked: Boolean) : JobDetailUiState
    data class Error(val message: String) : JobDetailUiState
}

package com.example.jobboard.ui.screens.jobdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.domain.repository.JobRepository
import com.example.jobboard.domain.usecase.GetJobByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobDetailViewModel(
    private val jobId: String,
    private val repository: JobRepository,
    private val getJobByIdUseCase: GetJobByIdUseCase = GetJobByIdUseCase(repository)
) : ViewModel() {

    private val _uiState = MutableStateFlow<JobDetailUiState>(JobDetailUiState.Loading)
    val uiState: StateFlow<JobDetailUiState> = _uiState.asStateFlow()

    init {
        loadJobDetail()
    }

    fun loadJobDetail() {
        viewModelScope.launch {
            _uiState.value = JobDetailUiState.Loading
            getJobByIdUseCase(jobId).fold(
                onSuccess = { job ->
                    _uiState.value = JobDetailUiState.Success(job = job, isBookmarked = job.isBookmarked)
                },
                onFailure = { error ->
                    _uiState.value = JobDetailUiState.Error(error.localizedMessage ?: "Job not found")
                }
            )
        }
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is JobDetailUiState.Success) {
                repository.toggleBookmark(jobId).onSuccess { newState ->
                    _uiState.value = currentState.copy(isBookmarked = newState)
                }
            }
        }
    }
}

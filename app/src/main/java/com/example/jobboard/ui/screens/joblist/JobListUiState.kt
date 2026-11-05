package com.example.jobboard.ui.screens.joblist

import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType

sealed interface JobListUiState {
    data object Loading : JobListUiState

    data class Success(
        val jobs: List<Job>,
        val searchQuery: String = "",
        val selectedLocation: LocationType = LocationType.ALL,
        val selectedJobType: JobType = JobType.ALL
    ) : JobListUiState

    data class Empty(
        val searchQuery: String = "",
        val selectedLocation: LocationType = LocationType.ALL,
        val selectedJobType: JobType = JobType.ALL
    ) : JobListUiState

    data class Error(
        val message: String
    ) : JobListUiState
}

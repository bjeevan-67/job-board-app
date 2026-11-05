package com.example.jobboard.ui.screens.joblist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType
import com.example.jobboard.domain.usecase.GetJobsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class JobListViewModel(
    private val getJobsUseCase: GetJobsUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedLocation = MutableStateFlow(LocationType.ALL)
    val selectedLocation: StateFlow<LocationType> = _selectedLocation.asStateFlow()

    private val _selectedJobType = MutableStateFlow(JobType.ALL)
    val selectedJobType: StateFlow<JobType> = _selectedJobType.asStateFlow()

    // Combined parameters flow
    private val filterParams = combine(
        _searchQuery.debounce(300),
        _selectedLocation,
        _selectedJobType
    ) { query, location, jobType ->
        Triple(query, location, jobType)
    }

    val uiState: StateFlow<JobListUiState> = filterParams
        .flatMapLatest { (query, location, jobType) ->
            getJobsUseCase(query, location, jobType)
                .map { result ->
                    result.fold(
                        onSuccess = { jobs ->
                            if (jobs.isEmpty()) {
                                JobListUiState.Empty(query, location, jobType)
                            } else {
                                JobListUiState.Success(
                                    jobs = jobs,
                                    searchQuery = query,
                                    selectedLocation = location,
                                    selectedJobType = jobType
                                )
                            }
                        },
                        onFailure = { error ->
                            JobListUiState.Error(error.localizedMessage ?: "Failed to load jobs")
                        }
                    )
                }
                .onStart { emit(JobListUiState.Loading) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = JobListUiState.Loading
        )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onLocationSelected(location: LocationType) {
        _selectedLocation.value = location
    }

    fun onJobTypeSelected(jobType: JobType) {
        _selectedJobType.value = jobType
    }

    fun resetFilters() {
        _searchQuery.value = ""
        _selectedLocation.value = LocationType.ALL
        _selectedJobType.value = JobType.ALL
    }
}

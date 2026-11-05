package com.example.jobboard.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object JobList : Screen

    @Serializable
    data class JobDetail(val jobId: String) : Screen
}

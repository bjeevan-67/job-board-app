package com.example.jobboard.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.jobboard.domain.repository.JobRepository
import com.example.jobboard.ui.screens.jobdetail.JobDetailScreen
import com.example.jobboard.ui.screens.joblist.JobListScreen

@Composable
fun JobNavGraph(
    navController: NavHostController,
    repository: JobRepository,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.JobList,
        modifier = modifier
    ) {
        composable<Screen.JobList>(
            enterTransition = { fadeIn(tween(250)) },
            exitTransition = { fadeOut(tween(250)) }
        ) {
            JobListScreen(
                repository = repository,
                onJobClick = { jobId ->
                    navController.navigate(Screen.JobDetail(jobId = jobId))
                }
            )
        }

        composable<Screen.JobDetail>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300)
                ) + fadeIn(tween(200))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300)
                ) + fadeOut(tween(200))
            }
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<Screen.JobDetail>()
            JobDetailScreen(
                jobId = route.jobId,
                repository = repository,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

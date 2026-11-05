package com.example.jobboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jobboard.data.repository.JobRepositoryImpl
import com.example.jobboard.ui.navigation.JobNavGraph
import com.example.jobboard.ui.theme.JobBoardTheme

class MainActivity : ComponentActivity() {

    // Dependency Injection (In a full Hilt setup, inject via @Inject)
    private val repository = JobRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JobBoardTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    JobNavGraph(
                        navController = navController,
                        repository = repository
                    )
                }
            }
        }
    }
}

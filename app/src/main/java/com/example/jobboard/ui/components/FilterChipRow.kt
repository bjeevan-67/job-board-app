package com.example.jobboard.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jobboard.domain.model.JobType
import com.example.jobboard.domain.model.LocationType

@Composable
fun FilterChipRow(
    selectedLocation: LocationType,
    onLocationSelect: (LocationType) -> Unit,
    selectedJobType: JobType,
    onJobTypeSelect: (JobType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Location Filters Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LocationType.entries.forEach { loc ->
                val selected = loc == selectedLocation
                FilterChip(
                    selected = selected,
                    onClick = { onLocationSelect(loc) },
                    label = { Text(loc.displayName) },
                    leadingIcon = if (selected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    } else null
                )
            }
        }

        // Job Type Filters Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            JobType.entries.forEach { type ->
                val selected = type == selectedJobType
                FilterChip(
                    selected = selected,
                    onClick = { onJobTypeSelect(type) },
                    label = { Text(type.displayName) },
                    leadingIcon = if (selected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    } else null
                )
            }
        }
    }
}

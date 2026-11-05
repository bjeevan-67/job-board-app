package com.example.jobboard.ui.screens.apply

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jobboard.domain.model.Job
import com.example.jobboard.domain.repository.JobRepository
import com.example.jobboard.ui.components.ValidationTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyBottomSheet(
    job: Job,
    repository: JobRepository,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ApplyViewModel = viewModel(
        key = "apply_${job.id}",
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return ApplyViewModel(jobId = job.id, repository = repository) as T
            }
        }
    )
) {
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = modifier
    ) {
        if (formState.isSuccess) {
            // Success Confirmation Dialog / Screen View
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = androidx.compose.foundation.shape.CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Application Submitted!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your application for ${job.title} at ${job.company} was successfully received. The recruiting team will contact you at ${formState.email}.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Done")
                }
            }
        } else {
            // Application Form Fields
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .navigationBarsPadding()
            ) {
                Text(
                    text = "Apply for Position",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${job.title} · ${job.company}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Full Name Field (Min 3 chars)
                ValidationTextField(
                    value = formState.fullName,
                    onValueChange = viewModel::onFullNameChange,
                    label = "Full Name *",
                    placeholder = "e.g. Alex Henderson",
                    leadingIcon = Icons.Outlined.Person,
                    errorMessage = formState.fullNameError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Email Address Field (Regex Check)
                ValidationTextField(
                    value = formState.email,
                    onValueChange = viewModel::onEmailChange,
                    label = "Email Address *",
                    placeholder = "alex@example.com",
                    leadingIcon = Icons.Outlined.Email,
                    errorMessage = formState.emailError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Phone Number Field (10+ digits check)
                ValidationTextField(
                    value = formState.phone,
                    onValueChange = viewModel::onPhoneChange,
                    label = "Phone Number *",
                    placeholder = "+1 (555) 019-2834",
                    leadingIcon = Icons.Outlined.Phone,
                    errorMessage = formState.phoneError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Portfolio / LinkedIn Link (Optional)
                ValidationTextField(
                    value = formState.portfolioUrl,
                    onValueChange = viewModel::onPortfolioChange,
                    label = "Portfolio / GitHub / LinkedIn",
                    placeholder = "https://github.com/alex-henderson",
                    leadingIcon = Icons.Outlined.Link,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Cover Letter / Notes
                OutlinedTextField(
                    value = formState.coverLetter,
                    onValueChange = viewModel::onCoverLetterChange,
                    label = { Text("Cover Letter / Notes (Optional)") },
                    placeholder = { Text("Share highlights of your Android Compose experience...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 5
                )

                if (formState.submissionError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = formState.submissionError ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Submit Button (Disabled until validation passes)
                Button(
                    onClick = viewModel::submitApplication,
                    enabled = formState.isSubmitEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (formState.isSubmitting) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = if (formState.isSubmitEnabled) "Submit Application" else "Complete Required Fields",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

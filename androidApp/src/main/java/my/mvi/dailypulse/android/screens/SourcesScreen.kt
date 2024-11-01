package my.mvi.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import my.mvi.dailypulse.articles.presentation.SourcesState
import my.mvi.dailypulse.sources.domain.Source
import my.mvi.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SourcesScreen(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit,
    viewModel: SourcesViewModel = koinViewModel<SourcesViewModel>(),
) {
    val sourcesState = viewModel.sourcesState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AppBar(onUpButtonClick)

        when {
            sourcesState.value.loading -> Loader()
            sourcesState.value.sources.isNotEmpty() -> SourcesList(state = sourcesState)
            sourcesState.value.error != null -> ErrorMessage(message = sourcesState.value.error!!)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Sources") },
        navigationIcon = {
            IconButton(onClick = { onUpButtonClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back Button",
                )
            }
        }
    )
}

@Composable
private fun SourcesList(
    modifier: Modifier = Modifier,
    state: State<SourcesState>
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
       items(items = state.value.sources) {
           SourceItem(
               modifier = Modifier.padding(8.dp),
               source = it
           )
       }
    }
}

@Composable
private fun SourceItem(
    modifier: Modifier = Modifier,
    source: Source
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = source.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            textAlign = TextAlign.Start
        )
        Text(
            text = source.desc,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp),
            color = MaterialTheme.colorScheme.secondary,
            maxLines = 3,
            textAlign = TextAlign.Start
        )
        Text(
            text = source.origin,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp),
            color = MaterialTheme.colorScheme.tertiary,
            maxLines = 1,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}
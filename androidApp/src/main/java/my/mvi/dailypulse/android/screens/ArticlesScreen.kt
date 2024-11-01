package my.mvi.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import my.mvi.dailypulse.articles.domain.Article
import my.mvi.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    onSourcesButtonClick: () -> Unit,
    onAboutButtonClick: () -> Unit,
    viewModel: ArticlesViewModel = koinViewModel<ArticlesViewModel>(),
) {
    val articlesState = viewModel.articlesState.collectAsState()

    Column {

        AppBar(
            modifier = modifier,
            onSourcesButtonClick = { onSourcesButtonClick() },
            onAboutButtonClick = { onAboutButtonClick() }
        )

        if (articlesState.value.error != null) ErrorMessage(message = articlesState.value.error!!)
        if (articlesState.value.loading) Loader()
        else ArticlesListView(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    onSourcesButtonClick: () -> Unit = {},
    onAboutButtonClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = onSourcesButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Sources Button",
                )
            }
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button",
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticlesListView(viewModel: ArticlesViewModel) {

    PullToRefreshBox(
        isRefreshing = viewModel.articlesState.value.loading,
        onRefresh = { viewModel.getArticles(forceFetch = true) }

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(viewModel.articlesState.value.articles) { article ->
                ArticleItemView(article = article)
            }
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBox(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    state: PullToRefreshState = rememberPullToRefreshState(),
    contentAlignment: Alignment = Alignment.TopStart,
    indicator: @Composable BoxScope.() -> Unit = {
        Indicator(
            modifier = Modifier
                .align(Alignment.Center),
            isRefreshing = isRefreshing,
            state = state
        )
    },
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier.pullToRefresh(
            state = state,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ),
        contentAlignment = contentAlignment
    ) {
        content()
        indicator()
    }
}
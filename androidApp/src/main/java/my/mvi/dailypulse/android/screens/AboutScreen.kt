package my.mvi.dailypulse.android.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import my.mvi.dailypulse.Platform

/** Created by Sergei Kolinichenko on 07.09.2024 at 12:14 (GMT+3) **/

@Composable
fun AboutScreen(
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.fillMaxSize()
  ) {
    ToolBar()
    ContentView()
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ToolBar(
  modifier: Modifier = Modifier,
) {
  TopAppBar(
    modifier = modifier,
    title = { Text(text = "About Device") }
  )
}

@Composable
private fun ContentView(
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
  ) {
    items(makeItems()) { RowView(row = it) }
  }
}

@Composable
private fun RowView(
  modifier: Modifier = Modifier,
  row: Pair<String, String>,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(8.dp),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text(
      text = row.first,
      style = MaterialTheme.typography.bodySmall,
      color = Color.Gray
    )
    Text(
      text = row.second,
      style = MaterialTheme.typography.bodyLarge
    )
    HorizontalDivider()
  }
}

private fun makeItems() = listOf(
  Pair("Operation System", "${Platform().osName} ${Platform().osVersion}"),
  Pair("Device", Platform().deviceModel),
  Pair("Density", Platform().density.toString())
)
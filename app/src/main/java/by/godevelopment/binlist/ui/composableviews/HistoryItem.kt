package by.godevelopment.binlist.ui.composableviews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import by.godevelopment.binlist.ui.theme.*

@Composable
fun HistoryItem(
    inputText: String? = null,
    onClickItem: (String) -> Unit
) {
    inputText?.let {
        Text(
            modifier = Modifier
                .clickable(onClick = { onClickItem(it) })
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .padding(
                    horizontal = mainPadding,
                    vertical = microPadding
                ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            text = it
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryItemPreview() {
    BinlistTheme {
        HistoryItem(
            "1234 5678",
            onClickItem = {
                println(it)
            }
        )
    }
}

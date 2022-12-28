package by.godevelopment.binlist.ui.composableviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import by.godevelopment.binlist.R
import by.godevelopment.binlist.ui.theme.BinlistTheme
import by.godevelopment.binlist.ui.theme.mainPadding
import by.godevelopment.binlist.ui.theme.microPadding

@Composable
fun PairTextItem(
    labelText: String,
    valueText: String? = null,
    onClickItem: ((String) -> Unit)?
) {

    Row(
        modifier = if (onClickItem != null && valueText != null) Modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onClickItem(valueText) }
            )
        else Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(
                    horizontal = mainPadding,
                    vertical = microPadding
                ),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1,
            text = labelText
        )

        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(
                    horizontal = mainPadding,
                    vertical = microPadding
                ),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.body1,
            text = valueText ?: stringResource(R.string.ui_text_null_value),
            color = if (valueText != null) MaterialTheme.colors.onBackground
            else MaterialTheme.colors.error,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PairTextItemPreview() {
    BinlistTheme {
        PairTextItem(
            "www.jyskebank.dk", // "+4589893300",
            "+4589893300", // "www.jyskebank.dk",
            onClickItem = {
                println(it)
            }
        )
    }
}
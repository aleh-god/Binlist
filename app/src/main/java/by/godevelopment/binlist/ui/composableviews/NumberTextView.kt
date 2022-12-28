package by.godevelopment.binlist.ui.composableviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import by.godevelopment.binlist.R
import by.godevelopment.binlist.ui.theme.microPadding
import by.godevelopment.binlist.domain.models.Number
import by.godevelopment.binlist.ui.theme.BinlistTheme

@Composable
fun NumberTextView(
    number: Number
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_length),
            valueText = number.length?.toString(),
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_luhn),
            valueText = when (number.luhn) {
                true -> stringResource(R.string.ui_text_yes)
                false -> stringResource(R.string.ui_text_no)
                null -> null
            },
            onClickItem = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NumberTextPreview() {
    BinlistTheme {
        NumberTextView(
            number = Number()
        )
    }
}
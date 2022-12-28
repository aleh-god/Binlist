package by.godevelopment.binlist.ui.composableviews

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import by.godevelopment.binlist.R
import by.godevelopment.binlist.domain.models.Bank
import by.godevelopment.binlist.domain.models.ClickLabel
import by.godevelopment.binlist.ui.theme.BinlistTheme
import by.godevelopment.binlist.ui.theme.microPadding

@Composable
fun BankTextView(
    bank: Bank,
    onClickItem: (ClickLabel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_name),
            valueText = bank.name,
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_city),
            valueText = bank.city,
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_phone),
            valueText = bank.phone,
            onClickItem = { onClickItem(ClickLabel.PHONE) }
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_url),
            valueText = bank.url,
            onClickItem = { onClickItem(ClickLabel.URL) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BankDefaultPreview() {
    BinlistTheme {
        BankTextView(
            bank = Bank(),
            onClickItem = { label ->
                println("label = $label")
            }
        )
    }
}

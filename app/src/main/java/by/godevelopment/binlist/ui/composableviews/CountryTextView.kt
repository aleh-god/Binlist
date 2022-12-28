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
import by.godevelopment.binlist.domain.models.ClickLabel
import by.godevelopment.binlist.domain.models.Country
import by.godevelopment.binlist.ui.theme.BinlistTheme
import by.godevelopment.binlist.ui.theme.microPadding

@Composable
fun CountryTextView(
    country: Country,
    onClickItem: (ClickLabel) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_country),
            valueText = country.name,
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_currency),
            valueText = country.currency,
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_latitude),
            valueText = country.latitude?.toString(),
            onClickItem = {
                onClickItem(ClickLabel.MAP)
            }
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_longitude),
            valueText = country.longitude?.toString(),
            onClickItem = {
                onClickItem(ClickLabel.MAP)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CountryTextViewPreview() {
    BinlistTheme {
        CountryTextView(
            country = Country(),
            onClickItem = { label ->
                println("label = $label")
            }
        )
    }
}
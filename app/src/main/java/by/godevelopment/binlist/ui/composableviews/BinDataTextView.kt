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
import by.godevelopment.binlist.domain.models.*
import by.godevelopment.binlist.domain.models.Number
import by.godevelopment.binlist.ui.theme.BinlistTheme
import by.godevelopment.binlist.ui.theme.microPadding

@Composable
fun BinDataTextView(
    binDataModel: BinDataModel,
    onClickItem: (ClickLabel) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_brand),
            binDataModel.brand,
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_scheme),
            binDataModel.scheme,
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_type),
            binDataModel.type,
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        PairTextItem(
            labelText = stringResource(R.string.ui_text_label_prepaid),
            valueText = when (binDataModel.prepaid) {
                true -> stringResource(R.string.ui_text_yes)
                false -> stringResource(R.string.ui_text_no)
                null -> null
            },
            onClickItem = null
        )

        Spacer(modifier = Modifier.height(microPadding))

        NumberTextView(number = binDataModel.number ?: Number())

        Spacer(modifier = Modifier.height(microPadding))

        BankTextView(bank = binDataModel.bank ?: Bank(), onClickItem = { label ->
            onClickItem(label)
        })

        Spacer(modifier = Modifier.height(microPadding))

        CountryTextView(country = binDataModel.country ?: Country(), onClickItem = { label ->
            onClickItem(label)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun BinDataTextPreview() {
    BinlistTheme {
        BinDataTextView(
            binDataModel = BinDataModel(),
            onClickItem = { label ->
                println("label = $label")
            }
        )
    }
}
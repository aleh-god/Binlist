package by.godevelopment.binlist.ui.screens.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.godevelopment.binlist.R
import by.godevelopment.binlist.domain.models.ClickLabel
import by.godevelopment.binlist.ui.composableviews.BinDataTextView
import by.godevelopment.binlist.ui.intent.IntentAction

fun startIntentWith(context: Context, intent: Intent) {
        context.startActivity(intent)
}

private fun showMap(context: Context, geoLocation: Uri) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = geoLocation
    }
    startIntentWith(context, intent)
}

private fun dialPhoneNumber(context: Context,phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    startIntentWith(context, intent)
}

private fun openWebPage(context: Context, url: String) {
    val webpage: Uri = Uri.parse("http:$url")
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    startIntentWith(context, intent)
}


@Composable
fun DetailsScreen(
    scaffoldState: ScaffoldState,
    contentPadding: PaddingValues,
    onClickBack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiAction.collect { event ->
            when (event) {
                is DetailsScreenUiAction.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = context.getString(event.message)
                    )
                }
                DetailsScreenUiAction.NavigateBack -> {
                    onClickBack()
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.intentAction
            .collect { intentAction ->
                when (intentAction) {
                    is IntentAction.Call -> dialPhoneNumber(context, intentAction.phoneNumber)
                    is IntentAction.Map -> showMap(context, intentAction.geoLocation)
                    is IntentAction.Web -> openWebPage(context, intentAction.url)
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(contentPadding)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (viewModel.uiState.isProcessing) LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )

        BinDataTextView(binDataModel = viewModel.uiState.binDataModel, onClickItem = { label ->
            viewModel.onEvent(
                when (label) {
                    ClickLabel.PHONE -> DetailsScreenUserEvent.CallIntent
                    ClickLabel.URL -> DetailsScreenUserEvent.WebIntent
                    ClickLabel.MAP -> DetailsScreenUserEvent.MapIntent
                }
            )
        })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    viewModel.onEvent(DetailsScreenUserEvent.BackPressed)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_description_button_back)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.ui_button_text_back))
            }
        }
    }
}

package by.godevelopment.binlist.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.godevelopment.binlist.R
import by.godevelopment.binlist.ui.composableviews.HistoryItem
import by.godevelopment.binlist.ui.theme.microPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState,
    contentPadding: PaddingValues,
    onClickItem: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.uiAction.collect { event ->
            when (event) {
                is HomeScreenUiAction.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = context.getString(event.message)
                    )
                }
                is HomeScreenUiAction.NavigateToDetailWith -> {
                    onClickItem(event.value)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(contentPadding)
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (viewModel.uiState.isProcessing) LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(microPadding))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                value = viewModel.uiState.number,
                onValueChange = {
                    viewModel.onEvent(HomeScreenUserEvent.NumberChanged(it))
                },
                label = {
                    Text(
                        if (viewModel.uiState.hasError) stringResource(R.string.ui_label_error)
                        else stringResource(R.string.ui_label_main)
                    )
                },
                isError = viewModel.uiState.hasError,
                placeholder = {
                    Text(
                        text = stringResource(R.string.ui_placeholder_main)
                    )
                }
            )

            Spacer(modifier = Modifier.width(microPadding))

            IconButton(
                onClick = {
                    viewModel.onEvent(HomeScreenUserEvent.QueryBinDataOnClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.content_description_button_query)
                )
            }
        }

        Spacer(modifier = Modifier.height(microPadding))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        ) {

            items(viewModel.uiState.queries) { query ->
                HistoryItem(
                    inputText = query,
                    onClickItem = {
                        viewModel.onEvent(
                            HomeScreenUserEvent.HistoryItemOnClick(
                                number = it
                            )
                        )
                    }
                )
            }
        }
    }
}

package com.example.financeapp.feature_transaction.presentation.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = stringResource(R.string.menu))
//                },
//                scrollBehavior = scrollBehavior,
//                navigationIcon = {
//                    IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = stringResource(R.string.go_back),
//                        )
//                    }
//                }
//            )
//        }
//    ) { padding ->
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.background)
//                .padding(padding)
//                .padding(horizontal = 16.dp)
//                .verticalScroll(rememberScrollState())
//        ) {
//
//        }
//    }

    val selectedTab = remember { mutableStateOf("Overview") }

    Column {

        TopHistoryBar(
            selectedTab = selectedTab.value,
            onTabSelected = { tab ->
                selectedTab.value = tab
            }
        )
    }
}

@Composable
fun TopHistoryBar(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf("Overview", "Expenses", "Transactions")
    val selectedIndex = tabs.indexOf(selectedTab)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        tabs.forEachIndexed { index, tab ->
            val isTabSelected = tab == selectedTab
            val paddingStart = if (isTabSelected) 0.dp else 8.dp
            val paddingEnd = if (isTabSelected) 0.dp else 8.dp

            val modifier = if (isTabSelected) {
                Modifier
                    .absolutePadding(
                        left = if (index < selectedIndex) paddingStart else 0.dp,
                        right = if (index > selectedIndex) paddingEnd else 0.dp
                    )
                    .weight(1f)
            } else {
                Modifier
            }

            TabButton(
                text = tab,
                isSelected = isTabSelected,
                onClick = { onTabSelected(tab) },
                modifier = modifier
            )
        }
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}


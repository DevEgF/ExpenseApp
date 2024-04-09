package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseManager
import data.repository.ExpenseRepositoryImpl
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpensesViewModel
import ui.screens.ExpensesScreen
import ui.theme.getColorsTheme

@Composable
fun Navigation(navigator: Navigator) {

    val color = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpensesViewModel::class) {
        ExpensesViewModel(ExpenseRepositoryImpl(ExpenseManager))
    }

    NavHost(
        modifier = Modifier.background(color.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            ExpensesScreen(
                uiState = uiState
            ) { expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }

        scene(route = "/addExpenses/{id}") {
            val idFromPath = it.path<Long>("id")
            val isAddExpense = idFromPath?.let {id->
                viewModel.getExpenseWithID(id)
            }

            // ExpenseDetailsScreen
        }
    }
}
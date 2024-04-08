import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import data.ExpenseManager
import data.repository.ExpenseRepositoryImpl
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.ExpensesViewModel
import ui.screens.ExpensesScreen
import ui.theme.AppTheme

@Composable
@Preview
fun App() {
    PreComposeApp {
        val viewModel = viewModel(modelClass = ExpensesViewModel::class) {
            ExpensesViewModel(ExpenseRepositoryImpl(ExpenseManager))
        }

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        AppTheme {
            ExpensesScreen(
                uiState = uiState,
                onExpenseClick = {}
            )
        }
    }
}
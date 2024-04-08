package ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.Expense
import ui.theme.getColorsTheme
import presentation.ExpenseUiState
import ui.component.AllExpensesHeader
import ui.component.ExpenseTotalHeader
import ui.component.ExpensesItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesScreen(uiState: ExpenseUiState, onExpenseClick: (expense: Expense) -> Unit) {
    val colors = getColorsTheme()

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Column(
                modifier = Modifier.background(colors.backgroundColor)
            ) {
                ExpenseTotalHeader(uiState.total)
                AllExpensesHeader()
            }
        }
        items(uiState.expense) { expense ->
            ExpensesItem(expense = expense, onExpenseClick = onExpenseClick)
        }
    }
}
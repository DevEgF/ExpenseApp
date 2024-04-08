package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import data.model.Expense
import data.model.ExpenseCategory
import data.model.ExpenseManager
import ui.ExpensesScreen
import ui.component.AllExpensesHeader
import ui.component.ExpenseTotalHeader
import ui.component.ExpensesItem

@Preview(showBackground = true)
@Composable
fun ExpenseTotalHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ExpenseTotalHeader(total = 1020.9)
    }
}

@Preview(showBackground = true)
@Composable
fun AllExpensesHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        AllExpensesHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensesItemPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        ExpensesItem(expense = ExpenseManager.fakeExpenseList[0], onExpenseClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensesScreenPreview() {
    ExpensesScreen(onExpenseClick = {})
}
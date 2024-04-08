package data.repository

import data.ExpenseManager
import data.model.Expense
import data.model.ExpenseCategory
import domain.repository.ExpenseRepository

class ExpenseRepositoryImpl(
    private val expenseManager: ExpenseManager
): ExpenseRepository {
    override fun getAllExpenses(): List<Expense> {
        return expenseManager.fakeExpenseListResponses
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun getCategory(): List<ExpenseCategory> {
        return expenseManager.getCategories()
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        TODO("Not yet implemented")
    }
}
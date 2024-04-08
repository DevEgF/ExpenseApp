package domain.repository

import data.model.Expense
import data.model.ExpenseCategory

interface ExpenseRepository {
    fun getAllExpenses(): List<Expense>
    fun addExpense(expense: Expense)
    fun editExpense(expense: Expense)
    fun getCategory(): List<ExpenseCategory>
    fun deleteExpense(expense: Expense): List<Expense>
}
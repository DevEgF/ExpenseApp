package presentation

import data.model.Expense
import data.model.ExpenseCategory
import domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class ExpensesViewModel(
    private val expenseRepository: ExpenseRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseUiState())
    val uiState = _uiState.asStateFlow()

    private val allExpense = expenseRepository.getAllExpenses()

    init {
        getAllExpense()
    }

    fun getAllExpense() {
        viewModelScope.launch {
            updateState()
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.addExpense(expense)
            updateState()
        }
    }

    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.addExpense(expense)
            updateState()
        }
    }

    fun getExpenseWithID(id: Long): Expense {
        return allExpense.first {
            it.id == id
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.deleteExpense(expense)
            updateState()
        }
    }

    fun getCategories(): List<ExpenseCategory> {
        return expenseRepository.getCategory()
    }

    private fun updateState() {
        _uiState.update { state ->
            state.copy(expense = allExpense, total = allExpense.sumOf {
                it.amount
            })
        }
    }


}

data class ExpenseUiState(
    val expense: List<Expense> = emptyList(),
    val total: Double = 0.0
)
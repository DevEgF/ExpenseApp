import data.ExpenseManager
import data.model.Expense
import data.model.ExpenseCategory
import data.repository.ExpenseRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

class ExpenseRepositoryTest {
    private val expenseManager = ExpenseManager
    private val repo = ExpenseRepositoryImpl(expenseManager)

    @Test
    fun expense_list_is_not_empty() {
        val expenseList = mutableListOf<Expense>()

        expenseList.addAll(repo.getAllExpenses())

        assertTrue(expenseList.isNotEmpty())
    }

    @Test
    fun add_new_expense() {
        val expenseList = repo.getAllExpenses()

        repo.addExpense(Expense(1, 4.5, ExpenseCategory.CAR, "Fuel"))

        assertContains(expenseList, expenseList.find { it.id == 7L })
    }
}
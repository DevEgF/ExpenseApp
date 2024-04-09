import data.model.Expense
import data.model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains

class ExpenseTest {

    @Test
    fun expense_model_list_test() {
        val expenseList = mutableListOf<Expense>()
        val expense = Expense(1, 4.5, ExpenseCategory.CAR, "Fuel")

        expenseList.add(expense)

        assertContains(expenseList, expense)
    }
}
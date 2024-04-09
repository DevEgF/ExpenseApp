package ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import data.TitleTopBarTypes
import data.model.Expense
import data.model.ExpenseCategory
import kotlinx.coroutines.launch
import ui.component.CategoryBottomSheetContent
import ui.component.ExpenseAmount
import ui.component.ExpenseDescription
import ui.component.ExpenseTypeSelector
import ui.theme.getColorsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesDetailScreen(
    expenseToEdit: Expense? = null,
    categoryList: List<ExpenseCategory> = emptyList(),
    addExpenseNavigatorBack: (Expense) -> Unit
) {

    var price by remember {
        mutableStateOf(
            expenseToEdit?.amount ?: 0.0
        )
    }

    var description by remember {
        mutableStateOf(
            expenseToEdit?.description ?: ""
        )
    }

    var expenseCategory by remember {
        mutableStateOf(
            expenseToEdit?.category?.name ?: ""
        )
    }

    var categorySelected by remember {
        mutableStateOf(
            expenseToEdit?.category?.name ?: "Select a category"
        )
    }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    val colors = getColorsTheme()

    LaunchedEffect(
        bottomSheetState.targetValue
    ) {
        if (bottomSheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyboardController?.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            CategoryBottomSheetContent(categoryList) {
                expenseCategory = it.name
                categorySelected = it.name
                scope.launch {
                    bottomSheetState.hide()
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            ExpenseAmount(
                priceContent = price,
                onPriceChange = {
                    price = it
                },
                keyboardController = keyboardController
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            ExpenseTypeSelector(
                categorySelected = categorySelected,
                openBottomSheet = {
                    scope.launch {
                        bottomSheetState.show()
                    }
                }
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            ExpenseDescription(
                descriptionContent = description,
                onDescriptionChange = {
                    description = it
                },
                keyboardController = keyboardController
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            OutlinedButton(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(45)),
                onClick = {
                    val expense = Expense(
                        amount = price,
                        category = ExpenseCategory.valueOf(expenseCategory),
                        description = description,
                    )
                    val expenseFromEdit = expenseToEdit?.id?.let {
                        expense.copy(id = it)
                    }

                    addExpenseNavigatorBack(expenseFromEdit ?: expense)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colors.purple,
                    contentColor = Color.White
                ),
                enabled = price != 0.0 && description.isNotBlank() && expenseCategory.isNotBlank()
            ) {
                expenseToEdit?.let {
                    Text(text = TitleTopBarTypes.EDIT.value)
                    return@OutlinedButton
                }
                Text(text = TitleTopBarTypes.ADD.value)
            }
        }
    }
}

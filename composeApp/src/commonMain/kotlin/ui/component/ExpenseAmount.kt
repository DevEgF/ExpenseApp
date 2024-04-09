package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.getColorsTheme

@Composable
fun ExpenseAmount(
    priceContent: Double,
    onPriceChange: (Double) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    val colors = getColorsTheme()
    var text by remember {
        mutableStateOf("$priceContent")
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Amount",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colors.textColor
            )
            TextField(
                modifier = Modifier.weight(1f),
                value = text,
                onValueChange = { newText ->
                    val num = newText.filter {
                        it.isDigit() || it == '.'
                    }
                    text = if (num.isNotEmpty() && num.count { it == '.' } <= 1) {
                        try {
                            val newValue = num.toDouble()

                            onPriceChange(newValue)

                            num
                        } catch (e: NumberFormatException) {
                            ""
                        }
                    } else {
                        onPriceChange(0.0)
                        ""
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colors.textColor,
                    backgroundColor = colors.backgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.ExtraBold)
            )
            Text(
                text = "USD",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray
            )
        }
        Divider(color = Color.Black, thickness = 2.dp)
    }
}
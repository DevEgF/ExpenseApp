package ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.getColorsTheme

@Composable
fun ExpenseDescription(
    descriptionContent: String,
    onDescriptionChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    var text by remember {
        mutableStateOf(descriptionContent)
    }

    val colors = getColorsTheme()

    Column {
        Text(
            text = "Description",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newText ->
                if (newText.length <= 200) {
                    text = newText
                    onDescriptionChange(newText)
                }
            },
            value = text,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colors.textColor,
                backgroundColor = colors.backgroundColor,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
            ),
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        Divider(color = Color.Black, thickness = 2.dp)
    }

}
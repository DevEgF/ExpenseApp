package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.getColorsTheme

@Composable
fun ExpenseTypeSelector(
    categorySelected: String,
    openBottomSheet: () -> Unit
) {
    val colors = getColorsTheme()

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Expenses made for",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
            Text(
                text = categorySelected,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.textColor
            )
        }
        IconButton(
            modifier = Modifier.clip(RoundedCornerShape(35)).background(colors.colorArrowRound),
            onClick = {
                openBottomSheet.invoke()
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Button Expense Type",
                tint = colors.textColor
            )
        }
    }
}
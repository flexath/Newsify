package com.flexath.newsify.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexath.newsify.ui.theme.NewsifyTheme
import com.flexath.newsify.ui.theme.WhiteGray

@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = "ATH",
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NewsButtonPreview() {
    NewsifyTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewsButton(
                text = "Next"
            ) {

            }

            NewsTextButton(
                text = "Back"
            ) {

            }
        }
    }
}
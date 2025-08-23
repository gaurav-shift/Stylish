package com.example.stylish.Presentation.onBoarding.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopIndicator(
    currentStep: Int,
    totalSteps: Int,
    onSkipClick: ()->Unit,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF1F2937),
    inactiveColor: Color = Color(0xFFE5E7EB)
){

    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${currentStep + 1}",
                    color = activeColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                Text(
                    text = "/$totalSteps",
                    color = inactiveColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
        Box(modifier= Modifier.weight(1f), contentAlignment = Alignment.CenterEnd){
            TextButton(onClick = onSkipClick){
                Text(text="Skip",
                    color=activeColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }

}


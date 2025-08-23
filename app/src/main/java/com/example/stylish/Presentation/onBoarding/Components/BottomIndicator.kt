package com.example.stylish.Presentation.onBoarding.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtomIndicator(
    totalSteps:Int,
    currentStep:Int,
    modifier: Modifier= Modifier,
    activeColor:Color = Color(0xFF1F2937),
    inactiveColor: Color= Color(0xFFE5E7EB),
    indicatorWidth: Dp = 24.dp,
    indicatorHeight: Dp= 4.dp,
    spacing:Dp = 8.dp,
    onNextClick:() -> Unit ={},
    onPreviousClick: () -> Unit={},
    onFinishClick: () -> Unit={}
){

    Row(modifier= Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            if (currentStep > 0) {
                TextButton(
                    onClick = onPreviousClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = inactiveColor
                    )
                ) {
                    Text(
                        "Previous",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
//        else {
//            Spacer(Modifier.width(70.dp))
//        }
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(totalSteps) { index ->
                Box(
                    modifier = Modifier.width(if (index == currentStep) indicatorWidth else indicatorHeight * 2)
                        .height(indicatorHeight)
                        .background(
                            color = if (index == currentStep) activeColor else inactiveColor,
                            shape = RoundedCornerShape(indicatorHeight / 2)
                        )
                )

            }

        }
    }


        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd){
        TextButton(
            onClick = {
                if(currentStep == totalSteps-1) onFinishClick()
                else onNextClick()
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color(0xFFEF4444)
            )

        ) {
            Text(
                text = if (currentStep == totalSteps - 1) "Get Started" else "Next",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
            }

    }



}

@Preview(showBackground = true)
@Composable
fun PreviewBottomIndicator(){
    ButtomIndicator(3,2)
}
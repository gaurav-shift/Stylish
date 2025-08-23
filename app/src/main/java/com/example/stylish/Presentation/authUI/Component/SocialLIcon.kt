package com.example.stylish.Presentation.authUI.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SocialIconButton(iconRes: Int) {
    OutlinedButton(
        onClick = { },
        shape = CircleShape,
        modifier = Modifier.size(50.dp),
        contentPadding = PaddingValues(0.dp),
        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
    ) {
        Image(painter = painterResource(id = iconRes), contentDescription = null, modifier = Modifier.fillMaxSize())
    }
}
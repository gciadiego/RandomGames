package com.example.composecodingchallenge

import android.content.Context
import android.graphics.ImageDecoder
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri


@Composable
fun randomDiceView(){
    val context = LocalContext.current

    val imageIdState = remember {
        mutableStateOf(R.drawable.dice_1)
    }

    val currentNumber = remember {
        mutableStateOf(1)
    }

    Image(
        painterResource(imageIdState.value),
        contentDescription = null,
        modifier = Modifier
            .clickable(
                enabled = true,
                onClick = {
                    val random = (1..6).random()
                    currentNumber.value = random
                    imageIdState.value = getDiceId(random, context)
                })
            .height(75.dp)
            .padding(bottom = 15.dp)
    )

    Text(text = "You got a ${currentNumber.value}")
}

fun getDiceId(randomNumber: Int, context: Context): Int{
    return context.resources.getIdentifier("dice_$randomNumber", "drawable", context.packageName)
}
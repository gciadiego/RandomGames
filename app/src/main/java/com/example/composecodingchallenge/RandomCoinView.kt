package com.example.composecodingchallenge

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun randomCoinView() {
    val context = LocalContext.current

    val imageIdState = remember {
        mutableStateOf(R.drawable.heads)
    }

    val currentNumber = remember {
        mutableStateOf(0)
    }

    val face = remember {
        mutableStateOf("heads")
    }

    Image(
        painterResource(imageIdState.value),
        contentDescription = null,
        modifier = Modifier
            .clickable(
                enabled = true,
                onClick = {
                    val random = (0..1).random()
                    currentNumber.value = random

                    face.value = if(random == 0) "heads" else "tails"

                    imageIdState.value = getCoinId(face.value, context)
                })
            .height(75.dp)
            .padding(bottom = 15.dp)
    )

    Text(text = "You got ${face.value}")
}

fun getCoinId(face: String, context: Context): Int{
    return context.resources.getIdentifier(face, "drawable", context.packageName)
}
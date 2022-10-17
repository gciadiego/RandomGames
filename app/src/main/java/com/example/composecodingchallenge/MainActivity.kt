package com.example.composecodingchallenge

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecodingchallenge.ui.theme.ComposeCodingChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodingChallengeTheme {
                MainActivityContent()
            }
        }
    }
}

@Preview
@Composable
fun MainActivityContent(){
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = {Text(text = stringResource(id = R.string.name))}
        )
    }) {
        GameContent()
    }
}

@Composable
fun GameContent() {
    val context = LocalContext.current

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val mCheckedState = remember{ mutableStateOf(false)}

        val title = rememberSaveable { mutableStateOf("Click to roll the dice") }

        //Display switch
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(text = "Dice")
            Switch(
                checked = mCheckedState.value,
                onCheckedChange = {
                    mCheckedState.value = it
                }
            )
            Text(text = "Coin")
        }

        //Display title
        Text(
            text = title.value,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 15.dp)
        )

        if(!mCheckedState.value){
            title.value = "Click to roll the dice"

            //Dice View
            randomDiceView()
        }
        else{
            title.value = "Click to flip the coin"

            //Coin View
            randomCoinView()
        }
    }
}

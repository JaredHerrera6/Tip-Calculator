package com.example.tipcalc

import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainFunc()
        }
    }
}
@Composable
fun MainFunc(){
    val checkAmount = remember{ mutableStateOf("")}
    val selectedPer = remember{ mutableStateOf(Percent.Ten)}
    val tip = remember{ mutableStateOf("")}
    val total = remember{ mutableStateOf("")}
    Column(
        modifier  = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tip Calculator",
            fontSize = 35.sp
        )
        Text(
            text = "Enter your check amount",
            fontSize = 25.sp
        )
        Row(
        ) {
            Text(
                text = "$",
                fontSize = 25.sp
            )
            TextField(
                value = checkAmount.value,
                onValueChange = {newAmount -> checkAmount.value = newAmount},
                keyboardOptions = KeyboardOptions
                    .Default
                    .copy(keyboardType = KeyboardType.Number, imeAction = androidx.compose.ui.text.input.ImeAction.Go)
            )
        }
        Text(
            text = "Select a tip Percentage",
            fontSize = 20.sp
        )
        Row {
            RadioButton(
                selected = selectedPer.value == Percent.Ten,
                onClick = { selectedPer.value = Percent.Ten}
            )
            Text("10%")
        }
        Row{
            RadioButton(
                selected = selectedPer.value == Percent.Twenty,
                onClick = { selectedPer.value = Percent.Twenty })
            Text(text = "20%")
        }
        Row {
            RadioButton(
                selected = selectedPer.value == Percent.Thirty,
                onClick = { selectedPer.value = Percent.Thirty})
            Text(text = "30%")
        }
        Button(
            onClick = {
                tip.value = calcTip(checkAmount.value, selectedPer.value)
                total.value = calctotal(checkAmount.value,tip.value)
            }
        ){
            Text("Calculate")
        }
        Text(
            text = "Your Calculated tip is $" + tip.value + " and your total is $" + total.value
        )
    }
}
enum class Percent{
    Ten,Twenty,Thirty
}
fun calcTip( chckAmount : String, selectedPer : Percent) : String{
    var tip = 0.0
    var amount = chckAmount.toFloat()
    val df = DecimalFormat("#.00")


    if(selectedPer == Percent.Ten){
        var ten = .10
        tip = df.format(amount * ten).toDouble()
        return tip.toString()
    }
    else if(selectedPer == Percent.Twenty){
        var twenty = .20
        tip = df.format(amount * twenty).toDouble()
        return tip.toString()
    }
    else{
        var thirty = .30
        tip = df.format(amount * thirty).toDouble()
        return tip.toString()
    }
    return tip.toString()
}
fun calctotal(chckAmount : String, tip :String): String {
    val df = DecimalFormat("#.00")
    var total = df.format(chckAmount.toDouble() + tip.toDouble())

    return total.toString()
}
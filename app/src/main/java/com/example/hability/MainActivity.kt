package com.example.hability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hability.componentes.Skills
import com.example.hability.componentes.TextFieldCustom
import com.example.hability.ui.theme.HabilityTheme
import com.example.hability.ui.theme.brown
import com.example.hability.ui.theme.darkBlue
import com.example.hability.ui.theme.purple
import com.example.hability.ui.theme.red
import com.example.hability.ui.theme.yellow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home() {

    var skill1 by remember{ mutableStateOf("") }
    var skill2 by remember{ mutableStateOf("") }
    var skill3 by remember{ mutableStateOf("") }
    var skill4 by remember{ mutableStateOf("") }
    var skill5 by remember{ mutableStateOf("") }

    var activate by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val animateProgression1 = remember { Animatable(0f) }
    val animateProgression2 = remember { Animatable(0f) }
    val animateProgression3 = remember { Animatable(0f) }
    val animateProgression4 = remember { Animatable(0f) }
    val animateProgression5 = remember { Animatable(0f) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Qual o seu nível de conhecimento nas seguintes linguagens de programação?",
            fontSize = 20.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center
        )

        Text(
            text = "De 0 a 100",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        TextFieldCustom(
            value = skill1,
            onValueChange = {
                            if(it.length <= 3){
                                skill1 = it
                            }
            },
            placeHolder = "Nível",
            skill = "Java")

        TextFieldCustom(
            value = skill2,
            onValueChange = {
                if(it.length <= 3){
                    skill2 = it
                }
            },
            placeHolder = "Nível",
            skill = "Kotlin")

        TextFieldCustom(
            value = skill3,
            onValueChange = {
                if(it.length <= 3){
                    skill3 = it
                }
            },
            placeHolder = "Nível",
            skill = "JavaScript")

        TextFieldCustom(
            value = skill4,
            onValueChange = {
                if(it.length <= 3){
                    skill4 = it
                }
            },
            placeHolder = "Nível",
            skill = "C++")

        TextFieldCustom(
            value = skill5,
            onValueChange = {
                if(it.length <= 3){
                    skill5 = it
                }
            },
            placeHolder = "Nível",
            skill = "PHP")

        Button(
            onClick = {
                      scope.launch(Dispatchers.IO){
                          if (
                              skill1.isNotEmpty() &&
                              skill2.isNotEmpty() &&
                              skill3.isNotEmpty() &&
                              skill4.isNotEmpty() &&
                              skill5.isNotEmpty()
                          ){
                              activate = true
                              delay(500)
                              animateProgression1.animateTo(converterToDecimal(skill1), animationSpec = tween(durationMillis = 600))
                              animateProgression2.animateTo(converterToDecimal(skill2), animationSpec = tween(durationMillis = 600))
                              animateProgression3.animateTo(converterToDecimal(skill3), animationSpec = tween(durationMillis = 600))
                              animateProgression4.animateTo(converterToDecimal(skill4), animationSpec = tween(durationMillis = 600))
                              animateProgression5.animateTo(converterToDecimal(skill5), animationSpec = tween(durationMillis = 600))
                              skill1 = ""
                              skill2 = ""
                              skill3 = ""
                              skill4 = ""
                              skill5 = ""
                          } else{
                              activate = false
                          }
                      }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(20.dp)
        ) {
            Text(text = "Confirmar")
        }

        if(activate){
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(20.dp)
                .border(
                    width = 2.dp, color = Color.Black, shape = RoundedCornerShape(15.dp)
                )
            ){
                Text(
                    text = "Nível de conhecimento",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp,10.dp)
                )

                Skills(language = "Java", animatable = animateProgression1, color = brown)
                Skills(language = "Kotlin", animatable = animateProgression2, color = purple)
                Skills(language = "JavaScript", animatable = animateProgression3, color = yellow)
                Skills(language = "C++", animatable = animateProgression4, color = red)
                Skills(language = "PHP", animatable = animateProgression5, color = darkBlue)
            }
        }
    }
}

fun converterToDecimal(skill: String): Float{
    return skill.toInt() / 100f
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}
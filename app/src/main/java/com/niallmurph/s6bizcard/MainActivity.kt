package com.niallmurph.s6bizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niallmurph.s6bizcard.ui.theme.S6BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S6BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(

) {
    val buttonClickedState = remember{ mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 5.dp
        ) {

            Column(
                modifier = Modifier.height(height = 300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile(image = R.drawable.ic_user_profile)
                Divider(thickness = 2.dp)
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    TitleAndCaption()
                }
                Button(
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                    }) {
                    Text(
                        text = "Portfolio",
                        style = MaterialTheme.typography.button
                    )
                }
                if(buttonClickedState.value){
                    Content()
                } else {
                    Box(){}
                }
            }
        }
    }
}

@Composable
private fun TitleAndCaption() {
    Text(
        text = "Murphy N.",
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.primaryVariant
    )
    Text(
        text = "Android Developer",
        modifier = Modifier.padding(5.dp),
        style = MaterialTheme.typography.subtitle1
    )
    Text(
        text = "nm@android.dev",
        modifier = Modifier.padding(5.dp),
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier, image : Int) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = 4.dp
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = "Profile placeholder pic",
            modifier = modifier.size(128.dp),
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(2.dp, Color.LightGray)
        ) {
            Portfolio(data = listOf("Project 1", "Project 2", "Project 3", "Project 4", "Project 5"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data){ item ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
                shape = RectangleShape){
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                ){
                    CreateImageProfile(modifier = Modifier.size(96.dp), image = R.drawable.ic_android)
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ){
                        Text(item, fontWeight = FontWeight.Bold)
                        Text(text = "Example project card", style = MaterialTheme.typography.subtitle1)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    S6BizCardTheme {
        CreateBizCard()
    }
}
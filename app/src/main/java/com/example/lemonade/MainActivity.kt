package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    PageWithImageAndContent(
        modifier = Modifier.fillMaxSize()

    )
}

@Composable
fun PageWithImageAndContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val yellow = Color(0xFFFFEB3F)
        var currentState by remember { mutableStateOf(1) }
        var count by remember { mutableStateOf(0) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(yellow),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Lemonade",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        when(currentState){
            1 ->LemonLabelAndImage(
                    R.string.lemon_tree_desc,
                    R.drawable.lemon_tree,
                    R.string.lemon_tree_content_desc,
                    onImageclick = {
                        currentState = 2
                        count = (2..4).random()
                    }
                )
            2 ->LemonLabelAndImage(
                    R.string.lemon_desc,
                    R.drawable.lemon_squeeze,
                    R.string.lemon_content_desc,
                    onImageclick = {
                        count--
                        if(count == 0){
                            currentState = 3
                        }
                    }
                )
            3 ->LemonLabelAndImage(
                R.string.glass_content_desc,
                R.drawable.lemon_drink,
                R.string.glass_content_desc,
                onImageclick = {
                    currentState = 4
                }
            )
            4 ->LemonLabelAndImage(
                R.string.empty_glass_desc,
                R.drawable.lemon_restart,
                R.string.empty_glass_content_desc,
                onImageclick = {
                    currentState = 1
                }
            )
        }

    }
}

@Composable
fun LemonLabelAndImage(
    textLabelId: Int,
    imageResourceId: Int,
    contentDescriptionId: Int,
    onImageclick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageclick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = stringResource(contentDescriptionId)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(textLabelId),
            fontSize = 18.sp
        )
    }
}


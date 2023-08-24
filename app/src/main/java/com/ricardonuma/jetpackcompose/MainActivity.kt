package com.ricardonuma.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ricardonuma.jetpackcompose.ui.theme.JetpackComposeTheme
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                var newText by remember {
                    mutableStateOf("")
                }
                var textList by remember {
                    mutableStateOf(listOf<String>())
                }
                var loading by remember {
                    mutableStateOf(false)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = newText,
                            onValueChange = { text ->
                                newText = text
                            },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            textList = textList + newText
                            newText = ""
                            loading = true
                            Timer().schedule(3000) {
                                loading = false
                            }
                        }) {
                            Text(text = "Add")
                        }
                    }
                    list(textList, loading)
                }
            }
        }
    }
}

@Composable
fun list(textList: List<String>, loading: Boolean) {
    if (textList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Empty List",
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    } else {
        if (loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        } else {
            LazyColumn {
                items(textList) { currentText ->
                    Text(
                        currentText,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Divider()
                }
            }
        }
    }
}
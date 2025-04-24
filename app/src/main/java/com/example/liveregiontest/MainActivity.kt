package com.example.liveregiontest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.liveregiontest.webview.WebView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val text = remember { mutableStateOf("Hello") }
            MaterialTheme {

                WebView()
                /*Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Talkback Testing") })
                    }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text("First normal text element")

                        Text(
                            buildAnnotatedString {
                                append("If I could click something, I would click ")
                                val link =
                                    LinkAnnotation.Clickable(
                                        "special_tag",
                                        TextLinkStyles(SpanStyle(color = Color.Blue))
                                    ) {
                                        println("Clickable link clicked")
                                    }
                                withLink(link) { append("here") }
                                append(".")
                            }
                        )

                        Text("Second normal text element")
                    }

                }*/
                /*Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Live Region Test") },
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Close",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .testTag("CLOSE_BUTTON")
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        Surface(shadowElevation = 8.dp) {
                            Column(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 20.dp,
                                        vertical = 4.dp
                                    ).fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                content = {
                                    Button(onClick = {
                                        text.value = "Hello world"
                                    }) {
                                        Text("Bottom Bar")
                                    }
                                }
                            )
                        }

                    }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(
                            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                            text = text.value,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )

                    }

//                    AnnotatedStringWithLinkSample(Modifier.padding(innerPadding))
                }*/
            }
        }
        }
}

@Composable
fun AnnotatedStringWithLinkSample(modifier: Modifier) {
    // Display multiple links in the text
    /*Text(
        modifier = modifier,
        text =
            annotatedStringWithLink()
    )*/
    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = modifier,
            text =
                annotatedStringAddLink()
        )
    }
}

@Composable
private fun annotatedStringAddLink() = buildAnnotatedString {
    append("Go to the ")
    addLink(
        url = LinkAnnotation.Url(
            url = "mailto:help@well.co",
            styles = TextLinkStyles(style = SpanStyle(color = Color.Blue)),
        ),
        start = 8,
        end = 20
    )
    append("website, and check out the")
    withLink(
        LinkAnnotation.Url(
            "https://developer.android.com/jetpack/compose",
            TextLinkStyles(style = SpanStyle(color = Color.Green))
        )
    ) {
        append("Compose guidance")
    }
    append(".")
}


@Composable
private fun annotatedStringWithLink() = buildAnnotatedString {
    append("Go to the ")
    withLink(
        LinkAnnotation.Url(
            "https://developer.android.com/",
            TextLinkStyles(style = SpanStyle(color = Color.Blue))
        )
    ) {
        append("Android Developers ")
    }
    append("website, and check out the")
    withLink(
        LinkAnnotation.Url(
            "https://developer.android.com/jetpack/compose",
            TextLinkStyles(style = SpanStyle(color = Color.Green))
        )
    ) {
        append("Compose guidance")
    }
    append(".")
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text1 by remember { mutableStateOf(TextFieldValue("Initial value")) }
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage1 by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showProgress by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(2000)
        showProgress = false
    }
    // Validation logic
    fun validateInput() {
        showProgress = true
        coroutineScope.launch {
            delay(2000)
            // For example, the input should not be empty
            errorMessage = if (text.text.isBlank()) {
                showProgress = false
                "This field cannot be empty."
            } else {
                showProgress = false
                ""
            }
            errorMessage1 = if (text1.text.isBlank()) {
                "This field cannot be empty."
            } else {
                ""
            }
        }
    }

    if(showProgress) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(54.dp)
            ) {
                CircularProgressIndicator(
                    modifier =
                    Modifier
                        .background(
                            MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(48.dp)
                        )
                        .padding(12.dp)
                        .fillMaxSize(),
                    strokeWidth = 3.dp
                )
            }
        }
    }


    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = text1,
            onValueChange = { newText ->
                text1 = newText
                errorMessage1 = "" // Clear the error while typing
            },
            label = { Text("Label") },
            isError = errorMessage1.isNotEmpty(),
            supportingText = null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if(errorMessage1.isNotEmpty()) {
            Text(text = errorMessage1, modifier = Modifier
                .padding(top = 8.dp)
                .semantics {
                    hideFromAccessibility()
                    liveRegion = LiveRegionMode.Polite
                })
        }

        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                errorMessage = "" // Clear the error while typing
            },
            label = { Text("Label") },
            isError = errorMessage.isNotEmpty(),
            supportingText = null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if(errorMessage.isNotEmpty()) {
            Text(text = errorMessage, modifier = Modifier
                .padding(top = 8.dp)
                .testTag("TEST_TAG")
                .semantics {
                    hideFromAccessibility()
                    liveRegion = LiveRegionMode.Polite
                })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            validateInput()
        }) {
            Text("Validate")
        }

        AnnotatedStringWithLinkSample(modifier = Modifier.padding(top = 20.dp))
    }
}
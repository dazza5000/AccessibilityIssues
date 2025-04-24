package com.example.liveregiontest.webview

import AccessibleWebView
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.liveregiontest.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebView() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Start menu")
            }
                )
            },
        bottomBar = { Button() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp)
        ) {
            item { ComposeWebView() }
            item {
                Text(
                    text = "Item 2",
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun Button() {
    TextButton(content = { Text(text = "Button") }, onClick = {})
}

@Composable
private fun ComposeWebView() {
    AndroidView(
        modifier = Modifier.padding(horizontal = 16.dp),
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )

                setBackgroundColor(context.getColor(R.color.white))

                loadDataWithBaseURL(
                    "file:///android_asset/",
                    sourceHtmlContent,
                    "text/html",
                    "UTF-8",
                    null,
                )
            }

        },
    )
}

private val sourceHtmlContent = """
<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sed vehicula nisi. Pellentesque pretium, odio sed posuere volutpat, nisl sapien aliquet ligula, ut bibendum mauris tellus a odio. Fusce in sem vel lectus accumsan rutrum at vitae velit. Etiam id consectetur ligula, vitae tincidunt augue. In fermentum nulla eget nunc suscipit aliquam. Integer nec est sit amet libero facilisis congue id quis elit. Nunc elit dui, varius in orci non, auctor sollicitudin mi. Cras ullamcorper justo fermentum ante posuere volutpat. Ut fermentum, massa id convallis accumsan, quam mauris condimentum ex, sollicitudin volutpat ante enim at enim. Suspendisse pretium, sapien sed tincidunt efficitur, est mauris aliquet neque, at congue arcu nibh id dolor.</p>

<p>Quisque eget aliquet massa. Integer in tellus ac velit porttitor mattis. Fusce rutrum ante ligula. Nulla commodo at magna non lacinia. Cras vitae quam augue. Morbi ut ultricies tortor. Aenean vehicula leo id augue facilisis eleifend. Donec vulputate faucibus ex sed cursus. In varius sem tempor purus pulvinar, vitae commodo quam tempor. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur tempor sem eros, a consectetur orci suscipit quis. Curabitur pretium est sit amet risus mattis tincidunt. Proin non dictum ante. Suspendisse elementum congue nunc id elementum.</p>

<p>Vestibulum pulvinar ipsum ac elementum dictum. Morbi pretium mi ornare nisi pulvinar, at scelerisque ligula luctus. Donec efficitur a purus quis pharetra. Nunc posuere porttitor ligula et sollicitudin. Praesent elementum ex nec arcu commodo, nec viverra dui tincidunt. Nulla fermentum faucibus felis, ac ornare ipsum congue at. Pellentesque eget eros magna. Proin ac eleifend dui. Aenean dictum tellus eget dapibus imperdiet. Nulla facilisi.</p>

<p>Fusce vestibulum pulvinar neque in ornare. Donec pretium pellentesque orci, at elementum est elementum id. Aliquam dignissim tellus erat, vel dignissim lorem tincidunt sit amet. Aliquam maximus elit tellus, quis condimentum quam ultricies quis. Aliquam aliquam augue eget nibh porttitor, eget rhoncus ex varius. Morbi vel lorem in lacus aliquet porta. Integer venenatis id ante vel ullamcorper.</p>

<p>Integer sem tortor, consequat at lacus nec, accumsan faucibus lorem. Aliquam vel commodo nunc. Vivamus tempus elit quis enim tristique hendrerit. Quisque et vulputate justo, a euismod orci. Integer turpis dui, luctus sed tincidunt id, imperdiet a velit. In at tortor urna. Vestibulum tincidunt, lacus non tempus semper, ex nisl luctus massa, quis commodo ante purus in dui. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla varius sodales nisi. Vestibulum vitae leo magna. Vestibulum in mauris id lacus auctor vestibulum. Etiam vel tristique lectus. Curabitur libero nunc, condimentum quis velit et, vestibulum viverra nisl. Aliquam eu neque non eros placerat tristique. Nullam a venenatis orci.</p>

<p>Suspendisse accumsan urna elit, in scelerisque turpis interdum porttitor. Curabitur justo metus, lobortis in ullamcorper ac, ultricies in dolor. Quisque hendrerit quam quis lacus ullamcorper lacinia. Aliquam erat volutpat. Pellentesque suscipit purus pretium felis convallis ullamcorper. Morbi tincidunt justo quis lacus condimentum cursus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus in ex id tellus tincidunt efficitur. Maecenas sollicitudin vestibulum metus, quis mollis purus porttitor in. Quisque blandit tincidunt velit non blandit. Nulla quis mollis ipsum. Vivamus id sapien quam. Suspendisse potenti. Curabitur eget eleifend enim, ut lobortis magna. Donec dolor nisi, mattis nec libero sit amet, efficitur volutpat augue. Praesent ullamcorper neque vitae eleifend laoreet.</p>

<p>Fusce purus ipsum, pellentesque aliquet nisi sed, ullamcorper facilisis eros. Duis id finibus metus. Praesent nec condimentum urna, quis tempus quam. Vestibulum consequat non augue nec egestas. Suspendisse sed iaculis odio, in dignissim tortor. Fusce pellentesque, est vel tempus egestas, justo ipsum viverra tellus, at gravida neque felis ac tortor. Pellentesque at hendrerit erat. Maecenas varius accumsan dolor, sed tempor magna porta in. Integer purus dolor, bibendum non blandit a, tempus sit amet mauris.</p>

<p>Mauris euismod lobortis neque et tempor. Curabitur mollis vel dui ut ultricies. Morbi at rhoncus urna, at pharetra turpis. Morbi efficitur odio a dictum efficitur. Pellentesque hendrerit fermentum arcu, non facilisis risus rutrum non. Suspendisse rhoncus dapibus quam id aliquam. Suspendisse quis nisl eget diam dictum convallis id vel nisi. Aenean dignissim lectus neque, eu aliquam tellus ultrices in. Morbi interdum, nisi scelerisque tincidunt feugiat, ex nulla vulputate purus, ut semper nibh diam vel dolor. Nulla a mi non ex iaculis vestibulum vel at nibh. Ut sodales neque at libero blandit, eu malesuada justo volutpat. Fusce accumsan tortor in nulla venenatis ullamcorper. Morbi id dui erat.</p>
""".trimIndent()
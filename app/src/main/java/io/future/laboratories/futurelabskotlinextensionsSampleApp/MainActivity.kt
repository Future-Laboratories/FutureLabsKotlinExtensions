package io.future.laboratories.futurelabskotlinextensionsSampleApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import io.future.laboratories.future.labs.kotlin.extensions.CoreExtensions.plus
import io.future.laboratories.future.labs.kotlin.extensions.Experimental
import io.future.laboratories.future.labs.kotlin.extensions.MapExtensions
import io.future.laboratories.future.labs.kotlin.extensions.MapExtensions.filterByType
import io.future.laboratories.future.labs.kotlin.extensions.MapExtensions.filterNotNull
import io.future.laboratories.future.labs.kotlin.extensions.RegExFactory
import io.future.laboratories.futurelabskotlinextensionsSampleApp.ui.theme.FutureLabsKotlinExtensionsSampleApp

class MainActivity : ComponentActivity() {
    private val nullableMap = mapOf<String?, Any?>(
        "X" to 12,
        "Y" to "5",
        null to true,
        "Z" to null,
    )

    private val nullableMap2 = mapOf<Any?, Any?>(
        "X" to 12,
        "Y" to "ABC",
        null to true,
        "Z" to null,
        12 to "X",
        "12" to 12,
    )

    private val testString = "W12a"

    private val RegExFactory = RegExFactory()
        .addClass {
            not {
                allowNumbers()
            }
        }
        .addClass {
            addClass {
                allowNumbers()
            }.or {
                allowLowerCharacters()
            }
        }
        .addWildCard()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FutureLabsKotlinExtensionsSampleApp {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                        nullableMap.filterNotNull(MapExtensions.Mode.Both) {
                            item {
                                Greeting(first = it.key, second = it.value)
                            }
                        }
                        nullableMap2.filterByType<String, Any>().forEach {
                            item {
                                Greeting(first = it.key, second = it.value)
                            }
                        }
                        item {
                            Text(text = testString.matches(RegExFactory.build()).toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(first: String, second: Any) {
    Text(fontSize = 40.sp, text = "$first to $second")
}

@OptIn(Experimental::class)
@Composable
fun Calculation(first: Number, second: Number) {
    Text(fontSize = 40.sp, text = "$first plus $second is ${first plus second}")
}
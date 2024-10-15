package pl.mazak.euroguesser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import pl.mazak.euroguesser.ui.EuroGuesserApp
import pl.mazak.euroguesser.ui.theme.EuroGuesserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EuroGuesserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EuroGuesserApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}




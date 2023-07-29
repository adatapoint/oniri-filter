package com.vince.onirifilter.scenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vince.onirifilter.ui.theme.OniriFilterTheme
import com.vince.onirifilter.utils.showToast

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OniriFilterTheme {
                MainScreen(
                    onWhatIsThisClick = {
                        showToast("This will show a description for this section")
                    },
                    onSearch = { query ->
                        showToast("Searching for $query in your previous dreams...")
                    }
                )
            }
        }
    }
}

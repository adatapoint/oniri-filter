package com.vince.onirifilter.scenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.OniriFilterTheme
import com.vince.onirifilter.utils.openInWebBrowser
import com.vince.onirifilter.utils.setStatusBarColor
import com.vince.onirifilter.utils.showToast

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(R.color.background)
        setContent {
            OniriFilterTheme {
                MainScreen(
                    onBackClick = { finish() },
                    onWhatIsThisClick = {
                        openInWebBrowser(getString(R.string.web_site))
                    },
                    onSearch = { query ->
                        showToast("Searching for $query in your previous dreams...")
                    }
                )
            }
        }
    }
}

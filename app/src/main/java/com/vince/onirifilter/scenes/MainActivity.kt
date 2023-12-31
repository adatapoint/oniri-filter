package com.vince.onirifilter.scenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vince.onirifilter.R
import com.vince.onirifilter.ui.theme.OniriFilterTheme
import com.vince.onirifilter.utils.openInWebBrowser
import com.vince.onirifilter.utils.showToast

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OniriFilterTheme {
                MainScreen(
                    onBackClick = { finish() },
                    onWhatIsThisClick = { openInWebBrowser(getString(R.string.web_site)) },
                    onSearch = { query -> showToast(String.format(getString(R.string.toast_search_query), query)) }
                )
            }
        }
    }
}

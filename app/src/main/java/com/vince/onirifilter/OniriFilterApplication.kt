package com.vince.onirifilter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.vince.onirifilter.injection.applicationModule
import com.vince.onirifilter.injection.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OniriFilterApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        setDependencyInjection()
        setPortraitMode()
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun setPortraitMode() {
        // Portrait mode in all activities
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityDestroyed(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        })
    }

    private fun setDependencyInjection() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@OniriFilterApplication)
            modules(applicationModule, domainModule)
        }
    }
}

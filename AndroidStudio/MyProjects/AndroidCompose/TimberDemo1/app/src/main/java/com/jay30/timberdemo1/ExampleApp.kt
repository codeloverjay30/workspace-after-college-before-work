package com.jay30.timberdemo1

import android.app.Application
import android.util.Log
import timber.log.Timber
import timber.log.Timber.Forest.plant


class ExampleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    /** A tree which logs important information for crash reporting.  */
    private open class CrashReportingTree : Tree() {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            FakeCrashLibrary.log(priority, tag, message)

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t)
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t)
                }
            }
        }
    }
}
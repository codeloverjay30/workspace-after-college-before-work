package com.jay30.hiltdemo1

import javax.inject.Inject

class AnalyticsAdapter @Inject constructor(
    private val service: AnalyticsService
) {
    // ...
}
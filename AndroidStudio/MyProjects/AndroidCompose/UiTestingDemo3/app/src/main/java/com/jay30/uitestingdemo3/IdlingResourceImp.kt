package com.jay30.uitestingdemo3

import androidx.compose.ui.test.IdlingResource

class IdlingResourceImpl(override val isIdleNow: Boolean) : IdlingResource {
}
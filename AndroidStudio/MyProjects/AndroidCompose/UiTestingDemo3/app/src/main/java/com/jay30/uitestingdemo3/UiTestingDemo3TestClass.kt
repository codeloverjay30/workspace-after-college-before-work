package com.jay30.uitestingdemo3

import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UiTestingDemo3TestClass {

    @get:Rule
    val composeTestRule = createComposeRule()

    val myCounter2 = mutableIntStateOf(0)

    @Test
    fun counterTest() {
        val myCounter = mutableIntStateOf(0) // State that can cause recompositions.
        var lastSeenValue = 0 // Used to track recompositions.
        composeTestRule.setContent {
            Text(myCounter.intValue.toString())
            lastSeenValue = myCounter.intValue
        }
        myCounter2.intValue = 1
        myCounter.intValue = 1 // The state changes, but there is no recomposition.

        // Fails because nothing triggered a recomposition.
        assertTrue(lastSeenValue == 1)

        // Passes because the assertion triggers recomposition.
        composeTestRule.onNodeWithText("1").assertExists()
    }

    // Disable automatic synchronization
    // for more details, see [`Synchronize your tests#disable-autosync`](https://developer.android.com/develop/ui/compose/testing/synchronization#disable-autosync)
    @Before
    fun disableAutoSync() {
        composeTestRule.mainClock.autoAdvance = false
    }

    // advance exactly one frame with advanceTimeByFrame method
    @Before
    fun advanceTimeByFrame() {
        composeTestRule.mainClock.advanceTimeByFrame()
    }

    // advance by a specific duration with advanceTimeBy method
    @Before
    fun advanceTimeBy() {
        val milliseconds = 100L
        composeTestRule.mainClock.advanceTimeBy(milliseconds)
    }

    // register specific Idling Resource
    @Before
    fun registerIdlingResource() {
        val idlingResource = IdlingResourceImpl(true)
        composeTestRule.registerIdlingResource(idlingResource)
    }

    // unregister specific Idling Resource
    @Before
    fun unregisterIdlingResource() {
        val idlingResource = IdlingResourceImpl(true)
        composeTestRule.unregisterIdlingResource(idlingResource)
    }

    // advances the clock until Compose is idle.
    @Before
    fun advancesClockUntilComposeIsIdle() {
        composeTestRule.mainClock.autoAdvance = true // Default
        composeTestRule.waitForIdle() // Advances the clock until Compose is idle.
    }

    // only waits for idling resources to become idle.
    @Before
    fun onlyWaitsForIdlingResourcesBecomeIdle() {
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.waitForIdle() // Only waits for idling resources to become idle.
    }

    // advance the clock until a certain condition is met with advanceTimeUntil method
    @Before
    fun advanceTimeUntilConditionIsMet() {
        val timeoutMs = 200L
        val condition = (myCounter2.intValue == 1)
        composeTestRule.mainClock.advanceTimeUntil(timeoutMs) { condition }
    }

    // wait until the condition is met
    @Before
    fun waitUntilConditionIsMet() {
        val timeoutMs = 200L
        val condition = (myCounter2.intValue == 1)
        composeTestRule.waitUntil(timeoutMs) { condition }
    }

    // For more details, see link in guide_reference.md
}
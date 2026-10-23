package com.jay30.uitestingdemo1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.jay30.uitestingdemo1.ui.theme.UiTestingDemo1Theme
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class UiTestingDemo1TestClass {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test // => annotation identifies a method as a test method.
    fun myTest1() {
        // Start the app
        composeTestRule.setContent {
            UiTestingDemo1Theme {
                MainScreen()
            }
        }

        composeTestRule.onNodeWithText("Continue").performClick()

        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()

        composeTestRule.onRoot().printToLog("TAG")
    }

    @Test(timeout = 100) //  =>  Fails if the method takes longer than 100 milliseconds.
    fun myTestFailsLongerThan100Milliseconds() {
        // Start the app
        composeTestRule.setContent {
            UiTestingDemo1Theme {
                MainScreen()
            }
        }
    }

    @Test(timeout = 1) // => Fails if the method takes longer than 1 milliseconds.
    fun myTestFailsLongerThan1Milliseconds() {
        // Start the app
        composeTestRule.setContent {
            UiTestingDemo1Theme {
                MainScreen()
            }
        }
    }

    @Before // => This method is executed before each test. It is used to prepare the test environment (e.g., read input data, initialize the class).
    fun myTestBeforeEachTest(){

    }

    @After // => This method is executed once, before the start of all tests. It is used to perform time intensive activities, for example, to connect to a database. Methods marked with this annotation need to be defined as static to work with JUnit.
    fun myTestAfterEachTest(){

    }

    companion object {
        @JvmStatic
        @BeforeClass // =>  This method is executed once, after all tests have been finished. It is used to perform clean-up activities, for example, to disconnect from a database. Methods annotated with this annotation need to be defined as static to work with JUnit.
        fun myTestBeforeAllTests(): Unit {

        }

        @JvmStatic
        @AfterClass // =>  This method is executed once, after all tests have been finished. It is used to perform clean-up activities, for example, to disconnect from a database. Methods annotated with this annotation need to be defined as static to work with JUnit.
        fun myTestAfterAllTests(): Unit {

        }
    }

    @Ignore("Unknown reason") // => Ignores the test method. This is useful when the underlying code has been changed and the test case has not yet been adapted. Or if the execution time of this test is too long to be included.
    fun ignoredTest(){

    }
}
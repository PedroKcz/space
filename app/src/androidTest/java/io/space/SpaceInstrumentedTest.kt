package io.space

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.rule.BaristaRule
import io.space.presentation.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class SpaceInstrumentedTest : KoinTest {

    @Rule
    @JvmField
    val baristaRule: BaristaRule<MainActivity> =
        BaristaRule.create(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Before
    fun setUp() {
        startKoin { modules(mockedModules) }
        baristaRule.launchActivity()
    }

    @Test
    fun itShouldOpenSpaceViewWithRightContent() {
        composeTestRule.onNode(hasText("bla")).assertExists()

        composeTestRule.onNode(hasText("bla bla")).assertExists()
    }

    @After
    fun after() {
        stopKoin()
    }
}

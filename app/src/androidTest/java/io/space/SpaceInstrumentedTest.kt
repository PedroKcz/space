package io.space

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(mockedModules)
    }

    @Rule
    @JvmField
    val baristaRule: BaristaRule<AccountActivity> =
        BaristaRule.create(AccountActivity::class.java)

    @Before
    fun setUp() {
        baristaRule.launchActivity()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("io.space", appContext.packageName)
    }
}
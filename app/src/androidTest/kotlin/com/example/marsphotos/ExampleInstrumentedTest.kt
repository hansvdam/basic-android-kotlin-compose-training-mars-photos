package com.example.marsphotos

import android.app.Instrumentation
import android.os.Bundle
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.example.marsphotos.data.MarsPhotosRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var marsPhotosRepository: MarsPhotosRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun useAppContext() {
        runTest {

            // Context of the app under test.
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            val marsPhotos = marsPhotosRepository.getMarsPhotos()
            val ouptut = "***** marsPhotos.size: ${marsPhotos.size}"
            Log.d("bla", ouptut)
            assertEquals("com.example.marsphotos", appContext.packageName)
        }
    }

    private fun out(str: String) {
        val b = Bundle()
        b.putString(
            Instrumentation.REPORT_KEY_STREAMRESULT, """
     
     $str
     """.trimIndent()
        )
        InstrumentationRegistry.getInstrumentation().sendStatus(0, b)
    }
}
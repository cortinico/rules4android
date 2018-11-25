package com.ncorti.rules4android.exampleapp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ncorti.rules4android.ChangeLocale
import com.ncorti.rules4android.LocaleRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class LocaleInstrumentedRuleTest {

    @get:Rule
    val rule = LocaleRule(Locale.ITALIAN)

    @Test
    @ChangeLocale("de")
    fun localeChangedByAnnotation() {
        assertEquals("de",
                InstrumentationRegistry.getTargetContext().resources.configuration.locales[0].language)
    }

    @Test
    fun localeChangedByRule() {
        assertEquals("it",
                InstrumentationRegistry.getTargetContext().resources.configuration.locales[0].language)
    }
}
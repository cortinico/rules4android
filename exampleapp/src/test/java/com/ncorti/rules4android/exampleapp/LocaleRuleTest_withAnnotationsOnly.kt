package com.ncorti.rules4android.exampleapp

import com.ncorti.rules4android.*
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class LocaleRuleTest_withAnnotationsOnly {

    @get:Rule val rule = LocaleRule()

    @Test
    @ChangeLocale("de")
    fun localeChangedByAnnotation() {
        assertEquals("de", Locale.getDefault().language)
    }

    @Test
    fun localeChangedByRule() {
        // This will fail if you run on a JVM with a German locale.
        assertNotEquals("de", Locale.getDefault().language)
    }
}
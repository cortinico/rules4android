package com.ncorti.rules4android.exampleapp

import com.ncorti.rules4android.*
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class LocaleRuleTest {

    @get:Rule val rule = LocaleRule(Locale.ITALIAN)

    @Test
    @ChangeLocale("de")
    fun localeChangedByAnnotation() {
        assertEquals("de", Locale.getDefault().language)
    }

    @Test
    fun localeChangedByRule() {
        assertEquals("it", Locale.getDefault().language)
    }
}
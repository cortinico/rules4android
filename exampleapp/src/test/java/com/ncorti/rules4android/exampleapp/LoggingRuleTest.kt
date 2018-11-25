package com.ncorti.rules4android.exampleapp

import com.ncorti.rules4android.TimingRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoggingRuleTest {

    @get:Rule
    val rule = TimingRule()

    @Test
    fun shortTest() {
        assertEquals(42, 42)
    }

    @Test
    fun longTest() {
        Thread.sleep(1234)
        assertEquals(42, 42)
    }
}
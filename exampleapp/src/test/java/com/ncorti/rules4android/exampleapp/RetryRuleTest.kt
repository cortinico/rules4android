package com.ncorti.rules4android.exampleapp

import com.ncorti.rules4android.RetryOnFailure
import com.ncorti.rules4android.RetryRule
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*

class RetryRuleTest {

    @get:Rule val rule = RetryRule()

    @Test
    @RetryOnFailure(1000)
    fun testingRetryOnFailure_withRandomValues() {
        assertEquals(2, (Math.random() * 10).toInt())
    }

    @Test
    @RetryOnFailure
    fun testingRetryOnFailure_withSuccess() {
        assertEquals(1, 1)
    }
}
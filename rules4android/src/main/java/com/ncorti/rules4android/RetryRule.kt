package com.ncorti.rules4android

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

annotation class RetryOnFailure(val value: Int = 2)

class RetryRule : TestRule {
    override fun apply(statement: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val retryCount = description
                        .annotations
                        .filterIsInstance<RetryOnFailure>()
                        .firstOrNull()
                        ?.value ?: 0

                var failureCause: Throwable? = null

                repeat(retryCount + 1) { _ ->
                    runCatching { statement.evaluate() }
                            .onSuccess { return }
                            .onFailure { failureCause = it }
                }

                println("Test ${description.className}:${description.methodName} - Giving up after ${retryCount + 1} attemps")
                failureCause?.printStackTrace()
            }
        }
    }
}
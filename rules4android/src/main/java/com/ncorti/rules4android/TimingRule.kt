package com.ncorti.rules4android

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TimingRule(val printCsv : Boolean = false) : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val startTime = System.currentTimeMillis()
                try {
                    base.evaluate()
                } finally {
                    val endTime = System.currentTimeMillis()
                    val time = endTime - startTime
                    if (printCsv) {
                        println("${description.className}:${description.methodName};$time")
                    } else {
                        println("Test ${description.className}:${description.methodName} took $time ms")
                    }
                }
            }
        }
    }
}
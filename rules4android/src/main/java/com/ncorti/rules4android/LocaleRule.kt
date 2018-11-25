package com.ncorti.rules4android

import android.os.Build
import android.support.test.InstrumentationRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.*


annotation class ChangeLocale(val value: String)

class LocaleRule @JvmOverloads constructor(
        var newLocale: Locale? = null
) : TestRule {

    var previousLocale: Locale? = null

    override fun apply(statement: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val annotationLocale = description
                        .annotations
                        .filterIsInstance<ChangeLocale>()
                        .firstOrNull()
                        ?.value

                if (annotationLocale != null) {
                    newLocale = Locale(annotationLocale)
                }

                if (newLocale != null) {
                    previousLocale = Locale.getDefault()
                    changeLocale(newLocale)
                }

                try {
                    statement.evaluate()
                } finally {
                    if (previousLocale != null) {
                        changeLocale(previousLocale)
                    }
                }
            }
        }
    }

    private fun changeLocale(locale: Locale?) {
        Locale.setDefault(locale)
        try {
            val resources = InstrumentationRegistry.getTargetContext().resources
            val config = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(locale)
            } else {
                config.locale = locale
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                InstrumentationRegistry.getTargetContext().createConfigurationContext(config)
            } else {
                resources.updateConfiguration(config, resources.displayMetrics)
            }
        } catch (ignored : IllegalStateException) {
            // no-op
        }
    }
}
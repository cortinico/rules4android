# @Rules 4 Android

[![CircleCI](https://circleci.com/gh/cortinico/rules4android/tree/master.svg?style=shield)](https://circleci.com/gh/cortinico/rules4android/tree/master)  [ ![Download](https://api.bintray.com/packages/cortinico/maven/rules4android/images/download.svg) ](https://bintray.com/cortinico/maven/rules4android/_latestVersion) [![License](https://img.shields.io/badge/license-MIT%20License-brightgreen.svg)](https://opensource.org/licenses/MIT) [![Twitter](https://img.shields.io/badge/Twitter-@cortinico-blue.svg?style=flat)](http://twitter.com/cortinico)

A collection of JUnit Rules that can be helpful to Android Developers. Do you have any idea for a JUnit rule that could be helpful for everyone? Feel free to open an Issue or a Pull request!

## Getting Started 👣

**rules4android** is distributed through [JCenter](https://bintray.com/bintray/jcenter?filterByPkgName=rules4android). To use it you need to add the following **Gradle dependency** to your **android app gradle file** (NOT the root file).

```groovy
dependencies {
   testImplementation 'com.ncorti:rules4android:1.0.0'
}
```

or if you need it from your Espresso tests:

```groovy
dependencies {
   androidTestImplementation 'com.ncorti:rules4android:1.0.0'
}
```

### RetryRule

You can use a `RetryRule` to retry tests that might be flaky, just by annotating them with a `@RetryOnFailure`. By default, annotated tests are retried other 2 times. You can specify the retry count in the annotation. If the test fails more than `1 + retryCount` times, a message will be printed out on the console.

Example:

```kotlin
class ExampleTest {

    @get:Rule val rule = RetryRule()

    @Test
    @RetryOnFailure(10) // 10 can be omitted, will default to 2.
    fun aFlakyTest() {
        assertEquals(2, Math.random().toInt())
    }
}
```

More examples can be found in the `RetryRuleTest.kt` file.

### LoggingRule

You can use a `LoggingRule` to print out the execution time of every test. If you need more structured data for further processing of your tests, you can pass a flag to the Rule to enable the CSV output.

Example:

```kotlin
class ExampleTest {

    @get:Rule val rule = TimingRule(printCsv = false)

    @Test
    fun aLongTest() {
        Thread.sleep(1000)
        assertEquals(42, 42)
    }
}
```

Will print on the console:
```
ExampleTest:aLongTest took 1001 ms
```

More examples can be found in the `LoggingRuleTest.kt` file.

### LocaleRule

You can use a `LocaleRule` to change locale of the device/JVM. The rule has support for both JUnit and Espresso tests. You can either pass a locale via the `@ChangeLocale` annotation, or via a parameter in the Rule constructor.

Example:

```kotlin
class ExampleTest {

    @get:Rule val rule = LocaleRule(Locale.ITALIAN)

    @Test
    fun anItalianTest() {
        // Locale Changed by constructor parameter.
        assertEquals("it", Locale.getDefault().language)
    }

    @Test
    @ChangeLocale("de")
    fun aGermanTest() {
        // Locale Changed by annotation.
        assertEquals("de", Locale.getDefault().language)
    }
}
```

Will print on the console:
```
ExampleTest:aLongTest took 1001 ms
```

More examples can be found in the `LoggingRuleTest.kt` file.

## Contributing 🤝

**Looking for contributors! Don't be shy.** 😁 Feel free to open issues/pull requests to help me improve this project.

## License 📄

This project is licensed under the MIT License - see the [License](License) file for details

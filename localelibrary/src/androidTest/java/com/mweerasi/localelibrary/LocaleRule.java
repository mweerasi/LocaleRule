package com.mweerasi.localelibrary;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;


/**
 * LocaleRule is used to switch the device locale.
 * To use you must add it as an @Rule.
 * Called before a test using the annotation @LocaleRule.TestLocale("xx-XX").
 * Where "xx-XX" is the locale desired, e.g. "ar-EG" for Arabic Egypt.
 */

public class LocaleRule implements TestRule {

    private Locale deviceLocale;

    @Override
    public Statement apply(final Statement base, Description description) {
        final TestLocale testLocaleAnnotation =
                description.getAnnotation(TestLocale.class);
        final String languageCodeFromTestMethod =
                testLocaleAnnotation.value();
        final Locale testLocale = Locale.forLanguageTag(
                languageCodeFromTestMethod);

        deviceLocale = Locale.getDefault();

        if (languageCodeFromTestMethod.length() > 0) {
            setLocale(testLocale);
        } else {
            Log.e("CustomLocaleRule", "Can't set test locale.");
        }

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } finally {
                    setLocale(deviceLocale);
                }
            }
        };
    }

    private void setLocale(Locale locale) {
        Locale.setDefault(locale);

        final Resources resources = InstrumentationRegistry
                .getTargetContext().getResources();
        final Configuration config = resources
                .getConfiguration();

        config.locale = locale;
        resources.updateConfiguration(config,
                resources.getDisplayMetrics());
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface TestLocale {
        String value() default "";
    }
}


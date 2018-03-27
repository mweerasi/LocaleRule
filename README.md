`Initial Code Development Finished`

## ToDo: Upload to Maven Central and/or jcenter 

# LocaleRule
Simple way to change device locale for Android Automation


## Setup
Download .aar file from `https://bintray.com/mweerasi/locale-rule/localeRule#files/LocaleRule%2Flocale-rule%2Funspecified`

Add it to the app:build.gradle `implementation(name: 'locale-rule:unspecified', ext: 'aar')`

Add it to settings.gradle `include ':locale-rule'`

## Usage
To implement the rule for a test class, add the rule with the following line
```
@Rule
public final LocaleRule localeRule = new LocaleRule();
```
Once the rule is added you can change a test to a specified language by adding ```@TestLocale("")``` before a test. The string inside should hold the locale you wish to run the test in.
For example, to run a test in ```da-DK```:
```
@Test
@TestLocale("da-DK")
public void openInDanishDenmark() {
    ...
}
```

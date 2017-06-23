![logo](https://raw.githubusercontent.com/adrielcafe/MoovAndroidApp/dev/logo.png)

**Moov** is an open source Android app made for test some architecture and UX concepts.

![logo](https://raw.githubusercontent.com/adrielcafe/MoovAndroidApp/dev/screenshots/0_all_screens.png)

## Demo Video
[![demo](https://img.youtube.com/vi/egYuyqEtFGo/0.jpg)](https://www.youtube.com/watch?v=egYuyqEtFGo)

## Instructions

### The Movie Database API Key
Add your API Key on [Constant.kt](/app/src/main/java/cafe/adriel/moov/Constant.kt).
```kotlin
const val TMDB_KEY = "ADD_HERE"
```

### Espresso tests
Espresso tests can be found on [MainActivityTest.java](/app/src/androidTest/java/cafe/adriel/moov/view/activity/MainActivityTest.java). It covers the below interactions:
* Select movie
* See movie details
* Search for movies

Run with:
```
./gradlew connectedAndroidTest
```

## Made with
* [Kotlin](https://kotlinlang.org/) (Because is better than Java :trollface:)
* [RxJava](https://github.com/ReactiveX/RxJava), [RxAndroid](https://github.com/ReactiveX/RxAndroid) and [RxKotlin](https://github.com/ReactiveX/RxKotlin) (For transform Retrofit's Call<> into Flowable<> and avoid the "Callback Hell" :fire:)
* [Retrofit](https://github.com/square/retrofit) (The best type-safe HTTP client :muscle:)
* [Glide](https://github.com/bumptech/glide) (My favorite image loading and caching library :heart:)
* [FastAdapter](https://github.com/mikepenz/FastAdapter) (The fast and easy to use adapter library :punch:)
* [IceKick](https://github.com/tinsukE/icekick) (For save the app state :snowflake:)
* [Iconify](https://github.com/JoanZapata/android-iconify) (Because vector icons are the best :squirrel:)
* [DiagonalLayout](https://github.com/florent37/DiagonalLayout) (Because I'm bold :sunglasses:)
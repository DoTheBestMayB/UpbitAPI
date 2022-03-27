object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}" }
}

object Androidx {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.CORE_KTX}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.APP_COMPAT}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.MATERIAL}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}" }
}

object Network {
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}" }
    val gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.GSON}" }
    val converterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.GSON}" }
    val okhttp3 by lazy { "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3}" }

    // testImplementation
    val mockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP3}" }
}

object RxJava {
    val rxjava3 by lazy { "io.reactivex.rxjava3:rxjava:${Versions.RXJAVA3}" }
    val rxjava3RetrofitAdapter by lazy { "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.RXJAVA3_RETROFIT_ADAPTER}" }
}

object Test {
    // testImplementation
    val jUnit by lazy { "junit:junit:${Versions.JUNIT}" }
    val truth by lazy { "com.google.truth:truth:${Versions.TRUTH}"}
    val testTruth by lazy { "androidx.test.ext:truth:${Versions.TEST_TRUTH}"}

    // androidTestImplementation
    val androidxJunit by lazy { "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}" }
}
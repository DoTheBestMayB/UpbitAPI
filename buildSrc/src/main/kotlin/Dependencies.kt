object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

object Androidx {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
}

object Network {
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.gson}" }
    val converterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.gson}" }
    val okhttp3 by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp3}" }

    // testImplementation
    val mockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3}" }
}

object RxJava {
    val rxjava3 by lazy { "io.reactivex.rxjava3:rxjava:${Versions.rxjava3}" }
}

object Test {
    // testImplementation
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val truth by lazy { "com.google.truth:truth:${Versions.truth}"}
    val testTruth by lazy { "androidx.test.ext:truth:${Versions.testTruth}"}

    // androidTestImplementation
    val androidxJunit by lazy { "androidx.test.ext:junit:${Versions.androidxJunit}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }
}
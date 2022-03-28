object BuildPlugins {
    const val ANDROID = "com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
}

object Androidx {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val MATERIAL_DESIGN = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
}

object Network {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val GSON = "com.squareup.retrofit2:converter-gson:${Versions.GSON}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.GSON}"
    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3}"

    // testImplementation
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP3}"
}

object RxJava {
    const val RXJAVA3 = "io.reactivex.rxjava3:rxjava:${Versions.RXJAVA3}"
    const val RXJAVA3_RETROFIT_ADAPTER = "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.RXJAVA3_RETROFIT_ADAPTER}"
}

object Test {
    // testImplementation
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val TRUTH = "com.google.truth:truth:${Versions.TRUTH}"
    const val TEST_TRUTH = "androidx.test.ext:truth:${Versions.TEST_TRUTH}"

    // androidTestImplementation
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}
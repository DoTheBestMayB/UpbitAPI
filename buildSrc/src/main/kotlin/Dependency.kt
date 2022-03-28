object BuildPlugins {
    const val ANDROID = "com.android.tools.build:gradle:${Version.GRADLE_PLUGIN}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
}

object Androidx {
    const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"
    const val MATERIAL_DESIGN = "com.google.android.material:material:${Version.MATERIAL}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}"
}

object Network {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val GSON = "com.squareup.retrofit2:converter-gson:${Version.GSON}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Version.GSON}"
    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${Version.OKHTTP3}"

    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Version.OKHTTP3}"
}

object RxJava {
    const val RXJAVA3 = "io.reactivex.rxjava3:rxjava:${Version.RXJAVA3}"
    const val RXJAVA3_RETROFIT_ADAPTER = "com.github.akarnokd:rxjava3-retrofit-adapter:${Version.RXJAVA3_RETROFIT_ADAPTER}"
}

object Test {
    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val TRUTH = "com.google.truth:truth:${Version.TRUTH}"
    const val TEST_TRUTH = "androidx.test.ext:truth:${Version.TEST_TRUTH}"

    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Version.ANDROIDX_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"
}
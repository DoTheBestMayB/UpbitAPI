object BuildPlugins {
    private const val GRADLE_PLUGIN_VERSION = "7.1.2"
    private const val KOTLIN_VERSION = "1.5.21"

    const val ANDROID = "com.android.tools.build:gradle:${GRADLE_PLUGIN_VERSION}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KOTLIN_VERSION}"
}

object Androidx {
    private const val CORE_KTX_VERSION = "1.7.0"
    private const val APP_COMPAT_VERSION = "1.4.1"
    private const val MATERIAL_VERSION = "1.5.0"
    private const val CONSTRAINT_LAYOUT_VERSION = "2.1.3"
    private const val FRAGMENT_KTX_VERSION = "1.3.0"

    const val CORE_KTX = "androidx.core:core-ktx:${CORE_KTX_VERSION}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${APP_COMPAT_VERSION}"
    const val MATERIAL_DESIGN = "com.google.android.material:material:${MATERIAL_VERSION}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${CONSTRAINT_LAYOUT_VERSION}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${FRAGMENT_KTX_VERSION}"
}

object Network {
    private const val RETROFIT_VERSION = "2.9.0"
    private const val GSON_VERSION = "2.9.0"
    private const val OKHTTP3_VERSION = "4.9.3"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${RETROFIT_VERSION}"
    const val GSON = "com.squareup.retrofit2:converter-gson:${GSON_VERSION}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${GSON_VERSION}"
    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${OKHTTP3_VERSION}"

    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${OKHTTP3_VERSION}"
}

object RxJava {
    private const val RXJAVA3_VERSION = "3.1.3"
    private const val RXJAVA3_RETROFIT_ADAPTER_VERSION = "3.0.0"

    const val RXJAVA3 = "io.reactivex.rxjava3:rxjava:${RXJAVA3_VERSION}"
    const val RXJAVA3_RETROFIT_ADAPTER = "com.github.akarnokd:rxjava3-retrofit-adapter:${RXJAVA3_RETROFIT_ADAPTER_VERSION}"
}

object Test {
    private const val JUNIT_VERSION = "4.13.2"
    private const val ANDROIDX_JUNIT_VERSION = "1.1.3"
    private const val ESPRESSO_CORE_VERSION = "3.4.0"
    private const val TRUTH_VERSION = "1.1.3"
    private const val TEST_TRUTH_VERSION = "1.4.0"

    const val JUNIT = "junit:junit:${JUNIT_VERSION}"
    const val TRUTH = "com.google.truth:truth:${TRUTH_VERSION}"
    const val TEST_TRUTH = "androidx.test.ext:truth:${TEST_TRUTH_VERSION}"

    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${ANDROIDX_JUNIT_VERSION}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${ESPRESSO_CORE_VERSION}"
}
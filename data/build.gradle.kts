plugins {
    id("java-library")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    with(Network) {
        implementation(RETROFIT)
        implementation(GSON)
        implementation(CONVERTER_GSON)
        implementation(OKHTTP3)

        testImplementation(MOCK_WEB_SERVER)
    }

    with(RxJava) {
        implementation(RXJAVA3)
        implementation(RXJAVA3_RETROFIT_ADAPTER)
    }

    with(Test) {
        implementation(JUNIT)
        implementation(TRUTH)

        testImplementation(ANDROIDX_JUNIT)
        testImplementation(ESPRESSO_CORE)
    }
}
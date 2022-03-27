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
        implementation(retrofit)
        implementation(gson)
        implementation(converterGson)
        implementation(okhttp3)

        testImplementation(mockWebServer)
    }

    with(RxJava) {
        implementation(rxjava3)
        implementation(rxjava3RetrofitAdapter)
    }

    with(Test) {
        implementation(jUnit)
        implementation(truth)

        testImplementation(androidxJunit)
        testImplementation(espressoCore)
    }
}
plugins {
    id("java-library")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    Network.apply {
        implementation(retrofit)
        implementation(gson)
        implementation(converterGson)
        implementation(okhttp3)

        testImplementation(mockWebServer)
    }

    RxJava.apply {
        implementation(rxjava3)
        implementation(rxjava3RetrofitAdapter)
    }

    Test.apply {
        implementation(jUnit)
        implementation(truth)

        testImplementation(androidxJunit)
        testImplementation(espressoCore)
    }
}
plugins {
    id("java-library")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":domain"))

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
}
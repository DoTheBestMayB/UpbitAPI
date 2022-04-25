plugins {
    id("java-library")
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(RxJava.RXJAVA3)

    with(Hilt) {
        implementation(HILT_CORE)
        kapt(HILT_COMPILER)
    }

    with(Test) {
        implementation(JUNIT)
        implementation(MOCKK)
    }
}
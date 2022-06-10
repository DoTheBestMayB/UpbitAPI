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
        implementation(CORE)
        kapt(COMPILER)
    }

    with(Test) {
        testImplementation(JUNIT)
        testImplementation(MOCKK)
    }
}
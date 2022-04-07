plugins {
    id("java-library")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    with(RxJava) {
        implementation(RXJAVA3)
    }

    with(Test) {
        implementation(JUNIT)
        implementation(MOCKK)
    }
}
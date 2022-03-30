plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    with(ConfigData) {
        compileSdk = COMPILE_SDK_VERSION

        defaultConfig {
            applicationId = APPLICATION_ID
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            versionCode = VERSION_CODE
            versionName = VERSION_NAME

            testInstrumentationRunner = ANDROID_JUNIT_RUNNER
        }
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    with(Androidx) {
        implementation(APP_COMPAT)
        implementation(CONSTRAINT_LAYOUT)
        implementation(MATERIAL_DESIGN)
    }
}
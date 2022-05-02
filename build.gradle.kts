// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(Repository.MAVEN)
    }
    dependencies {
        with(BuildPlugins) {
            classpath(ANDROID)
            classpath(KOTLIN)
            classpath(HILT_ANDROID)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
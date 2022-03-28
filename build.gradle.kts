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
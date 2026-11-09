plugins {
    id("com.android.application")
}

android {
    namespace = "md.balance"
    compileSdk = 37

    defaultConfig {
        applicationId = "md.balance"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

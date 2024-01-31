
plugins {
    alias(libs.plugins.almatar.android.application)
    alias(libs.plugins.almatar.android.application.compose)
    alias(libs.plugins.almatar.android.hilt)
}

android {
    namespace = "com.almatar.shoppingapp"

    defaultConfig {
        applicationId = "com.almatar.shoppingapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(project(":feature:home"))
    implementation(project(":feature:upsert"))

    implementation(project(":core:common"))
    implementation(project(":core:design-system"))
    implementation(project(":core:data"))
    implementation(project(":core:data"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines.guava)


    testImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:testing"))

    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)


}
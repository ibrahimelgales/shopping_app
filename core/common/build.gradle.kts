
plugins {
    alias(libs.plugins.almatar.android.library)
    alias(libs.plugins.almatar.android.hilt)
}

android {
    namespace = "com.almatar.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
}
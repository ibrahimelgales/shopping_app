plugins {
    alias(libs.plugins.almatar.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.almatar.core.domain"
}

dependencies {
    api(project(":core:data"))

    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.test)

    testImplementation(project(":core:testing"))
}
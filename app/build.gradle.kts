plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //KAPT
    id("kotlin-kapt")

//    kotlin("android") version "1.5.30"
//    kotlin("kapt") version "1.5.30"
//    id("com.google.devtools.ksp") version "1.5.30"  id("com.android.library")
}

android {
    namespace = "com.example.cryptoapp"
    //поменять на 34
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cryptoapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}
dependencies {

        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")



    implementation ("androidx.work:work-runtime-ktx:2.8.1")

    // Kotlin
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

    // RxJava 3
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.8")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // Retrofit
    implementation( "com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Picasso for image loading
    implementation ("com.squareup.picasso:picasso:2.8")

    // Gson for JSON serialization/deserialization
    implementation ("com.google.code.gson:gson:2.10.1")

    // Room Database
    implementation ("androidx.room:room-runtime:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")
    kapt ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
    // AndroidX Core and UI components
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    // Testing dependencies
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
}

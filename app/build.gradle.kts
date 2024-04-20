plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.kashapovrush.ticketssearchtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kashapovrush.ticketssearchtest"
        minSdk = 26
        targetSdk = 34
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core:network"))
    implementation(project(":features-mobile:common"))
    implementation(project(":features-mobile:palette"))
    implementation(project(":features-mobile:main-screen"))
    implementation(project(":features-mobile:subscribe-screen"))
    implementation(project(":features-mobile:profile-screen"))
    implementation(project(":features-mobile:geo-screen"))
    implementation(project(":features-mobile:weekend-screen"))
    implementation(project(":features-mobile:hot-tickets-screen"))
    implementation(project(":features-mobile:hard-screen"))
    implementation(project(":features-mobile:hotels-screen"))
    implementation(project(":features-mobile:country-selected-screen"))
    implementation(project(":features-mobile:tickets-screen"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.fragment.manager)

    implementation(libs.dagger.core)
    ksp(libs.dagger.compiler)
}
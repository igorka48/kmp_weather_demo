import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
    }

    // By default the android gradle plugin expects to find the kotlin source files in
    // the folder `main` and the test in the folder `test`. This is to be able place
    // the source code files inside androidMain and androidTest folders
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs(file("src/androidMain/kotlin"))
            res.srcDirs(file("src/androidMain/res"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }

}

val ktor_version = "1.3.2"
val coroutines_version = "1.3.3"
val serialization_version = "0.20.0"
val moko_mvvm_version = "0.6.0"
val androidx_lifecycle_version = "2.0.0"
val kodeinVersion = "6.5.3"

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "SharedCode"
            }
        }
    }


    targets {
        targetFromPreset(presets.getByName("android"), "android")
    }


    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization_version")
        implementation("io.ktor:ktor-client-core:$ktor_version")
        implementation("io.ktor:ktor-client-serialization:$ktor_version")
        implementation("dev.icerock.moko:mvvm:$moko_mvvm_version")
        implementation ("org.kodein.di:kodein-di-core:$kodeinVersion")
        implementation ("org.kodein.di:kodein-di-erased:$kodeinVersion")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        api ("io.ktor:ktor-client-core:$ktor_version")
        api ("io.ktor:ktor-client-android:$ktor_version")
        api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
        api ("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")
        implementation ("androidx.lifecycle:lifecycle-extensions:$androidx_lifecycle_version")
    }

    sourceSets["iosMain"].dependencies {
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutines_version")
        api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization_version")
        api("io.ktor:ktor-client-ios:$ktor_version")
        api("io.ktor:ktor-client-serialization-native:$ktor_version")
    }


}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText(
            "#!/bin/bash\n"
                    + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                    + "cd '${rootProject.rootDir}'\n"
                    + "./gradlew \$@\n"
        )
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)
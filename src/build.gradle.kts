import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.internal.CompileOptions
import com.android.build.gradle.internal.dsl.BuildType
import com.android.builder.core.DefaultApiVersion
import com.android.builder.core.DefaultProductFlavor
import com.android.builder.model.ApiVersion
import me.tatarka.RetrolambdaPlugin
import org.gradle.api.JavaVersion

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper

import java.lang.System

buildscript {
    //Temporary hack until Android plugin has proper support
    System.setProperty("com.android.build.gradle.overrideVersionCheck", "true")

    repositories {
        jcenter()
        gradleScriptKotlin()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:2.2.0-alpha6")
        classpath("me.tatarka:gradle-retrolambda:3.2.5")
        classpath(kotlinModule("gradle-plugin"))
    }
}

repositories {
    jcenter()
    gradleScriptKotlin()
    maven {
        setUrl("https://jitpack.io")
    }
}


apply {
    plugin<AppPlugin>()
    plugin<KotlinAndroidPluginWrapper>()
    plugin<RetrolambdaPlugin>()
}

android {
    buildToolsVersion("24.0.1")
    compileSdkVersion(24)

    compileOptions {
        it.setSourceCompatibility(JavaVersion.VERSION_1_8)
        it.setTargetCompatibility(JavaVersion.VERSION_1_8)
    }


    defaultConfigExtension {
        setMinSdkVersion(15)
        setTargetSdkVersion(23)

        applicationId = "com.tomaszpolanski.androidsandbox"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypesExtension {
        release {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}


dependencies {
    compile(kotlinModule("stdlib"))
    compile("com.android.support:appcompat-v7:23.4.0")
    compile("io.reactivex:rxjava:1.1.5")
    compile("io.reactivex:rxandroid:1.2.0")
    compile("com.android.support:design:23.4.0")
    compile ("com.github.tomaszpolanski:options:1.2.0")
}

//Extension functions to allow comfortable references
fun Project.android(setup: AppExtension.() -> Unit) = the<AppExtension>().setup()

fun NamedDomainObjectContainer<BuildType>.release(setup: BuildType.() -> Unit) = findByName("release").setup()

fun AppExtension.defaultConfigExtension(setup: DefaultProductFlavor.() -> Unit) = defaultConfig.setup()

fun AppExtension.buildTypesExtension(setup: NamedDomainObjectContainer<BuildType>.() -> Unit) = buildTypes { it.setup() }

fun DefaultProductFlavor.setMinSdkVersion(value: Int) = setMinSdkVersion(value.asApiVersion())

fun DefaultProductFlavor.setTargetSdkVersion(value: Int) = setTargetSdkVersion(value.asApiVersion())

fun Int.asApiVersion(): ApiVersion = DefaultApiVersion.create(this)

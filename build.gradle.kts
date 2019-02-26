import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradleWrapperVersion by extra { "5.2.1" }
val kotlinVersion by extra { "1.3.21" }

plugins {
    val kotlinVersion = "1.3.21"

    kotlin("jvm") version kotlinVersion
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib", kotlinVersion))
    compile(kotlin("stdlib-jdk7", kotlinVersion))
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile(files("vendor/OpenJUMP-1.14-r6065-CORE/lib/OpenJUMP-1.14-r6065-nosrc.jar"))
    compile(files("vendor/OpenJUMP-1.14-r6065-CORE/lib/jts-core-1.14.0.jar"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks {
    withType<Wrapper> {
        gradleVersion = gradleWrapperVersion
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

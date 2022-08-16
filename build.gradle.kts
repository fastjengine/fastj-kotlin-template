/*
 * FastJ Kotlin Template Build Script
 *
 * Thank you for choosing FastJ! This is a Gradle-based build script that will help you manage your
 * project with ease. */

plugins {
    /* To begin with, Gradle needs the "kotlin" plugin so that it knows this is a Kotlin project. */
    kotlin("jvm") version "1.7.10"
    /* This template is for an application -- we"ll need this plugin to make sure Gradle knows
     * this, too. */
    application
    /* To create distributable files for your game, we use this jlink plugin. */
    id("org.beryx.jlink") version "2.25.0"
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDirectory.set(compileKotlin.destinationDirectory.get())

/* Your project's group name goes here.
 * This should be a domain name you own.
 * If you don't own a domain, don"t worry! You can always set this to "io.github.yourgithubusername". */
group = "io.github.yourgithubusername"

/* Your project's version.
 * When you want to distribute a new version of your project, always make sure to update the
 * project version. */
version = "0.0.1"

/* Your project's description.
 * Give a one or two sentence description of your project -- if you publish it somewhere, you"ll be
 * able to use it. */
description = "A Kotlin Template for FastJ."

/* Here, we specify where the main entrypoint of the project.
 * Feel free to change this as needed. */
application.mainModule.set("fastj.templategame")
application.mainClass.set("tech.fastj.template.GameKt")


/* When you add a dependency on another project (like FastJ), you need to add specify where the
 * dependencies are coming from!
 * FastJ is hosted on Maven Central and Jitpack.io, so we"ll add the jitpack.io dependency here. */
repositories.maven {
    setUrl("https://jitpack.io/")
}
repositories.mavenCentral()

/* The dependency for FastJ, the game engine this template depends on. */
dependencies.implementation("io.github.lucasstarsz.fastj:fastj-library:1.7.0-SNAPSHOT-1")
/* We'll stick with the simplest logging option for now -- you can change it however you need. */
dependencies.implementation("org.slf4j:slf4j-simple:2.0.0-alpha7")

/* To make Kotlin compile and run properly with Gradle, this adds your Kotlin code to the Java
 * source sets. */
sourceSets.main {
    java.srcDirs("src/main/myJava", "src/main/myKotlin")
}


/* The Runtime plugin is used to configure the executables and other distributions for your
 * project. */
jlink {

    options.addAll(
        "--strip-debug",
        "--compress", "2",
        "--no-header-files",
        "--no-man-pages"
    )

    launcher {
        name = project.name
        noConsole = true
    }

    jpackage {

        /* Use this to define the path of the icons for your project. */
        val iconPath = "project-resources/fastj_icon"
        val currentOs = org.gradle.internal.os.OperatingSystem.current()

        when {
            currentOs.isWindows -> imageOptions = listOf("--icon", "${iconPath}.ico")
            currentOs.isLinux -> imageOptions = listOf("--icon", "${iconPath}.png")
            currentOs.isMacOsX -> imageOptions = listOf("--icon", "${iconPath}.icns")
        }

        /* Comment the line below to create an installer for your application */
        skipInstaller = true

        if (!skipInstaller) {
            installerOptions.addAll(
                listOf(
                    "--description", project.description as String,
                    "--vendor", project.group as String,
                    "--app-version", project.version as String
                )
            )

            when {
                currentOs.isWindows -> {
                    installerType = "msi"
                    installerOptions.addAll(listOf("--win-per-user-install", "--win-dir-chooser", "--win-shortcut"))
                }

                currentOs.isLinux -> {
                    installerType = "deb"
                    installerOptions.addAll(listOf("--linux-shortcut"))
                }

                currentOs.isMacOsX -> {
                    installerType = "pkg"
                    installerOptions.addAll(listOf("--mac-package-name", project.name))
                }
            }
        }
    }
}


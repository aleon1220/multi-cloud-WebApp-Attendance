import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    java
    id ("com.adarshr.test-logger") version "3.0.0"
    // https://docs.gradle.org/7.3/dsl/org.gradle.api.tasks.bundling.War.html
    war
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://repository.primefaces.org")
    }
    // maven {    //     url = uri("https://repo.maven.apache.org/maven2/")    // }
}

dependencies {
    implementation("org.primefaces.extensions:primefaces-extensions:6.0.0")
    implementation("org.primefaces:primefaces:6.0")
    implementation("com.sun.faces:jsf-api:2.2.12")
    implementation("com.sun.faces:jsf-impl:2.2.12")
    implementation("javax.json:javax.json-api:1.1")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1")
    implementation("com.sun.jersey:jersey-client:1.19.4")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.apache.httpcomponents:httpclient:4.5.3")
    implementation("org.glassfish.jersey.core:jersey-common:2.22.2")
    implementation("javax.servlet:javax.servlet-api:3.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testImplementation("io.jsonwebtoken:jjwt:0.7.0")
}

group = "soa.nz.aut"
version = "0.0.1"
description = "Class Attendance WebApp"
java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks.war {
    archiveBaseName.set("Attendance")
    webAppDirectory.set(file("src/main/webapp"))
    // from("src/rootContent") // adds a file-set to the root of the archive
    // webInf { from("src/additionalWebInf") } // adds a file-set to the WEB-INF dir.
    // classpath(fileTree("additionalLibs")) // adds a file-set to the WEB-INF/lib dir.
    // classpath(moreLibs) // adds a configuration to the WEB-INF/lib dir.
    // webXml = file("src/someWeb.xml") // copies a file to WEB-INF/web.xml
}

tasks {
  test {
      useJUnitPlatform()
      testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)      
  }
}

// task{
//     testlogger{
//         setTheme("mocha")
//         showExceptions=true
//         showStackTraces=true
//     }
// }

// afterEvaluate {
//     tasks.getByName("run") {
//         dependsOn("appRun")
//     }
// }
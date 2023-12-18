import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    // id ("com.adarshr.test-logger") version "3.0.0"
    // https://docs.gradle.org/7.3/dsl/org.gradle.api.tasks.bundling.War.html
    war
    id ("jacoco")
    id("com.github.bjornvester.wsdl2java") version "2.0.2"
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation("org.primefaces.extensions:primefaces-extensions:6.0.0")
    implementation("org.primefaces:primefaces:6.0")
    implementation("com.sun.faces:jsf-api:2.2.12")
    implementation("com.sun.faces:jsf-impl:2.2.12")
    implementation("javax.json:javax.json-api:1.1")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1")
    implementation("com.sun.jersey:jersey-client:1.19.4")
    // https://github.com/google/gson library JSON serialization/deserialization
    implementation("com.google.code.gson:gson:2.10.1")
    // https://bitbucket.org/snakeyaml/snakeyaml/wiki/Documentation
    // https://mavenlibs.com/maven/dependency/org.yaml/snakeyaml
    implementation("org.yaml:snakeyaml:2.1")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.glassfish.jersey.core:jersey-common:2.22.2")
    implementation("javax.servlet:javax.servlet-api:3.1.0")
    // QRFunctions
    implementation("com.google.zxing:javase:3.5.1")
    implementation("com.google.zxing:core:3.5.1")
    // dependency below only needed if using the Java 8 version of @Generated (through "jdk8") on Java 9 or later
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("io.github.threeten-jaxb:threeten-jaxb-core:2.1.0") // Use Java Date/Time API. Clunky GregorianCalendar class
    // JUnit Jupiter API and Engine for unit testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    // Mockito for mocking in tests
    testImplementation("org.mockito:mockito-core:4.5.1")
    // Authentication
    testImplementation("io.jsonwebtoken:jjwt:0.9.1")
    // https://central.sonatype.com/artifact/com.unboundid/unboundid-ldapsdk
    implementation("com.unboundid:unboundid-ldapsdk:6.0.11")
}

group = "soa.nz.aut"
version = "0.7.3"
description = "Student Attendance WebApp"
java.sourceCompatibility = JavaVersion.VERSION_17

fun setWarVersion() {
  // function body
    project.version?.let { version ->
        println("WAR Version is $version")
        System.setProperty("APP_WAR_FILE_VERSION", version.toString())
        println("For local testing do APP_WAR_FILE_VERSION=" + version.toString() )
        println("Append to the .env file \n")
        println("printf \"\$APP_WAR_FILE_VERSION \\n\" >> .env")
    }
}

fun getWarpackageVersion() {
  // function body
    project.version?.let { version ->
        println("WAR Version is $version")
        println("set variable with")
        println("export APP_WAR_FILE_VERSION=" + version.toString())
    }
}

tasks.register<DefaultTask>("getProjectInfo") {
    description = "Obtains detailed info about the java web project"
    setWarVersion()
}

tasks.war {
    archiveBaseName.set("Attendance")
    webAppDirectory.set(file("src/main/webapp"))
    // from("src/rootContent") // adds a file-set to the root of the archive
    // webInf { from("src/additionalWebInf") } // adds a file-set to the WEB-INF dir.
    // classpath(fileTree("additionalLibs")) // adds a file-set to the WEB-INF/lib dir.
    // classpath(moreLibs) // adds a configuration to the WEB-INF/lib dir.
    // webXml = file("src/someWeb.xml") // copies a file to WEB-INF/web.xml
    doLast{
        setWarVersion()
        println("WAR file version set")
    }
}

tasks {
    named<JacocoReport>("jacocoTestReport") {
        reports {
        }
    }
    test {
        useJUnitPlatform()
        testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
    }
}

tasks.register<Test>("singleTestQRFunctionality") {
    group = "Verification"
    description = "Runs a test to create a local QR code"
    filter {
        includeTestsMatching("utilities.TestQRFunctions.createQRCodeLocally")
    }
}

tasks.register<Test>("mockOpenLDAP") {
    description = "Runs openLDAP mock authentication testing"
    group = "LDAP_Testing"
    filter {
        includeTestsMatching("TestLDAPAuthentication.testMockOpenLDAPAdminSearch") 
    }
}

tasks.register<Test>("searchOpenLDAP") {
    description = "Runs openLDAP search"
    group = "LDAP_Testing"
    filter {
        includeTestsMatching("TestLDAPAuthentication.testOpenLDAPAdminSearch_withOpenLDAP")
    }
}

tasks.register<DefaultTask>("getAppversion") {
    description = "Obtains WAR version from Gradle file"
    getWarpackageVersion()
}

wsdl2java {
    // https://plugins.gradle.org/plugin/com.github.bjornvester.wsdl2java
    bindingFile.set(layout.projectDirectory.file("src/main/bindings/bindings.xjb"))

    includes.set(
        listOf(
        "src/main/resources/wsdl/NumberConversion.wsdl"
        )
    )
}
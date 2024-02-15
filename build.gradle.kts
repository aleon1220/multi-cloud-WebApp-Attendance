import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.net.InetAddress

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
    // https://www.primefaces.org
    // https://mvnrepository.com/artifact/org.primefaces/primefaces
    implementation("org.primefaces:primefaces:8.0")
    // https://mvnrepository.com/artifact/org.primefaces.extensions/primefaces-extensions
    implementation("org.primefaces.extensions:primefaces-extensions:8.0.5")
    // https://myfaces.apache.org
    // https://mvnrepository.com/artifact/org.apache.myfaces.core/myfaces-api
    implementation("org.apache.myfaces.core:myfaces-api:2.3.10") // JSF runtime
    implementation("org.apache.myfaces.core:myfaces-impl:2.3.10")
    // https://mvnrepository.com/artifact/com.sun.faces/jsf-api
    implementation("com.lowagie:itext:2.1.7") // itext DataExporter (PDF)
    implementation("org.apache.poi:poi:3.17") // apache poi DataExporter (Excel or XML)
    implementation("com.rometools:rome:1.9.0") // FeedReader
    implementation("org.apache.tika:tika-core:1.22") // apache tika FileUpload
    implementation("commons-fileupload:commons-fileupload:1.3.3") // commons-fileupload FileUpload
    implementation("commons-io:commons-io:2.4") // commons-io FileUpload
    // implementation("net.sf.barcode4j:barcode4j-light:2.3.0") // barcode4j-light
    // implementation("net.glxn.qrgen:qrgen:1.4") // qrgen QR Code support for Barcode
    implementation("com.googlecode.owasp-java-html-sanitizer:owasp-java-html-sanitizer:20181114.1")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1")
    implementation("com.sun.jersey:jersey-client:1.19.4")
    implementation("org.glassfish.jersey.core:jersey-common:2.22.2")
    // https://mvnrepository.com/artifact/javax.servlet/servlet-api
    compileOnly("javax.servlet:servlet-api:2.5")
    // implementation("javax.servlet:javax.servlet-api:3.1.0")
    implementation("javax.json:javax.json-api:1.1")
    // https://github.com/google/gson library JSON serialization/deserialization
    implementation("com.google.code.gson:gson:2.10.1")
    // https://bitbucket.org/snakeyaml/snakeyaml/wiki/Documentation
    // https://mavenlibs.com/maven/dependency/org.yaml/snakeyaml
    implementation("org.yaml:snakeyaml:2.1")
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
    // Mocking in tests
    testImplementation("org.mockito:mockito-core:4.5.1")
    // JWT Authentication
    testImplementation("io.jsonwebtoken:jjwt:0.9.1")
    // https://central.sonatype.com/artifact/com.unboundid/unboundid-ldapsdk
    implementation("com.unboundid:unboundid-ldapsdk:6.0.11")
    implementation("com.kstruct:gethostname4j:1.0.0")
}

group = "soa.nz.aut"
version = "0.7.7"
description = "Student Attendance WebApp"
// java.sourceCompatibility = JavaVersion.VERSION_17

fun setWarVersion() {
  // function body
    project.version?.let { version ->
        println("WAR Version is $version")
        System.setProperty("APP_WAR_FILE_VERSION", version.toString())
        println("For local testing do \n APP_WAR_FILE_VERSION=" + version)
        println("Append to the .env file with \n")
        println("printf \"\$APP_WAR_FILE_VERSION \\n\" >> .env")
    }
}

fun getAppVersion() {
    println(version)
}

fun getWarpackageVersion() {
    val hostname_local = System.getenv("HOSTNAME") ?: "localhost"
    val hostname = InetAddress.getLocalHost().getHostName()
    
    // Define the page names
    val pages = listOf(
        "01-login.xhtml",
        "02-attendance.xhtml",
        "03-attendanceOK.xhtml",
        "04-lecturerHome.xhtml",
        "05-adminStaff.xhtml",
        "06-Reports.xhtml",
        "07-StudentManagement.xhtml",
        "08-ClassManagement.xhtml",
        "09-UserManagement.xhtml",
        "home.xhtml",
        "404-attendanceError.xhtml",
        "404-loginError.xhtml",
        "home.xhtml",
        "index.xhtml"
    )    
  // function body
    project.version?.let { version ->
        println("WAR Version is $version")
        println("Set env variable with")
        println("export APP_WAR_FILE_VERSION=" + version.toString())
        println("http://" + hostname + ":8080/Attendance-"+ version.toString())
    }
    // Print URLs for each page
    pages.forEach { page ->
        println("http://$hostname:8080/Attendance-$version/$page")
    }
}

tasks.register<DefaultTask>("getProjectInfo") {
    description = "Obtains detailed info about the java web project"
    getWarpackageVersion()
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

tasks.register<DefaultTask>("getAppVersion") {
    description = "Obtains WAR version from Gradle file"
    getAppVersion()
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
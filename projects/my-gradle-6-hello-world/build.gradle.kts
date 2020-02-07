plugins {
    java
}

group = "com.jimtough"
version = "1.0-SNAPSHOT"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.12")
}

tasks.wrapper {
    // Set version of Gradle to use (this will auto-generate 'gradle-wrapper.jar' and 'gradle-wrapper.properties')
    gradleVersion = "6.1.1"
    distributionType = Wrapper.DistributionType.ALL
}

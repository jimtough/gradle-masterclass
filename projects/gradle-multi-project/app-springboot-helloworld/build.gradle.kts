
// I chose to use the 'build.gradle.kts' file for this subproject,
// rather than put its subproject-specific config in the root project
// buildfile as I did for the other subprojects.

plugins {
    id("org.springframework.boot")
    id("application")
}

apply {
    plugin("io.spring.dependency-management")
}

application {
    this.mainClassName = "com.jimtough.gradle.mp.helloworld.springboot.HelloWorldSpringBootApp"
}

dependencies {
    // Delcare the 'hello factory' and 'world factory' sibling (library) projects as dependencies
    implementation(project(":library-hello-factory"))
    implementation(project(":library-world-factory"))

    // Declare other regular dependencies that come from Maven Central (or whatever repo you declared in root project)
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

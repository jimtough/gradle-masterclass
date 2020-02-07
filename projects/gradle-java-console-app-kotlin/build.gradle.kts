// NOTE: Groovy code CANNOT be used inside the 'plugins' block!
plugins {
	// Plugin ids can be enclosed in either single or double quotes
	id("java")
}

group = "com.jimtough"
version = "1.0-SNAPSHOT"

java {
	sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
	targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.apache.commons:commons-math3:3.6.1")
	testImplementation("junit:junit:4.13")
}

val jar: Jar by tasks
jar.apply {
	logger.info(">>>> BEFORE archiveBaseName: [${archiveBaseName.get()}]")
	archiveBaseName.set("${project.name}-UBERJAR")
	logger.info(">>>> AFTER archiveBaseName: [${archiveBaseName.get()}]")

	manifest {
		attributes["foo"] = "bar"
		attributes["Implementation-Title"] = "Gradle-packaged UBERjar!"
		attributes["Implementation-Version"] = project.version
		attributes["Created-By"] = "jimtough"
		attributes["Main-Class"] = "com.jimtough.random.App"
	}

	logger.info(">>>> manifest.attributes: ${manifest.attributes}")

	from(configurations["runtimeClasspath"].map { file -> project.zipTree(file) })

}


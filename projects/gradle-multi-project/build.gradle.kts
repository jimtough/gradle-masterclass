
val baseGroupForSubprojects = "com.jimtough.gradle.mp"

// Add IntelliJ IDEA plugin
apply(plugin="idea")

plugins {
	java
}

// The settings inside this Closure will be applied to the root project and each subproject.
// The delegate object type is 'Project'.
allprojects {
	apply(plugin = "java")

	version = "0.1.0-SNAPSHOT"

	// Pull all dependencies from Maven Central
	repositories {
		mavenCentral()
	}
}

// The settings inside this Closure will be applied to each subproject, but NOT the root project.
// The delegate object type is 'Project'.
subprojects {
	configure<JavaPluginExtension> {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	// Used to create a 'sources' JAR file
	tasks.register<Jar>("sourcesJar") {
		archiveClassifier.set("sources")
		from(sourceSets.main.get().allJava)
	}

	dependencies {
		implementation("org.slf4j:slf4j-api") {
			version {
				// Allow any 1.7.x release
				strictly("[1.7, 1.8[")
				// Default to 1.7.22 if nothing else depends on this artifact and requests a different version
				prefer("1.7.22")
			}
		}
		// Use JUnit Jupiter API for testing
		testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
		// Use JUnit Jupiter Engine for testing
		testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
		// Use Logback when running unit tests
		testRuntimeOnly("ch.qos.logback:logback-classic") {
			version {
				// Always use a 1.2.x release of Logback
				strictly("[1.2, 1.3[")
			}
		}
	}

	tasks {
		jar {
			manifest {
				attributes(
					mapOf(
							"hi" to "mom",
							"Implementation-Title" to project.name,
							"Implementation-Version" to project.version
					)
				)
			}
		}
		test {
			// Use JUnit 5
			useJUnitPlatform()
		}
	}

}

//-------------------------------------------------------------------------------------------------
// In this section, project-specific configuration is applied to subprojects by name.
// NOTE: Must use colon-prefix notation when referring to a subproject by name.
//       In the case where subprojects are nested more than one level deep (not done in this example),
//       one must separate each nesting level with a colon like ':childprj:grandchildprj'.
project(":library-hello-factory") {
	group = "${baseGroupForSubprojects}.hellofactory"

	// This plugin is needed for library projects that produce a single JAR as output
	apply<JavaLibraryPlugin>()

	dependencies {
		// This library uses Google Guava internally, but is it not exposed in any public API.
		// Example of 'implementation' dependency scope.
		implementation("com.google.guava:guava:27.1-jre")
	}
}
project(":library-world-factory") {
	group = "${baseGroupForSubprojects}.worldfactory"

	// This plugin is needed for library projects that produce a single JAR as output
	apply<JavaLibraryPlugin>()
}
project(":app-console-helloworld") {
	group = "${baseGroupForSubprojects}.helloworld"

	dependencies {
		// Declare dependencies on sibling library projects
		implementation(project(":library-hello-factory"))
		implementation(project(":library-world-factory"))
		// Use Logback as the slf4j implementation for this app when packaged/deployed
		implementation("ch.qos.logback:logback-classic") {
			version {
				// Use a 1.2.x version
				strictly("[1.2, 1.3[")
			}
		}
	}

	tasks {
		jar {
			// The console app must have a manifest file that specifies the entry point class name.
			// NOTE: The attributes appear to be additive with respect to the 'subprojects' block.
			//       The attributes defined in the manifest config for 'jar' in subprojects are also used here.
			manifest {
				attributes(
						mapOf(
								"Main-Class" to "com.jimtough.gradle.mp.helloworld.console.HelloWorldApp"
						)
				)
			}
			// Because of the 'from' below, which builds an uberjar, we depend on the sibling library
			// projects being built and packaged in their own JARs first.
			dependsOn(":library-hello-factory:jar")
			dependsOn(":library-world-factory:jar")
			// The 'from' below will package all 'runtimeClasspath' files/classes into the output JAR.
			// This will result in the console app being packed as a runnable uberjar/fatjar.
			from(configurations["runtimeClasspath"].map { file -> project.zipTree(file) })
		}
	}
}
//-------------------------------------------------------------------------------------------------




// Applies to entire build
tasks {
	wrapper {
		gradleVersion = "6.2"
		distributionType = Wrapper.DistributionType.ALL
	}
}


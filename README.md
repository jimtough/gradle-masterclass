# gradle-masterclass

My practice source code and notes from this udemy class: https://www.udemy.com/course/gradle-masterclass

## Special Gradle directories

### USERHOME/.gradle/init.d
Gradle will look for files in the following location by default:

`<userhome>/.gradle/init.d`

This is even true on Windows-based machines, despite the Unix-style 'init.d' directory name.

I will keep a copy of these files under the following directory in this Git repo:

`common-files/USERHOME/.gradle/init.d`

Any files in this directory with the .gradle extension will be read by Gradle at build time
and executed in alphabetical order.

Gradle files in init.d are executed during the **initialization phase**, 
before the project's `settings.gradle` file.


## Miscellaneous notes

### enabling logging in Gradle

When running on the command line, you need to provide to '-i' argument when running the Gradle build script.
So something like this:

`./gradlew.bat build -i`

When running the build from inside IntelliJ, you need to edit your run configuration and add the -i as a program argument.
This will not only enable calls to 'logger.info', etc., but it will cause a lot more
Gradle diagnostic information to be displayed in the console. 

### The 'AAA-POST-COURSE' directory

After I finished the course, I chose to create some more Gradle projects in this Git repo to learn specific things. These were not part of the course.


## Gradle notes

* The 'gradle' object is available throughout the build lifecycle.
  * We can get/set properties of the 'gradle' object in our scripts.
* Each .gradle file consumed by your build is considered a script, and translates to an instance of the 'Script' interface in Gradle.
* Each Script object also has access to a 'delegate' object.
  * Which delegate object is available will vary at different points in the lifecycle.
  * For example: the delegate object during the configuration phase is an instance of the 'Project' interface.
  * Each of these interfaces has an API documentation page on the Gradle web site.
  * The `init.gradle` script delegates to a 'Gradle' interface object
  * The `build.gradle` script delegates to a 'Project' interface object
  * The `settings.gradle` script delegates to a 'Settings' interface object
    * NOTE: `settings.gradle` is most relevant when working with multi-project builds
  * The 'Gradle' interface object can be accessed by other interface objects
* Gradle will automatically read a file named 'gradle.properties' if it exists in the root directory of your project
  * Will also use 'gradle.properties' if it lives in the GRADLE_USER_HOME directory
    * `-Dgradle.user.home` system property can be used to explicitly define this location
  * Individual key/value property pair can be provided on command line as:
    * `-PmyPropKey=myPropValue`
* Some Gradle object types support 'extra properties'
  * REF: https://docs.gradle.org/current/dsl/org.gradle.api.plugins.ExtraPropertiesExtension.html
  * example: `project.ext.hello = "World"`
    * This creates an 'extra property' with key 'hellooo' and value "World" to the Project interface object
    * Reading the property is done like any other property: `logger.info hellooo`
* Important Gradle concept: **A Project is essentially a 'collection of 0 or more Tasks'**
  * Configuration Phase:
    * create Tasks
    * configure Tasks
  * Execution Phase:
    * execute Tasks
* Important Gradle concept: **A Task is a 'collection of 0 or more Actions'**
  * A Task is 'a single atomic piece of work'
  * _ref_: https://docs.gradle.org/current/dsl/org.gradle.api.Task.html
  * Each Gradle Task has a name
  * Tasks are held in the Project by a 'Task Container'
  * Actions are added to a Task via two methods:
    * doFirst()
    * doLast()
    * Each of these methods has two versions, but the version that accepts a Closure should be preferred
      * Closure version also gives us a way to access the Task
      * Closure version makes it easy to chain multiple calls together

### Gradle project dependencies

* Use the 'help' task named 'dependencies' to generate a text-based dependency graph
  * To generate an HTML-based report, add the 'project-report' plugin, and execute the 'htmlDependencyReport' task
* The 'java-library' plugin dependency scope 'api', when used on a project dependency, will expose that project's transitive dependencies


## About the 'gradle-teaching-academy' demo project

This one is interesting. It is a multi-project setup with about 20 subprojects that are composed into a web app.
Some of the subprojects are meant to demonstrate code reuse, where other teams within an organization could use
those individual components in other projects.

Some interesting points to note:
* Each subproject uses a custom name for its build file that is <subproject-name>.gradle.
  * In 'settings.gradle', there is a 'rootProject.children.each' call where the Closure does this: `subproject.buildFileName = "${subproject.name}.gradle"`
  * I like this, because it avoids confusion of having many subproject 'build.gradle' files.
  * Not sure why, but 'academy-shared' does NOT follow this pattern.
* The following properties are defined in the 'gradle.properties' file of the root project:
  * `theGroup=com.denofprogramming`
  * `theName=academy`
  * `theVersion=0.0.2-SNAPSHOT`
  * `theSourceCompatibility=1.8`
  * The root project build.gradle and settings.gradle files reference those externalized properties
* The large number of subprojects demonstrates how Gradle works well as an 'incremental build system'
  * Subprojects are only rebuilt if changes since the last build are detected


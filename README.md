# gradle-masterclass

My practice source code and notes from this udemy class: https://www.udemy.com/course/gradle-masterclass

## Gradle files that live outside the project

### Special Gradle directories

#### USERHOME/.gradle/init.d
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

# gradle-masterclass

My practice source code and notes from this udemy class: https://www.udemy.com/course/gradle-masterclass

## Miscellaneous notes

### enabling logging in Gradle

When running on the command line, you need to provide to '-i' argument when running the Gradle build script.
So something like this:

`./gradlew.bat build -i`

When running the build from inside IntelliJ, you need to edit your run configuration and add the -i as a program argument.
This will not only enable calls to 'logger.info', etc., but it will cause a lot more
Gradle diagnostic information to be displayed in the console. 

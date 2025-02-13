# Starting a new project

## Basic Project Setup using Maven
Starting a new Java project using Maven is a great choice for managing dependencies and building your project. Here are the steps to get you started:

1. Install Maven:

If you haven't already, download and install Maven from the official website.
Set up the M2_HOME environment variable to point to your Maven installation directory.
Add the bin directory of Maven to your PATH environment variable.
Create a New Maven Project:

2. Open a terminal or command prompt.
Navigate to the directory where you want to create your project.
Run the following command to create a new Maven project:

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

Replace com.example with your desired group ID and my-app with your desired artifact ID.

Navigate to Your Project Directory:

```
cd my-app
```

3. Build the Project:

Run the following command to build your project:

```
mvn package
```

This will compile your code and package it into a JAR file located in the target directory.

4. Run Your Application:

You can run your application using the following command:

```
java -cp target/my-app-1.0-SNAPSHOT.jar com.example.App
```

Replace com.example.App with the fully qualified name of your main class.
Add Dependencies:

To add dependencies, edit the pom.xml file in your project directory. For example, to add the JUnit dependency, include the following in the <dependencies> section:

```xml
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.13.2</version>
  <scope>test</scope>
</dependency>
```
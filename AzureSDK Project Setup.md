# Set up Azure SDK

> Taken from https://learn.microsoft.com/en-us/azure/developer/java/sdk/get-started-maven

Use the offical Maven archetype for performing basic Azure project setup which adds the required client dependencies. These are added with the `-DazureLibraries=XXXX` command which is a comma separated list of libraries taken from https://azure.github.io/azure-sdk/releases/latest/java.html

This needs to be run from a Bash shell:
```bash
mvn archetype:generate -DarchetypeGroupId=com.azure.tools
                       -DarchetypeArtifactId=azure-sdk-archetype
                       -DgroupId=com.mycompany.app
                       -DartifactId=azureSDK
                       -DazureLibraries=azure-cosmos
```

This will add content such as the following to the *pom.xml*

```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.azure</groupId>
                <artifactId>azure-sdk-bom</artifactId>
                <version>${bom.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>com.azure</groupId>
            <artifactId>azure-cosmos</artifactId>
        </dependency>
    </dependencies>
```
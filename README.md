# RailroadTycoon project

The project had the main goal applying a proper [SDP](https://www.geeksforgeeks.org/software-engineering/software-development-process/) given in the classes of the semester, and use the concepts of Object-Oriented programming, to develop a simulator based in the Railroad Tycoon.

SDP
[!image](SDP.png)

The simulator should have two types of user, an editor and a player, each one of them should have it's own menu where they could manage each of their functionalities. 

## Areas Developed
  ### Math  
  The **math** used were in the subject of **statitics** and **discret math**. 
  - **Discret Math**(US013, US014, ):
      - Graph (Construction, manipulation and usage of algorithms),
      - Matrix Manipulation,
      - Algorithm Analysis,
  
  ### Programming 
  - Object-Oriented programming,
  - Concept of heritage and polymorphism,
  - Interfaces,
  - Exceptions,
  - TDD,
  
  ### Software Engeneering
  - GRASP,
  - Requirement Gathering,
  - Analysis,
  - Design,
  - Implementations,
  - Test and Validation of required rules,
  - DTO and mapper,
  - Cohesion and coupling,
  - Protected Variation and Adapters,


  ### Teamwork (github usage and communication)
  - Board usage,
  - Teams,
  - Discord,
  - Pull request,
  - Branches,
  - SCRUM,

## My Resposabilities
  1. **Requirement Gathering, Analysis, Design and Implementation** of US001, US002, US0012, US0013, US0027, US026;
  2. General **Non-Functional Requirements**;
  3. **Use Case diagram**;
  4. Class diagram for the user Stories that were nominated for me;
  5. **General Class Diagram**;
  6. **Boot of the repository**. Coded all the classes boot with the basic methods, logic of the program and declarated methods from CD;
  7. Logic of the **map and position management**;
  8. Logic of the **simulator managment of time**;
  9. Logic of **station manipulation of resourses **of objects in it's range;
  10. **Industry Production Logic**;
  11. **JavaFx** Ui full built, design and implemtation;
  12. **Application of Discrete Math** theory (graphs algorithms);

## Acomplishments 

We managed to **deliver a great product** in the stipulated date, with a **great UI** and **all modules working as requested**, the logic of the simulator is not consuming to much memory, but the choices of library to represent the graphs(representation of connections between objects in the map and of the objects itselves) are of much cost.



## What I would have done differently  
  1. Used more of polymorphism and interfaces,
  2. Used more redundancy in the code to keep it less coupeled,
  3. Lower the texture quality to keep the game lighter,
  4. Created personalized exceptions,
  5. Changed the library used to draw the graphs,


<br /><br /><br /><br />

# Project Template

This project template contains didactic artifacts relevant to the Integrative Project to be developed during the second semester of the [Degree in Informatics Engineering (LEI)](https://www.isep.ipp.pt/Course/Course/26) from the [School of Engineering – Polytechnic of Porto (ISEP)](https://www.isep.ipp.pt).

In particular, it has:

* The [Team Members and Task Distribution](docs/TeamMembersAndTasks.md) during sprints;
* [Templates](docs/(template-files)) to capture and systematize evidence of proper application of the Software Development Process, namely regarding the activities of Requirements Engineering, OO Analysis and Design;
* [Sample documentation](docs/outsourcing-tasks-example) and [source code](src) available as a starting point;
* General description of how the provided application works (and it is structured).


## Maven goals

### Run the unit tests
```
mvn clean test
```

### Generate javadoc for the source code
```
mvn javadoc:javadoc
```

### Generate javadoc for the test code
```
mvn javadoc:test-javadoc
```

### Generate Jacoco source code coverage report
```
mvn test jacoco:report
```

### Check if thresholds limits are achieved
```
mvn test jacoco:check
```

## How to generate a Jar package for the project

Place the following plugin on the appropriate place of the pom.xml file.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.6.0</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>pt.ipp.isep.dei.esoft.project.ui.Main</mainClass>
                    </manifest>
                </archive>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
        </execution>
    </executions>
</plugin>
```

Run the following command on the project root folder. You can use IntelliJ to run the command or the command line of your computer if you hav Maven installed.

```
mvn package
```

## How to run the project from the generated Jar Package

Run the following command on the project root folder. You can use IntelliJ to run the command or the command line of your computer if you hav Maven installed.

```
java -jar target/project-template-1.0-SNAPSHOT-jar-with-dependencies.jar
```# sem2-pi-24.25-g001-repo

# 🚂 Railroad Tycoon Project

This project aimed to apply a structured [Software Development Process (SDP)](https://www.geeksforgeeks.org/software-engineering/software-development-process/) taught during the semester, while utilizing Object-Oriented Programming (OOP) principles to develop a simulation game inspired by **Railroad Tycoon**.

![SDP Diagram](SDP.png)

The simulator includes two distinct user types:  
- **Editor**: Manages and configures simulation data.  
- **Player**: Interacts with and plays the simulation.

Each user type has a dedicated menu with functionalities relevant to their role.

---

## 📚 Areas of Development

### 🔢 Math
The project incorporated concepts from:
- **Statistics**
- **Discrete Math**:
  - Graph creation, manipulation, and algorithm implementation
  - Matrix operations
  - Algorithm analysis

### 💻 Programming
- Object-Oriented Programming (OOP)
- Inheritance and Polymorphism
- Interfaces and Abstraction
- Exception Handling
- Test-Driven Development (TDD)

### 🧠 Software Engineering
- GRASP principles
- Requirement gathering & analysis
- Software design and implementation
- Validation and testing of business rules
- DTOs and Mappers
- Managing Cohesion and Coupling
- Protected Variation and Adapter Patterns

### 🤝 Teamwork & Tools
- Version control with GitHub (branches, pull requests)
- Task management via GitHub Boards
- Communication through Discord
- Agile methodology: SCRUM & sprints

---

## 👤 My Responsibilities

1. **Requirements, Analysis, Design, and Implementation** of:
   - US001, US002, US012, US013, US026, US027
2. Definition of **Non-Functional Requirements**
3. Creation of **Use Case Diagram**
4. Development of **Class Diagrams** for assigned user stories
5. Creation of a **General Class Diagram**
6. **Project bootstrapping**: basic class creation, method declarations, program logic scaffolding
7. Design and implementation of **Map & Position Management**
8. Development of **Time Simulation Logic**
9. **Station Resource Management**: handling object interactions within range
10. **Industry Production Logic**
11. Complete **UI Design and Implementation** using **JavaFX**
12. Application of **Graph Algorithms** from Discrete Math

---

## 🏆 Achievements

We successfully delivered a **fully functional product** by the deadline, with:
- A polished and user-friendly **JavaFX UI**
- All modules operating as specified
- Efficient memory usage
- A complex yet smooth **simulation engine**

However, the library chosen for graph representation (used for connections between map objects and objects themselves) incurred significant performance cost.

---

## 🔄 What I Would Improve

1. Apply **polymorphism and interfaces** more effectively
2. Introduce **intentional redundancy** to reduce tight coupling
3. Optimize performance by **reducing texture quality**
4. Create **custom exceptions** for better error handling
5. Choose a **lighter, more efficient graphing library**

---

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

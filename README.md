# Employee Management System
[![Total alerts](https://img.shields.io/lgtm/alerts/g/tirthasheshpatel/Employee-Management-System.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/tirthasheshpatel/Employee-Management-System/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/tirthasheshpatel/Employee-Management-System.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/tirthasheshpatel/Employee-Management-System/context:java)
[![Build Status](https://travis-ci.com/tirthasheshpatel/Employee-Management-System.svg?branch=master)](https://travis-ci.com/tirthasheshpatel/Employee-Management-System)
[![Java Support](https://img.shields.io/badge/Java-8%7C9%7C10%7C11-orange)](https://img.shields.io/badge/Java-8%7C9%7C10%7C11-orange)

# Object Oriented Programming with Java

## Concepts Used

**1. Packages in Java.**
  - Built packages to manage the project conviniently.
  - `com.EMS._abstract` provides interfaces for all classes.
  - `com.EMS.core` implements interfaces in `com.EMS._abstract` to manage Employees and Organizations.
  - `com.EMS.iomanager` implements interfaces in `con.EMS.core` to manage data files of `Employee` and `Organization` classes.
  
**2. Interfaces (Abstraction in Java).**
  - Built interfaces in package `com.EMS._abstract` to create an abstraction.
  - `BaseOrganization` \- A base class for class `Organization` in `com.EMS.core`.
  - `BaseEmployee` \- A base class for class `Employee` in `com.EMS.core`.
  - `IOManager` \- A base class for all classes in `com.EMS.iomanager`.
  
**3. Getters and Setters.**
  - Built getters and setters for all the attributes in all the classes in all packages.
  - Built getters and setters in class `Employee` present in package `com.EMS.core`.
  - Built getters and setters in class `Organization` present in package `com.EMS.core`.
  - Built getters and setters in class `IOManagerEmployee` present in package `com.EMS.iomanager`.
  - Built getters and setters in class `IOManagerOrganization` present in package `com.EMS.iomanager`.
  
**4. File Management.**
  - Use several classes present in package ``java.io`` for proper file management.
  - Built package `com.EMS.iomanager` to store Employee and Organization data in files.
  - class `IOManagerEmployee` manages Employee data.
  - class `IOManagerOrganization` manages Organization data.
  
**5. Serialization and Deserialization.**
  - Built serializable classes to save raw java objects as ``.ser`` files and retrive them later to work with.
  - Method `serialize` present in class `Employee` (present in package `com.EMS.core`) serailizes `Employee` objects.
  - Method `serialize` present in class `Organization` (present in package `com.EMS.core`) serailizes `Organization` objects.
  - Static Method `deserialize` present in class `Employee` (present in package `com.EMS.core`) deserailizes `Employee` objects.
  - Static Method `deserialize` present in class `Organization` (present in package `com.EMS.core`) deserailizes `Organization` objects.
  
**6. Multiple Inheitance.**
  - Perform multiple inheritance in several classes to demonstrate and test the behaviour of different classes and their objects.
**7. Polymorphism or Method Overriding.**
  - Use the concepts of method overriding to perform Polymorphism.
  
**8. Constructor and Parameterized Constructor.**
  - Create constructor and parameterized constructor for all classes to initialize the attributes appropriately.
  
**9. String and StringBuffer Methods**
  - Use multiple `String` and `StringBuffer` methods for managing the output on console and in files.
  
**10. Exceptions.**
  - Used `try`, `throw` and `catch` blocks to print proper exception message and handle exceptions. Use `throw` and `throws` keywords to throw proper exceptions.
  
**11. Methods and Static Methods.**
  - Mostly Everything is done using methods and at times static methods is used to deserialize objects.
  
**12. Custom Exceptions and Exception Handling.**
  - Built `com.EMS.exceptions` package with custom exceptions and add exception handling in all the classes in all packages.
  
**13. A list of keywords used:**
  - `this` for accessing class variables.
  - `final` to declare constant variables.
  - `static` to create a static block of code.
  - `try`, `catch`, `throw` and `throws` for exception management.
  - `interface` to create a interface.
  - `transient` to declare non-serializable objects.
  - `public` and `private` access modifiers to declare methods, classes and variables.
  - Many other common and trivial keywords.

## Concepts used beyond the scope of the course!

**1. JavaDoc tool for documentation.**
  - Used JavaDoc tool to write and generate documentation for the project.
  
**2. Continuous Integration**
  - Used CI tools like TravisCI and LGTM to automatically build the project.


Creater and Contributor
-----------------------
Tirth Patel
-----------------------


## Thank You!

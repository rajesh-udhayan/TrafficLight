# Traffic Light

A simple traffic signal simulator app developed by following TDD approach. 

This app consists of two screens
* Car Model Screen - Where the user needs to enter a car model and click Start Driving button
* Traffic Light Screen - Where there will be a Traffic Light which will transition between one light to other with the following delay
    * RED - 4 Seconds bright
    * GREEN - 4 Seconds bright
    * ORANGE - 1 Seconds bright

The Transistion will be as follows:
* Green -> Orange
* Orange -> Red
* Red -> Green

# Setup

Clone the project using below command

```bash
https://github.com/rajesh-udhayan/TrafficLight.git
```

Here are some useful Gradle/adb commands for executing this project:

 * `./gradlew runApp` - Builds and install the debug apk on the current connected device.
 * `./gradlew compileApp` - Builds the debug apk.
 * `./gradlew runUnitTests` - Execute unit tests (both unit and integration).
 * `./gradlew connectedAndroidTest` - Execute UI tests.
 
 # Dependencies used
 
 - Jetpack Compose
 
 - Android Hilt
 
 - Google Truth 
 
 - Espresso
 
 - Mockk
 
 # Approaches followed 
 
 - Test Driven Development (TDD)

- UI Tests & Unit Tests

- MVVM architecture

- Dependency Injection

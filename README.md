## NUI Samples
A series of samples for the [NUI](https://github.com/MovingBlocks/TeraNUI) UI framework
### Introduction
This project consists of a collection of small samples demonstrating the use of NUI widgets, as well as how to provide an environment for NUI to run in.
It uses LibGDX for rendering, audio and input handling.

I'm using this project as a way of practicing and learning how to best use NUI and also as an informal documentation reference.
I often discovered how things work and how to use them from looking at the UI files in [Terasology](https://github.com/MovingBlocks/Terasology), the project in which NUI originated. Their [Quick Start](https://github.com/Terasology/TutorialNui/wiki/Quick-Start) page provides a good starting point.
### Building
These samples can be built for both desktop platforms (Windows, MacOS, Linux) and Android. The desktop project can be run through the desktop entry point. To run the samples, use
```batch
gradlew :desktop:run
```
### Android
To build for Android, you will need an existing Android SDK installation with the API 30 build tools installed.

To enable the android project, create a `local.properties` file in the root project directory with the following contents
(substituting `<ANDROID_SDK_ROOT>` and `<ANDROID_NDK_ROOT>` with the Android SDK and NDK installation paths):
```properties
sdk.dir=<ANDROID_SDK_ROOT>
ndk.dir=<ANDROID_NDK_ROOT>
```
The project can then be built and installed on an attached device using the command
```batch
gradlew :android:installDebug
```
### Custom NUI source
For easier local development and testing, this project can embed NUI as a sub-project via gradle's `includeBuild`. When an embedded copy is present, that copy will be used when resolving all NUI dependencies in the project.

To embed a fresh copy of NUI's `master` branch, use the command
```batch
gradlew fetchNUI
```
For a copy of a custom remote and/or branch, provide options via the `nuiRemote` and `nuiBranch` gradle parameters e.g.
```batch
gradlew fetchNUI -PnuiRemote=<CUSTOM_REMOTE>/TeraNUI.git -PnuiBranch=<BRANCH_NAME>
```

### Project Structure
- Most of the code is contained within the `samples` project, which holds the LibGDX application and UI screens.
- The `desktop` project contains the code necessary to run the samples on desktop platforms (Windows, MacOS and Linux).
- The `android` project contains the code needed to run the sanples on Android.
#### Samples
- `samples/src/main/java` - Source code
  - `SamplesApp.java` - Application entry point
  - `UISample.java` - UI screens base class
  - `*Sample.java` - Sample UI screens
  - `SimulatedPointer.java` - Fake pointer code for testing
  - `DefaultSkins.java` - Code for creating default UI skin from assets
  - `Assets.java` - Utility class used for loading and unloading assets
- `samples/src/main/resources` - UI Assets
  - `textures` - UI widget textures (e.g. button image)
  - `audio` - UI widget sounds (e.g. button click sound)
## NUI Samples
A series of samples for the [NUI](https://github.com/MovingBlocks/TeraNUI) UI framework
### Introduction
This project consists of a collection of small samples demonstrating the use of NUI widgets, as well as how to provide an environment for NUI to run in.
It uses LibGDX for rendering, audio and input handling.

I'm using this project as a way of practicing and learning how to best use NUI and also as an informal documentation reference. Most of NUI is currently undocumented and so existing information is scattered over many places.
I often discovered how things work and how to use them from looking at the UI files in [Terasology](https://github.com/MovingBlocks/Terasology), the project in which NUI originated.
### Building
> At the moment these samples use my custom `multi-touch` branch of NUI.
> To use this you can clone the branch with the following command.
> ```batch
> gradlew fetchNUI -PnuiRemote=https://github.com/BenjaminAmos/TeraNUI.git -PnuiBranch=multi-touch
> ```

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
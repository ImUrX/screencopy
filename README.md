# Screencopy

This is a client-side mod that lets you copy screenshots to your clipboard, you just need to press F2 like you usually do in Minecraft. It works in macOS, Linux and Windows that use x64 or arm64. Also there is a config file for not saving the file to the screenshots folder if that's your preference!

This mod shouldn't be incompatible with any mod (including optifine) but do report it in the issues section ;)

[Discord Server](https://discord.gg/7EnaAuaAwF)

Tested as working with Minecraft 1.19.1.

## Building

First make sure to have the latest Java SDK installed for your system:

 - [Oracle Java](https://www.oracle.com/ca-en/java/technologies/downloads/)
 - [OpenJDK](https://openjdk.org/)

Once you have the build environment setup, all you need to is
run the appropriate script, according to your platform, to get
the build running (current build uses Gradle 7). See the sub-sections,
for the most common platforms.

Note, generated assets will be in `fabric/build/libs` and
`forge/build/libs`, for Fabric and Forge, respectively.

### Linux & macOS

```sh
chmod +x ./gradlew
./gradlew build
```

### Windows

```sh
gradlew.bat build
```

## Installation

If you are looking to install an already built release, then visit
the project on [Curseforge](https://www.curseforge.com/minecraft/mc-mods/screencopy),
otherwise if you are building from source, follow the steps below.

### Forge

This assumes you have [CurseForge](https://www.curseforge.com/) installed.

TODO

### Fabric

This assumes you have [Fabric](https://fabricmc.net/) installed.

 - Locate the `mods` folder for your Fabric installation.
 - Copy the file from `screenshotcopy-1.2.2-fabric.jar` (adjust version number as appropriate) to the `mods` folder of your Fabric installation.
 - Launch your Fabric version of Minecraft

For a video on installing Frabric, see https://www.youtube.com/watch?v=lP_Z0E6oGo8

## License

This project uses the [MIT license](./LICENSE-MIT).
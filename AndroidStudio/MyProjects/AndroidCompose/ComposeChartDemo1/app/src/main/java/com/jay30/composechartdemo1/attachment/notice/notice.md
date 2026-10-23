# notice
## replication
Before replication, you have to follow the instruction from [`JetChart` official website](https://github.com/fracassi-marco/JetChart).

I simply write the instructions from [`JetChart` official website](https://github.com/fracassi-marco/JetChart)

1. Add the JitPack repository to your build file. 

Take this project as example, it use `Gradle(Kotlin)`, 

and so it is placed in `setting.gradle.kts`

In `setting.gradle.kts` file, add 

```
maven { url = uri("https://repo1.maven.org/maven2/") }
```

in this block 

```
pluginManagement {    
    //...
    repositories {
        // ...
    }
    // add here
}
```

, becoming

```
pluginManagement {    
    //...
    repositories {
        // ...
    }
    maven { url = uri("https://repo1.maven.org/maven2/") }
}
```

> [!NOTE]
> The first step may vary a little due the future release of Android Studio.
> 
> But DON'T worry, the core concept will still be same.

2. Add the dependency

```
"io.github.bytebeats:compose-charts:${LATEST_VERSION}"
```

through project structure.
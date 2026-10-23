# notice
## replication
Before replication, you have to follow the instruction from [`JetChart` official website](https://github.com/fracassi-marco/JetChart).

I simply write the instructions from [`JetChart` official website](https://github.com/fracassi-marco/JetChart)

1. Add the JitPack repository to your build file. 

Take this project as example, it use `Gradle(Kotlin)`, 

and so it is placed in `setting.gradle.kts`

In `setting.gradle.kts` file, add 

```
maven { url= uri("https://jitpack.io") }
```

in this block 

```
dependencyResolutionManagement {
    //...
    repositories {
        // ...
        // add here
    }
}
```

,becoming

```
dependencyResolutionManagement {
    //...
    repositories {
        //...
        maven { url= uri("https://jitpack.io") }
    }
}
```

> [!NOTE]
> The first step may vary a little due the future release of Android Studio.
> 
> But DON'T worry, the core concept will still be same.

2. Add the dependency

```
com.github.fracassi-marco:JetChart:$your_version
```

through project structure.
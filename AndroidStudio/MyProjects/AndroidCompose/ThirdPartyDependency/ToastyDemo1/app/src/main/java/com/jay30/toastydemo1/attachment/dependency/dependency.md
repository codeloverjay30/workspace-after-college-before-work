# dependency
## `Toasty`
To use third-party class `Toasty`, one has to follow these steps.

1. enable it to download third-party dependency.

I assume you use `Gradle.kts` in your project.

To do that, add this following line

```
maven { url = uri("https://jitpack.io") }
```

under this section

```
dependencyResolutionManagement {
    // ...
    repositories {
        //...

        maven { url = uri("https://jitpack.io") }
    }
}
```

in `setting.gradle.kts (Project settings)` file.

> [!NOTE]
> This approach may vary in the future release of Android Studio.

2. add the dependency `com.github.GrenderG:Toasty:1.5.2` through project structure 

> [!NOTE]
> the version may vary in the future.

3. add import statements 

```
import es.dmoral.toasty.Toasty
```
# notice
If you want to create a new project and copy-paste the code, you have to do these things before building new project.

1. add these dependency discussed in dependency.md.

2. to adjust the component (if neccessary), add these following line in `activity` tag in `AndroidManifest.xml`

```
            android:windowSoftInputMode="adjustResize"
```

After adding, it will look like, for example

```
<activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:windowSoftInputMode="adjustResize"
            android:theme="@style/Theme.WindowInsetsDemo1">
  
  <!-- content -- >
  
</activity>          
  
```


Also notice that

1. `MainActivity` class inherits `AppCompatActivity`

Look like this

```
class MainActivity : AppCompatActivity() {
}
```

2. In `MainActivity` class, add these line 

```
WindowCompat.setDecorFitsSystemWindows(window, false)
```
before `setContent` statement and followed by `super.onCreate(savedInstanceState)` statement

```

```
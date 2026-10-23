# Notice
## error
error in code snippet from [`androidx.ink.brush API overview`](https://developer.android.com/develop/ui/compose/touch-input/stylus-input/ink-api-brush-apis)

```
val redBrush = Brush.createWithColorIntArgb(
family = StockBrushes.pressurePenLatest,
colorIntArgb = Color.RED.toArgb(),
size = 5F,
epsilon = 0.1F
)

val blueBrush = redBrush.copy(colorIntArgb = Color.BLUE.toArgb())
```

I have changed it to the following code snippets and it works fine at present.
```
    val redBrush = Brush.createWithColorIntArgb(
        family = StockBrushes.pressurePenLatest,
        colorIntArgb = Color.Red.toArgb(),
        size = 5F,
        epsilon = 0.1F
    )

    val blueBrush = redBrush.copyWithColorIntArgb(colorIntArgb = Color.Blue.toArgb())
```

I give a bad feedback to this docs due to errors in the above code snippets.

Some errors are about the API changes but the docs is not updated yet.

I think they are to careless. 

Some errors are about the typo (such as type word with all uppercase letters instead of word with other lowercase letters except first letter)


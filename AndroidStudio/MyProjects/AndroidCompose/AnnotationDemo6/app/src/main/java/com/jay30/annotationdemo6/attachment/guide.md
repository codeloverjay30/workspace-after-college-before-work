# guide about annotations that end with `Res`
For the parameter that is marked with annotation that ends with `Res` (such as `@StringRes`),

1. the parameter must be `Int` or `Long` type.
2. the argument must be assigned with value that start with `R.xyz.` (such as `R.string.text1`)

# current existing annotations that ends with `Res`
About current existing annotations that ends with `Res`, including but not limited to following:

+ `@StringRes`
+ `@DrawableRes`
+ `@LayoutRes`
+ `@StyleRes`
+ `@ColorRes`

> [!NOTE]
> NOTE that there are no `@ThemeRes` annotation at present.

| annotations that end with `Res` | value that is assigned for argument that must start with |
-------------------------------------------------------------------------
| `@StringRes` | `R.string.` |
| `@DrawableRes` | `R.drawable.` |
| `@LayoutRes` | `R.layout.` |
| `@StyleRes` | `R.style.` | 
|`@ColorRes` | `R.color.` |
package com.example.dietapp.util.components.button.iconbutton

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.dietapp.componentsattr.button.basebutton.BaseButtonAttr
import com.example.dietapp.componentsattr.icon.IconAttr

class MyButtonWithIcon(
    private val buttonAttr: BaseButtonAttr,
    private val iconAttr: IconAttr,
) {
    @Composable
    fun popUp(){
        Button(
            onClick = buttonAttr.onClick,
            shape = buttonAttr.shape,
            modifier = buttonAttr.modifier,
            contentPadding = buttonAttr.contentPadding,
            colors = buttonAttr.colors
        ){
            Icon(
                painter = painterResource(iconAttr.id),
                contentDescription = iconAttr.contentDescription,
                modifier = iconAttr.modifier
            )
        }
    }
}
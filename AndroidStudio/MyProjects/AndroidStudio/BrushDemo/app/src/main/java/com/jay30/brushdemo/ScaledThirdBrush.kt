package com.jay30.brushdemo

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

class ScaledThirdBrush(val shaderBrush: ShaderBrush): ShaderBrush() {
    override fun createShader(size: Size): Shader {
        return shaderBrush.createShader(size / 3f)
    }
}
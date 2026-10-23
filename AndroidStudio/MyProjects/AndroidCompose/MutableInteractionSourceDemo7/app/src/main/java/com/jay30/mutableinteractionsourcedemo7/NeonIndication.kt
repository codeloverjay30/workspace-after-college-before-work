package com.jay30.mutableinteractionsourcedemo7

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.unit.Dp

data class NeonIndication(private val shape: Shape, private val borderWidth: Dp) :
    IndicationNodeFactory {

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return NeonNode(
            shape,
            // Double the border size for a stronger press effect
            borderWidth * 2,
            interactionSource
        )
    }
}

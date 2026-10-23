package com.jay30.myfragment

import android.app.Fragment
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionSet
import android.view.View
import android.window.BackEvent
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.transition.TransitionSeekController

class MyFragment: Fragment() {

    val transitionSet = TransitionSet().apply {
        addTransition(Fade(Fade.MODE_OUT))
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.MODE_IN))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(enabled = false) {

            var controller: TransitionSeekController? = null

            @RequiresApi(34)
            override fun handleOnBackStarted(backEvent: BackEvent) {
                // Create the transition
                controller = TransitionManager.controlDelayedTransition(
                    binding.card,
                    transitionSet
                )
                changeTextVisibility(ShowText.SHORT)
            }

            @RequiresApi(34)
            override fun handleOnBackProgressed(backEvent: BackEvent) {
                // Play the transition as the user swipes back
                if (controller?.isReady == true) {
                    controller?.currentFraction = backEvent.progress
                }
            }

            override fun handleOnBackPressed() {
                // Finish playing the transition when the user commits back
                controller?.animateToEnd()
                this.isEnabled = false
            }

            @RequiresApi(34)
            override fun handleOnBackCancelled() {
                // If the user cancels the back gesture, reset the state
                transition(ShowText.LONG)
            }
        }

        binding.shortText.setOnClickListener {
            transition(ShowText.LONG)
            callback.isEnabled = true
        }

        this.requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun transition(showText: ShowText) {
        TransitionManager.beginDelayedTransition(
            binding.card,
            transitionSet
        )
        changeTextVisibility(showText)
    }

    enum class ShowText { SHORT, LONG }
    private fun changeTextVisibility(showText: ShowText) {
        when (showText) {
            ShowText.SHORT -> {
                binding.shortText.isVisible = true
                binding.longText.isVisible = false
            }
            ShowText.LONG -> {
                binding.shortText.isVisible = false
                binding.longText.isVisible = true
            }
        }
    }
}
package com.maximperevalov.shapeeditor.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maximperevalov.shapeeditor.R
import com.maximperevalov.shapeeditor.domain.Color

class ColorPickerDialogFragment(
    private var defaultSelectedColor: Color,
    private val colorSelectionListener: ColorSelectionListener,
) : DialogFragment() {

    private val colors = arrayOf(
        Color.WHITE,
        Color.BLACK,
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.YELLOW,
        Color.ORANGE,
        Color.PURPLE,
        Color.PINK,
    )

    private fun View.initColorButtons() {
        listOf<FloatingActionButton>(
            findViewById(R.id.btn_white),
            findViewById(R.id.btn_black),
            findViewById(R.id.btn_red),
            findViewById(R.id.btn_blue),
            findViewById(R.id.btn_green),
            findViewById(R.id.btn_yellow),
            findViewById(R.id.btn_orange),
            findViewById(R.id.btn_purple),
            findViewById(R.id.btn_pink),
        ).forEachIndexed(::setColorButtonListener)
    }

    private fun setColorButtonListener(index: Int, button: FloatingActionButton) {
        button.setOnClickListener {
            dialog?.cancel()
            colorSelectionListener.onSelection(selectedColor = colors[index])

        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.color_picker, null)
        view.initColorButtons()
        val builder = AlertDialog.Builder(activity)
            .setView(view)
            .setNegativeButton("Cancel", null)
        return builder.create()
    }

    fun interface ColorSelectionListener {
        fun onSelection(selectedColor: Color)
    }
}

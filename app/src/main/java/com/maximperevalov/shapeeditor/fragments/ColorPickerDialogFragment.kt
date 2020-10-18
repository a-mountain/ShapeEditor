package com.maximperevalov.shapeeditor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maximperevalov.shapeeditor.R
import com.maximperevalov.shapeeditor.domain.Color

class ColorPickerDialogFragment(
    private val title: String,
    private val checkboxLabel: String,
    private val checkBoxListener: (CompoundButton, Boolean) -> Unit,
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.color_picker, null).apply {
            initTitle()
            initCheckbox()
            initColorButtons()
            initCancelButton()
        }
    }

    private fun View.initCheckbox() {
        findViewById<CheckBox>(R.id.checkBox).apply {
            isChecked = true
            text = checkboxLabel
            setOnCheckedChangeListener { btn, isChecked ->
                checkBoxListener(btn, isChecked)
                dialog?.cancel()
            }
        }
    }

    private fun View.initTitle() {
        findViewById<TextView>(R.id.title).apply {
            text = title
        }
    }

    private fun View.initCancelButton() {
        findViewById<Button>(R.id.cancel).apply {
            setOnClickListener {
                dialog?.cancel()
            }
        }
    }

    fun interface ColorSelectionListener {
        fun onSelection(selectedColor: Color)
    }
}

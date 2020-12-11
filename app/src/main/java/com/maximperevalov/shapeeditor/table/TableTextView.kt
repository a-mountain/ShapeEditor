package com.maximperevalov.shapeeditor.table

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.TableRow

class TableTextView(context: Context) : androidx.appcompat.widget.AppCompatTextView(context) {

    private var weight = 1F

    constructor(context: Context, weight: Float) : this(context) {
        this.weight = weight
    }

    init {
        layoutParams =
            TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                weight
            )
        setTextColor(Color.BLACK)
        textAlignment = TEXT_ALIGNMENT_CENTER
        textSize = 15F
        setBorders()
    }


    private fun setBorders() {
        val gd = GradientDrawable()
        gd.setStroke(3, -0x1000000)
        background = gd
    }
}
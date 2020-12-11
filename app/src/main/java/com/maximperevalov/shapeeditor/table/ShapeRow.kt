package com.maximperevalov.shapeeditor.table

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TableRow

class ShapeRow(context: Context, attributes: AttributeSet? = null) : TableRow(context, attributes) {

    private val type = TableTextView(context)
    private val x1 = TableTextView(context)
    private val y1 = TableTextView(context)
    private val x2 = TableTextView(context)
    private val y2 = TableTextView(context)
    lateinit var shape: Shape

    constructor(context: Context) : this(
        context,
        null
    ) {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    fun init(shape: Shape, clickListener: () -> Unit, longClickListener: () -> Unit) {
        this.shape = shape
        setText()
        listOf(type, x1, y1, x2, y2).forEach {
            addView(it)
            it.setOnClickListener {
                clickListener()
            }
            it.setOnLongClickListener {
                longClickListener()
                true
            }
        }
    }

    fun select() {
        listOf(type, x1, y1, x2, y2).forEach {
            it.setTextColor(Color.RED)
        }
    }

    fun removeSelection() {
        listOf(type, x1, y1, x2, y2).forEach {
            it.setTextColor(Color.BLACK)
        }
    }

    private fun setText() {
        type.text = shape.shapeType
        x1.text = shape.x1.toString()
        y1.text = shape.y1.toString()
        x2.text = shape.x2.toString()
        y2.text = shape.y2.toString()
    }
}

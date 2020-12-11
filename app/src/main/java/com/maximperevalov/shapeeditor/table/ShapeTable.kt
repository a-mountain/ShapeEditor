package com.maximperevalov.shapeeditor.table

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TableLayout
import android.widget.TableRow

class ShapeTable(context: Context, attributes: AttributeSet?) : TableLayout(context, attributes) {

    private val shapeRows: MutableMap<String, ShapeRow> = HashMap()
    private var selectedRowId: String? = null
    private lateinit var listener: ShapeTableListener

    fun init(listener: ShapeTableListener) {
        this.listener = listener
        initRowHeader()
    }

    fun clearShapes() {
        selectedRowId = null
        shapeRows.values.forEach {
            removeView(it)
        }
        shapeRows.clear()
    }

    fun addShapeRow(shapeId: String, shapeType: String, x1: Int, y1: Int, x2: Int, y2: Int) {
        val row = ShapeRow(context)
        val shape = Shape(shapeType, x1, y1, x2, y2)
        val onClick: () -> Unit = {
            val event = when {
                selectedRowId == null -> {
                    selectedRowId = shapeId
                    row.select()
                    SelectEvent.SelectNew
                }
                selectedRowId!! == shapeId -> {
                    row.removeSelection()
                    selectedRowId = null
                    SelectEvent.Unselect
                }
                selectedRowId!! != shapeId -> {
                    shapeRows[selectedRowId!!]?.removeSelection()
                    row.select()
                    selectedRowId = shapeId
                    SelectEvent.SelectNew
                }
                else -> SelectEvent.Unselect
            }
            listener.onSelectShape(shapeId, event)
        }
        val onLongClick: () -> Unit = {
            removeShapeRow(shapeId)
            listener.onDeleteShape(shapeId)
        }
        row.init(shape, onClick, onLongClick)
        shapeRows[shapeId] = row
        addView(row)
    }

    fun removeShapeRow(shapeId: String) {
        removeView(shapeRows[shapeId])
        shapeRows.remove(shapeId)
    }

    private fun initRowHeader() {
        val rowHeaders = listOf("Тип", "X1", "Y1", "X2", "Y2")
        val headerRow = TableRow(context)
        rowHeaders
            .map {
                toTextView(it).apply {
                    setTextColor(Color.RED)
                }
            }
            .forEach {
                headerRow.addView(it)
            }
        addView(headerRow)
    }

    private fun toTextView(string: String) = TableTextView(context).apply { text = string }
}

package com.maximperevalov.shapeeditor

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.widget.Button
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.views.ShapeEditorView

/**
 * Головне вікно
 */
class MainActivity : AppCompatActivity() {

    private lateinit var shapeEditorView: ShapeEditorView
    private lateinit var btnShapes: Button
    private lateinit var btnClear: Button
    private lateinit var popupMenu: PopupMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEditorView()
        initClearButton()
        initShapeButton()
        initPopupMenu()
    }

    private fun initPopupMenu() {
        popupMenu = PopupMenu(this, btnShapes)
        popupMenu.inflate(R.menu.shape_menu)
        popupMenu.setOnMenuItemClickListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.shape_point_menu_item -> {
                    shapeEditorView.selectShape(SelectedShape.POINT)
                    true
                }
                R.id.shape_rect_menu_item -> {
                    shapeEditorView.selectShape(SelectedShape.RECTANGLE)
                    true
                }
                R.id.shape_ellipse_menu_item -> {
                    shapeEditorView.selectShape(SelectedShape.ELLIPSE)
                    true
                }
                R.id.shape_line_menu_item -> {
                    shapeEditorView.selectShape(SelectedShape.LINE)
                    true
                }
                else -> false
            }
        }
    }

    private fun initEditorView() {
        shapeEditorView = findViewById(R.id.shape_editor)
        shapeEditorView.init(getMetrics())
    }

    private fun initClearButton() {
        btnClear = findViewById(R.id.btn_clear)
        btnClear.apply {
            setOnClickListener {
                shapeEditorView.clearLastShape()
            }
            setOnLongClickListener {
                shapeEditorView.clearAllShapes()
                true
            }
        }
    }

    private fun initShapeButton() {
        btnShapes = findViewById(R.id.btn_shapes)
        btnShapes.setOnClickListener {
            popupMenu.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getMetrics(): DisplayMetrics {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        return metrics
    }
}
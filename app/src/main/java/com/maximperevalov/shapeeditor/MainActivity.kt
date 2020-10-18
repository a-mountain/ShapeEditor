package com.maximperevalov.shapeeditor

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.fragments.ColorPickerDialogFragment
import com.maximperevalov.shapeeditor.views.ShapeEditorView
import com.maximperevalov.shapeeditor.views.ShapeInfoButtonView


/**
 * Головне вікно додатку
 */
class MainActivity : AppCompatActivity() {

    private lateinit var shapeEditorView: ShapeEditorView

    private lateinit var btnShapes: ImageButton
    private lateinit var btnClear: ImageButton

    private lateinit var btnColorPicker: ImageButton
    private lateinit var btnStrokeColorPicker: ImageButton

    private lateinit var btnShapeInfoInfo: ShapeInfoButtonView

    private lateinit var popupMenu: PopupMenu

    private lateinit var fillColorPickerDialog: ColorPickerDialogFragment
    private lateinit var strokeColorPickerDialog: ColorPickerDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEditorView()
        initClearButton()
        initShapeButton()
        initFillColorPickerButton()
        initStrokeColorPickerButton()
        initPopupMenu()
        initFillColorPickerDialog()
        initStrokeColorPickerDialog()
        initShapeInfoButton()
    }

    private fun initShapeInfoButton() {
        btnShapeInfoInfo = findViewById<ShapeInfoButtonView>(R.id.btn_shape_info).apply {
            init(100, 100, 15F)
            setShapeType(shapeEditorView.selectedShape)
            fillColor = shapeEditorView.styleManager.fillColor
            strokeColor = shapeEditorView.styleManager.strokeColor
        }
    }

    private fun initStrokeColorPickerDialog() {
        val colorSelectionListener = ColorPickerDialogFragment.ColorSelectionListener {
            shapeEditorView.styleManager.strokeColor = it
            btnShapeInfoInfo.strokeColor = it
        }

        val checkboxListener: (CompoundButton, Boolean) -> Unit = { btn, isChecked ->
            try {
                shapeEditorView.styleManager.hasStroke = isChecked
                btnShapeInfoInfo.hasStroke = isChecked
            } catch (e: Exception) {
                btn.isChecked = !isChecked
                showStrokeAndFillWarning()
            }
        }

        strokeColorPickerDialog = ColorPickerDialogFragment(
            "Обведення",
            "Обведення",
            checkboxListener,
            colorSelectionListener,
        )
    }

    private fun showStrokeAndFillWarning() {
        Toast.makeText(
            this,
            "Заповнення і обводку не можна виключити одночасно ",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun initFillColorPickerDialog() {
        val colorSelectionListener = ColorPickerDialogFragment.ColorSelectionListener {
            shapeEditorView.styleManager.fillColor = it
            btnShapeInfoInfo.fillColor = it
        }

        val checkboxListener: (CompoundButton, Boolean) -> Unit = { btn, isChecked ->
            try {
                shapeEditorView.styleManager.hasFill = isChecked
                btnShapeInfoInfo.hasFill = isChecked
            } catch (e: Exception) {
                btn.isChecked = !isChecked
                showStrokeAndFillWarning()
            }
        }

        fillColorPickerDialog = ColorPickerDialogFragment(
            "Заповнення",
            "Заповненення",
            checkboxListener,
            colorSelectionListener
        )
    }

    private fun initStrokeColorPickerButton() {
        btnStrokeColorPicker = findViewById<ImageButton>(R.id.btn_color_stroke).apply {
            setOnClickListener {
                strokeColorPickerDialog.show(supportFragmentManager, "strokeColorPickerDialog")
            }
        }
    }

    private fun initFillColorPickerButton() {
        btnColorPicker = findViewById<ImageButton>(R.id.btn_fill_color).apply {
            setOnClickListener {
                fillColorPickerDialog.show(supportFragmentManager, "colorPicker")
            }
        }
    }

    private fun initPopupMenu() {
        popupMenu = PopupMenu(this, btnShapes)
        popupMenu.inflate(R.menu.shape_menu)
        popupMenu.setOnMenuItemClickListener {
            it.isChecked = true
            val selectedShape = getSelectedShapeByMenuItemId(it.itemId)
            shapeEditorView.selectedShape = selectedShape
            btnShapeInfoInfo.setShapeType(selectedShape)
            true
        }
    }

    private fun getSelectedShapeByMenuItemId(itemId: Int) = when (itemId) {
        R.id.shape_point_menu_item -> SelectedShape.POINT
        R.id.shape_rect_menu_item -> SelectedShape.RECTANGLE
        R.id.shape_ellipse_menu_item -> SelectedShape.ELLIPSE
        R.id.shape_line_menu_item -> SelectedShape.LINE
        else -> throw RuntimeException("${itemId}: Does not exist corresponding shape")
    }

    private fun initEditorView() {
        shapeEditorView = findViewById<ShapeEditorView>(R.id.shape_editor).apply {
            init(getMetrics())
        }
    }

    /**
     * Якщо натиснути один раз, то стереться останній об'ект,
     * якщо натиснути і тримати, то зітруться всі об'екти.
     */
    private fun initClearButton() {
        btnClear = findViewById<ImageButton>(R.id.btn_clear).apply {
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
        btnShapes = findViewById<ImageButton>(R.id.btn_shapes).apply {
            setOnClickListener {
                popupMenu.show()
            }
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

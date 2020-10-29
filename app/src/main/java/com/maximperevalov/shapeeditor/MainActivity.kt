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
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import com.maximperevalov.shapeeditor.fragments.ColorPickerDialogFragment
import com.maximperevalov.shapeeditor.views.ShapeEditorView
import com.maximperevalov.shapeeditor.views.ShapeInfoButtonView

/**
 * Головне вікно додатку
 */
class MainActivity : AppCompatActivity() {

    private lateinit var shapeEditorController: ShapeEditorController

    private lateinit var popupMenu: PopupMenu

    private lateinit var fillColorPickerDialog: ColorPickerDialogFragment
    private lateinit var strokeColorPickerDialog: ColorPickerDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shapeEditor = initEditorView()
        val shapeInfoButton = initShapeInfoButton(shapeEditor.selectedShape, shapeEditor.shapeStyle)
        initShapeEditorController(shapeEditor, shapeInfoButton)
        initClearButton()

        val shapesButton = initShapeButton()
        initPopupMenu(shapesButton)

        initFillColorPickerDialog()
        initFillColorPickerButton()
        initStrokeColorPickerDialog()
        initStrokeColorPickerButton()
    }

    private fun initShapeEditorController(
        shapeEditorView: ShapeEditorView,
        shapeInfoButtonView: ShapeInfoButtonView
    ) {
        shapeEditorController = ShapeEditorController(shapeEditorView, shapeInfoButtonView)
    }

    private fun initShapeInfoButton(selectedShape: SelectedShape, shapeStyle: Style) =
        findViewById<ShapeInfoButtonView>(R.id.btn_shape_info).apply {
            init(100, 100, 15F)
            setSelectedShape(selectedShape)
            style = shapeStyle
        }


    private fun initStrokeColorPickerDialog() {
        val colorSelectionListener = ColorPickerDialogFragment.ColorSelectionListener {
            shapeEditorController.setStrokeColor(it)
        }

        val strokelessCheckboxListener: (CompoundButton, Boolean) -> Unit = { btn, isChecked ->
            try {
                shapeEditorController.isStrokeless(!isChecked)
            } catch (e: Exception) {
                btn.isChecked = !isChecked
                showStrokeAndFillWarning()
            }
        }

        strokeColorPickerDialog = ColorPickerDialogFragment(
            "Обведення",
            "Обведення",
            strokelessCheckboxListener,
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
            shapeEditorController.setFillColor(it)
        }

        val absoluteTransparentCheckboxListener: (CompoundButton, Boolean) -> Unit =
            { btn, isChecked ->
                try {
                    shapeEditorController.isAbsoluteTransparent(!isChecked)
                } catch (e: Exception) {
                    btn.isChecked = !isChecked
                    showStrokeAndFillWarning()
                }
            }

        fillColorPickerDialog = ColorPickerDialogFragment(
            "Заповнення",
            "Заповненення",
            absoluteTransparentCheckboxListener,
            colorSelectionListener
        )
    }

    private fun initStrokeColorPickerButton() {
        findViewById<ImageButton>(R.id.btn_color_stroke).apply {
            setOnClickListener {
                strokeColorPickerDialog.show(supportFragmentManager, "strokeColorPickerDialog")
            }
        }
    }

    private fun initFillColorPickerButton() {
        findViewById<ImageButton>(R.id.btn_fill_color).apply {
            setOnClickListener {
                fillColorPickerDialog.show(supportFragmentManager, "colorPicker")
            }
        }
    }

    private fun initPopupMenu(btnShapes: ImageButton) {
        popupMenu = PopupMenu(this, btnShapes)
        popupMenu.inflate(R.menu.shape_menu)
        popupMenu.setOnMenuItemClickListener {
            it.isChecked = true
            val selectedShape = getSelectedShapeByMenuItemId(it.itemId)
            shapeEditorController.selectedShape = selectedShape
            true
        }
    }

    private fun getSelectedShapeByMenuItemId(itemId: Int) = when (itemId) {
        R.id.shape_point_menu_item -> SelectedShape.POINT
        R.id.shape_rect_menu_item -> SelectedShape.RECTANGLE
        R.id.shape_ellipse_menu_item -> SelectedShape.ELLIPSE
        R.id.shape_line_menu_item -> SelectedShape.LINE
        R.id.shape_circle_menu_item -> SelectedShape.CIRCLE
        R.id.shape_line_with_2circle -> SelectedShape.LINE_WITH_DOUBLE_CIRCLE
        else -> throw RuntimeException("${itemId}: Does not exist corresponding shape")
    }

    private fun initEditorView() = findViewById<ShapeEditorView>(R.id.shape_editor).apply {
        init(getMetrics())
    }

    /**
     * Якщо натиснути один раз, то стереться останній об'ект,
     * якщо натиснути і тримати, то зітруться всі об'екти.
     */
    private fun initClearButton() {
        findViewById<ImageButton>(R.id.btn_clear).apply {
            setOnClickListener {
                shapeEditorController.clearLastShape()
            }
            setOnLongClickListener {
                shapeEditorController.clearAllShapes()
                true
            }
        }
    }

    private fun initShapeButton() = findViewById<ImageButton>(R.id.btn_shapes).apply {
        setOnClickListener {
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

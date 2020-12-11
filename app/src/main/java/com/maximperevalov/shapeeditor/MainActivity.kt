package com.maximperevalov.shapeeditor

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.events.ClearShapesEventHandler
import com.maximperevalov.shapeeditor.domain.events.DrawNewShapeEventHandler
import com.maximperevalov.shapeeditor.domain.events.EditorEvent
import com.maximperevalov.shapeeditor.domain.events.RemoveShapeEventHandler
import com.maximperevalov.shapeeditor.domain.shapes.*
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import com.maximperevalov.shapeeditor.fragments.ColorPickerDialogFragment
import com.maximperevalov.shapeeditor.table.ShapeTable
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
    private lateinit var table: ShapeTable
    private lateinit var layout: LinearLayout

    private var isTableVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        table = findViewById(R.id.shape_table)
        val shapeEditorView = createEditorView()
        val shapeInfoButton =
            initShapeInfoButton(
                shapeEditorView.shapeEditor.selectedShape,
                shapeEditorView.shapeEditor.currentShapeStyle
            )
        initShapeEditorController(shapeEditorView, shapeInfoButton)
        initTable(shapeInfoButton)
        initClearButton()

        val shapesButton = initShapeButton()
        initPopupMenu(shapesButton)

        initFillColorPickerDialog()
        initFillColorPickerButton()
        initStrokeColorPickerDialog()
        initStrokeColorPickerButton()
    }

    private fun initTable(
        shapeInfoBtn: ImageButton,
    ): ShapeTable {
        layout = findViewById(R.id.main_layout)
        val tableScrollView = findViewById<ScrollView>(R.id.table_wrapper)
        val listener = ShapeTableListenerImpl(shapeEditorController, table)
        table.init(listener)
        shapeEditorController.addEventHandler(
            EditorEvent.DrawNewShape,
            object : DrawNewShapeEventHandler {
                override fun onAddNewShape(shape: Shape) {
                    var x1 = 0F
                    var y1 = 0F
                    var x2 = 0F
                    var y2 = 0F
                    when (shape) {
                        is Circle -> {
                            x1 = shape.centerX
                            y1 = shape.centerY
                            x2 = shape.centerY + shape.radius
                            y2 = shape.centerY + shape.radius
                        }
                        is Cube -> {
                            x1 = shape.x1
                            y1 = shape.y1
                            x2 = shape.x2
                            y2 = shape.y2
                        }
                        is Ellipse -> {
                            x1 = shape.x
                            y1 = shape.y
                            x2 = shape.x + shape.width
                            y2 = shape.x + shape.height
                        }
                        is Line -> {
                            x1 = shape.startX
                            y1 = shape.startY
                            x2 = shape.endX
                            y2 = shape.endY
                        }
                        is LineWithDoubleCircle -> {
                            x1 = shape.startX
                            y1 = shape.startY
                            x2 = shape.endX
                            y2 = shape.endY
                        }
                        is Point -> {
                            x1 = shape.x
                            y1 = shape.y
                            x2 = shape.x
                            y2 = shape.y
                        }
                        is Rectangle -> {
                            x1 = shape.x
                            y1 = shape.y
                            x2 = shape.x + shape.width
                            y2 = shape.x + shape.height
                        }
                    }
                    val id = shape.hashCode().toString()
                    val shapeType = when (shape) {
                        is Circle -> "Коло"
                        is Cube -> "Куб"
                        is Ellipse -> "Еліпс"
                        is Line -> "Лінія"
                        is LineWithDoubleCircle -> "Лінія з двома колами"
                        is Point -> "Точка"
                        is Rectangle -> "Прямокутник"
                        else -> throw RuntimeException("${shape::javaClass}: Does not exist value for this shape class")
                    }
                    table.addShapeRow(id, shapeType, x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
                }
            })

        shapeEditorController.addEventHandler(
            EditorEvent.RemoveShape,
            object : RemoveShapeEventHandler {
                override fun onRemoveShape(shape: Shape) {
                    table.removeShapeRow(shape.hashCode().toString())
                }
            })

        shapeEditorController.addEventHandler(
            EditorEvent.ClearShapes,
            object : ClearShapesEventHandler {
                override fun onClearShapes() {
                    table.clearShapes()
                }
            })

        shapeInfoBtn.setOnClickListener {
            if (isTableVisible) {
                tableScrollView.visibility = View.GONE
            } else {
                tableScrollView.visibility = View.VISIBLE
            }
            isTableVisible = !isTableVisible
        }
        return table
    }

    private fun initShapeEditorController(
        shapeEditorView: ShapeEditorView,
        shapeInfoButtonView: ShapeInfoButtonView,
    ) {
        shapeEditorController = ShapeEditorController(shapeEditorView, shapeInfoButtonView, table)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miSave -> shapeEditorController.saveShapesToStorage()
            R.id.miStorage -> shapeEditorController.setShapesFromStorage()
        }
        return super.onOptionsItemSelected(item)
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
        R.id.shape_cube -> SelectedShape.CUBE
        else -> throw RuntimeException("${itemId}: Does not exist corresponding shape")
    }

    private fun createEditorView() = findViewById<ShapeEditorView>(R.id.shape_editor).apply {
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

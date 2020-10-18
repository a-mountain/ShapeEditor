package com.maximperevalov.shapeeditor

import com.maximperevalov.shapeeditor.domain.Color

/**
 * Трансформує, кросплатформенний клас Сolor в реалізацію цього класу на Android
 */
fun Color.toAndroidColor() = when (this) {
    Color.RED -> android.graphics.Color.RED
    Color.BLACK -> android.graphics.Color.BLACK
    Color.WHITE -> android.graphics.Color.WHITE
    Color.YELLOW -> android.graphics.Color.YELLOW
    Color.BLUE -> android.graphics.Color.BLUE
    Color.ORANGE -> android.graphics.Color.rgb(255, 128, 0)
    Color.GRAY -> android.graphics.Color.GRAY
    Color.GREEN -> android.graphics.Color.GREEN
    Color.PURPLE -> android.graphics.Color.rgb(128, 0, 128)
    Color.PINK -> android.graphics.Color.rgb(255, 192, 203)
}

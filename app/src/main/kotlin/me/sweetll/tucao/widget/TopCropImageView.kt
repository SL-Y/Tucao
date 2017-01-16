package me.sweetll.tucao.widget

import android.content.Context
import android.widget.ImageView

class TopCropImageView(context: Context?): ImageView(context) {
    init {
        scaleType = ScaleType.MATRIX
    }

    override fun setFrame(l: Int, t: Int, r: Int, b: Int): Boolean {
        if (drawable == null) {
            return super.setFrame(l, t, r, b)
        }
        val matrix = imageMatrix
        val scaleWidth = width / drawable.intrinsicWidth.toFloat()
        val scaleHeight = height / drawable.intrinsicHeight.toFloat()
        val scaleFactor = if (scaleWidth > scaleHeight) scaleWidth else scaleHeight
        matrix.setScale(scaleFactor, scaleFactor, 0f, 0f)
        if (scaleFactor == scaleHeight) {
            val translateX = (drawable.intrinsicWidth * scaleFactor - width) / 2f
            matrix.postTranslate(-translateX, 0f)
        }
        imageMatrix = matrix
        return super.setFrame(l, t, r, b)
    }
}

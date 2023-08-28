package priscilla.draw.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View

/**
Created by Zebra-RD张先杰 on 2022年6月20日16:26:52

Description:
 */
fun Float.px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
    this,
    Resources.getSystem().displayMetrics)

fun Context.getColorPrimary(): Int {
    val typedValue = TypedValue();
    theme.resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)
    return typedValue.data
}


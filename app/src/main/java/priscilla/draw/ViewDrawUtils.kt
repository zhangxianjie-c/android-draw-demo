package priscilla.draw

import android.content.res.Resources
import android.util.TypedValue

/**
Created by Zebra-RD张先杰 on 2022年6月20日16:26:52

Description:
 */
fun Float.px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
    this,
    Resources.getSystem().displayMetrics)
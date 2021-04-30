package com.example.blackjack.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.blackjack.R

class CustomTextView(context: Context, attrs: AttributeSet)
    : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    init {
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 40F)
        typeface = ResourcesCompat.getFont(context, R.font.f19268)
    }
}
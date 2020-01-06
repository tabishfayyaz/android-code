package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.custom_text_view.view.custom_text_view_icon
import kotlinx.android.synthetic.main.custom_text_view.view.custom_text_view_heading
import kotlinx.android.synthetic.main.custom_text_view.view.custom_text_view_subheading

class CustomTextView : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.custom_text_view, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView, defStyleAttr, R.style.AppTheme)

        setHeading(typedArray.getString(R.styleable.CustomTextView_heading) ?: resources.getString(R.string.placeholder))
        setSubheading(typedArray.getString(R.styleable.CustomTextView_subheading) ?: resources.getString(R.string.placeholder))
        setIcon(typedArray.getResourceId(R.styleable.CustomTextView_icon, 0))

        typedArray.recycle()
    }

    /**
     * @attr ref android.R.styleable#CustomTextView_heading
     */
    fun setHeading(titleString: String?) {
        custom_text_view_heading?.text = titleString
    }

    /**
     * @attr ref android.R.styleable#CustomTextView_subheading
     */
    fun setSubheading(valueString: String?) {
        custom_text_view_subheading?.text = valueString
    }

    /**
     * @attr ref android.R.styleable#CustomTextView_icon
     */
    fun setIcon(iconDrawableRes: Int) {
        if (iconDrawableRes == 0) return
        val drawable = ContextCompat.getDrawable(context, iconDrawableRes) ?: return
        custom_text_view_icon?.setImageDrawable(drawable)
    }

    fun setIconColor(iconColorInt: Int) {
        custom_text_view_icon?.setColorFilter(iconColorInt)
    }
}
package dn.marjan.texteditmodules.span

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ScaleXSpan
import android.util.AttributeSet
import android.widget.TextView
import dn.marjan.texteditmodules.R

class LetterSpacingTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

     var mLetterSpacing = LetterSpacing.BIGGEST
    set(value) {
        if (value != 0F)
            applyLetterSpacing(value)
    }


    private var mOriginalText: CharSequence? = ""

    init {
        mOriginalText = super.getText()
        val ta = context.obtainStyledAttributes(attrs, R.styleable.LetterSpacingTextView)
        this.mLetterSpacing = ta.getFloat(R.styleable.WordSpacingTextView_ws_space, LetterSpacing.BIGGEST)
        this.invalidate()
    }


    private fun applyLetterSpacing(span: Float) {
        if (this == null || this.mOriginalText == null) return
        val builder = StringBuilder()
        for (i in 0 until mOriginalText!!.length) {
            val c = "" + mOriginalText!![i]
            builder.append(c.toLowerCase())
            if (i + 1 < mOriginalText!!.length) {
                builder.append("\u00A0")
            }
        }
        val finalText = SpannableString(builder.toString())
        if (builder.toString().length > 1) {
            var i = 1
            while (i < builder.toString().length) {
                finalText.setSpan(ScaleXSpan(span), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                i += 2
            }
        }
        super.setText(finalText, TextView.BufferType.SPANNABLE)
    }

    object LetterSpacing {
        const val NORMAL = 0F
        const val NORMALBIG = 0.025F
        const val BIG = 0.05F
        val BIGGEST = 0.2F
    }
}
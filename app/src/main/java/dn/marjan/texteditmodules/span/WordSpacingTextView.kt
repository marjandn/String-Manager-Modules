package dn.marjan.texteditmodules.span

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ScaleXSpan
import android.util.AttributeSet
import android.widget.TextView
import dn.marjan.texteditmodules.R


class WordSpacingTextView(context: Context, val attrs: AttributeSet) : AppCompatTextView(context, attrs) {
     var mWordSpacing = WordSpacing.BIGGEST
        set(value) {
            applyWordSpacing(value)
        }


    private var mOriginalText: CharSequence = ""

    init {
        mOriginalText = super.getText()
        val ta = context.obtainStyledAttributes(attrs, R.styleable.WordSpacingTextView)
        this.mWordSpacing = ta.getFloat(R.styleable.WordSpacingTextView_ws_space, WordSpacing.BIGGEST)
        this.invalidate()
    }


    private fun applyWordSpacing(span: Float) {
        if (this == null || this.mOriginalText == null) return


        val finalText = SpannableString(mOriginalText)
        if (mOriginalText.length > 1) {

            var end = 0
            while (true) {
                end = mOriginalText.indexOf(' ', end + 1)

                if (end == -1)
                    break

                finalText.setSpan(
                        ScaleXSpan(span),
                        end,
                        end + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE  //for more info https://developer.android.com/reference/android/text/Spanned
                )
            }
            super.setText(finalText, TextView.BufferType.SPANNABLE)
        }

    }
}

object WordSpacing {
    const val NORMAL = 0f
    const val NORMALBIG = 0.025.toFloat()
    const val BIG = 0.05.toFloat()
    const val BIGGEST = 0.2.toFloat()
}



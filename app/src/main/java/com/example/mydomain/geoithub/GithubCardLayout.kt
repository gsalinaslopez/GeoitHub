package com.example.mydomain.geoithub

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.RelativeLayout

/**
 * Custom Button with a special 'pressed' effect for touch events.
 */
class GithubCardLayout : RelativeLayout {

    private var mSwiping = false
    private var mDownX = 0f
    private var mDownY = 0f
    private var mTouchSlop = 0f

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        isFocusable = true
        descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS
        setWillNotDraw(false)
        isClickable = true

        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop * 2f
    }

}
package com.ct.circularprogressview

/**
 * Created by Gokul on 1/8/2018.
 * CircularProgressView
 */


import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.RectF
import android.graphics.Typeface
import android.support.v4.view.InputDeviceCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.ct.circularprogressview.R


class CircularProgressView : View {
    private var mArcRadius: Int = 0
    private var mArcRect: RectF? = null
    private var mArcStartAngle: Int = 0
    private var mArcWidth: Int = 0
    private var mBaseArcColor: Int = 0
    private var mBaseArcPaint: Paint? = null
    private var mBaseArcSweepAngle: Int = 0
    private var mContext: Context? = null
    private var mTypeface: Typeface? = null
    private var mLabelTypeface: Typeface? = null
    private var mProgressArcColor: Int = 0
    private var mProgressArcPaint: Paint? = null
    var progressArcSweepAngle: Float = 0.toFloat()

    private var mProgressLabelStr: String? = null
    private var mProgressTimeLabelStr: String? = null
    private var mProgressTimeTextLabelStr: String? = null

    private var mProgressLabelTextColor: Int = 0
    private var mProgressLabelTextTimeColor: Int = 0
    private var mProgressLabelTextTimeTextColor: Int = 0

    private var mProgressLabelTextPaint: TextPaint? = null
    private var mProgressLabelTimePaint: TextPaint? = null
    private var mProgressLabelTimeElapsedPaint: TextPaint? = null

    private var mProgressLabelTextSize: Int = 0
    private val mProgressLabelTImeSize: Int = 0
    private val mProgressLabelElapsedSize: Int = 0

    private var mProgressValueStr: String? = null
    private var mProgressValueTextColor: Int = 0
    private var mProgressValueTextPaint: TextPaint? = null
    private var mProgressValueTextSize: Int = 0
    private var mRotationAngle: Int = 0

    internal var mTimeFull = "0:00:00"

    constructor(context: Context) : super(context) {
        this.mRotationAngle = -90
        this.mArcStartAngle = 0
        this.progressArcSweepAngle = 0.0f
        this.mBaseArcSweepAngle = 360
        this.mProgressValueStr = "0:00:00"
        this.mArcRect = RectF()
        this.mBaseArcColor = -16776961
        this.mProgressArcColor = InputDeviceCompat.SOURCE_ANY
        this.mProgressLabelTextColor = -16711936
        this.mProgressLabelTextTimeColor = -16711936
        this.mProgressLabelTextTimeTextColor = -16711936
        this.mProgressValueTextColor = -1
        this.mProgressLabelTextSize = 32
        this.mProgressValueTextSize = 125
        this.mArcWidth = 15
        this.mProgressLabelStr = "Progress"
        this.mProgressTimeLabelStr = "Put your data here"
        this.mProgressTimeTextLabelStr = "0:00:00"
        initialization(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mRotationAngle = -90
        this.mArcStartAngle = 0
        this.progressArcSweepAngle = 0.0f
        this.mBaseArcSweepAngle = 360
        this.mProgressValueStr = "0:00:00"
        this.mArcRect = RectF()
        this.mBaseArcColor = -16776961
        this.mProgressArcColor = InputDeviceCompat.SOURCE_ANY
        this.mProgressLabelTextColor = -16711936
        this.mProgressLabelTextTimeColor = -16711936
        this.mProgressLabelTextTimeTextColor = -16711936
        this.mProgressValueTextColor = -1
        this.mProgressLabelTextSize = 32
        this.mProgressValueTextSize = 125
        this.mArcWidth = 15
        this.mProgressLabelStr = "Progress"
        this.mProgressTimeLabelStr = "Time Elapsed"
        this.mProgressTimeTextLabelStr = "0:00:00"
        initialization(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        this.mRotationAngle = -90
        this.mArcStartAngle = 0
        this.progressArcSweepAngle = 0.0f
        this.mBaseArcSweepAngle = 360
        this.mProgressValueStr = "0:00:00"
        this.mArcRect = RectF()
        this.mBaseArcColor = -16776961
        this.mProgressArcColor = InputDeviceCompat.SOURCE_ANY
        this.mProgressLabelTextColor = -16711936
        this.mProgressLabelTextTimeColor = -16711936
        this.mProgressLabelTextTimeTextColor = -16711936
        this.mProgressValueTextColor = -1
        this.mProgressLabelTextSize = 32
        this.mProgressValueTextSize = 125
        this.mArcWidth = 15
        this.mProgressLabelStr = "Progress"
        this.mProgressTimeLabelStr = "Time Elapsed"
        this.mProgressTimeTextLabelStr = "0:00:00"
        initialization(context, attrs, defStyle)
    }

    private fun initialization(context: Context, attrs: AttributeSet?, defStyle: Int) {
        this.mContext = context
        var typedArray: TypedArray? = null
        if (attrs != null) {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressView, defStyle, 0)
            this.mBaseArcColor =
                typedArray!!.getColor(R.styleable.CircularProgressView_cv_base_arc_color, this.mBaseArcColor)
            this.mProgressArcColor =
                typedArray.getColor(R.styleable.CircularProgressView_cv_progress_arc_color, this.mProgressArcColor)
            this.mProgressLabelTextColor = typedArray.getColor(
                R.styleable.CircularProgressView_cv_progress_label_text_color,
                this.mProgressLabelTextColor
            )
            this.mProgressValueTextColor = typedArray.getColor(
                R.styleable.CircularProgressView_cv_progress_value_text_color,
                this.mProgressValueTextColor
            )
            this.mProgressLabelTextSize = typedArray.getDimension(
                R.styleable.CircularProgressView_cv_progress_label_text_size,
                this.mProgressLabelTextSize.toFloat()
            ).toInt()
            this.mProgressValueTextSize = typedArray.getDimension(
                R.styleable.CircularProgressView_cv_progress_value_text_size,
                this.mProgressValueTextSize.toFloat()
            ).toInt()
            this.mArcWidth =
                typedArray.getDimension(R.styleable.CircularProgressView_cv_arc_width, this.mArcWidth.toFloat()).toInt()
            this.mProgressLabelStr = typedArray.getString(R.styleable.CircularProgressView_cv_progress_label_str)

            val fontNameLabel = typedArray.getString(R.styleable.CircularProgressView_cv_label_fontName)
            val fontName = typedArray.getString(R.styleable.CircularProgressView_cv_fontName)
            if (fontName != null) {
                val typeface = Typeface.createFromAsset(mContext!!.assets, fontName)
                if (typeface != null) {
                    this.mTypeface = typeface
                }
            }
            if (fontNameLabel != null) {
                val typeface = Typeface.createFromAsset(mContext!!.assets, fontNameLabel)
                if (typeface != null) {
                    this.mLabelTypeface = typeface
                }
            }
        }

        this.mProgressLabelTextPaint = TextPaint()
        if (mLabelTypeface != null) {
            this.mProgressLabelTextPaint!!.typeface = mLabelTypeface
        }
        this.mProgressLabelTextPaint!!.color = this.mProgressLabelTextColor
        this.mProgressLabelTextPaint!!.textSize = this.mProgressLabelTextSize.toFloat()
        this.mProgressLabelTextPaint!!.isAntiAlias = true

        this.mProgressValueTextPaint = TextPaint()
        if (mTypeface != null) {
            this.mProgressValueTextPaint!!.typeface = mTypeface
        }
        this.mProgressValueTextPaint!!.textAlign = Paint.Align.LEFT
        this.mProgressValueTextPaint!!.color = this.mProgressValueTextColor
        this.mProgressValueTextPaint!!.textSize = this.mProgressValueTextSize.toFloat()
        this.mProgressValueTextPaint!!.isAntiAlias = true

        this.mProgressLabelTimePaint = TextPaint()
        if (mTypeface != null) {
            this.mProgressLabelTimePaint!!.typeface = mLabelTypeface
        }
        this.mProgressLabelTimePaint!!.textAlign = Paint.Align.LEFT
        this.mProgressLabelTimePaint!!.color = this.mProgressLabelTextColor
        this.mProgressLabelTimePaint!!.textSize = this.mProgressLabelTextSize.toFloat()
        this.mProgressLabelTimePaint!!.isAntiAlias = true


        this.mProgressLabelTimeElapsedPaint = TextPaint()
        if (mTypeface != null) {
            this.mProgressLabelTimeElapsedPaint!!.typeface = mLabelTypeface
        }
        this.mProgressLabelTimeElapsedPaint!!.color = this.mProgressValueTextColor
        this.mProgressLabelTimeElapsedPaint!!.textSize = this.mProgressLabelTextSize.toFloat()
        this.mProgressLabelTimeElapsedPaint!!.isAntiAlias = true
        this.mProgressLabelTimeElapsedPaint!!.isLinearText = true


        this.mBaseArcPaint = Paint()
        this.mBaseArcPaint!!.color = this.mBaseArcColor
        this.mBaseArcPaint!!.isAntiAlias = true
        this.mBaseArcPaint!!.style = Style.STROKE
        this.mBaseArcPaint!!.strokeWidth = this.mArcWidth.toFloat()
        this.mProgressArcPaint = Paint()
        this.mProgressArcPaint!!.color = this.mProgressArcColor
        this.mProgressArcPaint!!.isAntiAlias = true
        this.mProgressArcPaint!!.style = Style.STROKE
        this.mProgressArcPaint!!.strokeWidth = this.mArcWidth.toFloat()
        this.mProgressArcPaint!!.setShadowLayer(this.mArcWidth.toFloat(), 0.0f, 0.0f, this.mProgressArcColor)
        setLayerType(1, this.mProgressArcPaint)
        typedArray?.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val arcDiameter: Int
        val height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val minOfScreenWidthHeight = Math.min(width, height)
        if (minOfScreenWidthHeight == width) {
            arcDiameter = minOfScreenWidthHeight - paddingLeft - paddingRight - this.mArcWidth
        } else {
            arcDiameter = minOfScreenWidthHeight - paddingTop - paddingBottom - this.mArcWidth
        }
        this.mArcRadius = arcDiameter / 2
        val top = (height / 2 - arcDiameter / 2).toFloat()
        val left = (width / 2 - arcDiameter / 2).toFloat()
        this.mArcRect!!.set(
            this.mArcWidth.toFloat() + left,
            this.mArcWidth.toFloat() + top,
            arcDiameter.toFloat() + left - this.mArcWidth.toFloat(),
            arcDiameter.toFloat() + top - this.mArcWidth.toFloat()
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBaseArc(canvas)
        drawProgressArc(canvas)
        drawProgressLabelText(canvas)
        drawProgressValueText(canvas)
        //   drawProgressTimeLabelText(canvas);
        drawProgressTimeValueText(canvas)
    }

    private fun drawBaseArc(canvas: Canvas) {
        canvas.drawArc(
            this.mArcRect!!,
            (this.mRotationAngle + this.mArcStartAngle).toFloat(),
            this.mBaseArcSweepAngle.toFloat(),
            false,
            this.mBaseArcPaint!!
        )
    }

    private fun drawProgressArc(canvas: Canvas) {
        if (this.progressArcSweepAngle > this.mBaseArcSweepAngle.toFloat()) {
            this.progressArcSweepAngle = this.mBaseArcSweepAngle.toFloat()
        }
        canvas.drawArc(
            this.mArcRect!!,
            (this.mRotationAngle + this.mArcStartAngle).toFloat(),
            this.progressArcSweepAngle,
            false,
            this.mProgressArcPaint!!
        )
    }

    private fun drawProgressLabelText(canvas: Canvas) {
        val progressLabelTextHeight = this.mProgressLabelTextPaint!!.descent() + this.mProgressLabelTextPaint!!.ascent()
        val progressValueTextHeight = this.mProgressValueTextPaint!!.descent() + this.mProgressValueTextPaint!!.ascent()
        if (this.mProgressLabelStr == null) {
            this.mProgressLabelStr = "Progress"
        }
        canvas.drawText(
            this.mProgressLabelStr!!,
            this.mArcRect!!.centerX() - this.mProgressLabelTextPaint!!.measureText(this.mProgressLabelStr) / 2.0f,
            this.mArcRect!!.centerY() + progressLabelTextHeight + 1f * progressValueTextHeight,
            this.mProgressLabelTextPaint!!
        )
    }

    private fun drawProgressValueText(canvas: Canvas) {
        val xConst = this.mArcRect!!.centerX() - this.mProgressValueTextPaint!!.measureText(mTimeFull) / 2.0f
        canvas.drawText(
            this.mProgressValueStr!!,
            xConst,
            this.mArcRect!!.centerY() - (this.mProgressValueTextPaint!!.descent() + this.mProgressValueTextPaint!!.ascent()) / 2.0f,
            this.mProgressValueTextPaint!!
        )
    }

    //////////////////////////////////////////////////
    private fun drawProgressTimeLabelText(canvas: Canvas) {
        val progressLabelTextHeight = this.mProgressLabelTextPaint!!.descent() + this.mProgressLabelTextPaint!!.ascent()
        val progressValueTextHeight = this.mProgressValueTextPaint!!.descent() - this.mProgressValueTextPaint!!.ascent()

        if (this.mProgressTimeLabelStr == null) {
            this.mProgressTimeLabelStr = "Time Elapsed"
        }
        val x = this.mArcRect!!.centerX() - this.mProgressLabelTextPaint!!.measureText(this.mProgressLabelStr) / 4f
        val y = this.mArcRect!!.centerY() - progressLabelTextHeight + .8f * progressValueTextHeight
        canvas.drawText(
            this.mProgressTimeLabelStr!!, x, y,
            this.mProgressLabelTextPaint!!
        )

    }


    private fun drawProgressTimeValueText(canvas: Canvas) {
        val progressLabelTextHeight = this.mProgressLabelTextPaint!!.descent() + this.mProgressLabelTextPaint!!.ascent()
        val progressValueTextHeight = this.mProgressValueTextPaint!!.descent() - this.mProgressValueTextPaint!!.ascent()
        val xConst = this.mArcRect!!.centerX() - this.mProgressValueTextPaint!!.measureText(mTimeFull) / 7f
        val x = this.mArcRect!!.centerX() - this.mProgressValueTextPaint!!.measureText(this.mProgressValueStr) / 7f
        val y = this.mArcRect!!.centerY() - progressLabelTextHeight + 1.1f * progressValueTextHeight
        canvas.drawText(
            this.mProgressTimeTextLabelStr!!, xConst,
            y, this.mProgressLabelTimeElapsedPaint!!
        )
    }


    fun updateProgressWithProgressText(progressText: String, progressSweepAngle: Float, progressTimeElapsed: String) {
        this.mProgressValueStr = progressText
        this.mProgressTimeTextLabelStr = progressTimeElapsed
        this.progressArcSweepAngle = progressSweepAngle
        invalidate()
    }

    companion object {
        private var TAG_NAME: String? = null

        init {
            TAG_NAME = "CircularProgressView"
        }
    }
}
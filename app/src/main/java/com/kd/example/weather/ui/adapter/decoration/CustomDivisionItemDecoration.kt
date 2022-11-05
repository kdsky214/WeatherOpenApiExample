package com.kd.example.weather.ui.adapter.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class CustomDivisionItemDecoration(context: Context, titleResId:Int, contentResId:Int) : RecyclerView.ItemDecoration() {

    private val padding = 20
    private var titleDivider: Drawable? = null
    private var contentDivider: Drawable? = null

    init {
        titleDivider = ContextCompat.getDrawable(context, titleResId)
        contentDivider = ContextCompat.getDrawable(context, contentResId)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)


        val position = parent.getChildAdapterPosition(view)
        val titlePosition = position % 7
        val left = parent.paddingLeft + padding
        val right = parent.width - parent.paddingRight + padding

//        if(titlePosition == 0){
//            /* Title Divider xml 적용 */
//            val params = view.layoutParams as RecyclerView.LayoutParams
//            val top = view.bottom + params.bottomMargin
//            val bottom = top + (titleDivider?.intrinsicHeight ?: 0)
//
//
//            val canvas = Canvas()
//            canvas.rect
//            titleDivider?.let {
//                it.setBounds(left, top, right, bottom)
//                it.draw(c)
//            }
//        }
    }
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft + padding
        val right = parent.width - parent.paddingRight + padding
        val childCount = parent.childCount



//        Log.e("TAG","state.targetScrollPosition : ${state.targetScrollPosition}  // state.remainingScrollVertical:${state.remainingScrollVertical}")
        Log.e("TAG","childCount : $childCount  // state.itemCount:${state.itemCount}")
        for (i in 0 until childCount) {
            //title 순번 체크
            val titlePosition = i % 7
            if(titlePosition == 0){
                /* Title Divider xml 적용 */
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + (titleDivider?.intrinsicHeight ?: 0)

                titleDivider?.let {
                    it.setBounds(left, top, right, bottom)
                    it.draw(c)
                }
            }else{
                /* Content Divider xml 적용 */
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + (contentDivider?.intrinsicHeight ?: 0)
                contentDivider?.let {
                    it.setBounds(left, top, right, bottom)
                    it.draw(c)
                }
            }
        }
    }
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

    }

}
//class CustomDivisionItemDecoration(private val colorString: String, private val margin: Int) : RecyclerView.ItemDecoration() {
//    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDraw(c, parent, state)
//
//        val paint = Paint().apply {
//            color = Color.parseColor(colorString)
//        }
//        val height = 20
//
//        for (i in 0 until parent.childCount) {
//            val child = parent.getChildAt(i)
//            if (i != parent.childCount - 1) {
//                c.drawRect(child.left.toFloat() + margin, child.bottom.toFloat() + margin, child.right.toFloat() - margin, child.bottom.toFloat() + height + margin, paint)
//            }
//        }
//    }
//}
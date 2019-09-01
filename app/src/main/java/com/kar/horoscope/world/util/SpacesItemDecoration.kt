package com.kar.horoscope.world.util

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View


class SpacesItemDecoration(space: Int) : RecyclerView.ItemDecoration() {

    private val halfSpace: Int = space / 2

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        if (parent.paddingLeft != halfSpace) {
            parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace)
            parent.clipToPadding = false
        }

        outRect.left = halfSpace
        outRect.right = halfSpace
    }
}
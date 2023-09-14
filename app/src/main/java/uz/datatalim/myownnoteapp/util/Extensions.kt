package uz.datatalim.myownnoteapp.util

import android.view.View

object Extensions {

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }
}
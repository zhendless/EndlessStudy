package cn.zhendless.endlessstudy.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.AdapterView


class StudyActivity : Activity() {

    private val mOnItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> }

    private inner class OnStudyItemClickListener : AdapterView.OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val intent = Intent()
            intent.setClass(this@StudyActivity, StudyActivity::class.java)
            startActivity(intent)
        }
    }

}

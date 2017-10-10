package cn.zhendless.endlessstudy.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import cn.zhendless.endlessstudy.R
import cn.zhendless.endlessstudy.adapter.StudyContentAdapter
import cn.zhendless.endlessstudy.bean.StudyContentBean
import kotlinx.android.synthetic.main.activity_recycler_view_study.*

class RecyclerViewStudyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_study)
        initData()
    }

    private fun initData() {
        val sourceList: MutableList<StudyContentBean> = ArrayList()
        sourceList.add(StudyContentBean("Like ListView", RecyclerViewLikeListViewActivity::class.java))
        sourceList.add(StudyContentBean("Like GridView", RecyclerViewLikeGridViewActivity::class.java))
        sourceList.add(StudyContentBean("CustomView", RecyclerViewCustomViewActivity::class.java))

        val adapter = StudyContentAdapter()
        adapter.setContext(this)
        adapter.setDataList(sourceList)

        list_view_recycler_view_content.adapter = adapter
        list_view_recycler_view_content.onItemClickListener = mOnListItemClickListener
    }

    private val mOnListItemClickListener = OnListItemClickListener()

    private inner class OnListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (null != parent) {
                val adapter = parent.adapter as StudyContentAdapter
                val contentClass = adapter.getItem(position).mContentClass
                val intent = Intent()
                intent.setClass(this@RecyclerViewStudyActivity, contentClass)
                startActivity(intent)
            }
        }

    }
}
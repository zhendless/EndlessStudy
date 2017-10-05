package cn.zhendless.endlessstudy.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cn.zhendless.endlessstudy.R
import cn.zhendless.endlessstudy.activity.OtherMainActivity
import cn.zhendless.endlessstudy.bean.StudyContentBean

class StudyContentAdapter : BaseAdapter() {

    private var mContext: Context? = null
    private var mStudyContentBeanList: MutableList<StudyContentBean> = ArrayList()

    fun setContext(context: Context) {
        mContext = context
    }

    fun setDataList(studyContentBeanList: MutableList<StudyContentBean>) {
        mStudyContentBeanList = studyContentBeanList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val holder: ViewHolder
        var convertView = convertView
        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_item_study_content, null)
            holder.mTextViewContent = convertView.findViewById<TextView>(R.id.textView_content_name)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val studyContentBean = mStudyContentBeanList[position]
        holder.mTextViewContent!!.text = studyContentBean.mContentName
        return convertView
    }

    private class ViewHolder {
        internal var mTextViewContent: TextView? = null
    }

    override fun getItem(position: Int): StudyContentBean {
        if (mStudyContentBeanList.size >= position) {
            Log.e(TAG, "StudyContentAdapter -> getItem, position out of index!")
            return StudyContentBean("unknown", OtherMainActivity::class.java)
        }
        return mStudyContentBeanList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mStudyContentBeanList.size
    }

    companion object {
        private val TAG = StudyContentAdapter::class.java.simpleName
    }

}
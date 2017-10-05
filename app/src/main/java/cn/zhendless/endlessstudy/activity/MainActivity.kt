package cn.zhendless.endlessstudy.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import cn.zhendless.endlessstudy.R
import cn.zhendless.endlessstudy.adapter.StudyContentAdapter
import cn.zhendless.endlessstudy.bean.StudyContentBean
import cn.zhendless.endlessstudy.utils.QrCodeUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initView()
        initDataList()
    }

    private fun initView() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                return true
            }
            R.id.action_new_activity -> {
                startOtherMainActivity()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun startOtherMainActivity() {
        val intent = Intent()
        intent.setClass(this@MainActivity, OtherMainActivity::class.java)
        startActivity(intent)
    }

    /**
     * 生成300张纯色图片，给媳妇测试用
     * */
    fun generateOneColorPicture() {
        for (i in 0..300) {
            val bitmap = QrCodeUtil.generateOneColorPicture(6110, 8110)
            val dirStr: String = QrCodeUtil.getDefaultDir()
            QrCodeUtil.saveBitmapAsJpg(dirStr, "Pic_$i.jpg", bitmap)
            bitmap.recycle()
        }
    }

    private fun initDataList() {
        val studyContentList: MutableList<StudyContentBean> = ArrayList()
        studyContentList.add(getNewStudyContentBean("Recycler View Study", RecyclerViewStudyActivity::class.java))
        studyContentList.add(getNewStudyContentBean("Recycler View Study", RecyclerViewStudyActivity::class.java))
        studyContentList.add(getNewStudyContentBean("Recycler View Study", RecyclerViewStudyActivity::class.java))
        studyContentList.add(getNewStudyContentBean("Recycler View Study", RecyclerViewStudyActivity::class.java))
        val adapter = StudyContentAdapter()

        findViewById<ListView>(R.id.listView_study_content)

        adapter.setContext(this)
        adapter.setDataList(studyContentList)
        adapter.notifyDataSetChanged()
        listView_study_content.adapter = adapter
    }

    private fun getNewStudyContentBean(contentName: String, contentClass: Class<*>): StudyContentBean {
        return StudyContentBean(contentName, contentClass)
    }
}

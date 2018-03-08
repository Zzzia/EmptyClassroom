package com.zia.emptyclassroom.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.zia.emptyclassroom.Config
import com.zia.emptyclassroom.R
import com.zia.emptyclassroom.adapter.PagerAdapter
import com.zia.emptyclassroom.bean.Empty
import com.zia.emptyclassroom.bean.NowTime
import com.zia.emptyclassroom.model.InternetModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    private var week = 0
    private var day = 0
    private var start = 1
    private var end = 2
    private val model = InternetModel()
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBuildingTab()
        toolbar.inflateMenu(R.menu.menu_toolbar)
        toolbar.setOnMenuItemClickListener(this)
        initTime()
    }

    private fun freshEmptyClass() {
        model.getEmpty(week + 1, day + 1, start, end).subscribe(object : Observer<Empty> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Empty) {
                runOnUiThread {
                    pagerAdapter.freshData(t.empty)
                    pagerAdapter.notifyDataSetChanged()
                }
            }

            override fun onError(e: Throwable) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "向服务器获取空教室出现错误..", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }

        })
    }

    private fun initTime() {
        model.getTime().subscribe(object : Observer<NowTime> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: NowTime) {
                Config.nowTime = t
                week = t.week.toInt() - 1
                day = t.day.toInt() - 1
                Log.e("test", "week:$week,day:$day")
                setTab()
                freshEmptyClass()
            }

            override fun onError(e: Throwable) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "向服务器获取时间出现错误..", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }

        })
    }

    private fun setTab() {
        setWeekTab(week)
        setDayTab(day)
        setTimeTab(0)
    }


    private fun setTab(tabLayout: TabLayout, array: ArrayList<String>, position: Int) {
        array.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
        tabLayout.getTabAt(position)?.select()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                week = weekTB.selectedTabPosition + 1
                day = dayTB.selectedTabPosition + 1
                start = timeTB.selectedTabPosition * 2 + 1
                end = start + 1
                Log.e("test", "week:$week,day:$day,start:$start,end:$end")
                freshEmptyClass()
            }

        })
    }

    private fun setBuildingTab() {
        pagerAdapter = PagerAdapter(supportFragmentManager)
        viewpager.adapter = pagerAdapter
        viewpager.offscreenPageLimit = 5
        buildingTB.setupWithViewPager(viewpager)
    }

    private fun setWeekTab(position: Int) {
        val trans = arrayListOf("第一周", "第二周", "第三周", "第四周", "第五周"
                , "第六周", "第七周", "第八周", "第九周", "第十周"
                , "第十一周", "第十二周", "第十三周", "第十四周", "第十五周"
                , "第十六周", "第十七周", "第十八周", "第十九周", "第二十周")
        setTab(weekTB, trans, position)
    }

    private fun setDayTab(position: Int) {
        val trans = arrayListOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
        setTab(dayTB, trans, position)
    }

    private fun setTimeTab(position: Int) {
        val trans = arrayListOf("1-2节", "3-4节", "午间", "5-6节", "7-8节", "晚间", "9-10节", "11-12节")
        setTab(timeTB, trans, position)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.update -> {
                if (Config.nowTime.version != "" && Config.version != Config.nowTime.version) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Config.github))
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity, "已经是最新版本", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.about -> {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
        }
        return true
    }
}

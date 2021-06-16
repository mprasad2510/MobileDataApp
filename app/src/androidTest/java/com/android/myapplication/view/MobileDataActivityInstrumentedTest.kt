package com.android.myapplication.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import com.android.myapplication.R
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MobileDataActivityInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MobileDataActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun ensureListIsPresent() {
        val activity = MobileDataActivity()
        val viewById: RecyclerView = activity.findViewById(R.id.recyclerView)
        Assert.assertThat(viewById, CoreMatchers.notNullValue())
        Assert.assertThat(viewById, CoreMatchers.instanceOf(RecyclerView::class.java))
        val listView: RecyclerView = viewById as RecyclerView
        val adapter = listView.adapter
        Assert.assertThat(adapter, CoreMatchers.instanceOf(MobileDataAdapter::class.java))
        Assert.assertSame(59, 59)
    }
}
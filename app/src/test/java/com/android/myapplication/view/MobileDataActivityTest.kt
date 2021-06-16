package com.android.myapplication.view


import com.android.myapplication.R
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify


class MobileDataActivityTest {
    @Test
    fun `should inflate layout`() {
        val tested = spy(MobileDataActivity())
        tested.onCreate(null)
        verify(tested).setContentView(R.layout.activity_records)
    }
}
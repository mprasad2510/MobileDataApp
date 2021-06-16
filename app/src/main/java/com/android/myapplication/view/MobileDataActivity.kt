package com.android.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.R
import com.android.myapplication.di.AppModule
import com.android.myapplication.model.RecordsItem
import com.android.myapplication.viewmodel.MobileDataViewModel
import kotlinx.android.synthetic.main.activity_records.*
import kotlinx.android.synthetic.main.layout_error.*


class MobileDataActivity : AppCompatActivity() {

    private lateinit var viewModel: MobileDataViewModel
    private lateinit var adapter: MobileDataAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        setupViewModel()
        setupUI()
    }

    //ui
    private fun setupUI() {
        adapter = MobileDataAdapter(viewModel.mobileData.value ?: emptyList(), viewModel.getDataAnnually(),viewModel.findMinValueOfData())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    //view model
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            AppModule.provideViewModelFactory()
        ).get(MobileDataViewModel::class.java)

        viewModel.mobileData.observe(this, renderData)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    //observers
    private val renderData = Observer<List<RecordsItem>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRecords()
    }

    companion object {
        const val TAG = "MobileDataActivity"
    }

}
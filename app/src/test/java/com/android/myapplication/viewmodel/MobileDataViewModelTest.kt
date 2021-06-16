package com.android.myapplication.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.myapplication.capture
import com.android.myapplication.data.MobileDataRemoteDataSource
import com.android.myapplication.data.OperationCallback
import com.android.myapplication.model.MobileDataRepository
import com.android.myapplication.model.RecordsItem
import com.android.myapplication.model.Response
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.*
import org.mockito.Mockito.*


class MobileDataViewModelTest {

    @Mock
    private lateinit var mobileDataRemoteDataSource: MobileDataRemoteDataSource

    @Mock
    private lateinit var context: Application

    @Captor
    private lateinit var operationCallbackCaptor: ArgumentCaptor<OperationCallback<Response>>

    private lateinit var viewModel: MobileDataViewModel
    private lateinit var repository: MobileDataRepository

    private lateinit var isViewLoadingObserver: Observer<Boolean>
    private lateinit var onMessageErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>
    private lateinit var onRenderMobileDataObserver: Observer<List<RecordsItem>>

    private lateinit var mobileDataEmptyList: List<RecordsItem>
    private lateinit var mobileDataList: List<RecordsItem>


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(context.applicationContext).thenReturn(context)

        repository = MobileDataRepository(mobileDataRemoteDataSource)
        viewModel = MobileDataViewModel(repository)

        mockData()
        setupObservers()
    }

    @Test
    fun `retrieve records with ViewModel and Repository returns empty data`() {
        with(viewModel) {
            loadRecords()
            isViewLoading.observeForever(isViewLoadingObserver)
            isEmptyList.observeForever(emptyListObserver)
            mobileData.observeForever(onRenderMobileDataObserver)
        }

        verify(mobileDataRemoteDataSource, times(1)).retrieveRecords(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(mobileDataEmptyList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.isEmptyList.value == true)
        Assert.assertTrue(viewModel.mobileData.value?.size == 0)
    }

    @Test
    fun `retrieve records with ViewModel and Repository returns full data`() {
        with(viewModel) {
            loadRecords()
            isViewLoading.observeForever(isViewLoadingObserver)
            mobileData.observeForever(onRenderMobileDataObserver)
        }

        verify(mobileDataRemoteDataSource, times(1)).retrieveRecords(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(mobileDataList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.mobileData.value?.size == 3)
    }

    @Test
    fun `retrieve records with ViewModel and Repository returns an error`() {
        with(viewModel) {
            loadRecords()
            isViewLoading.observeForever(isViewLoadingObserver)
            onMessageError.observeForever(onMessageErrorObserver)
        }
        verify(mobileDataRemoteDataSource, times(1)).retrieveRecords(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onError("An error occurred")
        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertNotNull(viewModel.onMessageError.value)
    }

    private fun setupObservers() {
        isViewLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver = mock(Observer::class.java) as Observer<Any>
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
        onRenderMobileDataObserver = mock(Observer::class.java) as Observer<List<RecordsItem>>
    }

    private fun mockData() {
        mobileDataEmptyList = emptyList()
        val mockList: MutableList<RecordsItem> = mutableListOf()
        mockList.add(
            RecordsItem(
                "0.00012",
                2,
                "2004-Q4"
            )
        )
        mockList.add(RecordsItem("1.234", 3, "2008-Q4"))
        mockList.add(RecordsItem("0.123", 5, "2018-Q4"))

        mobileDataList = mockList.toList()
    }
}
package com.android.myapplication.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.R
import com.android.myapplication.model.MobileData
import com.android.myapplication.model.RecordsItem
import kotlinx.android.synthetic.main.row_records.view.*


class MobileDataAdapter(private var mobileData: List<RecordsItem>) :
    RecyclerView.Adapter<MobileDataAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_records, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        vh.bind(mobileData[position])
    }

    override fun getItemCount(): Int {
        return mobileData.size
    }

    fun update(data: List<RecordsItem>) {
        mobileData = data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.textViewName
        private val imageView: ImageView = view.imageView
        fun bind(data: RecordsItem) {
            textViewName.text = data.volumeOfMobileData.toString()
            Log.d(MobileDataActivity.TAG, "data $data")
            Log.d(MobileDataActivity.TAG, "data 2 ${data.volumeOfMobileData.toString()}")
            imageView.isClickable = true
        }
    }
}
package com.android.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.R
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
        if (findMinValueOfData(position)) {
            vh.imageView.isClickable = true
        } else {
            vh.imageView.visibility = View.GONE
        }
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
        val imageView: ImageView = view.imageView
        fun bind(data: RecordsItem) {
            textViewName.text = data.volume_of_mobile_data
        }
    }

    private fun findMinValueOfData(position : Int) : Boolean {
        val listData = arrayOf(mobileData[position].volume_of_mobile_data)
        val minimum = listData.toList()
        println("Minimum: ${minimum.minOrNull()}")
        return true

    }
}
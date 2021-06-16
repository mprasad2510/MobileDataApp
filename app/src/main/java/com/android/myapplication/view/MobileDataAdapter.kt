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


class MobileDataAdapter(private var mobileData: List<RecordsItem>,
private var annualData: Map<String,String>, private var minVal: Boolean) :
    RecyclerView.Adapter<MobileDataAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_records, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        vh.bind(mobileData[position])
        val data = annualData[mobileData[position].quarter]
        vh.txtQuarter.text = data
        if (minVal) {
            vh.imageView.isClickable = true
            vh.imageView.visibility = View.VISIBLE
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
        val textViewName: TextView = view.textViewName
        val imageView: ImageView = view.imageView
        val txtQuarter = view.textViewLink
        fun bind(data: RecordsItem) {
            textViewName.text = data.volume_of_mobile_data
        }
    }
}
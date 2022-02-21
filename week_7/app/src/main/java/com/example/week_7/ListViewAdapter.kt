package com.example.week_7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.week_7.databinding.ItemLayoutBinding

class ListViewAdapter(var mArrayList: ArrayList<Data>, mContext : Context) : BaseAdapter() {
    private var layoutInflate : LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var item : ItemLayoutBinding

    override fun getCount(): Int = mArrayList.size
    override fun getItem(p0: Int): Any = mArrayList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(index: Int, p1: View?, parent: ViewGroup?): View {
        item = ItemLayoutBinding.inflate(layoutInflate, parent, false)

        item.itemLayoutTitle.text = mArrayList[index].title
        item.itemLayoutContent.text = mArrayList[index].contents

        return item.root
    }
}
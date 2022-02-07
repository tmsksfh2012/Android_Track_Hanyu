package com.example.week_4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.week_4.databinding.ListviewItemBinding

class CustomAdapter(context : Context, private val cardArrayList: ArrayList<Card>) : BaseAdapter() {
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding:ListviewItemBinding

    override fun getCount(): Int = cardArrayList.size

    override fun getItem(index: Int): Any = cardArrayList[index]

    override fun getItemId(id: Int): Long = id.toLong()

    override fun getView(index: Int, p1: View?, parent: ViewGroup?): View {
        binding = ListviewItemBinding.inflate(inflater, parent, false)
        binding.nameItem.text = cardArrayList[index].name
        binding.contentItem.text = cardArrayList[index].content

        return binding.root
    }
}
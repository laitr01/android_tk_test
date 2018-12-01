package com.bestapplication.tikiandroidtest.ui.hotkeyword

import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bestapplication.tikiandroidtest.R
import com.bestapplication.tikiandroidtest.extendsion.random
import com.bestapplication.tikiandroidtest.model.KeywordTwoLine
import kotlinx.android.synthetic.main.hot_keyword_layout.view.*

class HotKeywordAdapter(
    private val listener: (KeywordTwoLine) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val hotKeywords: ArrayList<KeywordTwoLine> = ArrayList()

    enum class Color(val value: String) {
        ORANGE("#b36b00"),
        RED("#992600"),
        DARK("#333333"),
        PURPLE("#b300b3"),
        BLUE("#000099"),
        PURPLE300("#660066"),
        GREEN("#003300"),
        GREEN1000("#006600");
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HotKeywordViewHolder.create(parent, listener)
    }

    fun setList(keywords: List<KeywordTwoLine>) {
        hotKeywords.clear()
        hotKeywords.addAll(keywords)
        notifyDataSetChanged()
    }

    override fun getItemCount() = hotKeywords.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as HotKeywordViewHolder
        holder.bind(hotKeywords[position], android.graphics.Color.parseColor(randomColor()))
    }

    private fun randomColor(): String {
        val index = (0 until Color.values().size).random()
        return Color.values()[index].value
    }

    class HotKeywordViewHolder(
        val view: View,
        private val itemClickListener: (KeywordTwoLine) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(keyword: KeywordTwoLine, color: Int) {

            val keywordText = if (keyword.lineTwo.isEmpty()) {
                keyword.lineOne
            } else {
                "${keyword.lineOne}\n${keyword.lineTwo}"
            }

            view.hotKeyword.text = keywordText
            val bgShape = view.hotKeyword.background as? GradientDrawable
            bgShape?.setColor(color)

            view.setOnClickListener {
                itemClickListener.invoke(keyword)
            }
        }


        companion object {
            fun create(parent: ViewGroup, itemClickListener: (KeywordTwoLine) -> Unit): HotKeywordViewHolder {
                return HotKeywordViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.hot_keyword_layout, parent, false), itemClickListener
                )
            }
        }
    }
}
package com.tanuj.stackman.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanuj.stackman.datasource.entity.Response

class QueryRVAdapter(var queries: List<Response>) :
    RecyclerView.Adapter<QueryRVAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_query, parent, false)
            .run(::LaunchViewHolder)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bindData(queries[position])
    }

    override fun getItemCount(): Int {
        return queries.count()
    }

    inner class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val queryNameTextView = itemView.findViewById<TextView>(R.id.queryTitle)
        private val upvoteScoreTextView = itemView.findViewById<TextView>(R.id.upvoteScore)

        fun bindData(response: Response) {
            val context = itemView.context
            queryNameTextView.text = context.getString(R.string.title_field, response.items.title)
            upvoteScoreTextView.text = context.getString(R.string.score_field, response.items.score)
        }
    }

}
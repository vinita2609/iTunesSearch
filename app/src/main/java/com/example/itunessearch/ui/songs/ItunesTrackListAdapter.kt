package com.example.itunessearch.ui.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.itunessearch.R
import com.example.itunessearch.data.ItunesSongsDataModel
import com.example.itunessearch.databinding.SongItemBinding

class ItunesTrackListAdapter: RecyclerView.Adapter<ItunesTrackListAdapter.ViewHolder>() {
    private lateinit var postList:List<ItunesSongsDataModel.Results>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesTrackListAdapter.ViewHolder {
        val binding: SongItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.song_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItunesTrackListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<ItunesSongsDataModel.Results>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: SongItemBinding): RecyclerView.ViewHolder(binding.root){

        private val viewModel = ItunesLayoutModel()
        fun bind(result:ItunesSongsDataModel.Results){
            viewModel.bind(result)
            binding.viewModel = viewModel
        }
    }

}
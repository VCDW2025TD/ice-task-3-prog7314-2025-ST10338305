package com.example.memeservice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import androidx.lifecycle.observe
import com.example.memeservice.R
import com.example.memeservice.MemeViewModel
import com.example.memeservice.TenorGif
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MemeFragment : Fragment() {

    // ✅ Correct lowercase 'viewModels()'
    private val viewModel: MemeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_meme, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerMemes)

        val adapter = MemeAdapter()
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        // Observe the ViewModel meme list
        lifecycleScope.launch {
            viewModel.memes.collectLatest { gifs ->
                adapter.submitList(gifs)
            }
        }


        return view
    }

    class MemeAdapter : RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {
        private var list: List<TenorGif> = emptyList()

        fun submitList(newList: List<TenorGif>) {
            list = newList
            notifyDataSetChanged()
        }

        class MemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val gifView: ImageView = view.findViewById(R.id.gifImage)
            val title: TextView = view.findViewById(R.id.gifTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_meme, parent, false)
            return MemeViewHolder(view)
        }

        override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
            val meme = list[position]
            holder.title.text = meme.title
            holder.gifView.load(meme.mediaFormats.gif.url)
        }

        override fun getItemCount(): Int = list.size
    }
}

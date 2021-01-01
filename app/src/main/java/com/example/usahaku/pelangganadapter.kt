package com.example.usahaku


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.pelanggan
import kotlinx.android.synthetic.main.pelanggan_item.view.*


class pelangganadapter : ListAdapter<pelanggan, pelangganadapter.pelangganHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<pelanggan>() {
            override fun areItemsTheSame(oldItem: pelanggan, newItem: pelanggan): Boolean {
                return oldItem.id_pelanggan == newItem.id_pelanggan
            }
            override fun areContentsTheSame(oldItem: pelanggan,
                                            newItem: pelanggan): Boolean {
                return oldItem.namapelanggan == newItem.namapelanggan
                        && oldItem.alamatpelanggan == newItem.alamatpelanggan
                        && oldItem.notelppelanggan == newItem.notelppelanggan
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            pelangganHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.pelanggan_item, parent, false)
        return pelangganHolder(itemView)
    }
    override fun onBindViewHolder(holder: pelangganHolder, position: Int) {
        val currentpelanggan: pelanggan = getItem(position)
        holder.textViewnama.text = currentpelanggan.namapelanggan
        holder.textViewnotelp.text = currentpelanggan.notelppelanggan
    }
    fun getpelangganAt(position: Int): pelanggan {
        return getItem(position)
    }
    inner class pelangganHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewnama: TextView = itemView.nama_pelanggan
        var textViewnotelp: TextView = itemView.notelpel
    }
    interface OnItemClickListener {
        fun onItemClick(pelanggan: pelanggan)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
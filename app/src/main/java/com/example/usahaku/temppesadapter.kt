package com.example.usahaku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.temppesproduk


class temppesprodukAdapter internal constructor(context: Context) : RecyclerView.Adapter<temppesprodukAdapter.temppesprodukViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tempspes = emptyList<temppesproduk>()
    var clickListener : OnClickListener? = null
    inner class temppesprodukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val temppesproduknama: TextView = itemView.findViewById(R.id.namapesproduk)
        val temppesprodukjumlahdibeli: TextView = itemView.findViewById(R.id.jumlahpesdibeli)
        val temppesprodukhargasatuan: TextView = itemView.findViewById(R.id.hargapessatuan)
        val temppesproduksatuan: TextView = itemView.findViewById(R.id.satuanpesdibeli)
        val temppesprodukhargatotal: TextView = itemView.findViewById(R.id.hargatotpesproduk)
        val buttondelete: ImageView = itemView.findViewById(R.id.btndelpesprod)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): temppesprodukViewHolder {
        val itemView = inflater.inflate(R.layout.item_pesproduk,parent, false)
        return temppesprodukViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: temppesprodukViewHolder, position: Int){
        val current = tempspes[position]
        holder.temppesproduknama.text = current.namaprodukpesbeli
        holder.temppesprodukjumlahdibeli.text = current.jumlahpesbeli.toString()
        holder.temppesproduksatuan.text = current.satuanpesprodukbeli
        holder.temppesprodukhargasatuan.text = current.hargasatuanpesbeli.toString()
        holder.temppesprodukhargatotal.text = current.hargapestot.toString()
        holder.buttondelete.setOnClickListener({itemView -> clickListener?.onClick(position)})

    }

    internal fun settemps(temps: List<temppesproduk>) {
        this.tempspes = temps
        notifyDataSetChanged()
    }

    override fun getItemCount() = tempspes.size

    fun setOnClickListener(listener: (Int) -> Unit){
        this.clickListener = object: OnClickListener {
            override fun onClick(position: Int) {
                listener(position)
            }
        }
    }


    interface OnClickListener{
        fun onClick(position: Int)
    }}
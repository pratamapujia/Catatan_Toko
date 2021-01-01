package com.example.usahaku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.tempproduk
import org.w3c.dom.Text

class tempprodukAdapter internal constructor(context: Context) : RecyclerView.Adapter<tempprodukAdapter.tempprodukViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var temps = emptyList<tempproduk>()
    var clickListener : OnClickListener? = null
    inner class tempprodukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tempproduknama: TextView = itemView.findViewById(R.id.namapro)
        val tempprodukjumlahdibeli: TextView = itemView.findViewById(R.id.jumlahdibeli)
        val tempprodukhargasatuan: TextView = itemView.findViewById(R.id.hargasatuan)
        val tempproduksatuan: TextView = itemView.findViewById(R.id.satuandibeli)
        val tempprodukhargatotal: TextView = itemView.findViewById(R.id.hargatotproduk)
        val buttondelete: ImageView = itemView.findViewById(R.id.btndelete)
        val hidden : TextView = itemView.findViewById(R.id.hidden)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tempprodukViewHolder {
        val itemView = inflater.inflate(R.layout.item_pilproduk,parent, false)
        return tempprodukViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: tempprodukViewHolder, position: Int){
        val current = temps[position]
        holder.tempproduknama.text = current.namaprodukbeli
        holder.tempprodukjumlahdibeli.text = current.jumlahbeli.toString()
        holder.tempproduksatuan.text = current.satuanprodukbeli
        holder.tempprodukhargasatuan.text = current.hargasatuanbeli.toString()
        holder.tempprodukhargatotal.text = current.hargatot.toString()
        holder.hidden.text = temps.size.toString()
        holder.buttondelete.setOnClickListener({itemView -> clickListener?.onClick(position)})

    }

    internal fun settemps(temps: List<tempproduk>) {
        this.temps = temps
        notifyDataSetChanged()
    }

    override fun getItemCount() = temps.size

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
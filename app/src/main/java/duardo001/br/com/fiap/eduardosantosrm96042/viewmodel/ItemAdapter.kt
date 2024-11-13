package duardo001.br.com.fiap.eduardosantosrm96042.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import duardo001.br.com.fiap.eduardosantosrm96042.R
import duardo001.br.com.fiap.eduardosantosrm96042.model.ItemModel

class ItemAdapter(
    private var items: List<ItemModel> = emptyList(),
    private val onItemRemoved: (ItemModel) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloDica: TextView = itemView.findViewById(R.id.tituloDica)
        val descricaoDica: TextView = itemView.findViewById(R.id.descricaoDica)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dica = items[position]
        holder.tituloDica.text = dica.titulo
        holder.descricaoDica.text = dica.descricao


        holder.itemView.setOnLongClickListener {
            onItemRemoved(dica)
            true
        }
    }


    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<ItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}
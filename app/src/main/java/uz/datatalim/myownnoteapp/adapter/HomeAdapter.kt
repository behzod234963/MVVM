package uz.datatalim.myownnoteapp.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import uz.datatalim.myownnoteapp.R
import uz.datatalim.myownnoteapp.model.Note

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var onClick: ((Int) -> Unit)? = null
    val notes: ArrayList<Note> = ArrayList()

    fun submitList(list: ArrayList<Note>) {
        this.notes.clear()
        this.notes.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val note = notes[position]

        holder.apply {
            tvText.text = note.title
            Log.d("@@@@@", "onBindViewHolder: $note")
            item.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor(note.color!!)))
            item.setOnLongClickListener {
                onClick?.invoke(position)
                return@setOnLongClickListener true
            }


        }

    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvText: TextView = view.findViewById(R.id.tv_title)
        val item: CardView = view.findViewById(R.id.item)

    }

}
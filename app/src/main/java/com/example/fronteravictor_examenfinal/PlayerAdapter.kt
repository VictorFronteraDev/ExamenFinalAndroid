package com.example.fronteravictor_examenfinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.fronteravictor_examenfinal.model.Player

class PlayerAdapter(private val listenerBtn: (Button, Int) -> Unit) : RecyclerView.Adapter<PlayerAdapter.MyViewHolder>() {

    private var list: ArrayList<Player> = ArrayList()
    private var listener: View.OnClickListener? = null

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val id: TextView
        val name: TextView
        val surname: TextView
        val years: TextView
        val btnDelete: Button

        init {
            id = view.findViewById(R.id.txtidElement)
            name = view.findViewById(R.id.txtNameElement)
            surname = view.findViewById(R.id.txtSurnameElement)
            years = view.findViewById(R.id.txtYearsElement)
            btnDelete = view.findViewById(R.id.btnDelete)
        }
        fun setVisibility(visibility: Boolean) {
            id.isVisible = visibility
            surname.isVisible = visibility
            years.isVisible = visibility

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.element_list, viewGroup, false)

        view.setOnClickListener(listener)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.id.text = list[position].id.toString()
        viewHolder.name.text = list[position].name
        viewHolder.surname.text = list[position].surname
        viewHolder.years.text = list[position].years.toString()


        viewHolder.btnDelete.setOnClickListener {
            listenerBtn(it as Button, position)
        }

    }

    override fun getItemCount() = list.size

    fun getItem(pos: Int) = list[pos]

    fun addToList(list_: ArrayList<Player>) {
        list.clear()
        list.addAll(list_)

        notifyDataSetChanged()
    }

    fun addToList(player: Player) {
        list.add(player)

        notifyDataSetChanged()
    }

    fun updateList(pos: Int, player: Player) {
        list[pos] = player

        notifyDataSetChanged() // Update recyclerView
    }

    fun deleteFromList(pos: Int){
        list.removeAt(pos)

        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: View.OnClickListener) {
        listener = onClickListener
    }

}
package com.rikkei.tra_02t0115kotlin.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rikkei.tra_02t0115kotlin.R
import com.rikkei.tra_02t0115kotlin.model.People

class PeopleAdapter(var peoples: MutableList<People>?,var peopleOnclickListener: PeopleOnclickListener) : RecyclerView.Adapter<PeopleAdapter.PeopleHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder = PeopleHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)
    )

    override fun getItemCount(): Int{
        return if(peoples != null){
            peoples?.size!!
        }else {
            0
        }
    }

    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {
        var people = peoples?.get(position)
        holder.id.text = people?.id
        holder.age.text = people?.age.toString()
        holder.name.text = people?.name
        holder.gender.text = people?.gender
        holder.place.text = people?.place
        holder.cvPeople.setOnClickListener { peopleOnclickListener.onClickItem(position) }

    }


    class PeopleHolder(v: View) : RecyclerView.ViewHolder(v) {

        val id: TextView = v.findViewById(R.id.tvId)
        val name: TextView = v.findViewById(R.id.tvName)
        val gender: TextView = v.findViewById(R.id.tvGender)
        val age: TextView = v.findViewById(R.id.tvAge)
        val place: TextView = v.findViewById(R.id.tvPlace)
        val cvPeople: LinearLayout = v.findViewById(R.id.llPeople)


    }
    interface PeopleOnclickListener {
        fun onClickItem(people: Int)
    }
}
package com.molarmak.foodtracker.main.eatFood

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.helper.ViewType
import com.molarmak.foodtracker.main.overview.EatenFoodResponse
import com.molarmak.foodtracker.main.overview.FoodResponse
import com.molarmak.foodtracker.more.AddFoodActivity
import kotlinx.android.synthetic.main.fragment_eat.*
import kotlinx.android.synthetic.main.fragment_eat.recycler

interface EatFoodView: ViewType {
    fun endLoadAllFood(foods: ArrayList<FoodResponse>)
}

class EatFoodFragment: Fragment(), EatFoodView {

    private val presenter: EatFoodPresenterInterface = EatFoodPresenterImpl(this)
    private lateinit var adapter: RecycleViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_eat, null)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.startLoadAllFood(s.toString())
            }

        })
        presenter.startLoadAllFood("")
        adapter = RecycleViewAdapter(context)
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.adapter = adapter
        eatButton.setOnClickListener {

        }
        addButton.setOnClickListener {
            activity?.finish()
            startActivity(Intent(context, AddFoodActivity::class.java))
        }
    }

    override fun endLoadAllFood(foods: ArrayList<FoodResponse>) {
        activity?.runOnUiThread {
            adapter.setFoodHistory(foods)
        }
    }

    override fun onError(errors: ArrayList<String>) {
        activity?.runOnUiThread {
            Toast.makeText(context, errors.joinToString("\n"), Toast.LENGTH_SHORT).show()
        }
    }
}

class RecycleViewAdapter(private val context: Context?): RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    private val repoList = ArrayList<FoodResponse>()

    fun setFoodHistory(list: ArrayList<FoodResponse>) {
        repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_food, viewGroup, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        try {
            val item = repoList[i]

            viewHolder.proteinText.text = "${context?.getString(R.string.protein_hint)} ${item.protein}"
            viewHolder.fatsText.text = "${context?.getString(R.string.fats_hint)} ${item.fats}"
            viewHolder.carbohydratesText.text = "${context?.getString(R.string.carbohydrates_hint)} ${item.carbohydrates}"
            viewHolder.foodName.text = item.name
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val proteinText: TextView = itemView.findViewById(R.id.proteinText)
        val fatsText: TextView = itemView.findViewById(R.id.fatsText)
        val carbohydratesText: TextView = itemView.findViewById(R.id.carbohydratesText)
        val foodName: TextView = itemView.findViewById(R.id.foodName)
    }
}
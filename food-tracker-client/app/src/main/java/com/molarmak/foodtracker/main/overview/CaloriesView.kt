package com.molarmak.foodtracker.main.overview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.molarmak.foodtracker.R
import com.molarmak.foodtracker.helper.ViewType
import kotlinx.android.synthetic.main.fragment_calories.*

interface CaloriesView: ViewType {
    fun endLoadCalories(response: CaloriesResponse)
}

class CaloriesFragment: Fragment(), CaloriesView {

    private lateinit var adapter: RecycleViewAdapter
    private val presenter: CaloriesPresenterInterface = CaloriesPresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_calories, null)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = RecycleViewAdapter(context)
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.adapter = adapter
        presenter.startLoadCalories()
    }

    override fun endLoadCalories(response: CaloriesResponse) {
        activity?.runOnUiThread {
            caloriesText?.text = "${getString(R.string.calories_hint)} ${response.calories}"
            statusText?.text = "${getString(R.string.status_hint)} ${if(response.ok) "ok" else "not ok"}"
            adapter.setFoodHistory(response.eatenFood)
        }
    }

    override fun onError(errors: ArrayList<String>) {
        activity?.runOnUiThread {
            Toast.makeText(context, errors.joinToString("\n"), Toast.LENGTH_SHORT).show()
        }
    }

}

class RecycleViewAdapter(private val context: Context?): RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    private val repoList = ArrayList<EatenFoodResponse>()

    fun setFoodHistory(list: ArrayList<EatenFoodResponse>) {
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

            viewHolder.proteinText.text = "${context?.getString(R.string.protein_hint)} ${item.food.protein}"
            viewHolder.fatsText.text = "${context?.getString(R.string.fats_hint)} ${item.food.fats}"
            viewHolder.carbohydratesText.text = "${context?.getString(R.string.carbohydrates_hint)} ${item.food.carbohydrates}"
            viewHolder.foodName.text = item.food.name
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
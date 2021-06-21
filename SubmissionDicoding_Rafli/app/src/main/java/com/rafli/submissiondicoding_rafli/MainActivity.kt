package com.rafli.submissiondicoding_rafli

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvFoods: RecyclerView
    private var list:ArrayList<Food> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title="YUK MAKAN"
        setContentView(R.layout.activity_main)

        rvFoods = findViewById(R.id.rv_foods)
        rvFoods.setHasFixedSize(true)
        list.addAll(FoodsData.listData)
        showRecyclerList()

    }

    private fun showSelectedFood(food: Food){
        val MoveToSelectedFood = Intent(this@MainActivity, ClickedFood::class.java)
        MoveToSelectedFood.putExtra(ClickedFood.FOODNAME, food.name)
        MoveToSelectedFood.putExtra(ClickedFood.FOODDETAIL,food.detail)
        MoveToSelectedFood.putExtra(ClickedFood.FOODPHOTO,food.photo)
        MoveToSelectedFood.putExtra(ClickedFood.FOODPRICE,food.price)
        startActivity(MoveToSelectedFood)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionabout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        showAbout()
        return super.onOptionsItemSelected(item)
    }

    private fun showAbout() {
        startActivity(Intent(this@MainActivity, about_activity::class.java))
    }

    private fun showRecyclerList(){
        rvFoods.layoutManager= LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list)
        rvFoods.adapter = listFoodAdapter

        listFoodAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Food) {
                showSelectedFood(data)
            }
        })

    }
}
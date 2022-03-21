package com.rafli.myrecyclerview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafli.myrecyclerview2.databinding.ActivityMainBinding
import com.rafli.myrecyclerview2.databinding.ItemRowHeroBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Hero>()

    private var mode: Int = 0

    companion object{
        private  const val STATE_TITLE = "state_string"
        private  const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvHeroes.setHasFixedSize(true)

        if(savedInstanceState==null){
            list.addAll(getListHeroes())
            showRecyclerList()
            mode=R.id.action_list
        }
        else{
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)
            val stateMode = savedInstanceState.getInt(STATE_MODE)

            if(stateList != null){
                list.addAll(getListHeroes())
            }
            setMode(stateMode)

        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(STATE_LIST,list)
        outState.putInt(STATE_MODE,mode)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int){
        when (selectedMode){
            R.id.action_list->{
                Toast.makeText(this,"${selectedMode.toString()}",Toast.LENGTH_SHORT).show()
                showRecyclerList()
            }
            R.id.action_grid -> showRecyclerGrid()
            R.id.action_cardview -> showRecyclerCardview()
        }
        mode = selectedMode
    }

    private fun showRecyclerCardview(){
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.adapter = CardViewHeroAdapter(list)
    }

    private fun showRecyclerGrid(){
        binding.rvHeroes.layoutManager= GridLayoutManager(this,1)
        val  gridHeroAdapter = GridHeroAdapter(list)
        binding.rvHeroes.adapter=gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })

    }

    private fun showRecyclerList() {
        binding.rvHeroes.layoutManager=LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        binding.rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })

    }

    private fun showSelectedHero(hero:Hero){
        Toast.makeText(this,"Kamu memilih ${hero.name}", Toast.LENGTH_SHORT).show()
    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()
        for (position in dataName.indices){
            val hero = Hero(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listHero.add(hero)
        }
        return listHero
    }

}
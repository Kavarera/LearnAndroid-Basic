package com.rafli.submission1_githubuser

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {

    private lateinit var rvListUser: RecyclerView

    private lateinit var guNama: Array<String>
    private lateinit var guUsername: Array<String>
    private lateinit var guLokasi: Array<String>
    private lateinit var guPhoto: TypedArray
    private lateinit var guFav: Array<String>
    private lateinit var guFollowing: Array<String>
    private lateinit var guRepo: Array<String>
    private lateinit var guCompany: Array<String>
    private lateinit var guFollowers: Array<String>



    private var users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvListUser = findViewById(R.id.rv_users)
        rvListUser.setHasFixedSize(true)

        prepare()
        addUser()

        showRecyclerList()

    }
    private fun showRecyclerList(){
        rvListUser.layoutManager=LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(users)
        rvListUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: User) {
                val moveIntent = Intent(this@MainActivity,UserDetail::class.java)
                moveIntent.putExtra(UserDetail.EXTRA_USER,data)
                startActivity(moveIntent)
            }
        })

    }

    private fun prepare(){
        guNama = resources.getStringArray(R.array.name)
        guPhoto = resources.obtainTypedArray(R.array.avatar)
        guUsername = resources.getStringArray(R.array.username)
        guLokasi = resources.getStringArray(R.array.location)
        guFav = resources.getStringArray(R.array.favorited)
        guFollowers = resources.getStringArray(R.array.followers)
        guFollowing = resources.getStringArray(R.array.following)
        guCompany = resources.getStringArray(R.array.company)
        guRepo = resources.getStringArray(R.array.repository)

    }

    private fun addUser(){
        var a = resources.getStringArray(R.array.name).size
        for (position in guNama.indices){
            val user =User(
                guPhoto.getResourceId(position,-1),
                guUsername[position],
                guNama[position],
                guLokasi[position],
                guFollowers[position],
                guFollowing[position],
                guCompany[position],
                guRepo[position],
                guFav[position]
            )
            users.add(user)
        }

    }




}
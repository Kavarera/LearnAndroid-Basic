package com.rafli.submission1_githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

class UserDetail : AppCompatActivity(),View.OnClickListener {

    companion object{
        const val EXTRA_USER = "extra_user"
    }
    private lateinit var favorited:String
    private lateinit var btnFavProfile:Button
    private lateinit var username: String
    private lateinit var person: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        person = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val civPhoto:CircleImageView = findViewById(R.id.civ_profile)
        val tvNamaPengguna:TextView= findViewById(R.id.tv_namapengguna)
        val tvFollowersNumber: TextView = findViewById(R.id.tv_followers_number)
        val tvFollowingNumber: TextView = findViewById(R.id.tv_following_number)
        val tvLokasi: TextView = findViewById(R.id.tv_lokasi)
        val tvKantor:TextView = findViewById(R.id.tv_kantor)
        val tvRepo:TextView = findViewById(R.id.tv_repo)
        val tvUsername: TextView=findViewById(R.id.tv_username)
        username = person.username.toString()
        btnFavProfile = findViewById(R.id.btn_profileFav)

        Glide.with(this)
                .load(person.photo)
                .apply(RequestOptions())
                .into(civPhoto)
        tvNamaPengguna.text = person.nama.toString()
        tvFollowersNumber.text = person.follower
        tvFollowingNumber.text = person.following
        tvKantor.text =person.company
        tvLokasi.text = person.lokasi
        tvUsername.text = person.username?.toLowerCase()
        tvRepo.text = "${person.repo} Repository"
        favorited = person.favorited.toString()
        checkFavorited(favorited)
        btnFavProfile.setOnClickListener(this)

    }
    fun checkFavorited(s: String){
        if (s=="true"){
            btnFavProfile.setBackgroundResource(R.drawable.ic_heart_filled)
        }
        else if(s=="false"){
            btnFavProfile.setBackgroundResource(R.drawable.ic_heart)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sharemenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val shareIntent:Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "https://www.github.com/${username}")
            type ="text/plain"
        }
        startActivity(shareIntent)
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_profileFav->{

                if(favorited=="true"){
                    favorited="false"
                }
                else if(favorited=="false"){
                    favorited="true"
                }
                checkFavorited(favorited)
            }


        }
    }
}
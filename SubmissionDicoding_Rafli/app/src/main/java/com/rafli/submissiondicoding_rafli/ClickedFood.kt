package com.rafli.submissiondicoding_rafli

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.io.FileOutputStream

class ClickedFood : AppCompatActivity() {

    companion object {
        const val FOODNAME = "FoodName"
        const val FOODDETAIL = "FoodDetail"
        const val FOODPRICE = "FoodPrice"
        const val FOODPHOTO = "0"
        const val FOODLINK = "FoodLink"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = intent.getStringExtra((FOODNAME))

        setContentView(R.layout.activity_clicked_food)
        val tv_foodDetail = findViewById<TextView>(R.id.tv_food_detail)
        val tv_harga: TextView = findViewById(R.id.tv_harga)
        val iv_food = findViewById<ImageView>(R.id.iv_food)

        //Declare button
        val btnFavorite: Button = findViewById(R.id.btn_favorite)
        val btnShare: Button = findViewById(R.id.btn_share)

        //Button Func
        btnShare.setOnClickListener {
            shareImage(this,iv_food)
            //checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,intent.getIntExtra(FOODPHOTO,0))
        }
        btnFavorite.setOnClickListener {
            if(btnFavorite.text=="Favorite"){
                btnFavorite.text="Favorited"
                Toast.makeText(this, "Added to Favorite", Toast.LENGTH_SHORT).show()
            }
            else{
                btnFavorite.text="Favorite"
                Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show()
            }
        }



        tv_foodDetail.text = intent.getStringExtra(FOODDETAIL)
        tv_harga.text = "Harga(IDR) = " + intent.getStringExtra(FOODPRICE)
        Glide.with(this)
                .load(intent.getIntExtra(FOODPHOTO, 0))
                .apply(RequestOptions())
                .into(iv_food)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.moreinfo, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra(FOODLINK)))
        startActivity(browserIntent)
        return super.onOptionsItemSelected(item)
    }


    private fun getBitmapFromView(view:View):Bitmap?{
        val returnBitmap = Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnBitmap)
        view.draw(canvas)
        return returnBitmap
    }

    private fun shareImage(context:Context,iview:ImageView){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            try {
                val bitmap = getBitmapFromView(iview)

                val file = File(context.externalCacheDir,"bakso.jpg")
                val outputStream:FileOutputStream = FileOutputStream(file)
                bitmap?.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
                outputStream.flush()
                outputStream.close()
                file.setReadable(true,false)

                val share =Intent()
                share.setAction(Intent.ACTION_SEND)
                share.putExtra(Intent.EXTRA_TEXT,"LAGI PENGEN INI NIH")
                val uri:Uri = FileProvider.getUriForFile(context,context.applicationContext.packageName+".provider",file)
                share.putExtra(Intent.EXTRA_STREAM,uri)
                share.type="image/*"
                share.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(Intent.createChooser(share,null))
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this, "Android Nouget ke atas mendapatkan fitur lebih", Toast.LENGTH_SHORT).show()
            try {
                val share = Intent()
                share.setAction(Intent.ACTION_SEND)
                share.putExtra(Intent.EXTRA_TEXT,"MAKAN BARENG YUK, LAPER NI")
                startActivity(share)
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}
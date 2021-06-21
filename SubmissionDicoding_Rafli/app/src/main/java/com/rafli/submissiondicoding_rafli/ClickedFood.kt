package com.rafli.submissiondicoding_rafli

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Path
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.io.FileOutputStream
import java.util.jar.Manifest

class ClickedFood : AppCompatActivity() {

    companion object {
        const val FOODNAME = "FoodName"
        const val FOODDETAIL = "FoodDetail"
        const val FOODPRICE = "FoodPrice"
        const val FOODPHOTO = "0"
    }

    lateinit var clickedFoodImg: Drawable

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
            checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,intent.getIntExtra(FOODPHOTO,0))
        }
        btnFavorite.setOnClickListener {
            btnFavorite.text = "Favorited"
        }



        tv_foodDetail.text = intent.getStringExtra(FOODDETAIL)
        tv_harga.text = "Harga(IDR) = " + intent.getStringExtra(FOODPRICE)
        Glide.with(this)
                .load(intent.getIntExtra(FOODPHOTO, 0))
                .apply(RequestOptions())
                .into(iv_food)

    }

    val WRITE_STORAGE = 1

    fun showDialog(permission: String) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage("Permission to share the image to your friend.")
            setTitle("Permissions Required")
            setPositiveButton("OK") { dialog, which ->
                ActivityCompat.requestPermissions(this@ClickedFood, arrayOf(permission), 1)
            }
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun saveCacheImage(context: Context, R_ID: Int,filename: String){
        //val bImg = drawableImg.toBitmap()
        val bitmap = BitmapFactory.decodeResource(context.resources,R_ID)

        //save file
        val ofile = File(context.getCacheDir(), filename+".png")
        val outputStream:FileOutputStream = FileOutputStream(ofile)
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
        outputStream.flush()
        outputStream.close()
        ofile.setReadable(true,false)

        val sendIntent: Intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_STREAM, Uri.fromFile(ofile))
            putExtra(Intent.EXTRA_TEXT,"Hey, Ini kelihatan enak. Mari makan bareng!")
        }
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }


    }

    private fun checkPermission(permission: String, id:Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when {
                ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED -> {
//                    val file: File = File(Environment.getDataDirectory(), "cache_share_image")
//                    if (!file.exists()) {
//                        file.mkdir()
//                    }
                    saveCacheImage(applicationContext,id,"makanyuk")

                }

                else -> {
                    showDialog(permission)
                    ActivityCompat.requestPermissions(this, arrayOf(permission), 1)
                }
            }
        }
    }
}
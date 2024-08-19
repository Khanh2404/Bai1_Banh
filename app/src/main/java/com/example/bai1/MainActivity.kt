package com.example.bai1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cake(var imageCakeId:Int,var price: String, var quantity: Int)
class MainActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private lateinit var priceTextView: TextView
    private lateinit var quantityTextView:TextView
    private lateinit var chooseCakeTextView: TextView
    private lateinit var item:TextView
    private lateinit var TongTien:TextView
    private var Cake= listOf(
        Cake(R.drawable.cake4,"1000$",20),
        Cake(R.drawable.cake5,"2000$",50),
        Cake(R.drawable.cake1,"3000$",5)
    )
    private var currentCakeIndex = 0
    private var demSoLuongMatHang =0
    private var demSoTien = 0
    private fun updetaCakeUI(cake: Cake){
        imageView?.setImageResource(cake.imageCakeId)
        priceTextView.text=cake.price
        quantityTextView.text=cake.quantity.toString()
    }
    private fun cnSlmhSt( ){
        item.text="ban đang có $demSoLuongMatHang mặt hang";
        TongTien.text="Tổng Tiền : ${demSoTien}"
        quantityTextView.text="${Cake[currentCakeIndex].quantity}";
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        imageView=findViewById(R.id.imgBanh)
        priceTextView=findViewById(R.id.txtGiaTien)
        quantityTextView=findViewById(R.id.txtSoLuongBanh)
        chooseCakeTextView=findViewById(R.id.txtCloss)
        item=findViewById(R.id.txtItems)
        TongTien=findViewById(R.id.txtTongTien)
        updetaCakeUI(Cake[currentCakeIndex])
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        chooseCakeTextView.setOnClickListener {
            currentCakeIndex++
            if (currentCakeIndex>= Cake.size){
                currentCakeIndex=0
            }
            updetaCakeUI(Cake[currentCakeIndex])
            imageView?.isEnabled = Cake[currentCakeIndex].quantity > 0
        }
        imageView?.setOnClickListener{
            demSoLuongMatHang++
            var giaBanh = Cake[currentCakeIndex].price.replace("$","").toInt()
            demSoTien +=giaBanh
            Cake[currentCakeIndex].quantity--
            if (Cake[currentCakeIndex].quantity==0){
                imageView?.isEnabled = false
                Toast.makeText(this, "Hết bánh", Toast.LENGTH_SHORT).show()
            }
            cnSlmhSt()
        }
    }
}
package com.sahibaliyev.catchtheicon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sahibaliyev.catchtheicon.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score =0
    var imageList = ArrayList<ImageView>()
    var runnable = Runnable{ }
    var handler = Handler(Looper.getMainLooper())

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageList.add(binding.imageView1)
        imageList.add(binding.imageView2)
        imageList.add(binding.imageView3)
        imageList.add(binding.imageView4)
        imageList.add(binding.imageView5)
        imageList.add(binding.imageView6)
        imageList.add(binding.imageView7)
        imageList.add(binding.imageView8)
        imageList.add(binding.imageView9)


        object : CountDownTimer(16000 , 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                binding.timeText.text = "Time: ${p0/1000}"
            }


            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)

                for (img in imageList) {
                    img.visibility = View.INVISIBLE
                }


                    val alert = AlertDialog.Builder(this@MainActivity)

                    alert.setMessage("Restart the game?")
                    alert.setTitle("Game Over")

                    alert.setPositiveButton("Yes") { dialog, which ->
                        val intent = intent
                        finish()
                        startActivity(intent)
                    }

                    alert.setNegativeButton("No") { dialog, which ->
                        Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_LONG).show()
                    }

                alert.show()

            }



        }.start()

        imageHide()

    }


    fun imageHide (){

        runnable = object : Runnable{
            override fun run() {


                for (image in imageList){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(9)
                imageList[randomIndex].visibility= View.VISIBLE

                handler.postDelayed(runnable , 500)
            }
        }
        handler.post(runnable)
    }




    @SuppressLint("SetTextI18n")
    fun scoreincrease (view : View){

        score = score + 1
        binding.scoreText.text = "Score  $score"
    }
}
package com.example.runnable

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.runnable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var runnable : Runnable=Runnable {}  // sayaç yapmamızı sağlayacak
    var handler : Handler = Handler(Looper.getMainLooper()) //runnable ın nesnelerine handler ile erişeceğiz
    var sayac = 0
    var durdur = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root

        setContentView(view)




    }
    fun basla(view : View){

    sayac = 0
        runnable = object : Runnable{
            override fun run() {
              sayac = durdur
                durdur = sayac + 1
                binding.txtGoster.text = "Süre : ${durdur}"
                handler.postDelayed(runnable,1000)  //rötar yapmak için

            }
        }
        handler.post(runnable)   //runnable ı başlatmak için
        binding.btnBasla.isEnabled=false
    }

    fun sifirla(view: View){
var alert = AlertDialog.Builder(this)
        alert.setTitle("sayaç")
        alert.setMessage("sıfırlansın mı")
        alert.setPositiveButton("evet" , object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                binding.btnBasla.isEnabled = true

                binding.txtGoster.text=" Süre : 0 "
                durdur = 0
                handler.removeCallbacks(runnable)
            }

        })
        alert.setNegativeButton("hayır",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
           Toast.makeText(this@MainActivity,"sıfırlanmadı",Toast.LENGTH_LONG).show()
            }

        })
        alert.show()


    }

    fun dur(view: View){

        binding.btnBasla.isEnabled=true
        durdur = sayac
          handler.removeCallbacks(runnable)
    }

}
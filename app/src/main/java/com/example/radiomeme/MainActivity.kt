package com.example.radiomeme

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // pone los botones en el layout
        val buttonShowFragment = findViewById<Button>(R.id.button_show_fragment)
        val buttonExit = findViewById<Button>(R.id.button_exit)

        // config el clic para mostrar el fragmento
        buttonShowFragment.setOnClickListener {
            val fragment = RadioFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // hacia  al layout anterior
                .commit()
        }

        // config el clic para cerrar la aplicación
        buttonExit.setOnClickListener {
            finish()
        }
    }
}

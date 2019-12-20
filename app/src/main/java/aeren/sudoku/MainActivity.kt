package aeren.sudoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.buton)

        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SudokuActivity::class.java)
            startActivity(intent)
        })
    }
}

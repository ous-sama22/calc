package com.oussama.calculation_moyeen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var Note1: EditText
    private lateinit var Note2: EditText
    private lateinit var Note3: EditText
    private lateinit var Note4: EditText
    private lateinit var btnCalculate: Button
    private lateinit var DisplayResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Note1 = findViewById(R.id.Note1)
        Note2 = findViewById(R.id.Note2)
        Note3 = findViewById(R.id.Note3)
        Note4 = findViewById(R.id.Note4)
        btnCalculate = findViewById(R.id.btnCalculate)
        DisplayResult = findViewById(R.id.DisplayResult)

        btnCalculate.setOnClickListener {
            calculateAverage()
        }
    }

    private fun calculateAverage() {
        val notes = listOf(Note1, Note2, Note3, Note4)
        val validNotes = notes.mapNotNull { it.text.toString().toFloatOrNull() }

        for (note in validNotes){
            if (note > 20 ){
                Toast.makeText(this, "note need to be <= 20!", Toast.LENGTH_SHORT).show()
                return
            }
        }

        if (validNotes.size != 4 ) {
            Toast.makeText(this, "Enter 4 notes!", Toast.LENGTH_SHORT).show()
            return
        }

        val average = validNotes.average().toFloat()
        val mention = getMention(average)

        DisplayResult.text = "Moyenne: %.2f\nMention: %s".format(average, mention)
    }

    private fun getMention(average: Float): String {
        return when {
            average >= 16 -> "Excellent"
            average >= 14 -> "Très bien"
            average >= 12 -> "Bien"
            average >= 10 -> "Assez bien"
            else -> "Échec"
        }
    }
}
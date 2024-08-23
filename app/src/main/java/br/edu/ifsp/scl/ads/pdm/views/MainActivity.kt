package br.edu.ifsp.scl.ads.pdm.views

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.views.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.estadoCivilSp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected (
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val estadoCivil = (view as TextView).text.toString()
                if (estadoCivil == "Casado") {
                    amb.conjugeLl.visibility = View.VISIBLE
                }
                else {
                    amb.run {
                        conjugeLl.visibility = View.GONE
                        nomeConjugeEt.setText("")
                        sobrenomeConjugeEt.setText("")
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // NSA
            }
        }

        amb.salvarBt.setOnClickListener {
            val estadoCivil = amb.estadoCivilSp.selectedItem.toString()
            val nomeConjuge = if (estadoCivil == "Casado") {
                "\nNome do Cônjuge: ${amb.nomeConjugeEt.text}" +
                        "\nSobrenome do Cônjuge: ${amb.sobrenomeConjugeEt.text}"
            } else {
                ""
            }

            val sexoId = amb.sexoRg.checkedRadioButtonId
            val sexo = findViewById<RadioButton>(sexoId).text.toString()

            Toast.makeText(
                this@MainActivity,
                "Nome: ${amb.nomeEt.text}\n" +
                        "Sobrenome: ${amb.sobrenomeEt.text}\n" +
                        "Email: ${amb.emailEt.text}\n" +
                        "Sexo: $sexo\n" +
                        "Estado civil: $estadoCivil\n" +
                        nomeConjuge,
                Toast.LENGTH_SHORT
            ).show()
        }
        
        amb.limparBt.setOnClickListener {
            amb.run {
                nomeEt.text.clear()
                sobrenomeEt.text.clear()
                emailEt.text.clear()
                estadoCivilSp.setSelection(0)
                masculinoRb.isChecked = false
                femininoRb.isChecked = false
            }
        }
    }
}
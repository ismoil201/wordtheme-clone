package com.ismoil.lugat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ismoil.lugat.databinding.ActivityAddBinding
import com.ismoil.lugat.room.AppDataBase
import com.ismoil.lugat.room.entity.Theme
import androidx.lifecycle.lifecycleScope
import com.ismoil.lugat.room.dao.ThemeDao
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var dao: ThemeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = AppDataBase.getInstance(this).themeDao()

        binding.btnAddTheme.setOnClickListener {
            addTheme()
        }
    }

    private fun addTheme() {
        val themeName = binding.etTheme.text.toString()

        if (themeName.isNotEmpty()) {
            lifecycleScope.launch {
                dao.insertTheme(Theme(name = themeName))
                runOnUiThread {
                    Toast.makeText(this@AddActivity, "Saved", Toast.LENGTH_SHORT).show()
                    binding.etTheme.text?.clear()
                    binding.tilWord.isErrorEnabled = false

                    finish()
                }
            }
        } else {
            binding.tilWord.error = "Please enter theme"
            binding.tilWord.isErrorEnabled = true
        }
    }
}

package com.ismoil.lugat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ismoil.lugat.databinding.ActivityAddWordBinding
import com.ismoil.lugat.room.AppDataBase
import com.ismoil.lugat.room.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddWord.setOnClickListener {
            addWord()
        }
    }

    private fun addWord() {
        val word = binding.etWord.text.toString()
        val translation = binding.etMeaning.text.toString()
        val themeId = intent.getIntExtra("themeId", -1)

        if (word.isNotEmpty() && translation.isNotEmpty() && themeId != -1) {
            val wordDao = AppDataBase.getInstance(this).wordDao()

            lifecycleScope.launch(Dispatchers.IO) {
                wordDao.insertWord(
                    Word(word = word, meaning = translation, themeId = themeId)
                )

                launch(Dispatchers.Main) {
                    binding.etWord.text?.clear()
                    binding.etMeaning.text?.clear()
                    finish()
                }
            }
        }
    }

}

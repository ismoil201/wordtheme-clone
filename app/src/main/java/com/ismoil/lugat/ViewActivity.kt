package com.ismoil.lugat

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ismoil.lugat.databinding.ActivityViewBinding
import com.ismoil.lugat.room.AppDataBase
import com.ismoil.lugat.room.entity.Word

class ViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewBinding

    private var selectedWord: Word? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        binding.deleteBtn.setOnClickListener {
            delete()
        }

        binding.editBtn.setOnClickListener {
            edit()

        }

    }

    private  fun initView() {

        var wordId = intent.getIntExtra("wordId", 0)

        Thread {

            selectedWord = AppDataBase.getInstance(this).wordDao().getWordById(wordId)

            runOnUiThread {

                binding.text.text = selectedWord?.word
                binding.mean.text = selectedWord?.meaning
            }
        }.start()

    }

    private fun delete(){

        Thread {
            selectedWord?.let {
                AppDataBase.getInstance(this).wordDao().deleteWord(word = it)
            }
            finish()

        }.start()

    }

    private fun edit(){

        startActivity(Intent(this, AddWordActivity::class.java))
        intent.putExtra("wordId", selectedWord?.id)
        finish()

    }
}
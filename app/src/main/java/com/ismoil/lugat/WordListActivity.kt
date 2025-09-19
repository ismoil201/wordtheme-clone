package com.ismoil.lugat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ismoil.lugat.adapter.WordAdapter
import com.ismoil.lugat.databinding.ActivityWordListBinding
import com.ismoil.lugat.room.AppDataBase
import com.ismoil.lugat.room.entity.Word
import kotlinx.coroutines.launch

class WordListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordAdapter(
            listOf(),
            object : WordAdapter.OnItemClickListener {
                override fun onItemClick(word: Word) {
                    val intent = Intent(this@WordListActivity, ViewActivity::class.java)
                    intent.putExtra("wordId", word.id)
                    startActivity(intent)
                }
            }
        )

        binding.rvView.adapter = adapter

        val themeId = intent.getIntExtra("themeId", 0)
        val wordDao = AppDataBase.getInstance(this).wordDao()

        wordDao.getWordsForTheme(themeId).observe(this) { date ->
            adapter.updateList(date)
        }

        binding.btnAddWord.setOnClickListener {
            addWord()
        }
    }

    private fun addWord() {
        val themeId = intent.getIntExtra("themeId", -1)
        if (themeId != -1) {
            val intent = Intent(this, AddWordActivity::class.java)
            intent.putExtra("themeId", themeId) // MUHIM!
            startActivity(intent)
        }
    }

}

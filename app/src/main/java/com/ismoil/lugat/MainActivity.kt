package com.ismoil.lugat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismoil.lugat.adapter.ThemeAdapter
import com.ismoil.lugat.databinding.ActivityMainBinding
import com.ismoil.lugat.room.AppDataBase
import com.ismoil.lugat.room.entity.Theme

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView adapterini bo'sh list bilan boshlaymiz
        val adapter = ThemeAdapter(listOf(), object : ThemeAdapter.OnItemClickListener {
            override fun onItemClick(theme: Theme) {
                val intent = Intent(this@MainActivity, WordListActivity::class.java)
                intent.putExtra("themeId", theme.id) // Agar kerak bo‘lsa
                startActivity(intent)
            }
        })
        binding.rvView.adapter = adapter

        // Room orqali LiveData bilan kuzatamiz
        val themeDao = AppDataBase.getInstance(this).themeDao()
        themeDao.getAllThemes().observe(this, Observer { data ->
            // Adapterdagi ma’lumotni yangilaymiz
            adapter.updateList(data)
        })

        binding.btnAddTheme.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
}

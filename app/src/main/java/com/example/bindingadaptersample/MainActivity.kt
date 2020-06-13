package com.example.bindingadaptersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bindingadaptersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UserViewModel を取得。
        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // gradle ファイルで DataBinding を有効にするとバインディングクラスが自動生成される。
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.also {
            it.viewModel = viewModel // レイアウトファイルで定義した viewModel にバインド。
            it.lifecycleOwner = this
        }

        val users = viewModel.users.value ?: listOf()
        val adapter = UserListAdapter(users, this)
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = manager
            it.addItemDecoration(decoration) // リストアイテム間に境界線をつける。
        }
    }
}

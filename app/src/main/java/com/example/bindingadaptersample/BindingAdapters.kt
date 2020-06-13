package com.example.bindingadaptersample

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    /**
     * RecyclerView の拡張関数を定義。
     * レイアウトファイル内で users という属性を設定できるようにする。
     */
    @BindingAdapter("users")
    @JvmStatic
    fun RecyclerView.bindUsers(users: List<User>?) {
        users ?: return
        // adapter が UserListAdapter ならリスト情報をアップデートする。
        when (val adapter = this.adapter) {
            is UserListAdapter -> adapter.update(users)
        }
    }
}

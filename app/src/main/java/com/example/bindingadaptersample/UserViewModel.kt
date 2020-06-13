package com.example.bindingadaptersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * User モデルクラス
 */
data class User(var name: String)

/**
 * User の情報を保持する ViewModel。
 */
class UserViewModel : ViewModel() {

    // クラス内部で扱う LiveData は変更可能な形式で保持する。
    private val _users: MutableLiveData<List<User>> = MutableLiveData()

    // クラス外部に公開する LiveData は変更不可な形式で保持する。
    val users: LiveData<List<User>> = _users

    // 初期値として10人分のユーザー情報を LiveData に突っ込む。
    init {
        val names = listOf(
            "一郎", "二郎", "三郎", "四郎", "五郎", "六郎", "七郎", "八郎", "九郎", "十郎"
        )
        val users = names.map { name -> User(name) }
        _users.value = users
    }

    /**
     * API 通信してユーザー情報を取ってくることを模した関数。
     * 今回は通信せずにリストをシャッフルする仕様にしている。
     */
    fun fetchUsers() {
        val shuffled = _users.value?.shuffled()
        _users.value = shuffled
    }
}

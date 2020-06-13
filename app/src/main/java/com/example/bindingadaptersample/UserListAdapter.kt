package com.example.bindingadaptersample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.bindingadaptersample.databinding.RowUserListBinding

/**
 * ユーザーリスト用の Adapter。
 */
class UserListAdapter(
    private var users: List<User>,
    private val owner: LifecycleOwner
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: RowUserListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_user_list,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.apply {
            userName.text = user.name
            lifecycleOwner = owner
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    /**
     * リストの中身を更新する。
     */
    fun update(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    /**
     * ユーザーリスト用の ViewHolder。
     */
    inner class UserViewHolder(val binding: RowUserListBinding) :
        RecyclerView.ViewHolder(binding.root)
}

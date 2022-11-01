package com.example.roomhilthindi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomhilthindi.model.User
import com.example.roomhilthindi.R
import com.example.roomhilthindi.databinding.RowWordItemBinding
import java.util.*
import java.util.concurrent.ThreadLocalRandom.current
import kotlin.collections.ArrayList

class UserAdapter(var userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(user: User)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_word_item, parent, false).context
            ).inflate(R.layout.row_word_item, parent, false), mListener
        )


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user, mListener)

    }

    override fun getItemCount(): Int {
        if (userList.size == 0) return 0
        return userList.size
    }

    class UserViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val mBinding = RowWordItemBinding.bind(itemView)


        fun bind(user: User, mlistener: onItemClickListener) {
            mBinding.txvName.text = "Welcome " + user.name.substring(0, 1)
                .toUpperCase() + user.name.substring(1) + ", your age:"
            mBinding.txvNumber.text =
                user.year.toString() + "y " + user.months.toString() + "m " + user.days.toString() + "d"

            mBinding.delet.setOnClickListener {

                mlistener.onItemClick(user)
            }
        }
    }

    fun setData(userList: ArrayList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}
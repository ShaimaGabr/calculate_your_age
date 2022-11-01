package com.example.roomhilthindi.ui.allUser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.roomhilthindi.R
import com.example.roomhilthindi.adapter.UserAdapter
import com.example.roomhilthindi.databinding.FragmentAllUserBinding
import com.example.roomhilthindi.model.User
import com.example.roomhilthindi.ui.main.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllUserFragment : Fragment() {
    lateinit var binding:FragmentAllUserBinding
    private lateinit var userAdapter: UserAdapter

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAllUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        userViewModel.getUserData.observe(viewLifecycleOwner) {
            userAdapter.setData(it as ArrayList<User>)
        if(it.isEmpty()){
            showGIF()
            binding.imgNoData.visibility= View.VISIBLE
            binding.txtNoData.visibility= View.VISIBLE
        }
            else{
            binding.imgNoData.visibility= View.INVISIBLE
            binding.txtNoData.visibility= View.INVISIBLE
            }
        }
        binding.toAddFrag.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_allUserFragment_to_addUserFragment)
        }
        userAdapter.setOnItemClickListener(object :UserAdapter.onItemClickListener{
            override fun onItemClick(user: User) {
                Log.d("os", "onItemClick: ${user.name}")
                userViewModel.deletbyid(user)
            }
        })
    }
    private fun initRecyclerView() {
        userAdapter = UserAdapter(ArrayList())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = userAdapter

        }
    }
    fun showGIF() {
        Glide.with(this).load(R.drawable.question).into(binding.imgNoData)
    }
    override fun onPause() {
        super.onPause()
        Log.d("ana", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ana", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ana", "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("ana", "onDetach: ")
    }
}
package com.example.roomhilthindi.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomhilthindi.R
import com.example.roomhilthindi.Utl.PrefManager
import com.example.roomhilthindi.adapter.UserAdapter
import com.example.roomhilthindi.model.User
import com.example.roomhilthindi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var nav: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        nav= Navigation.findNavController(this, R.id.nav_host_fragment)
        val save= PrefManager(this)
        val valu:String?= save.getName()

        if (valu?.isNotEmpty()!!){
             nav.popBackStack(R.id.allUserFragment, false)
             nav.navigate(R.id.action_allUserFragment_to_addUserFragment)
        }
      else{
            nav.popBackStack(R.id.allUserFragment, false)
      }


    }


}
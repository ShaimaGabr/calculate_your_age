package com.example.roomhilthindi.ui.addUser

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.roomhilthindi.R
import com.example.roomhilthindi.Utl.PrefManager
import com.example.roomhilthindi.databinding.FragmentAddUserBinding
import com.example.roomhilthindi.model.User
import com.example.roomhilthindi.ui.main.UserViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddUserFragment : Fragment() {
    lateinit var binding: FragmentAddUserBinding
    private val userViewModel: UserViewModel by viewModels()
    var age:String=""
    var mon:String=""
    var dayss:String=""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBack.setOnClickListener {
          back(view)
        }
        binding.btnSave.setOnClickListener {
            insertIntoRoom(view)

        }

        val datePicker = binding.datePicker
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            val msg = "$day/$month/$year"
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val c = Calendar.getInstance()
            val date: String = sdf.format(c.time)
            val date1: Date
            val date2: Date
            val dates = SimpleDateFormat("dd/MM/yyyy")
           date1 = dates.parse(msg)
           date2 = dates.parse(date)
            val difference: Long = abs(date1.time - date2.time)
            val differenceDates = difference / (24 * 60 * 60 * 1000)
            val yeardif=differenceDates/365
            val yDifference = yeardif.toString()
            val days=differenceDates%365
            val months=days/30
            age=yDifference
            mon= months.toString()
            dayss= (days%30-(yeardif/4)).toString()

        }
    }

    private fun insertIntoRoom(view: View) {
        val name = binding.txvName.text.toString().trim()
        if (!TextUtils.isEmpty(name)&& age!="" ) {
            val user = User(name, age.toInt(),mon.toInt(),dayss.toInt())
            userViewModel.insert(user)
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
            binding.txvName.text?.clear()
            back(view)
        } else {
            if (TextUtils.isEmpty(name)){
            binding.txvName.error = "You need to enter a name"}
            else if(age==""){
                Snackbar.make(view ,"Choose your birthDate",Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show()
            }
        }
    }
    private fun back(view:View){
        Navigation.findNavController(view)
            .navigate(R.id.action_addUserFragment_to_allUserFragment)
    }

    override fun onPause() {
        super.onPause()
        Log.d("ana", "onPause: ")
         if(binding.txvName.text!!.isNotEmpty()){
            savName(binding.txvName.text.toString())
        }
    }
    fun savName(name:String){
        val save=PrefManager(context!!)
        save.saveName(name)

    }

    override fun onResume() {
        super.onResume()
        val save=PrefManager(context!!)
       val valu:String?= save.getName()

        if (valu?.isNotEmpty()!!){
        binding.txvName.setText(valu)
            binding.txvName.setTextColor(Color.RED)
        savName("")
    }}

    override fun onStop() {
        super.onStop()
        Log.d("ana", "onStop: ")
    }


}
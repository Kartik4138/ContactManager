package com.example.contactsmanager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmanager.databinding.ActivityMainBinding
import com.example.contactsmanager.repository.ContactRepository
import com.example.contactsmanager.room.ContactDatabase
import com.example.contactsmanager.room.Contacts
import com.example.contactsmanager.view.MyRecyclerViewAdapter
import com.example.contactsmanager.viewmodel.ContactViewModel
import com.example.contactsmanager.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)
        val factory = ViewModelFactory(repository)

        contactViewModel = ViewModelProvider(this,factory)
            .get(ContactViewModel::class.java)

        binding.contactViewModel = contactViewModel

        binding.lifecycleOwner = this

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUserList()
    }

    private fun DisplayUserList(){
        contactViewModel.contacts.observe(this){
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it,{ selectedItem: Contacts -> listItemClicked(selectedItem)}
            )
        }
    }

    private fun listItemClicked(selectedItem: Contacts) {
        Toast.makeText(
            this,
            "Selected name is ${selectedItem.contact_name}",
            Toast.LENGTH_LONG
        ).show()

        contactViewModel.initUpdateAndDelete(selectedItem)
    }
}

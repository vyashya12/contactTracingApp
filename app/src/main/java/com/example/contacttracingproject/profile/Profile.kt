package com.example.contacttracingproject.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.BaseApplication
import com.example.contacttracingproject.MainActivity
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.FragmentProfileBinding

//Profile fragment kotlin
//Links the xml with the adapter and layout
class Profile : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<VaccCertAdapter.VaccViewHolder>? = null
    private lateinit var binding: FragmentProfileBinding
    private lateinit var fullname: String

    val cancelButtonClick = { dialog: DialogInterface, which: Int ->
        dialog.dismiss()
    }

    val editButtonClick = { dialog: DialogInterface, which: Int ->
        val profileViewModel: ProfileViewModel by viewModels {
            ProfileViewModelFactory((activity?.application as BaseApplication).repository)
        }

//        Update goes into here

        dialog.dismiss()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_edit -> {
                val builder = AlertDialog.Builder(context)
                val inflater = requireActivity().layoutInflater;
                val dialogLayout = R.layout.edit_profile
                builder.setTitle("Edit Profile")
                builder.setView(inflater.inflate(dialogLayout, null))
                builder.setPositiveButton("Edit", editButtonClick)
                builder.setNegativeButton("Cancel", cancelButtonClick)

                builder.show()
                true
            }
            R.id.nav_exit -> {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val profileViewModel: ProfileViewModel by viewModels {
            ProfileViewModelFactory((activity?.application as BaseApplication).repository)
        }

        binding.viewModel = profileViewModel

        binding.lifecycleOwner = this

        val intent = requireActivity().intent
        fullname = intent.getStringExtra("fullname").toString()

        profileViewModel.getUser(fullname)

        val cert_recycler: RecyclerView = itemView.findViewById(R.id.cert_recycler)
        cert_recycler.setNestedScrollingEnabled(false)
        cert_recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = VaccCertAdapter()
        }


    }



    companion object {
        fun newInstance() = Profile()
    }
}
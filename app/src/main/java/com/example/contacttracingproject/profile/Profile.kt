package com.example.contacttracingproject.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Layout
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
import com.example.contacttracingproject.data.User
import com.example.contacttracingproject.databinding.FragmentProfileBinding
import com.example.contacttracingproject.login.LoginForm

//Profile fragment kotlin
//Links the xml with the adapter and layout
class Profile : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<VaccCertAdapter.VaccViewHolder>? = null
    private lateinit var binding: FragmentProfileBinding
    private lateinit var fullname: String




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
                val inflateLayout = (inflater.inflate(dialogLayout, null))
                builder.setTitle("Edit Profile")
                builder.setView(inflateLayout)

                val etNameText: EditText = inflateLayout.findViewById(R.id.edit_name)
                val etPasswordText: EditText = inflateLayout.findViewById(R.id.edit_password)
                builder.setPositiveButton("Edit", DialogInterface.OnClickListener {
                    dialog: DialogInterface, which: Int ->

                    val profileViewModel: ProfileViewModel by viewModels {
                        ProfileViewModelFactory((activity?.application as BaseApplication).repository)
                    }
                    profileViewModel.editName.value = etNameText.text.toString()
                    profileViewModel.editPassword.value = etPasswordText.text.toString()

                    profileViewModel.updateUser(etNameText.text.toString(), etPasswordText.text.toString())

                    val intent = Intent(activity, LoginForm::class.java)
                    startActivity(intent)
                    activity?.finishAffinity()


                })

                builder.setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog: DialogInterface, which: Int ->
                        dialog.dismiss()
                })

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

//        val dialogLayout: View? = itemView.findViewById(R.layout.edit_profile)
//        val etName = dialogLayout?.findViewById<EditText>(R.id.edit_name)
//        var etNameText: String = etName?.text.toString()
//        profileViewModel.editName.value = etNameText
//
//        val etPassword = dialogLayout?.findViewById<EditText>(R.id.edit_name)
//        var etPasswordText: String = etPassword?.text.toString()
//        profileViewModel.editPassword.value = etPasswordText
    }



    companion object {
        fun newInstance() = Profile()
    }
}
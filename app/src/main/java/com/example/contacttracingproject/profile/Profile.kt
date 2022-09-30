package com.example.contacttracingproject.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.MainActivity
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//Profile fragment kotlin
//Links the xml with the adapter and layout
@AndroidEntryPoint
class Profile : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<VaccCertAdapter.VaccViewHolder>? = null
    private lateinit var binding: FragmentProfileBinding
    private lateinit var nric: String

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

                builder.setPositiveButton("Edit", DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->

                    val profileViewModel: ProfileViewModel by viewModels()
                    profileViewModel.editName.value = etNameText.text.toString()
                    profileViewModel.editPassword.value = etPasswordText.text.toString()

                    val homeIntent = requireActivity().intent
                    nric = homeIntent.getStringExtra("nric").toString()

                    profileViewModel.updateUser(etNameText.text.toString(), nric, etPasswordText.text.toString())
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

        val profileViewModel: ProfileViewModel by viewModels()
        binding.viewModel = profileViewModel

        binding.lifecycleOwner = this

        val intent = requireActivity().intent
        nric = intent.getStringExtra("nric").toString()

        Log.i("profileFragment", nric)

        profileViewModel.errorToast.asLiveData().observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            profileViewModel._finish.value = false
        }

        lifecycleScope.launch {
            profileViewModel.getUser(nric)
        }
    }

    companion object {
        fun newInstance() = Profile()
    }
}
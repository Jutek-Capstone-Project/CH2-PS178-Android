package com.bangkit.ch2_ps178_android.view.profile

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bangkit.ch2_ps178_android.R
import com.bangkit.ch2_ps178_android.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    interface LogoutListener {
        fun onLogout()
    }

    private lateinit var binding: FragmentProfileBinding

    lateinit var textName: TextView
    lateinit var textEmail: TextView
    lateinit var btnLogout: Button

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textName = binding.profileName
        textEmail = binding.profileEmail
        btnLogout = binding.btnLogout

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            textName.text = firebaseUser.displayName
            textEmail.text = firebaseUser.email
        }

        btnLogout.setOnClickListener {
            (activity as? LogoutListener)?.onLogout()
        }
    }
}

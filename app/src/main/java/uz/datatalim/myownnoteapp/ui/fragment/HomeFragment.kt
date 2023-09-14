package uz.datatalim.myownnoteapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myownnoteapp.R
import uz.datatalim.myownnoteapp.ViewModel.HomeViewModel
import uz.datatalim.myownnoteapp.adapter.HomeAdapter
import uz.datatalim.myownnoteapp.data.remote.ApiClient
import uz.datatalim.myownnoteapp.model.Note
import uz.datatalim.myownnoteapp.util.Extensions.hide
import uz.datatalim.myownnoteapp.util.Extensions.show

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var adapter: HomeAdapter
    lateinit var loading: LottieAnimationView
    lateinit var llEmpty: LinearLayout
    lateinit var viewModel:HomeViewModel
    var myNotes = ArrayList<Note>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        viewModel=ViewModelProvider(this).get(HomeViewModel::class.java)

        adapter = HomeAdapter()
        loading = view.findViewById(R.id.lav_loading)
        llEmpty = view.findViewById(R.id.ll_empty)
        llEmpty.hide()
        val bAdd = view.findViewById<LottieAnimationView>(R.id.lav_add)
        val rvNotes = view.findViewById<RecyclerView>(R.id.rv_notes)
        rvNotes.adapter = adapter

        adapter.onClick={position->

            viewModel.deleteNote(myNotes[position].id!!).observe(viewLifecycleOwner,){

                loadList()

            }

        }

        loadList()

        bAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    private fun loadList() {

        viewModel.apiGetAll().observe(viewLifecycleOwner) {

            myNotes.clear()
            myNotes.addAll(it)
            adapter.submitList(it)

        }

    }

}
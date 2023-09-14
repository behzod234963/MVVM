package uz.datatalim.myownnoteapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myownnoteapp.R
import uz.datatalim.myownnoteapp.adapter.HomeAdapter
import uz.datatalim.myownnoteapp.data.remote.ApiClient
import uz.datatalim.myownnoteapp.model.Note
import uz.datatalim.myownnoteapp.util.Extensions.hide
import uz.datatalim.myownnoteapp.util.Extensions.show

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var adapter: HomeAdapter
    lateinit var loading: LottieAnimationView
    lateinit var llEmpty: LinearLayout
    var myNotes = ArrayList<Note>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        adapter = HomeAdapter()
        loading = view.findViewById(R.id.lav_loading)
        llEmpty = view.findViewById(R.id.ll_empty)
        llEmpty.hide()
        val bAdd = view.findViewById<LottieAnimationView>(R.id.lav_add)
        val rvNotes = view.findViewById<RecyclerView>(R.id.rv_notes)
        rvNotes.adapter = adapter
        loadAllNotes()
        bAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    private fun loadAllNotes() {
        ApiClient.apiService.getAllNotes().enqueue(object : Callback<ArrayList<Note>> {
            override fun onResponse(
                call: Call<ArrayList<Note>>,
                response: Response<ArrayList<Note>>
            ) {

                if (response.isSuccessful){


                }

            }

            override fun onFailure(call: Call<ArrayList<Note>>, t: Throwable) {

            }
        })
    }

}
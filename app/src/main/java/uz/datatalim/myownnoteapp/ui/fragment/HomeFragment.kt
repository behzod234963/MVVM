package uz.datatalim.myownnoteapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.datatalim.myownnoteapp.R
import uz.datatalim.myownnoteapp.ViewModel.Factory.HomeFactory
import uz.datatalim.myownnoteapp.ViewModel.Home.HomeViewModel
import uz.datatalim.myownnoteapp.adapter.HomeAdapter
import uz.datatalim.myownnoteapp.data.Repositories.HomeRepository
import uz.datatalim.myownnoteapp.data.remote.ApiClient
import uz.datatalim.myownnoteapp.data.remote.ApiService
import uz.datatalim.myownnoteapp.model.Note
import uz.datatalim.myownnoteapp.util.Extensions.hide
import uz.datatalim.myownnoteapp.util.UiStateList

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var adapter: HomeAdapter
    lateinit var loading: LottieAnimationView
    lateinit var llEmpty: LinearLayout
    lateinit var viewModel: HomeViewModel
    var myNotes = ArrayList<Note>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        setupViewModel()
        setupObserve()

        adapter = HomeAdapter()
        loading = view.findViewById(R.id.lav_loading)
        llEmpty = view.findViewById(R.id.ll_empty)
        llEmpty.hide()
        val bAdd = view.findViewById<LottieAnimationView>(R.id.lav_add)
        val rvNotes = view.findViewById<RecyclerView>(R.id.rv_notes)
        rvNotes.adapter = adapter


        loadList()

        bAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    private fun setupObserve() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModel.notes.collect{

                when(it){

                    is UiStateList.LOADING->{

//                        show loading

                    }
                    is UiStateList.SUCCESS->{

                       setData(it.data as ArrayList<Note>)

                    }

                    is UiStateList.ERROR->{

//                        show error

                    }else->Unit

                }

            }

        }

    }

    private fun setData(data: ArrayList<Note>) {

        adapter.submitList(data)

    }


    private fun loadList() {

        viewModel.getNotes()

    }

    private fun setupViewModel() {

        viewModel= ViewModelProvider(this,HomeFactory(HomeRepository(ApiClient.apiService)))[HomeViewModel::class.java]

    }

}
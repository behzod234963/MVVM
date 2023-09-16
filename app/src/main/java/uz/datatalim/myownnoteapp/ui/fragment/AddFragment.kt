package uz.datatalim.myownnoteapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.flow.collect
import uz.datatalim.myownnoteapp.R
import uz.datatalim.myownnoteapp.ViewModel.Add.AddViewModel
import uz.datatalim.myownnoteapp.ViewModel.Factory.AddFactory
import uz.datatalim.myownnoteapp.data.Repositories.AddRepository
import uz.datatalim.myownnoteapp.data.remote.ApiClient
import uz.datatalim.myownnoteapp.model.Note
import uz.datatalim.myownnoteapp.util.UiStateObject


class AddFragment : Fragment(R.layout.fragment_add) {
    lateinit var loading: LottieAnimationView
    lateinit var viewModel: AddViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(view: View) {

        setupViewModel()
        setupObserve()

        val color: String = getRandomColor()
        loading = view.findViewById(R.id.lav_loading)
        val llMain = view.findViewById<LinearLayout>(R.id.ll_main)
        val appBarLayout = view.findViewById<AppBarLayout>(R.id.app_bar_layout)
//        llMain.setBackgroundColor(Color.parseColor(color))
//        appBarLayout.setBackgroundColor(Color.parseColor(color))
        val llSave = view.findViewById<LinearLayout>(R.id.ll_save)
        val etTitle = view.findViewById<EditText>(R.id.et_title)
        val etDescription = view.findViewById<EditText>(R.id.et_description)

        llSave.setOnClickListener {
            Toast.makeText(requireContext(), "clickcksajclskjclkasj", Toast.LENGTH_SHORT).show()
            Log.d(1111.toString(), "")
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val note = Note(title = title, description = description, color = color)
            if (title.isNotEmpty() && description.isNotEmpty()) saveNote(note)
        }
    }

    private fun setupObserve() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModel.addNotes.collect{

                when(it){

                    is UiStateObject.LOADING->{

//                        show loading
                        loading.visibility=View.VISIBLE

                    }

                    is UiStateObject.SUCCESS->{

                        loading.visibility=View.GONE
                        findNavController().navigate(R.id.action_addFragment_to_homeFragment)

                    }

                    is UiStateObject.ERROR->{

//                        show Error
                        loading.visibility=View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    }

                    else -> {}
                }

            }

        }

    }

    private fun saveNote(note: Note) {

        viewModel.addNotes(note)

    }

    private fun setupViewModel() {

        viewModel=ViewModelProvider(this,AddFactory(AddRepository(ApiClient.apiService)))[AddViewModel::class.java]

    }

    private fun getRandomColor(): String {
        val rnds = (0..3).random()
        when (rnds) {
            0 -> {
                return "#FF9E9E"
            }

            1 -> {
                return "#91F48F"
            }

            2 -> {
                return "#FFF599"
            }

            else -> {
                return "#B69CFF"
            }
        }
    }
}
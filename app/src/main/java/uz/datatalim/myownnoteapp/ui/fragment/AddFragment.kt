package uz.datatalim.myownnoteapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.appbar.AppBarLayout
import uz.datatalim.myownnoteapp.R
import uz.datatalim.myownnoteapp.ViewModel.Home.AddViewModel
import uz.datatalim.myownnoteapp.model.Note


class AddFragment : Fragment(R.layout.fragment_add) {
    lateinit var loading: LottieAnimationView
    lateinit var viewModel: AddViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        viewModel= ViewModelProvider(this)[AddViewModel::class.java]

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
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val note = Note(title = title, description = description, color = color)
            if (title.isNotEmpty() && description.isNotEmpty()) saveNote(note)
        }
    }

    private fun saveNote(note: Note) {

        viewModel.saveNote(note).observe(viewLifecycleOwner){

            findNavController().navigate(R.id.action_addFragment_to_homeFragment)

        }

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
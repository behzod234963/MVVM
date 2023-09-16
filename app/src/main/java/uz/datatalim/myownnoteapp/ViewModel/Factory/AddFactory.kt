package uz.datatalim.myownnoteapp.ViewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.datatalim.myownnoteapp.ViewModel.Add.AddViewModel
import uz.datatalim.myownnoteapp.ViewModel.Home.HomeViewModel
import uz.datatalim.myownnoteapp.data.Repositories.AddRepository
import java.lang.RuntimeException

class AddFactory(private val addRepository: AddRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddViewModel::class.java)){

            return AddViewModel(addRepository) as T

        }

        throw RuntimeException("Unknown error")

    }

}
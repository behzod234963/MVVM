package uz.datatalim.myownnoteapp.ViewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.datatalim.myownnoteapp.ViewModel.Home.HomeViewModel
import uz.datatalim.myownnoteapp.data.Repositories.HomeRepository
import java.lang.RuntimeException

class HomeFactory(private val repository: HomeRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){

            return HomeViewModel(repository) as T

        }

        throw RuntimeException("Unknown Model class")

    }

}
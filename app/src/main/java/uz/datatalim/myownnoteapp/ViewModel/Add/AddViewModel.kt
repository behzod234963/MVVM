package uz.datatalim.myownnoteapp.ViewModel.Add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myownnoteapp.data.Repositories.AddRepository
import uz.datatalim.myownnoteapp.data.Repositories.HomeRepository
import uz.datatalim.myownnoteapp.data.remote.ApiClient
import uz.datatalim.myownnoteapp.model.Note
import uz.datatalim.myownnoteapp.util.UiStateObject

class AddViewModel (private val addRepository: AddRepository):ViewModel(){

    private val _addNotes=MutableStateFlow<UiStateObject<Note>>(UiStateObject.EMPTY)
    val addNotes=_addNotes

    fun addNotes(note: Note): Job {

        return viewModelScope.launch {

            _addNotes.value=UiStateObject.LOADING

            try {

                val response=addRepository.saveNote(note)

                if (response.code()>=400){

                    _addNotes.value=UiStateObject.ERROR(response.code().toString())

                }else{

                    _addNotes.value=UiStateObject.SUCCESS(response.body()!!)

                }

            }catch (e:Exception){

                _addNotes.value=UiStateObject.ERROR("No Internet")

            }

        }

    }

}
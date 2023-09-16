package uz.datatalim.myownnoteapp.ViewModel.Home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.datatalim.myownnoteapp.data.Repositories.HomeRepository
import uz.datatalim.myownnoteapp.model.Note
import uz.datatalim.myownnoteapp.util.UiStateList

class HomeViewModel(private val repository: HomeRepository):ViewModel() {




    private val _notes= MutableStateFlow<UiStateList<Note>>(UiStateList.EMPTY)
    val notes=_notes

    fun getNotes()=viewModelScope.launch {

        _notes.value=UiStateList.LOADING

        try {

            val response=repository.getAllNotes()

            if (response.code()>=400){

                _notes.value=UiStateList.ERROR(response.code().toString())

            }else{

                _notes.value=UiStateList.SUCCESS(response.body()!!)

            }

        }catch (e:Exception){

            _notes.value=UiStateList.ERROR("No Internet")

        }

    }



























//    val allNotes=MutableLiveData<ArrayList<Note>>()
//    val deleteNote=MutableLiveData<Note>()





//    fun apiGetAll():LiveData<ArrayList<Note>>{
//
//        ApiClient.apiService.getAllNotes().enqueue(object :
//            Callback<ArrayList<uz.datatalim.myownnoteapp.model.Note>> {
//            override fun onResponse(
//                call: Call<ArrayList<Note>>,
//                response: Response<ArrayList<Note>>
//
//            ) {
//
//                if (response.isSuccessful){
//
//                    allNotes.value=response.body()
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<ArrayList<uz.datatalim.myownnoteapp.model.Note>>, t: Throwable) {
//
//                allNotes.value=ArrayList()
//
//            }
//        })
//
//        return allNotes
//
//    }
//
//    suspend fun deleteNote(id:String):LiveData<Note>{
//
//        ApiClient.apiService.deleteNote(id).enqueue(object :Callback<Note>{
//            override fun onResponse(call: Call<Note>, response: Response<Note>) {
//
//                if (response.isSuccessful){
//
//                    deleteNote.value=response.body()
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<Note>, t: Throwable) {
//
//                deleteNote.value=Note()
//
//            }
//        })
//
//        return deleteNote
//
//    }

}
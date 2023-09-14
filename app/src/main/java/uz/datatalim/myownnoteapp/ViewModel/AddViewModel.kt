package uz.datatalim.myownnoteapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myownnoteapp.R
import uz.datatalim.myownnoteapp.data.remote.ApiClient
import uz.datatalim.myownnoteapp.model.Note
import uz.datatalim.myownnoteapp.util.Extensions.hide

class AddViewModel :ViewModel(){

    val addNotes=MutableLiveData<Note>()

    fun saveNote(note:Note):LiveData<Note>{

        ApiClient.apiService.saveNote(note).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                //hideLoading
                if (response.isSuccessful) {

                    addNotes.value=response.body()

                }
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {

                addNotes.value=Note()

            }
        })

        return addNotes

    }

}
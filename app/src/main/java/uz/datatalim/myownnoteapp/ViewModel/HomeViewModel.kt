package uz.datatalim.myownnoteapp.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myownnoteapp.data.remote.ApiClient
import uz.datatalim.myownnoteapp.model.Note

class HomeViewModel:ViewModel() {

    val allNotes=MutableLiveData<ArrayList<Note>>()

    fun apiGetAll():LiveData<ArrayList<Note>>{

        ApiClient.apiService.getAllNotes().enqueue(object :
            Callback<ArrayList<uz.datatalim.myownnoteapp.model.Note>> {
            override fun onResponse(
                call: Call<ArrayList<Note>>,
                response: Response<ArrayList<Note>>

            ) {

                if (response.isSuccessful){

                    allNotes.value=response.body()

                }

            }

            override fun onFailure(call: Call<ArrayList<uz.datatalim.myownnoteapp.model.Note>>, t: Throwable) {

            }
        })

    }

}
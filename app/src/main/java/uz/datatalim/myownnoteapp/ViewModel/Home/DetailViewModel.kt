package uz.datatalim.myownnoteapp.ViewModel.Home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.datatalim.myownnoteapp.data.remote.ApiClient

class DetailViewModel: ViewModel() {

    val detailNotes=MutableLiveData<uz.datatalim.myownnoteapp.model.Note>()

//    fun detailtsNote(id:String): MutableLiveData<uz.datatalim.myownnoteapp.model.Note> {
//
//        ApiClient.apiService.getNoteById(id).enqueue(object : Callback<uz.datatalim.myownnoteapp.model.Note> {
//            override fun onResponse(
//                call: Call<uz.datatalim.myownnoteapp.model.Note>,
//                response: Response<uz.datatalim.myownnoteapp.model.Note>
//            ) {
//
//                if (response.isSuccessful){
//
//                    detailNotes.value=response.body()
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<uz.datatalim.myownnoteapp.model.Note>, t: Throwable) {
//
//                detailNotes.value=uz.datatalim.myownnoteapp.model.Note()
//
//            }
//
//
//        })
//
//        return detailNotes
//
//    }

}
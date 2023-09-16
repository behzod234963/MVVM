package uz.datatalim.myownnoteapp.data.Repositories

import uz.datatalim.myownnoteapp.data.remote.ApiService

class HomeRepository (private val apiService: ApiService){



//    Remote
    suspend fun getAllNotes()=apiService.getAllNotes()

    suspend fun deleteNote(id:String)=apiService.deleteNote(id)

}
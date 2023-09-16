package uz.datatalim.myownnoteapp.data.Repositories

import uz.datatalim.myownnoteapp.data.remote.ApiService
import uz.datatalim.myownnoteapp.model.Note

class AddRepository(val apiServis: ApiService) {

    suspend fun saveNote(note: Note) = apiServis.saveNote(note)

}
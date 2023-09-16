package uz.datatalim.myownnoteapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.datatalim.myownnoteapp.model.Note

interface ApiService {

    @GET("notes")
    suspend fun getAllNotes(): Response<ArrayList<Note>>

    @GET("notes/{id}")
    suspend fun getNoteById(@Path("id") id: String):Response< Note>

    @POST("notes")
    suspend fun saveNote(@Body note: Note):Response<Note>

    @PUT("notes/{id}")
    suspend fun editNote(
        @Path("id") id: String,
        @Body note: Note
    ): Response<Note>

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id: String): Note


}
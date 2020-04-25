package app.anuj.cameracvinfo.DB.Rooms

import androidx.room.*
import app.anuj.cameracvinfo.DB.ResponseDataEntity


@Dao
interface ImgDao {

    @Insert
    suspend fun addNote(note: ResponseDataEntity)

    @Query("SELECT * FROM ResponseDataEntity ORDER BY  img_date DESC")
    suspend fun getAllNotes() : List<ResponseDataEntity>
    @Query("SELECT DISTINCT img_date FROM ResponseDataEntity ORDER BY  img_date DESC")
    suspend fun getDistinctNotes() : List<String>
    @Query("SELECT * FROM ResponseDataEntity WHERE img_date = :dateval")
    fun getdataById(dateval: String): List<ResponseDataEntity>


}
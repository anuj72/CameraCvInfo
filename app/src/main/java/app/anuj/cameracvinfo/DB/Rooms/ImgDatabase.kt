package app.anuj.cameracvinfo.DB.Rooms

import android.content.Context
import androidx.room.*
import app.anuj.cameracvinfo.DB.ResponseDataEntity


@Database(
    entities = [ResponseDataEntity::class],
    version = 1
)

abstract class ImgDatabase : RoomDatabase(){

    abstract fun getNoteDao() : ImgDao
    companion object {

        @Volatile private var instance : ImgDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                ).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ImgDatabase::class.java,
            "notedatabase"
        ).build()

    }
}
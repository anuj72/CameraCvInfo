package app.anuj.cameracvinfo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.anuj.cameracvinfo.Adapter.adapter1
import app.anuj.cameracvinfo.DB.ResponseDataEntity
import app.anuj.cameracvinfo.DB.Rooms.ImgDatabase
import app.anuj.cameracvinfo.DB.Util.BaseFragment
import kotlinx.android.synthetic.main.activity_view_img_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

class ViewImgList : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_img_list)


        var data= mutableListOf<ResponseDataEntity>()
        var url=ArrayList<String>()
        var title=ArrayList<String>()
        var desc=ArrayList<String>()
        var data1=
        launch {
            applicationContext?.let {
                var datares1= getval2()

               var  datares2=getval1()
                Log.v("kkkkkkkk",datares2.toString())
                var jArray = JSONArray()





                rv1?.layoutManager= LinearLayoutManager(applicationContext)
                rv1?.adapter=adapter1(datares1,datares2,applicationContext)
                progressBar.visibility=View.INVISIBLE
            }}




    }

    suspend fun getval(i: String): List<ResponseDataEntity> {
        return withContext(Dispatchers.IO) {
            var datares2 = ImgDatabase(applicationContext).getNoteDao().getdataById(i)
            return@withContext datares2
        }
    }

    suspend fun getval1():  List<ResponseDataEntity> {
        return withContext(Dispatchers.IO) {
            var datares= ImgDatabase(applicationContext).getNoteDao().getAllNotes()
            return@withContext datares
        }
    }
    suspend fun getval2():  List<String>{
        return withContext(Dispatchers.IO) {
            var datares= ImgDatabase(applicationContext).getNoteDao().getDistinctNotes()
            return@withContext datares
        }
    }

}

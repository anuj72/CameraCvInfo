package app.anuj.cameracvinfo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import app.anuj.cameracvinfo.DB.ResponseDataEntity
import app.anuj.cameracvinfo.DB.Rooms.ImgDao
import app.anuj.cameracvinfo.DB.Rooms.ImgDatabase
import app.anuj.cameracvinfo.DB.Util.BaseFragment
import com.afollestad.materialdialogs.MaterialDialog
import com.google.gson.Gson
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import com.squareup.picasso.Picasso
import com.vlk.multimager.activities.GalleryActivity
import com.vlk.multimager.utils.Constants
import com.vlk.multimager.utils.Image
import com.vlk.multimager.utils.Params
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun pickImg(view: View) {
        val intent = Intent(this, GalleryActivity::class.java)
        val params = Params()
        params.setCaptureLimit(10)
        params.setPickerLimit(10)
        params.setToolbarColor(111111)
        params.setActionButtonColor(1111111)
        params.setButtonTextColor(1111111)
        intent.putExtra(Constants.KEY_PARAMS, params)
        startActivityForResult(intent, Constants.TYPE_MULTI_PICKER)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            Constants.TYPE_MULTI_CAPTURE ->{
                val imagesList =
                    intent!!.getParcelableArrayListExtra<Parcelable>(Constants.KEY_BUNDLE_LIST)

                Log.v("sssss", imagesList[0].describeContents().toString())
                var entity: Image = imagesList[0] as Image
                var imv=entity.imagePath
                Log.v("Pppppppp",imv)
            }

            Constants.TYPE_MULTI_PICKER ->{
                val imagesList =
                    intent!!.getParcelableArrayListExtra<Parcelable>(Constants.KEY_BUNDLE_LIST)
                Log.v("sssss",imagesList.toString())
                var pathlist =ArrayList<String>()
                var datelist =ArrayList<String>()
                var data =ArrayList<Int>()
                var jsonlist =ArrayList<String>()
                for(i in imagesList){
                    var entity: Image = i as Image
                    pathlist.add(entity.imagePath)
                    val file = File(entity.imagePath)
                    val lastModifiedDate = Date(file.lastModified())
                      var  sdf =  SimpleDateFormat("dd/MM/yyyy");
       var currentDateandTime = sdf.format( Date());

                    var dateFormat =  SimpleDateFormat("dd/MM/yyyy")
                    Log.v("pppp","Last Modified Date : " + dateFormat.format(lastModifiedDate))
                    datelist.add( currentDateandTime)
                    data.add(lastModifiedDate.date)

                }
       var datajson= Gson().toJson(pathlist)

                Log.v("sssss",pathlist.toString())
                Log.v("sssss",datelist.toString())
                Log.v("xxxx",datajson.toString())


                val jResult = JSONObject() // main object

              // /ItemDetail jsonArray


                for (i in 0 until pathlist.size) {
                    val jGroup = JSONObject() // /sub Object
                    val jArray = JSONArray()
                    try {
                        jGroup.put("path", pathlist.get(i))
                        jGroup.put("date", datelist.get(i))
                        jGroup.put("data", data.get(i))
                        // /itemDetail Name is JsonArray Name
                        jResult.put("itemDetail", jGroup)
                        Log.v("yyyyyyy",jResult.toString())
                        var response= Gson().fromJson(jResult.toString(),ResponseDataEntity::class.java)
                        Log.v("ppppp",response.toString())
                        launch {
                            applicationContext?.let {
                                ImgDatabase(applicationContext).getNoteDao().addNote(response)
                                Toast.makeText(applicationContext,"responseSaved", Toast.LENGTH_SHORT).show()
                                var datares=ImgDatabase(applicationContext).getNoteDao().getAllNotes()
                                tv.setText(datares.toString())

                            }}
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                var entity: Image = imagesList[0] as Image
                var imv=entity.imagePath
                Log.v("Pppppppp",imv)
                Picasso.with(applicationContext)
                    .load(entity.uri)
                    .placeholder(android.R.drawable.ic_menu_camera)
                    .error(android.R.drawable.ic_menu_camera)
                    .resize(1200 , 1200 as Int)
                    .onlyScaleDown()
                    .centerInside()
                    .into(imageView2)

            }

        }
    }

    fun savedImg(view: View) {
        methodRequiresPermissions1()




    }

    private fun methodRequiresPermissions1() = runWithPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, options = quickPermissionsOption) {
      var i =Intent(applicationContext,ViewImgList::class.java)
        startActivity(i)
    }
    val quickPermissionsOption = QuickPermissionsOptions(
        handleRationale = false,
        permanentlyDeniedMessage = "Custom permanently denied message",
        rationaleMethod = {  Toast.makeText(this, "permissions granted", Toast.LENGTH_LONG).show()  },
        permanentDeniedMethod = {
            MaterialDialog(this).show {
                title(R.string.PermissionHead)
                message(R.string.PermissionBody)
                icon(R.drawable.ic_camera)
                positiveButton(text = "Open Setting"){
                    startActivity( Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", getPackageName(), null)) )}}})

}

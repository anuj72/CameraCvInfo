package app.anuj.cameracvinfo

import android.media.ExifInterface
import android.util.Log
import java.io.File
import java.util.*

class timeGetter(filePath:String) {
    var file: File = File(filePath)
   var path=filePath
    //Extra check, Just to validate the given path
    fun getdate():String {
        var intf: ExifInterface? = null
        try {
            intf = ExifInterface(path)

                val dateString = intf.getAttribute(ExifInterface.TAG_DATETIME)
                Log.v(
                    "PPPPP",
                    "Dated : $dateString"
                ) //Dispaly dateString. You can do/use it your own way
                return dateString


        } catch (e: Exception) {
            return ""
        }

    }
}
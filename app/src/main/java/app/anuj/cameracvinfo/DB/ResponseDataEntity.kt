package app.anuj.cameracvinfo.DB


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class ResponseDataEntity(
    @Embedded(prefix = "img_")
     var itemDetail: ItemDetail?
):Serializable {
    @Keep
    data class ItemDetail(
        @SerializedName("date") var date: String?,
        @SerializedName("path") var path: String?,
        @SerializedName("data") var data: Int?
    )

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
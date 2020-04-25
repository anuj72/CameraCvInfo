package app.anuj.cameracvinfo.Adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.anuj.cameracvinfo.DB.ResponseDataEntity
import app.anuj.cameracvinfo.DB.Rooms.ImgDatabase
import app.anuj.cameracvinfo.DB.Util.BaseFragment
import app.anuj.cameracvinfo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter2.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.CoroutineContext


class adapter2(
    val url: List<String>,val title: List<String>,val data: List<String>
    , val context: Context?
): RecyclerView.Adapter<ViewHolderUserGuide1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUserGuide1 {
        return ViewHolderUserGuide1(
            LayoutInflater.from(context).inflate(
                R.layout.adapter2,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return url.size
       }

    override fun onBindViewHolder(holder: ViewHolderUserGuide1, position: Int) {

        Picasso.with(context).load( File(url[position])) .placeholder(R.drawable.ic_camera)
            .error(R.drawable.ic_camera)
            .resize(200, 200).into(holder.iv7);

        holder.tv3.text=url[position]

    }


}


class ViewHolderUserGuide1(view: View) : RecyclerView.ViewHolder(view) {
    var iv7=view.imageView7
     var tv3=view.textView3

}


package app.anuj.cameracvinfo.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.anuj.cameracvinfo.DB.ResponseDataEntity
import app.anuj.cameracvinfo.DB.Rooms.ImgDatabase
import app.anuj.cameracvinfo.DB.Util.BaseFragment
import app.anuj.cameracvinfo.R
import kotlinx.android.synthetic.main.adapter1.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class adapter1(
    val data: List<String>,val data1:List<ResponseDataEntity>
    , val context: Context?
): RecyclerView.Adapter<ViewHolderUserGuide>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUserGuide {
        return ViewHolderUserGuide(
            LayoutInflater.from(context).inflate(
                R.layout.adapter1,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
       }

    override fun onBindViewHolder(holder: ViewHolderUserGuide, position: Int) {
        holder.tv.text=data[position]
        var url=ArrayList<String>()
        var title=ArrayList<String>()
        var desc=ArrayList<String>()
        var dataval=data[position]
       for(i in data1){
          // Log.v("zzzz",data1.toString())
           if(i.itemDetail!!.date!!.equals(dataval.trim())){
               url.add(i.itemDetail!!.path.toString())
               title.add(i.itemDetail!!.date.toString())
               desc.add(i.itemDetail!!.data.toString())
           }


       }
        if(url.size>6){
            holder.img4.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp)
           holder.img4.visibility=View.VISIBLE
        }

        Log.v("zzzz",url.toString())
        Log.v("zzzz",url.size.toString())
        holder.tv2.text=url.size.toString()
        holder.rvc?.layoutManager= GridLayoutManager(context,3)
        holder.rvc?.adapter=adapter2(url,title,desc,context!!)
        holder.img3.setImageResource(R.color.black)

    }




}


class ViewHolderUserGuide(view: View) : RecyclerView.ViewHolder(view) {

   var rvc=view.rvc
    var img3=view.imageView3
    var tv=view.textView
    var tv2=view.textView2
    var img4=view.imageView4


}


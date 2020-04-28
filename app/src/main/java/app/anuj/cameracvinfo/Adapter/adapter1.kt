package app.anuj.cameracvinfo.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.anuj.cameracvinfo.DB.ResponseDataEntity
import app.anuj.cameracvinfo.R
import kotlinx.android.synthetic.main.adapter1.view.*


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
        var furl=ArrayList<String>()
        var ftitle=ArrayList<String>()
        var fdesc=ArrayList<String>()
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
               furl.add(i.itemDetail!!.path.toString())
               ftitle.add(i.itemDetail!!.date.toString())
               fdesc.add(i.itemDetail!!.data.toString())
           }


       }
        holder.tv2.text=url.size.toString()
        if(url.size>6){
            holder.img4.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp)
            holder.img4.visibility=View.VISIBLE
            var status=0
            var curl=ArrayList<String>()
            var ctitle=ArrayList<String>()
            var cdesc=ArrayList<String>()
            for(j in 0 until 6){
                curl.add(url[j])
                ctitle.add(title[j])
                cdesc.add(desc[j])
            }
            url.removeAll(url)
            title.removeAll(title)
            desc.removeAll(desc)
            url.addAll(curl)
            title.addAll(ctitle)
            desc.addAll(cdesc)

            holder.img4.setOnClickListener {
                if(status.equals(1)){
                    status=0
                    var curl=ArrayList<String>()
                    var ctitle=ArrayList<String>()
                    var cdesc=ArrayList<String>()
                    for(j in 0 until 6){
                        curl.add(url[j])
                        ctitle.add(title[j])
                        cdesc.add(desc[j])
                    }
                    url.removeAll(url)
                    title.removeAll(title)
                    desc.removeAll(desc)
                    url.addAll(curl)
                    title.addAll(ctitle)
                    desc.addAll(cdesc)

                }else{
                    status=1
                    url.removeAll(url)
                    title.removeAll(title)
                    desc.removeAll(desc)
                    url.addAll(furl)
                    title.addAll(ftitle)
                    desc.addAll(fdesc)

                }

               holder.rvc?.adapter!!.notifyDataSetChanged()
            }
        }else{

        }



        holder.rvc?.layoutManager= GridLayoutManager(context,3)
        holder.rvc?.adapter=adapter2(url,title,desc,context!!)
        holder.img3.setImageResource(R.color.black)

    }




}


class ViewHolderUserGuide(view: View) : RecyclerView.ViewHolder(view) {
    var cv=view.cv
   var rvc=view.rvc
    var img3=view.imageView3
    var tv=view.textView
    var tv2=view.textView2
    var img4=view.imageView4


}


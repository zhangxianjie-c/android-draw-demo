package priscilla.draw.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import priscilla.draw.R

/**
 * Created by xianjie on 2023年8月9日16:53:09
 *
 * Description:
 */
class MainListAdapter(private val customViewData: ArrayList<CustomViewManage.CustomViewData>) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {
    //简单的View缓存
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val viewList = HashMap<Int, View>()
        fun <T : View> getViewById(id: Int): T {
            return if (viewList[id] == null) {
                viewList[id] = view.findViewById<View>(id)
                view.findViewById<T>(id)
            } else viewList[id] as T
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list_main, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int = customViewData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Title
        holder.getViewById<TextView>(R.id.contentView).text = customViewData[position].name
        //ClickListener
        holder.getViewById<CardView>(R.id.clickView).setOnClickListener {
            itemClickListener?.invoke(customViewData[position].viewClass,customViewData[position].packet)
        }
    }

    fun setItemClickListener(itemClickListener:((String,String)->Unit)){
        this.itemClickListener = itemClickListener
    }

    private var itemClickListener:((String,String)->Unit)? = null
}
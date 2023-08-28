package priscilla.draw

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by xianjie on 2023年8月9日16:22:37
 *
 * Description:
 */
class CustomViewManage(private val context:Context) {
    data class CustomViewData(val id:Int,val name:String,val viewClass:String,val packet:String)

    private val customViewList = arrayListOf<CustomViewData>()

    fun getCustomViewData(): ArrayList<CustomViewData> = customViewList

    /**
     * 添加一个自定义View条目至数组
     * @param finishListener 加载完成回调
     */
    fun setCustomViewData(finishListener:()->Unit){
        try {
            val inputStream: InputStream = context.assets.open("viewdata.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let{
                    val data = it.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    customViewList.add(CustomViewData(data[0].toInt(),data[1],data[2],data[3]))
                }
            }
            reader.close()
            inputStream.close()
            finishListener.invoke()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
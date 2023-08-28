package priscilla.draw.base

import android.content.Context
import androidx.core.text.isDigitsOnly
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by xianjie on 2023年8月9日16:22:37
 *
 * Description:View信息解析及管理
 */
class CustomViewManage(private val context:Context) {
    //实体
    data class CustomViewData(val id:Int,val name:String,val viewClass:String,val packet:String)

    //容器
    private val customViewList = arrayListOf<CustomViewData>()

    //获取数据集合
    fun getCustomViewData(): ArrayList<CustomViewData> = customViewList

    /**
     * 解析viewdata.csv中的数据至customViewList中
     * @param finishListener 解析完成回调
     */
    fun setCustomViewData(finishListener:()->Unit){
        try {
            //输入流
            val inputStream: InputStream = context.assets.open("viewdata.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let{
                    //字符切割
                    val data = it.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    //符合格式时加入customViewList
                    if (data.size == 4 && data[0].isDigitsOnly()) customViewList.add(CustomViewData(data[0].toInt(),data[1],data[2],data[3]))
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
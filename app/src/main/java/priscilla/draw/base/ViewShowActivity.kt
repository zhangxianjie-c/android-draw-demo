package priscilla.draw.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.lang.reflect.InvocationTargetException

class ViewShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View类型
        val viewClass = intent.extras?.getString("viewClass","")
        //View包名
        val packet = intent.extras?.getString("packet","")
        if (!viewClass.isNullOrBlank() && !packet.isNullOrBlank()) {
            try {
                //获取对应类型
                val clazz = Class.forName("${packet}.${viewClass}")
                //获取构造
                val constructor = clazz.getDeclaredConstructor(Context::class.java)
                //可访问
                constructor.isAccessible = true
                //创建实例
                val view = constructor.newInstance(this) as View
                //添加至页面
                val window = window.decorView as ViewGroup
                window.addView(view)
                //尝试调用一次名为initialize的方法
                val method = clazz.getDeclaredMethod("initialize")
                method.invoke(view)
            }catch (e:ClassNotFoundException){
                e.printStackTrace()
                Toast.makeText(this, "匹配指定View失败", Toast.LENGTH_SHORT).show()
                finishAfterTransition()
            }catch (e: InstantiationException){
                e.printStackTrace()
                Toast.makeText(this, "View实例创建失败", Toast.LENGTH_SHORT).show()
                finishAfterTransition()
            }catch (e:NoSuchMethodException){
                e.printStackTrace()
            }catch (e:IllegalAccessException){
                e.printStackTrace()
            }catch (e: InvocationTargetException){
                e.printStackTrace()
            }
        }
    }
}
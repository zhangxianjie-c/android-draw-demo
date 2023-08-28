package priscilla.draw

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class ViewShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewClass = intent.extras?.getString("viewClass","")
        val packet = intent.extras?.getString("packet","")
        if (!viewClass.isNullOrBlank() && !packet.isNullOrBlank()) {
            try {
                val clazz = Class.forName("${packet}.${viewClass}")
                val constructor = clazz.getDeclaredConstructor(Context::class.java)
                constructor.isAccessible = true
                val view = constructor.newInstance(this) as View
                val window = window.decorView as ViewGroup
                window.addView(view)
            }catch (e:ClassNotFoundException){
                Toast.makeText(this, "匹配指定View失败", Toast.LENGTH_SHORT).show()
                finishAfterTransition()
            }
        }
    }
}
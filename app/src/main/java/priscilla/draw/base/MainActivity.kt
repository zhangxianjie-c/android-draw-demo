package priscilla.draw.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import priscilla.draw.R

class MainActivity : AppCompatActivity() {
    private val customViewManage by lazy {
        CustomViewManage(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //解析viewdata
        customViewManage.setCustomViewData {
            //配置RecyclerView
            findViewById<RecyclerView>(R.id.recyclerView).apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainListAdapter(customViewManage.getCustomViewData()).apply {
                    //点击回调
                    setItemClickListener{ viewClass,packet ->
                        startActivity(Intent(this@MainActivity, ViewShowActivity::class.java).apply {
                            putExtras(Bundle().apply {
                                putString("viewClass",viewClass)
                                putString("packet",packet)
                            })
                        })
                    }
                }
            }
        }
    }
}
package priscilla.draw

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import priscilla.draw.view.DrawView

class MainActivity : AppCompatActivity() {
    private val customViewManage by lazy {
        CustomViewManage(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customViewManage.setCustomViewData {
            findViewById<RecyclerView>(R.id.recyclerView).apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainListAdapter(customViewManage.getCustomViewData()).apply {
                    setItemClickListener{ viewClass,packet ->
                        startActivity(Intent(this@MainActivity,ViewShowActivity::class.java).apply {
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
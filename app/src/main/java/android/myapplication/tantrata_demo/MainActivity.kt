package android.myapplication.tantrata_demo

import CustomAdapter
import android.icu.lang.UCharacter.VerticalOrientation
import android.myapplication.tantrata_demo.ModelClass.Data
import android.myapplication.tantrata_demo.ModelClass.DataX
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private var dataX: MutableList<DataX> = ArrayList()
    lateinit var adapter: CustomAdapter
    private var layoutManager: GridLayoutManager? = null
    lateinit var recyclerview : RecyclerView
    var count : Int = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // launching a new coroutin

        getViral()
        var edittext : EditText= findViewById(R.id.edittext)
        var search :ImageButton = findViewById(R.id.search)
        search.setOnClickListener(View.OnClickListener {
            val query: String = edittext.text.toString().trim().lowercase()
            getmyData(query)
        })

       // Log.d("Size of Arraylist", dataX.size.toString())
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.change_layout -> {
                if (layoutManager?.spanCount == 1) {
                    layoutManager?.spanCount = 3
                    item.title = "list"
                } else {
                    layoutManager?.spanCount = 1
                    item.title = "grid"
                }
                adapter?.notifyItemRangeChanged(0, adapter?.itemCount ?: 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getViral (){
        val Api = RetrofitHelper.getInstance2().create(APIinterface::class.java)
        val retrofidata = Api.getviral()
        retrofidata.enqueue(object : retrofit2.Callback<Data?> {
            override fun onResponse(
                call: Call<Data?>,
                response: Response<Data?>
            ) {
                val responsebody = response.body()
                if (responsebody != null) {
                    Toast.makeText(this@MainActivity,responsebody.data.size.toString(), Toast.LENGTH_SHORT).show()
                }

                if (responsebody != null) {
                    recyclerview = findViewById<RecyclerView>(R.id.recyclerviewlist)
                    recyclerview.setHasFixedSize(true)

                    // this creates a vertical layout Manager
//                    if(count==1) {
//                        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
//                    }
//                    else {
//                        recyclerview.layoutManager = GridLayoutManager(this@MainActivity,3)
//                    }
                    layoutManager = GridLayoutManager(this@MainActivity, 3)
                    recyclerview.layoutManager = layoutManager
                    adapter = CustomAdapter(this@MainActivity, responsebody.data, layoutManager)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter
                    // Toast.makeText(this@MainActivity,responsebody.data.size.toString(), Toast.LENGTH_SHORT).show()

                    adapter.notifyDataSetChanged()
                    Log.d("Atharv", responsebody.toString())
                    Log.d("Size of Arraylist", dataX.size.toString())

                }
                if (responsebody != null) { Log.d("Atharv", responsebody.data.get(1).title)
                }
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
            }
        })
    }

    private fun getmyData (query : String){
        val Api = RetrofitHelper.getInstance().create(APIinterface::class.java)
        val retrofidata = Api.getData(query)
        retrofidata.enqueue(object : retrofit2.Callback<Data?> {
            override fun onResponse(
                call: Call<Data?>,
                response: Response<Data?>
            ) {
                val responsebody = response.body()
                if (responsebody != null) {
                    Toast.makeText(this@MainActivity,responsebody.data.size.toString(), Toast.LENGTH_SHORT).show()
                }

                if (responsebody != null) {
                    recyclerview = findViewById<RecyclerView>(R.id.recyclerviewlist)
                    recyclerview.setHasFixedSize(true)

                    // this creates a vertical layout Manager
//                    if(count==1) {
//                        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
//                    }
//                    else {
//                        recyclerview.layoutManager = GridLayoutManager(this@MainActivity,3)
//                    }
                    layoutManager = GridLayoutManager(this@MainActivity, 3)
                    recyclerview.layoutManager = layoutManager
                    adapter = CustomAdapter(this@MainActivity, responsebody.data,layoutManager)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter
                   // Toast.makeText(this@MainActivity,responsebody.data.size.toString(), Toast.LENGTH_SHORT).show()

                    adapter.notifyDataSetChanged()
                    Log.d("Atharv", responsebody.data.get(1).title)
                    Log.d("Size of Arraylist", dataX.size.toString())

                }
                if (responsebody != null) { Log.d("Atharv", responsebody.data.get(1).title)
                }
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
            }
        })
    }
    }
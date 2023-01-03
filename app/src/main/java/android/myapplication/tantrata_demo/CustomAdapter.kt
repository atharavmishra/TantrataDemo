import android.content.Context
import android.myapplication.tantrata_demo.ModelClass.DataX
import android.myapplication.tantrata_demo.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class CustomAdapter(val context : Context, private val dataSet: List<DataX>, private val layoutManager: GridLayoutManager? = null) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageview: ImageView
        val textview : TextView
        val time : TextView
        val number : TextView
        init {
            // Define click listener for the ViewHolder's View
            imageview = view.findViewById(R.id.image)
            textview = view.findViewById(R.id.heading)
            time = view.findViewById(R.id.time)
            number = view.findViewById(R.id.number)
        }
    }
    enum class ViewType {
        SMALL,
        DETAILED
    }

    // Creates new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Creates a new view, which defines the UI of the list item
        return when (viewType){
            ViewType.DETAILED.ordinal -> ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recyclerview, viewGroup, false))
            else -> ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.grid_recycler_view, viewGroup, false))
        }


    }
    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.DETAILED.ordinal
        else ViewType.SMALL.ordinal
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textview.text = dataSet[position].title.toString()
        if(dataSet[position].images!=null) {
            Glide.with(context).load(dataSet[position].images[0].link).into(viewHolder.imageview)
        }
        viewHolder.time.text=SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(dataSet[position].datetime.toLong())).toString()
        if(dataSet[position].images!=null) {
            viewHolder.number.text =
                "\nImages from this post=" + dataSet[position].images.size.toString()
        }
        else{
            viewHolder.number.text = "couldn't get"
        }
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(dataSet[position].datetime.toLong() * 1000)
            val sdf2 = SimpleDateFormat("hh:mm a")
            val netDate2 = Date(dataSet[position].datetime.toLong() * 1000)
            viewHolder.time.text = "\nPosted " + sdf.format(netDate) +" "+ sdf2.format(netDate2)
        } catch (e: Exception) {
            Log.d("customadapter",e.toString())

        }
        Log.d("customadapter",dataSet[position].title)

        // Gets element from your dataset at this position and replace the
        // contents of the view with that element
    }

    // Returns the size dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
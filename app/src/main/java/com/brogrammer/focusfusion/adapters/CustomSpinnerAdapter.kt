import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.brogrammer.focusfusion.model.TaskModel

class CustomSpinnerAdapter(
    context: Context,
    private val resource: Int,
    private val textViewResourceId: Int,
    private val objects: List<TaskModel>
) : ArrayAdapter<TaskModel>(context, resource, textViewResourceId, objects) {

    override fun getCount(): Int {
        // Adjust the count to include all items
        return super.getCount()
    }

    override fun getItem(position: Int): TaskModel? {
        // Adjust the getItem to avoid ArrayIndexOutOfBoundsException
        return super.getItem(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(resource, parent, false)

        // Get the TextView and set the text
        val taskNameTextView = view.findViewById<TextView>(textViewResourceId)
        taskNameTextView.text = objects[position].taskName ?: ""

        return view
    }
}

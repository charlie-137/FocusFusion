import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.model.TaskModel

class CustomSpinnerAdapter(
    context: Context,
    resource: Int,
    textViewResourceId: Int,
    objects: List<TaskModel>
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
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.task_dropdown_item, parent, false)

        // Get the TextView and set the text
        val taskName = view.findViewById<TextView>(R.id.textViewItem)


//        val dailyGoal = view.findViewById<TextView>(R.id.dailyGoal)

        val itemModel = getItem(position)
        taskName.text = itemModel?.taskName
//        textView.text = "${itemModel?.empName} - ${itemModel?.empCode}"

        // Set the visibility of the divider based on the position
        val dividerView = view.findViewById<View>(R.id.dividerView)
        dividerView.visibility = if (position == count - 1) View.INVISIBLE else View.VISIBLE

        return view
    }
}

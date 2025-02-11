package edu.ddukk.ddukkattendance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import edu.ddukk.ddukkattendance.R
import edu.ddukk.ddukkattendance.models.Breaks

class BreaksAdapter(val list: List<Breaks>) : RecyclerView
.Adapter<BreaksAdapter
.ViewHolder>() {

    private lateinit var parent: ViewGroup

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView =
            itemView.findViewById(R.id.tvID_Breaks)
        val tvType: TextView = itemView.findViewById(R.id.tvType_Breaks)
        val tvStart: TextView = itemView.findViewById(R.id.tvStart_Breaks)
        val tvEnd: TextView = itemView.findViewById(R.id.tvEnd_Breaks)
        val iBDelete: ImageButton = itemView.findViewById(R.id.ibDeleteBreaks)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        this.parent = parent
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout
                .breaks_card_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvId.text = item.breakId.toString()
        holder.tvType.text = item.breakType
        holder.tvStart.text = item.breakStartTime
        holder.tvEnd.text = item.breakEndTime
        holder.iBDelete.setOnClickListener {
            Toast.makeText(
                parent.context, "Deleted" + item.breakId, Toast
                    .LENGTH_LONG
            ).show()
        }
    }
}
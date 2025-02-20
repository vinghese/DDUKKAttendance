package edu.ddukk.ddukkattendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ddukk.ddukkattendance.adapters.RecyclerAdapter
import edu.ddukk.ddukkattendance.dao.TimeTableViewDAO
import edu.ddukk.ddukkattendance.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TimetableFragment : Fragment() {

    lateinit var spinnerItemSelected: String

    val timeTableViewDAO: TimeTableViewDAO by lazy {
        AppDatabase.getDatabase(requireContext()).timetableViewDAO()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView =
            view.findViewById(R.id.recycleTimetable)

        recyclerView.layoutManager =
            GridLayoutManager(view.context, 2)

        lifecycleScope.launch {

            var adapter: RecyclerAdapter = RecyclerAdapter(emptyList())
            recyclerView.adapter = adapter

            adapter.isLoading = true
            adapter.notifyItemInserted(adapter.itemCount)

            val items = withContext(Dispatchers.IO) {
                delay(2000)
                timeTableViewDAO.getTimetableByDay("Monday")
            }

            adapter.isLoading = false
            adapter = RecyclerAdapter(items)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

        }


        val spinner: Spinner = view.findViewById(R.id.spinnerTimetable)

        val adapter = ArrayAdapter.createFromResource(
            view.context, R.array
                .week_days, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(
            android.R.layout
                .simple_spinner_dropdown_item
        )

        val spinnerItem = resources.getStringArray(R.array.week_days)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView
        .OnItemSelectedListener {
            override fun onItemSelected(
                parrent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerItemSelected = spinnerItem[position].toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }
}
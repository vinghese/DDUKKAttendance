package edu.ddukk.ddukkattendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment


class TimetableFragment : Fragment() {

    lateinit var spinnerItemSelected: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
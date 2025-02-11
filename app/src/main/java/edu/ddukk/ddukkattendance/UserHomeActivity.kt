package edu.ddukk.ddukkattendance

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ddukk.ddukkattendance.adapters.BreaksAdapter
import edu.ddukk.ddukkattendance.dao.BreaksDAO
import edu.ddukk.ddukkattendance.database.AppDatabase
import edu.ddukk.ddukkattendance.models.Breaks
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class UserHomeActivity : AppCompatActivity() {

    lateinit var breakType: String
    val etStart: EditText by lazy { findViewById(R.id.etTimeStartBreaks) }
    val etEnd: EditText by lazy { findViewById(R.id.etTimeEndBreaks) }
    val btnSave: Button by lazy { findViewById(R.id.btnBreaks_Save) }

    val db: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    val breaksDAO: BreaksDAO by lazy { db.breaksDAO() }

    val breaksCard: RecyclerView by lazy { findViewById(R.id.recycler_breaks) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        breaksCard.layoutManager = LinearLayoutManager(this)
        RefreshCard()

        var spinner: Spinner = findViewById(R.id.spinnerBreakType)

        ArrayAdapter.createFromResource(
            this, R.array.breaks_array, android.R
                .layout.simple_list_item_1
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }

        spinner.onItemSelectedListener = object : AdapterView
        .OnItemSelectedListener {
            override fun onItemSelected(
                parrent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                breakType = parrent?.getItemAtPosition(pos).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }

        etEnd.setOnClickListener {
            val cal = Calendar.getInstance()
            val timePickerListner: TimePickerDialog.OnTimeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    etEnd.setText(
                        SimpleDateFormat("hh:mm aa").format
                            (cal.time).toString()
                    )
                }
            val timePicker: TimePickerDialog = TimePickerDialog(
                this,
                timePickerListner,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                false
            )

            TimePickerDialog(
                this, timePickerListner, cal.get(
                    Calendar
                        .HOUR_OF_DAY
                ), cal.get(Calendar.MINUTE), false
            ).show()
        }

        etStart.setOnClickListener {

            val cal = Calendar.getInstance()
            val timePickerListner: TimePickerDialog.OnTimeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    etStart.setText(
                        SimpleDateFormat("hh:mm aa").format
                            (cal.time).toString()
                    )
                }
            val timePicker: TimePickerDialog = TimePickerDialog(
                this,
                timePickerListner,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                false
            )

            TimePickerDialog(
                this, timePickerListner, cal.get(
                    Calendar
                        .HOUR_OF_DAY
                ), cal.get(Calendar.MINUTE), false
            ).show()

        }

        btnSave.setOnClickListener {

            lifecycleScope.launch {
                val res = breaksDAO.insertBreaks(
                    Breaks(
                        0,
                        breakType,
                        etStart.text.toString(),
                        etEnd.text.toString()
                    )
                )
                RefreshCard()
                Log.d("Breaks Result  ***************", res.toString())

            }


        }

    }

    fun RefreshCard() = lifecycleScope.launch {

        val data = breaksDAO.getAllBreaks()
        val adapter = BreaksAdapter(data)
        breaksCard.adapter = adapter
        adapter.notifyDataSetChanged()

    }

}









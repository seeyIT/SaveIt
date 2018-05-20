package pl.kornelius.saveit

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    val myDataset: ArrayList<Expanse> = ArrayList()
    val viewAdapter: ExpanseAdapter = ExpanseAdapter(myDataset)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readExpanses()

        val viewManager: LinearLayoutManager = LinearLayoutManager(this)

        main_recycler_view.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        setupPermissions()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1)
        }
    }

    fun addExpanse(view: View) {
        expanse_entry_layout.visibility = View.VISIBLE

    }

    fun saveExpanse(view: View) {
        if (!File(Environment.getExternalStorageDirectory().absolutePath + "/SaveIt/expanses.txt").exists()) {
            File(Environment.getExternalStorageDirectory().absolutePath + "/SaveIt").mkdir()
        }

        File(Environment.getExternalStorageDirectory().absolutePath + "/SaveIt/expanses.txt").appendText(
                "${expanse_thing_input.text};${expanse_cost_input.text};${System.currentTimeMillis()}${java.lang.System.lineSeparator()}",
                Charset.defaultCharset())
        expanse_entry_layout.visibility = View.GONE
        myDataset.add(Expanse(expanse_thing_input.text.toString(), expanse_cost_input.text.toString(), System.currentTimeMillis()))
        expanse_thing_input.text.clear()
        expanse_cost_input.text.clear()

        viewAdapter.notifyDataSetChanged()

    }

    private fun readExpanses() {
        if (File(Environment.getExternalStorageDirectory().absolutePath + "/SaveIt/expanses.txt").exists()) {
            val bufferedReader = File(Environment.getExternalStorageDirectory().absolutePath + "/SaveIt/expanses.txt").bufferedReader()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    val data = it.split(";")
                    myDataset.add(Expanse(data[0], data[1], data[2].toLong()))
                }
            }

        }
    }
}

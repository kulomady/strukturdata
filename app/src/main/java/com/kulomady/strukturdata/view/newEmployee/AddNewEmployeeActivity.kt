package com.kulomady.strukturdata.view.newEmployee

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kulomady.strukturdata.R
import com.kulomady.strukturdata.model.DataPersistent
import com.kulomady.strukturdata.model.Employee
import com.kulomady.strukturdata.view.newDepartment.AddNewDepartmentActivity
import com.kulomady.strukturdata.view.viewModel.DepartmentViewModel
import kotlinx.android.synthetic.main.activity_add_new_employee.*

class AddNewEmployeeActivity : AppCompatActivity() {

    private val departmentList = mutableListOf<DepartmentViewModel>()
    private lateinit var dataPersistent: DataPersistent
    private lateinit var selectDepartmentViewModel: DepartmentViewModel
    private var isDepartmentSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_employee)

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_department -> {
                startActivity(Intent(this, AddNewDepartmentActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRestart() {
        super.onRestart()
        initSpinner()
    }

    fun initView() {

        initSpinner()

        add_employee.setOnClickListener { view ->
            if (isDepartmentSelected) {
                val employeId = id_employee.text.toString()
                val employeName = name_employee.text.toString()
                val employeeAddress = address_employee.text.toString()
                val departmentId = selectDepartmentViewModel.id
                dataPersistent.addNewEmployee(Employee(employeId, departmentId, employeName, employeeAddress))
                this.finish()
            } else {
                if (departmentList.isEmpty()) {
                    showSnackbarDepartmentEmpty(view)
                } else {
                    showSnackbarMustSelectDepartment(view)
                }
            }
        }

    }

    private fun showSnackbarDepartmentEmpty(view: View) {
        Snackbar.make(view, "You must create department first", Snackbar.LENGTH_LONG)
                .setAction("Add") {
                    Log.d("Test", "onClick: Action button was clicked")
                    startActivity(Intent(this, AddNewDepartmentActivity::class.java))
                }
                .show()
    }

    private fun showSnackbarMustSelectDepartment(view: View) {
        Snackbar.make(view, "You must set department name", Snackbar.LENGTH_LONG)
                .setAction("Add") {
                    Log.d("Test", "onClick: Action button was clicked")
                    startActivity(Intent(this, AddNewDepartmentActivity::class.java))
                }
                .show()
    }

    private fun initSpinner() {
        dataPersistent = DataPersistent()
        departmentList.clear()
        dataPersistent.readDepartmentFromCsv().mapTo(departmentList) { DepartmentViewModel(it.id, it.name) }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, departmentList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_department.adapter = adapter
        spinner_department.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
                isDepartmentSelected = false
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectDepartmentViewModel = departmentList.get(position)
                isDepartmentSelected = true
            }

        }
    }
}

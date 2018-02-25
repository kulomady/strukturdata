package com.kulomady.strukturdata.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kulomady.strukturdata.model.Employee
import com.kulomady.strukturdata.presenter.EmployeePresenter
import com.kulomady.strukturdata.R
import com.kulomady.strukturdata.model.DataPersistent
import com.kulomady.strukturdata.util.CheckPermissionUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    private var employess = mutableListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val parsingData = DataPersistent()
        initData(parsingData)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            parsingData.clearData()

        }
    }

    override fun onRestart() {
        super.onRestart()
        initData(DataPersistent())
    }


    private fun initData(dataPersistent: DataPersistent) {
        CheckPermissionUtil.checkWriteSdCard(this
        ) { success ->
            if (success) {
                startRequestingData(dataPersistent)
            } else {
                //
            }
        }
    }

    private fun startRequestingData(dataPersistent: DataPersistent) {
        // when first install data is empty so will make some initial value
        if (dataPersistent.isFirsInstall) {
            dataPersistent.createDefaultValue()
            Log.e(TAG, "Not Exist")
        } else {
            Log.e(TAG, "Exist")
        }

        val presenter = EmployeePresenter(dataPersistent)

        val employeeWithDepartment = ArrayList<Employee>()

        for (employee in presenter.employeeWithDepartmentName) {
            Log.i("Employee is  ", employee.toString())
            employeeWithDepartment.add(employee)
        }

        employess = presenter.bubbleSortEmployee(employeeWithDepartment, EmployeePresenter.SortBy.EMPLOYEE_NAME)

        for (employee in employess) {
            Log.e(TAG, employee.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_clear_Data -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}

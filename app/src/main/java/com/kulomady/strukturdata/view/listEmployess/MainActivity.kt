package com.kulomady.strukturdata.view.listEmployess

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kulomady.strukturdata.model.Employee
import com.kulomady.strukturdata.presenter.EmployeePresenter
import com.kulomady.strukturdata.R
import com.kulomady.strukturdata.model.DataPersistent
import com.kulomady.strukturdata.util.CheckPermissionUtil
import com.kulomady.strukturdata.view.BaseActivity
import com.kulomady.strukturdata.view.newEmployee.AddNewEmployeeActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : BaseActivity(), EmployeeView {
    private val TAG = "MainActivity"

    private var employess = mutableListOf<Employee>()
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var presenter: EmployeePresenter
    lateinit var dataPersistent: DataPersistent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initLayout()
         dataPersistent = DataPersistent()
        initData()
    }

    override fun onRestart() {
        super.onRestart()
        initData()
    }

    override fun showEmployess(employees: List<Employee>) {
       employeeAdapter.addEmployess(employees)
    }

    override fun showSortResult(employees: List<Employee>) {
        employeeAdapter.clearData()
        employeeAdapter.addEmployess(employees)
    }

    private fun initLayout() {
        employeeAdapter = EmployeeAdapter()
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        recyclerview.adapter = employeeAdapter

        fab.setOnClickListener {
            startActivity(Intent(this,AddNewEmployeeActivity::class.java))
        }
    }

    private fun initData() {
        CheckPermissionUtil.checkWriteSdCard(this
        ) { success ->
            if (success) {
                startInitializeData()
            }
        }
    }

    private fun startInitializeData() {
        // when first install data is empty so will make some initial value
        if (dataPersistent.isFirsInstall) {
            dataPersistent.createDefaultValue()
        }

        presenter = EmployeePresenter(dataPersistent, this)
        presenter.loadEmployeesWithDepartmentName()

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
        return when (item.itemId) {
            R.id.action_clear_Data -> {
                dataPersistent.clearData()
                employeeAdapter.clearData()
                employeeAdapter.notifyDataSetChanged()
                true
            }
            R.id.action_sort_by_employee_id -> {
                presenter.loadEmployessSortByEmployeeId()
                true
            }
            R.id.action_sort_by_employee_name -> {
                presenter.loadEmployessSortByEmployeeName()
                 true
            }
            R.id.action_sort_by_department_name-> {
                presenter.loadEmployessSortByDepartmentName()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

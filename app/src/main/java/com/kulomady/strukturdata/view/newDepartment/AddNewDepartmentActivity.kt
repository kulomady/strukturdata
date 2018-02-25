package com.kulomady.strukturdata.view.newDepartment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kulomady.strukturdata.R
import com.kulomady.strukturdata.model.DataPersistent
import com.kulomady.strukturdata.model.Department
import kotlinx.android.synthetic.main.activity_add_new_department.*

class AddNewDepartmentActivity : AppCompatActivity() {

    private lateinit var dataPersistent: DataPersistent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_department)
        dataPersistent = DataPersistent()
        initView()
    }

    fun initView() {
        add_department.setOnClickListener{
            val idDepartment = id_department.text.toString()
            val nameDepartment = name_department.text.toString()
            dataPersistent.addNewDepartment(Department(idDepartment,nameDepartment))
            this.finish()
        }
    }

}

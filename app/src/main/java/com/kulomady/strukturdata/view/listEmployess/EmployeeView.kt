package com.kulomady.strukturdata.view.listEmployess

import com.kulomady.strukturdata.model.Employee

/**
 * @author kulomady on 2/25/18.
 */


interface EmployeeView {

    fun showEmployess(employees: List<Employee>)
    fun showSortResult(employees: List<Employee>)
}
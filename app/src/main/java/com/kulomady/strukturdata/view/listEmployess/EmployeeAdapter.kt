package com.kulomady.strukturdata.view.listEmployess

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kulomady.strukturdata.R
import com.kulomady.strukturdata.model.Employee
import kotlinx.android.synthetic.main.layout_item_employee.view.*

/**
 * @author kulomady on 2/25/18.
 */

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employess = mutableListOf<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_item_employee, parent, false)

        return EmployeeViewHolder(view)

    }

    override fun getItemCount(): Int {
        return employess.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder?, position: Int) {
        holder?.bind(employess[position])

    }

    fun addEmployess(listEmployee: List<Employee>) {
        employess.clear()
        employess.addAll(listEmployee)
        notifyDataSetChanged()
    }

    fun clearData() {
        employess.clear()
    }


    class EmployeeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(employee: Employee) {
            itemView.tv_id.text = "Id : " + employee.id
            itemView.tv_name.text = "Name : " + employee.name
            itemView.tv_department.text = "Department: " + employee.departmentId
            itemView.tv_alamat.text = "Address : " + employee.alamat
        }

    }

}
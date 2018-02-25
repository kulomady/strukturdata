package com.kulomady.strukturdata.presenter;

import com.kulomady.strukturdata.model.DataPersistent;
import com.kulomady.strukturdata.model.Department;
import com.kulomady.strukturdata.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kulomady on 2/24/18.
 */

public class EmployeePresenter {

    public enum SortBy {
        EMPLOYEE_NAME, DEPARTMENT_NAME, EMPLOYEE_ID
    }

    private List<Department> departments = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();

    public EmployeePresenter(DataPersistent dataPersistent) {
        employees = dataPersistent.readEmployeeFromCsv();
        departments = dataPersistent.readDepartmentFromCsv();
    }

    public List<Employee> getEmployeeWithDepartmentName() {
        List<Employee> result = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employee.setDepartmentId(getDepartmentNameById(employee.getDepartmentId()));
            result.add(employee);
        }
        return result;
    }

    public List<Employee> bubbleSortEmployee(List<Employee> list, SortBy sortBy) {

        for(int i = list.size()-1; i >= 0; i--) {
            for(int j = 0; j < i; j++) {

                String firstValue;
                String secondValue;
                if(sortBy == SortBy.EMPLOYEE_NAME){
                    firstValue = list.get(j).getName();
                    secondValue = list.get(j + 1).getName();
                } else if (sortBy == SortBy.DEPARTMENT_NAME) {
                    firstValue = list.get(j).getDepartmentId();
                    secondValue = list.get(j + 1).getDepartmentId();
                } else {
                    firstValue = list.get(j).getId();
                    secondValue = list.get(j + 1).getId();
                }

                if(firstValue.compareTo(secondValue) > -1 ) {
                    Employee temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        return list;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    private Department createDepartment(String id , String name ){
        return new Department(id, name);
    }

    private Employee createEmployee(String id,String departmentId, String name, String address) {
        return new Employee(id,departmentId, name, address);
    }

    private String getDepartmentNameById(String id) {
        boolean isExist = false;
        int indexDepartmentFound = 0;
        for (int i = 0; i < departments.size(); i++) {
            if (id.equals(departments.get(i).getId())) {
                isExist = true;
                indexDepartmentFound = i;
                break;
            }
        }
        if (isExist) {
            return departments.get(indexDepartmentFound).getName();
        } else {
            return "";
        }
    }

}



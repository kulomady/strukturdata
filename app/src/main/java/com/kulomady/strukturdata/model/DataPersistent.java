package com.kulomady.strukturdata.model;

import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kulomady on 2/25/18.
 */

public class DataPersistent {
    private final static String TAG = "DataPersistent";

    private File employeeCsvFile;
    private File departmentCsvFile;

    public DataPersistent() {
        String employeeCsvName = "employee.csv";
        employeeCsvFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), employeeCsvName);

        String departmentCsvName = "department.csv";
        departmentCsvFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), departmentCsvName);
    }

    public List<Employee> readEmployeeFromCsv() {
        try {
            CSVReader reader = new CSVReader(new FileReader(employeeCsvFile));
            List<Employee> employees = new ArrayList<>();

            String[] record;

            while ((record = reader.readNext()) != null) {
                Employee emp = new Employee(record[0], record[1], record[2], record[3]);
                employees.add(emp);
            }
            reader.close();
            Log.d(TAG, String.valueOf(employees));
            return employees;

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public List<Department> readDepartmentFromCsv() {
        try {
            CSVReader reader = new CSVReader(new FileReader(departmentCsvFile));
            List<Department> departments = new ArrayList<>();

            String[] record;

            while ((record = reader.readNext()) != null) {
                departments.add(new Department(record[0], record[1]));
            }

            reader.close();
            Log.e(TAG, String.valueOf(departments));
            return departments;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void addNewEmployee(Employee employee) {
        try {
            String[] employeeInString = new String[]{
                    employee.getId(),
                    employee.getDepartmentId(),
                    employee.getName(),
                    employee.getDepartmentId()
            };
            FileWriter mFileWriter = new FileWriter(employeeCsvFile, true);
            CSVWriter writer = new CSVWriter(mFileWriter);
            writer.writeNext(employeeInString);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewDepartment(Department department) {
        try {
            String[] employeeInString = new String[]{
                    department.getId(),
                    department.getName()
            };
            FileWriter mFileWriter = new FileWriter(departmentCsvFile, true);
            CSVWriter writer = new CSVWriter(mFileWriter);
            writer.writeNext(employeeInString);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void clearData() throws Exception {
        clearCsvData(employeeCsvFile);
        clearCsvData(departmentCsvFile);
    }

    public boolean isFirsInstall() {
       return !(departmentCsvFile.exists() && employeeCsvFile.exists());
    }


    public void createDefaultValue() {
        createDefaultDepartment();
        createDefaultEmployee();

    }

    private void createDefaultEmployee() {
        addNewEmployee(new Employee("emp1", "dep1", "Cahyo", "Jl. Petukangan utara"));
        addNewEmployee(new Employee("emp2", "dep2", "Dika", "Jl. Ciledug raya"));
        addNewEmployee(new Employee("emp3", "dep3", "Agus", "Jl. Kemanggisan raya"));
        addNewEmployee(new Employee("emp4", "dep1", "Bagja", "Jl. Palmerah utara III"));

    }

    private void createDefaultDepartment() {
        addNewDepartment(new Department("dep1", "IT"));
        addNewDepartment(new Department("dep2", "Finance"));
        addNewDepartment(new Department("dep3", "HRD"));
        addNewDepartment(new Department("dep4", "Sales"));
    }

    private void clearCsvData(File file) throws IOException {
        FileWriter fw = new FileWriter(file, false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }


}

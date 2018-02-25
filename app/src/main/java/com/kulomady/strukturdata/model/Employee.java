package com.kulomady.strukturdata.model;

/**
 * @author kulomady on 2/24/18.
 */

public class Employee {

    private String id;
    private String departmentId;
    private String name;
    private String alamat;

    public Employee(String id, String departmentId, String name, String alamat) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


    @Override
    public String toString() {
        return id + " " + departmentId + " " + name + " " + alamat;
    }
}

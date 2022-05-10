package com.preving.intranet.gestioncentrosapi.model.domain.workers;

import java.util.List;

public class WorkersFilter {
    //    private Integer workCenterId;
    private List<Integer> employeeId;
    private List<Integer> departmentId;

    public WorkersFilter() {
    }

    public WorkersFilter(List<Integer> employeeId, List<Integer> departmentId) {
//        this.workCenterId = workCenterId;
        this.employeeId = employeeId;
        this.departmentId = departmentId;
    }

//    public Integer getWorkCenterId() {
//        return workCenterId;
//    }
//
//    public void setWorkCenterId(Integer workCenterId) {
//        this.workCenterId = workCenterId;
//    }

    public List<Integer> getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(List<Integer> employeeId) {
        this.employeeId = employeeId;
    }

    public List<Integer> getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(List<Integer> departmentId) {
        this.departmentId = departmentId;
    }
}

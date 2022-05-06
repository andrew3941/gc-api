package com.preving.intranet.gestioncentrosapi.model.domain.workers;

public class WorkersFilter {
//    private Integer workCenterId;
    private Long employeeId;
    private Integer departmentId;

    public WorkersFilter() {
    }

    public WorkersFilter(Integer workCenterId, Long employeeId, Integer departmentId) {
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}

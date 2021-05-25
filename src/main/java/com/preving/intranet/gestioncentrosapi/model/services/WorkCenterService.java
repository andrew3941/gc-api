package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WorkCenterService {

    void addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request);

    void editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request);

    List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter);

    List<Province> findAllProvinces();

    List<Entities> findAll();

    List<City> findCitiesByProvince(int provinceCod, String criterion);

    List<User> getUsers (String criterion);

}

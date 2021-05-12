package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.City;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WorkCenterService {

    void addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request);

    void editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request);

    List<City> findCitiesByProvince(int provinceCod, String criterion);

}

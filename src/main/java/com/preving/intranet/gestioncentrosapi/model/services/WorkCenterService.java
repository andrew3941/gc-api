package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;

public interface WorkCenterService {

    ResponseEntity<?> addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request);

    ResponseEntity<?> editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request);

    List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter);

    WorkCenter getWorkCenterById(int workId);

    List<Department> getDepartments();

    List<City> findCitiesByProvince(String provinceCod, String criterion);

    List<User> findUsersByCriterion (String criterion);

    ResponseEntity<?> editWorkCenterDetails(WorkCenterDetails workCenterDetails);


    WorkCenterDetails getWorkCenterDetails(int workCenterId);
}

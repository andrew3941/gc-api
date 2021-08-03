package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.domain.*;
import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenterDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface WorkCenterService {

    ResponseEntity<?> addWorkCenter(WorkCenter newWorkCenter, HttpServletRequest request);

    ResponseEntity<?> editWorkCenter(int workCenterId, WorkCenter newWorkCenter, HttpServletRequest request);

    List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter);

    WorkCenter getWorkCenterById(int workId);

    List<Department> getDepartments();

    List<City> findCitiesByProvince(String provinceCod, String criterion);

    List<User> findUsersByCriterion (String criterion);

    ResponseEntity<?> editWorkCenterDetails(int workCenterId, WorkCenterDetails workCenterDetails, HttpServletRequest request);

    ResponseEntity<?> exportWorkCenters(WorkCenterFilter workCenterFilter, HttpServletResponse response);

    WorkCenterDetails getWorkCenterDetails(int workCenterId);

    List<Drawing> getDrawingByWorkCenter(int workCenterId);

    List<Room> getRoomListByWorkCenter(int workCenterId);

    ResponseEntity<?> deleteDrawing(HttpServletRequest request,int workCenterId, int drawingId);

    ResponseEntity<?> addWorkCenterDrawing(int workCenterId, Drawing newWorkCenterDrawing, MultipartFile attachedFile, HttpServletRequest request);

    ResponseEntity<?> editWorkCenterDrawing(int workCenterId, int WorkCenterDrawingId,  Drawing newWorkCenterDrawing, HttpServletRequest request);

    ResponseEntity<?> editRoomList(int workCenterId, HttpServletRequest request);

    ResponseEntity<?> deleteRoom(HttpServletRequest request,int workCenterId, int roomId);

}

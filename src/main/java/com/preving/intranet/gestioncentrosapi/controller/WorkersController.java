package com.preving.intranet.gestioncentrosapi.controller;

import com.preving.intranet.gestioncentrosapi.model.domain.workers.Employees;
import com.preving.intranet.gestioncentrosapi.model.domain.workers.WorkersFilter;
import com.preving.intranet.gestioncentrosapi.model.dto.workers.EmployeeProjection;
import com.preving.intranet.gestioncentrosapi.model.services.WorkersService;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path= "/workCenters")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkersController {
    @Autowired
    WorkersService workersService;
    /**
     * Obtiene los trabajadores mediante filtro
     *
     * @return
     * @RequestBody WorkCenterFilter
     */
    @PostMapping("workers/filter")
    public ResponseEntity<?> findWorkersByFilter(HttpServletRequest request,
                                                 @RequestBody WorkersFilter workersFilter) {

        try {
//            UsuarioWithRoles user = this.jwtTokenUtil.getUserWithRolesFromToken(request);
//            return new ResponseEntity<>(this.workCenterService.getWorkCenters(workCenterFilter, user), HttpStatus.OK);
//            return new ResponseEntity<>(workersService.findAllByEmpLabHistoryFchSalidaIsNull(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  null;
    }

    @RequestMapping(value = "{workCenterId}/workers/filter", method = RequestMethod.POST)
    public ResponseEntity<?> findWorkersByFilter(HttpServletRequest request,
                                                 @PathVariable(value = "workCenterId") int workCenterId,
                                                 @RequestBody WorkersFilter workersFilter) {

        try {
//            UsuarioWithRoles user = this.jwtTokenUtil.getUserWithRolesFromToken(request);
            List<Employees> workersList = this.workersService.getFilteredEmployees(workCenterId, workersFilter);
            return new ResponseEntity<>(workersList, HttpStatus.OK);
        } catch (Exception e) {e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

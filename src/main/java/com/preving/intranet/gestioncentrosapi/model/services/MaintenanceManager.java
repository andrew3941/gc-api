package com.preving.intranet.gestioncentrosapi.model.services;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceByAttachmentRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.maintenance.MaintenanceRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceByAttachement;
import com.preving.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.File;




@Service
public class MaintenanceManager implements MaintenanceService {
    private static final int MAINTENANCE = 3;
    @Autowired
   private MaintenanceRepository maintenanceRepository;
    private JwtTokenUtil jwtTokenUtil;
    private MaintenanceManager maintenanceCustomRepository;
    @Override
    public ResponseEntity<?> saveMaintenance(int workCenterId, Maintenance newMaintenance, MultipartFile[] attachedFile, HttpServletRequest request) {
        return null;
    }

    @Autowired
    private MaintenanceByAttachmentRepository maintenanceByAttachmentRepository;

    @Override
    public ResponseEntity<?> deleteMaintenance(HttpServletRequest request, int workCenterId, int maintenanceId) {
        long uId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();
        Maintenance maintenance = this.maintenanceRepository.findMaintenanceById(maintenanceId);
        if (maintenance==null) {
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
        try {
            this.maintenanceRepository.maintenanceLogicDelete((int) uId,maintenance.getId(), workCenterId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> downloadMaintenanceDoc(HttpServletRequest request, int generalMaintenanceId) {
        MaintenanceByAttachement mba = null;

        File file = null;
        byte[] content = null;

      return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }

    @Override
    public Maintenance getMaintenanceById(int maintenanceId){
        Maintenance maintenance = maintenanceRepository.findMaintenanceById(maintenanceId);
        return maintenance;
    }

    @Override
    public ResponseEntity<?> editMaintenance(int workCenterId, Maintenance maintenance, MultipartFile[] attachedFile, HttpServletRequest request) {
        return null;
    }

    @Override
    public List<Maintenance> getAllMaintenance() {
        return null;
    }

    @Override
    public void saveOrUpdate(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }


    @Autowired
    public MaintenanceManager(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> findAllMaintenance(){
        return maintenanceRepository.findAll();
    }


    @Override
    public List<Maintenance> getMaintenance(int workCenterId, MaintenanceFilter maintenanceFilter, UsuarioWithRoles user) {
        return maintenanceCustomRepository.getMaintenance(workCenterId, maintenanceFilter, user);
    }

}


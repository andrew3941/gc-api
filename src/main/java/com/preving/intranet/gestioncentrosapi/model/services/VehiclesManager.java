package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.vehicles.BrandsRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.vehicles.VehiclesRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.vehicles.VehiclesCustomRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.Maintenance;
import com.preving.intranet.gestioncentrosapi.model.domain.maintenance.MaintenanceFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Brands;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.Vehicles;
import com.preving.intranet.gestioncentrosapi.model.domain.vehicles.VehiclesFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class VehiclesManager implements VehiclesService {

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private VehiclesCustomRepository vehiclesCustomRepository;

    private static final String EXPORT_TITLE_1 = "BrandType";
    static final String EXPORT_TITLE_2 = "License Plate";

    @Autowired
    private BrandsRepository brandsRepository;

    @Override
    public List<Vehicles> getFilteredVehicles(int workCenterId, VehiclesFilter vehiclesFilter, UsuarioWithRoles user) {
        return this.vehiclesCustomRepository.getVehiclesFiltered(workCenterId, vehiclesFilter, user);
    }


    @Override
    public ResponseEntity<?> exportVehicle(int workCenterId, VehiclesFilter vehiclesFilter, HttpServletResponse response, UsuarioWithRoles user) {
        byte[] content = null;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet hoja = workbook.createSheet();
        workbook.setSheetName(0, "performances");
        // We create style for the header
        CellStyle cellStyleHeaders = workbook.createCellStyle();
        CellStyle dateCell = workbook.createCellStyle();
        Font font = workbook.createFont();
        // TODO color the background of the headers
        font.setBold(true);
        cellStyleHeaders.setFont(font);
        //style for date format
        CellStyle cellStyleData = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));

        // We get the data
        List<Vehicles> vehicles = this.vehiclesCustomRepository.getVehiclesFiltered(workCenterId, vehiclesFilter, user);

        String[] titleArray = {EXPORT_TITLE_1, EXPORT_TITLE_2};

        // We create a row in the sheet at position 0 for the headers
        HSSFRow headerRow = hoja.createRow(0);

        // We create the headers
        for (int i = 0; i < titleArray.length; i++) {
            HSSFCell celda = headerRow.createCell(i);
            celda.setCellValue(titleArray[i]);
            celda.setCellStyle(cellStyleHeaders);
        }

        // We create the rows
        for (int i = 0; i < vehicles.size(); i++) {
            HSSFRow dataRow = hoja.createRow(1 + i);


            // date
            HSSFCell date = dataRow.createCell(4);
//   date.setCellValue(maintenances.get(i).getDate());
            date.setCellStyle(cellStyleData);

            // adjust columns

            try {
                String nombreFichero = "report-actions";
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "inline; filename=\"" +
                        java.net.URLEncoder.encode(nombreFichero, "UTF-8")
                        + "\"");

                ServletOutputStream out = response.getOutputStream();
                workbook.write(out);
                out.flush();

            } catch (IOException ex) {
                return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }

    @Override
    public List<Brands> getAllBrandTypes() {
        return brandsRepository.findAll();
    }

    @Override
    public List<Vehicles> findAllVehiclesByWorkCenter(int workCenterId) {
        return vehiclesRepository.findAllByWorkCenterIdAndUserUnsubscribeNotNull(workCenterId);
    }

}


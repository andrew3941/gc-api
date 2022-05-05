package com.preving.intranet.gestioncentrosapi.model.services;


import com.preving.intranet.gestioncentrosapi.model.dao.workers.WorkersCustomRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.workers.Employees;
import com.preving.intranet.gestioncentrosapi.model.domain.workers.WorkersFilter;
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
import com.preving.intranet.gestioncentrosapi.model.dao.workers.WorkersRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.workers.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



public class WorkersManager implements WorkersService{

    private static final String EXPORT_TITLE_1 = "";
    static final String EXPORT_TITLE_2 = "";
    static final String EXPORT_TITLE_3 = "";
    static final String EXPORT_TITLE_4 = "";
    static final String EXPORT_TITLE_5 = "";
    static final String EXPORT_TITLE_6 = "";
    static final String EXPORT_TITLE_7 = "";
    static final String EXPORT_TITLE_8 = "";
    static final String EXPORT_TITLE_9 = "";
    static final String EXPORT_TITLE_10 = "";


    @Autowired
    private WorkersCustomRepository workersCustomRepository;

    @Autowired
    private WorkersRepository workersRepository;


    @Override
    public ResponseEntity<?> exportWorkers(int workCenterId, WorkersFilter wFilter, HttpServletResponse response, UsuarioWithRoles user) {
        return null;
    }

    //filterWorkers
    @Override
    public List<Employees> getFilteredEmployees(int workCenterId, WorkersFilter workersFilter, UsuarioWithRoles user) {
        return this.workersCustomRepository.getEmployeesFiltered(workCenterId, workersFilter, user);
    }

    // exportWorkers
//    @Override
//    public ResponseEntity<?> exportWorkers(int workCenterId, WorkersFilter wFilter, HttpServletResponse response, UsuarioWithRoles user) {
//
//        byte[] content = null;
//
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet hoja = workbook.createSheet();
//        workbook.setSheetName(0, "performances");
//
//        // We create style for the header
//        CellStyle cellStyleHeaders = workbook.createCellStyle();
//        CellStyle dateCell = workbook.createCellStyle();
//        Font font = workbook.createFont();
//
//        // TODO color the background of the headers
//        font.setBold(true);
//        cellStyleHeaders.setFont(font);
//
//        //style for date format
//        CellStyle cellStyleData = workbook.createCellStyle();
//        CreationHelper createHelper = workbook.getCreationHelper();
//        cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
//
//        // We get the data
//        List<Employees> employees = this.workersCustomRepository.getEmployeesFiltered(workCenterId, wFilter, user);
//        String[] titleArray = {EXPORT_TITLE_1, EXPORT_TITLE_2, EXPORT_TITLE_3, EXPORT_TITLE_4, EXPORT_TITLE_5, EXPORT_TITLE_6, EXPORT_TITLE_7, EXPORT_TITLE_8};
//        // We create a row in the sheet at position 0 for the headers
//        HSSFRow headerRow = hoja.createRow(0);
//
//        // We create the headers
//        for (int i = 0; i < titleArray.length; i++) {
//            HSSFCell celda = headerRow.createCell(i);
//            celda.setCellValue(titleArray[i]);
//            celda.setCellStyle(cellStyleHeaders);
//        }
//
//        // We create the rows
//        HSSFRow dataRow = null;
//        for (int i = 0; i < employees.size(); i++) {
//            dataRow = hoja.createRow(1 + i);
//
//            //
//            HSSFCell  = dataRow.createCell(0);
//            lukman.setCellValue(employees.get(i).());
//
//            //
//            HSSFCell  = dataRow.createCell(1);
//            gibo.setCellValue(employees.get(i).().());
//
//        }
//
//        // adjust columns
//        for (int i = 0; i < titleArray.length; i++) {
//            hoja.autoSizeColumn(i);
//        }
//
//        try {
//            String nombreFichero = "report-actions";
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("Content-Disposition", "inline; filename=\"" +
//                    java.net.URLEncoder.encode(nombreFichero, "UTF-8")
//                    + "\"");
//
//            ServletOutputStream out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//
//        } catch (IOException ex) {
//            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
//    }


    @Override
    public List<Employees> getAllEmployees() {
        return workersRepository.findAllByName("NOEMI");
    }


}

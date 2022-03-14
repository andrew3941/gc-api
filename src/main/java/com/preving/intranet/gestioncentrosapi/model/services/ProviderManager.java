package com.preving.intranet.gestioncentrosapi.model.services;

import com.preving.intranet.gestioncentrosapi.model.dao.vendor.*;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersCustomizeRepository;
import com.preving.intranet.gestioncentrosapi.model.dao.workCenters.WorkCentersRepository;
import com.preving.intranet.gestioncentrosapi.model.domain.User;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.*;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData.ProviderDetail;
import com.preving.intranet.gestioncentrosapi.model.domain.vendors.specificData.ProviderDetailConf;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.security.JwtTokenUtil;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProviderManager implements ProviderService {

    @Autowired
    private ProviderTypesRepository providerTypesRepository;

    @Autowired
    private ProviderAreaRepository providerAreaRepository;

    @Autowired
    private ProviderEvaluationTypesRepository providerEvaluationTypesRepository;

    @Autowired
    private ExpenditurePeriodRepository expenditurePeriodRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderCustomRepository providerCustomRepository;

    @Autowired
    WorkCentersCustomizeRepository workCentersCustomizeRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ProvidersByWorkCentersRepository providersByWorkCentersRepository;

    @Autowired
    private WorkCentersRepository workCentersRepository;

    @Autowired
    private ProvidersCommonDetailsRepository providersCommonDetailsRepository;

    @Autowired
    private ProviderDetailConfRepository providerDetailConfRepository;

    @Autowired
    private ProviderDetailsRepository providerDetailsRepository;

    @Autowired
    private ProviderByAreasRepository providerByAreasRepository;

    private static final String EXPORT_TITLE_1 = "Proveedor";
    static final String EXPORT_TITLE_2 = "CIF/NIF";
    private static final String EXPORT_TITLE_3 = "Tipo";
    private static final String EXPORT_TITLE_4 = "Area Asociada";
    private static final String EXPORT_TITLE_5 = "Centros de trabajo";
    private static final String EXPORT_TITLE_6 = "Provincia";
    private static final String EXPORT_TITLE_7 = "Fecha inicio";
    private static final String EXPORT_TITLE_8 = "Fecha fin";


    private static final int PROVIDER_DOCUMENTS = 2;
    private static final boolean ACTIVE = true;
    private static final boolean INACTIVE = false;

    @Override
    public List<Provider> getProviders(int workCenterId, ProviderFilter providerFilter, UsuarioWithRoles user) {
        List<Provider> providers = this.providerCustomRepository.getProviders(workCenterId, providerFilter, user);

        for (Provider provider : providers) {
            // Buscamos las areas por proveedor y las seteamos
            List<ProvidersByAreas> providersByAreas = providerByAreasRepository.findAllByProvider(provider);
            provider.setProviderAreas(providersByAreas);

            // Buscar los centros por proveedorId
            List<ProvidersByWorkCenters> providersByWorkCenters = providersByWorkCentersRepository.findAllByProvider(provider);

            for (ProvidersByWorkCenters provByWorkCenters : providersByWorkCenters) {
                // Obtener los datos del centro por id
                WorkCenter workCenter = this.workCentersRepository.findWorkCenterById(provByWorkCenters.getWorkCenter().getId());

                // Obtenemos los detalles del centro
                ProvidersCommonDetails details = providersCommonDetailsRepository.findAllByProvDelegacionId(provByWorkCenters.getId());

                if (workCenterId == 0) {
                    if (providerFilter.getProviderStatus() != 0) {
                        // Comprobamos si tiene fecha fin de servicio
                        if (details.getServiceEndDate() != null) {

                            if (providerFilter.getProviderStatus() != 2) {
                                // Comprobamos si la fecha fin es anterior a hoy
                                if (details.getServiceEndDate().after(new Date())) {
                                    // Metemos los centros en la lista de proveedores
                                    provider.getWorkCenters().add(workCenter);

                                    // Metemos los detalles del centro en details
                                    details.setWorkCenterName(workCenter.getName());
                                    details.setWorkCenterId(workCenter.getId());

                                    // Metemos los detalles en el proveedor
                                    provider.getProvidersCommonDetails().add(details);
                                }
                            } else {
                                // Metemos los centros en la lista de proveedores
                                provider.getWorkCenters().add(workCenter);

                                // Metemos los detalles del centro en details
                                details.setWorkCenterName(workCenter.getName());
                                details.setWorkCenterId(workCenter.getId());

                                // Metemos los detalles en el proveedor
                                provider.getProvidersCommonDetails().add(details);
                            }

                        } else {
                            // Metemos los centros en la lista de proveedores
                            provider.getWorkCenters().add(workCenter);

                            // Metemos los detalles del centro en details
                            details.setWorkCenterName(workCenter.getName());
                            details.setWorkCenterId(workCenter.getId());

                            // Metemos los detalles en el proveedor
                            provider.getProvidersCommonDetails().add(details);
                        }
                    } else {
                        // Comprobamos si tiene fecha fin de servicio
                        if (details.getServiceEndDate() != null) {
                            // Comprobamos si la fecha fin es posterior a hoy
                            if (details.getServiceEndDate().before(new Date())) {
                                // Metemos los centros en la lista de proveedores
                                provider.getWorkCenters().add(workCenter);

                                // Metemos los detalles del centro en details
                                details.setWorkCenterName(workCenter.getName());
                                details.setWorkCenterId(workCenter.getId());

                                // Metemos los detalles en el proveedor
                                provider.getProvidersCommonDetails().add(details);
                            }
                        }
                    }
                } else {
                    // Metemos los centros en la lista de proveedores
                    provider.getWorkCenters().add(workCenter);

                    // Metemos los detalles del centro en details
                    details.setWorkCenterName(workCenter.getName());
                    details.setWorkCenterId(workCenter.getId());

                    // Metemos los detalles en el proveedor
                    provider.getProvidersCommonDetails().add(details);
                }
            }
        }
        return providers;
    }

    @Override
    public List<ProviderTypes> getProviderTypes(int workCenterId) {
        return providerTypesRepository.findAllByOrderByOrder();
    }

    @Override
    public List<ProviderArea> getProviderArea(int workCenterId) {
        return providerAreaRepository.findAllByOrderByOrder();
    }

    @Override
    public List<ProviderEvaluationTypes> getProviderEvaluationTypes(int workCenterId) {
        return providerEvaluationTypesRepository.findAll();
    }

    @Override
    public List<ExpenditurePeriod> getExpenditurePeriod(int workCenterId) {
        return expenditurePeriodRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> saveProvider(int workCenterId, Provider newProvider, List<ProviderDetail> specificData,
                                          MultipartFile attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        newProvider.setCreated(new Date());
        newProvider.getCreatedBy().setId(userId);

        // Setting active or inactive provider
        activeInactiveProvider(newProvider);

        // Guardamos proveedor
        Provider provider = providerRepository.save(newProvider);

        // Guardamos en areas_x_proveedor
        for (ProvidersByAreas providersByAreas : newProvider.getProviderAreas()) {

            providersByAreas.getProvider().setId(provider.getId());

            providerByAreasRepository.save(providersByAreas);
        }

        try {
            for (WorkCenter workCenter : newProvider.getWorkCenters()) {

                // Seteamos los valores del objeto
                ProvidersByWorkCenters providersByWorkCenters = new ProvidersByWorkCenters();
                providersByWorkCenters.getProvider().setId(provider.getId());
                providersByWorkCenters.getWorkCenter().setId(workCenter.getId());

                // Guardamos en proveedores_x_delegaciones
                providersByWorkCentersRepository.save(providersByWorkCenters);

                // Seteamos los valores para guardar los detalles
                ProvidersCommonDetails providersCommonDetails = new ProvidersCommonDetails();

                providersCommonDetails.setCreated(new Date());
                providersCommonDetails.getCreatedBy().setId(userId);
                providersCommonDetails.getExpenditurePeriod().setId(newProvider.getProvidersCommonDetails().get(0).getExpenditurePeriod().getId());
                providersCommonDetails.setProvDelegacionId(providersByWorkCenters.getId());
                providersCommonDetails.setAnualSpending(newProvider.getProvidersCommonDetails().get(0).getAnualSpending());
                providersCommonDetails.setSpending(newProvider.getProvidersCommonDetails().get(0).getSpending());

                if (attachedFile != null) {
                    providersCommonDetails.setDocUrl("doc_url");
                    providersCommonDetails.setDocName(attachedFile.getOriginalFilename());
                    providersCommonDetails.setDocContentType(attachedFile.getContentType());
                }

                // Guardamos en proveedores_detalles_comun
                ProvidersCommonDetails providersComDetails = this.providersCommonDetailsRepository.save(providersCommonDetails);

                if (attachedFile != null) {
                    String url = null;

                    // Guardamos documento en el server
                    url = commonService.saveDocumentServer(workCenter.getId(), provider.getId(), attachedFile, PROVIDER_DOCUMENTS);

                    // Actualizamos la ruta del documento guardado
                    if (url != null) {
                        this.providersCommonDetailsRepository.updateProviderDocUrl(providersComDetails.getId(), url);
                    }
                }

                // Guardamos los datos especï¿½ficos por proveedor tipo
                for (ProviderDetail spcData : specificData) {

                    // Construimos el objeto proveedor detalles
                    ProviderDetail providerDetails = new ProviderDetail();

                    providerDetails.setProvidersByWorkCentersId(providersByWorkCenters.getId());
                    providerDetails.getProviderDetailConf().setId(spcData.getProviderDetailConf().getId());
                    providerDetails.setProviderDetailValue(spcData.getProviderDetailValue());

                    // Guardamos en proveedores_detalles
                    this.providerDetailsRepository.save(providerDetails);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void activeInactiveProvider(Provider provider) {

        if (provider.getServiceEndDate() != null
                && (provider.getServiceEndDate().before(new Date()) || provider.getServiceEndDate().equals(new Date()))) {
            provider.setActive(INACTIVE);
        } else {
            provider.setActive(ACTIVE);
        }

    }

    @Override
    public Provider getProviderById(int workCenterId, int providerId) {

        // Buscamos el proveedor por id
        Provider myProvider =  this.providerRepository.findProviderById(providerId);

        // Obtenemos los detalles de todos los centros del proveedor
        if (workCenterId == 0) {
            // Buscar las delegaciones por proveedorId
            List<ProvidersByWorkCenters> providersByWorkCenters = providersByWorkCentersRepository.findAllByProvider(myProvider);

            for (ProvidersByWorkCenters provByWorkCenters : providersByWorkCenters) {

                // Obtenemos los detalles
                ProvidersCommonDetails details = providersCommonDetailsRepository.findAllByProvDelegacionId(provByWorkCenters.getId());

                // Obtenemos los detalles especificos
                List<ProviderDetail> specificDetails = providerDetailsRepository.findByProvidersByWorkCentersId(provByWorkCenters.getId());

                WorkCenter workCenter = new WorkCenter();

                workCenter.setId(provByWorkCenters.getWorkCenter().getId());
                workCenter.setName(provByWorkCenters.getWorkCenter().getName());

                // Si fecha fin es superior a la fecha de hoy
                if (details.getServiceEndDate() != null) {
                    if (details.getServiceEndDate().after(new Date())) {
                        // Detalles comunes
                        myProvider.getProvidersCommonDetails().add(details);

                        // Detalles específicos
                        myProvider.setProviderDetails(specificDetails);

                        // Delegaciones
                        myProvider.getWorkCenters().add(workCenter);
                    }
                } else {
                    // Detalles comunes
                    myProvider.getProvidersCommonDetails().add(details);

                    // Detalles específicos
                    myProvider.setProviderDetails(specificDetails);

                    // Delegaciones
                    myProvider.getWorkCenters().add(workCenter);
                }
            }
        } else { // Obtenemos los detalles de un centro específico
            // Buscar las delegaciones por proveedorId
            ProvidersByWorkCenters providerByWorkCenter = providersByWorkCentersRepository.findByProvider_IdAndWorkCenter_Id(providerId, workCenterId);

            // Obtenemos los detalles comunes
            ProvidersCommonDetails details = providersCommonDetailsRepository.findAllByProvDelegacionId(providerByWorkCenter.getId());

            // Obtenemos los detalles especificos
            List<ProviderDetail> specificDetails = providerDetailsRepository.findByProvidersByWorkCentersId(providerByWorkCenter.getId());

            WorkCenter workCenter = new WorkCenter();

            workCenter.setId(providerByWorkCenter.getWorkCenter().getId());
            workCenter.setName(providerByWorkCenter.getWorkCenter().getName());

            // Detalles comunes
            myProvider.getProvidersCommonDetails().add(details);

            // Detalles específicos
            myProvider.setProviderDetails(specificDetails);

            // Delegaciones
            myProvider.getWorkCenters().add(workCenter);
        }

        return myProvider;
    }

    @Override
    public List<ProviderDetailConf> getSpecificProviderForm(int providerTypeId) {

        ProviderTypes providerType = providerTypesRepository.getOne(providerTypeId);
        return providerDetailConfRepository.findAllByProviderTypeOrderByOrden(providerType);

    }

    @Override
    public void activateProvider() {

        System.out.println("--------------------------------------------------------------");
        System.out.println("--- INICIO DEL PROCESO DE ACTIVACION DE PROVEEDORES");
        System.out.println("--------------------------------------------------------------");

        // Getting work centers with expired end date
        List<Provider> providers = providerRepository.findProvidersByServiceStartDateEquals(formatCurrentDate());

        System.out.println("----- Se han obtenido " + providers.size() + " proveedores para activar");

        // Setting inactive attribute for each work center
        providers.forEach(provider -> {
            providerRepository.setActiveProvider(provider.getId());
            System.out.println("--------- Proveedor (" + provider.getId() + ") -> Activado");
        });

        System.out.println("--------------------------------------------------------------");
        System.out.println("--- FIN DEL PROCESO DE ACTIVACION DE PROVEEDORES");
        System.out.println("--------------------------------------------------------------");

    }

    @Override
    public void desactivateProvider() {

        System.out.println("--------------------------------------------------------------");
        System.out.println("--- INICIO DEL PROCESO DE DESACTIVACION DE PROVEEDORES");
        System.out.println("--------------------------------------------------------------");

        // Getting work centers with expired end date
        List<Provider> providers = providerRepository.findProvidersByServiceEndDateEquals(formatCurrentDate());

        System.out.println("----- Se han obtenido " + providers.size() + " proveedores para finalizar");

        // Setting inactive attribute for each work center
        providers.forEach(provider -> {
            providerRepository.setInactiveProvider(provider.getId());
            System.out.println("--------- Proveedor (" + provider.getId() + ") -> Desactivado");
        });

        System.out.println("--------------------------------------------------------------");
        System.out.println("--- FIN DEL PROCESO DE DESACTIVACION DE PROVEEDORES");
        System.out.println("--------------------------------------------------------------");

    }

    private Date formatCurrentDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());
        Date date = null;

        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    @Transactional
    public ResponseEntity<?> editProvider(int workCenterId, int providerId, Provider provider, List<ProviderDetail> details,

       MultipartFile attachedFile, HttpServletRequest request) {

        long userId = this.jwtTokenUtil.getUserWithRolesFromToken(request).getId();

        provider.setModifiedBy(new User());
        provider.getModifiedBy().setId(userId);

        // Setting active or inactive provider
        activeInactiveProvider(provider);

        // Editamos el proveedor
        providerRepository.editProvider(provider);

        // Eliminamos las areas por proveedor
        providerByAreasRepository.deleteAllByProvider(provider);

        // Guardamos las areas por proveedor
        for (ProvidersByAreas providersByAreas : provider.getProviderAreas()) {

            providersByAreas.getProvider().setId(provider.getId());

            providerByAreasRepository.save(providersByAreas);
        }

        try {
            // Provider management
            if (workCenterId == 0) {

                // TODO faltaría comprobar si es un solo centro y se han tocado los detalles

                // Obtenemos las delegaciones del proveedor
                List<ProvidersByWorkCenters> provByWorkCenters = providersByWorkCentersRepository.findAllByProvider(provider);


                // Comprobamos si viene un número menor de centros ya guardados
                if (provider.getWorkCenters().size() < provByWorkCenters.size()) {
                    // Si el objecto trae menos centros de los guardados en bbdd devolvemos error
                    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
                }


                // Creamos los arrays de los centros
                List<WorkCenter> wcBack = new ArrayList<WorkCenter>();
                List<WorkCenter> wcAdd = new ArrayList<WorkCenter>();


                // Añadimos los centros recuperados desde bbdd en un array de centros
                for (ProvidersByWorkCenters prov: provByWorkCenters) {
                    wcBack.add(prov.getWorkCenter());
                }

//                if (!Objects.equals(wcBack, provider.getWorkCenters())) {
//                    System.out.println("no son iguales");
//                }

                // Recorremos los centros que vienen desde angular
                for (WorkCenter workCenter: provider.getWorkCenters()) {
                    // TODO comprobar si está o no el centro en el array
                    // Insertamos los centros agregados en angular a nuevo array de centros
                    if (!wcBack.contains(workCenter)) {
                        wcAdd.add(workCenter);
                    }

//                    if (!Objects.equals(wcBack, workCenter.getId())) {
//                        System.out.println("no son iguales");
//                    }

                }


                // Si existen nuevos centros los insertamos en las tablas PROVEEDORES_X_DELEGACIONES y PROVEEDORES_DETALLES
                if (wcAdd.size() > 0) {
                    // Recorremos el array de los centros nuevos para guardarlos
                    for(WorkCenter workCenter: wcAdd) {

                        // Seteamos los valores del objeto
                        ProvidersByWorkCenters providersByWorkCenters = new ProvidersByWorkCenters();
                        providersByWorkCenters.getProvider().setId(provider.getId());
                        providersByWorkCenters.getWorkCenter().setId(workCenter.getId());

                        // Guardamos la delegación por proveedor
                        providersByWorkCentersRepository.save(providersByWorkCenters);

                        // Seteamos los valores por defecto de los detalles comunes
                        ProvidersCommonDetails commonDetails = new ProvidersCommonDetails();
                        commonDetails.setCreated(new Date());
                        commonDetails.getCreatedBy().setId(userId);
                        commonDetails.setProvDelegacionId(providersByWorkCenters.getId());

                        // Guardamos los detalles comunes del centro
                        providersCommonDetailsRepository.save(commonDetails);
                    }
                }
            }

            // Provider's inside workCenter
            if (workCenterId != 0) {

                int provDelId = providersByWorkCentersRepository.findByProvider_IdAndWorkCenter_Id(providerId, workCenterId).getId();

                // Borramos los detalles comunes del proveedor
                providersCommonDetailsRepository.deleteAllByProvDelegacionId(provDelId);

                // Borramos los detalles especï¿½ficos del proveedor
                providerDetailsRepository.deleteAllByProvidersByWorkCentersId(provDelId);

                // Borramos todas las delegaciones por proveedor con el id de proveedor
                providersByWorkCentersRepository.deleteById(provDelId);

                // Seteamos los valores del objeto
                ProvidersByWorkCenters providersByWorkCenters = new ProvidersByWorkCenters();
                providersByWorkCenters.getProvider().setId(provider.getId());
                providersByWorkCenters.getWorkCenter().setId(workCenterId);

                providersByWorkCentersRepository.save(providersByWorkCenters);

                for (ProvidersCommonDetails commonDetails : provider.getProvidersCommonDetails()) {

                    if (attachedFile != null) {
                        commonDetails.setDocName(attachedFile.getOriginalFilename());
                        commonDetails.setDocContentType(attachedFile.getContentType());
                    }

                    commonDetails.setCreated(new Date());
                    commonDetails.getCreatedBy().setId(userId);
                    commonDetails.setProvDelegacionId(providersByWorkCenters.getId());

                    if (provider.getServiceEndDate() != null){
                        commonDetails.setServiceEndDate(provider.getServiceEndDate());
                    }

                    ProvidersCommonDetails providersComDetails = this.providersCommonDetailsRepository.save(commonDetails);

                    if (attachedFile != null) {
                        // Borramos el documento anterior del servidor
                        commonService.deleteDocumentServer(workCenterId, providersComDetails.getId(), PROVIDER_DOCUMENTS);

                        String url = null;
                        // Guardamos documento en el server
                        url = commonService.saveDocumentServer(workCenterId, providerId, attachedFile, PROVIDER_DOCUMENTS);

                        // Actualizamos la ruta del documento guardado
                        if (url != null) {
                            this.providersCommonDetailsRepository.updateProviderDocUrl(providersComDetails.getId(), url);
                        }
                    }
                }

                // Editing specific provider data by type
                for (ProviderDetail det : details) {

                    // Construimos el objeto proveedor detalles
                    ProviderDetail providerDetails = new ProviderDetail();

                    providerDetails.setProvidersByWorkCentersId(providersByWorkCenters.getId());
                    providerDetails.getProviderDetailConf().setId(det.getProviderDetailConf().getId());
                    providerDetails.setProviderDetailValue(det.getProviderDetailValue());

                    // Guardamos en proveedores_detalles
                    this.providerDetailsRepository.save(providerDetails);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> exportProvider(ProviderFilter providerFilter, HttpServletResponse response, UsuarioWithRoles user) {
        byte[] content=null;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet hoja = workbook.createSheet();
        workbook.setSheetName(0, "Actuaciones");
        // Creamos estilo para el encabezado
        CellStyle cellStyleHeaders = workbook.createCellStyle();
        CellStyle dateCell = workbook.createCellStyle();
        Font font = workbook.createFont();
        // TODO colorear el fondo de las cabeceras
        font.setBold(true);
        cellStyleHeaders.setFont(font);
        // Creamos estilo para formato fecha
        CellStyle cellStyleData = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

       // Obtenemos los datos
        List<Provider> providers = getProviders(0,providerFilter, user);

        String[] titulos = {EXPORT_TITLE_1, EXPORT_TITLE_2, EXPORT_TITLE_3, EXPORT_TITLE_4, EXPORT_TITLE_5, EXPORT_TITLE_6, EXPORT_TITLE_7, EXPORT_TITLE_8};

       // Creamos una fila en la hoja en la posicion 0 para los headers
        HSSFRow headerRow = hoja.createRow(0);

        // Creamos los headers
        for (int i = 0; i < titulos.length; i++) {
            HSSFCell celda = headerRow.createCell(i);
            celda.setCellValue(titulos[i]);
            celda.setCellStyle(cellStyleHeaders);
        }

        // Creamos las filas
        for (int i = 0; i < providers.size(); i++) {
            HSSFRow dataRow = hoja.createRow(1 + i);

            // Proveedor
            HSSFCell center = dataRow.createCell(0);
            center.setCellValue(providers.get(i).getName());

            // CIF/NIF
            HSSFCell nif = dataRow.createCell(1);
            nif.setCellValue(providers.get(i).getCif());

            // Tipo
            HSSFCell type = dataRow.createCell(2);
            type.setCellValue(providers.get(i).getProviderTypes().getName());

            // Areas
            HSSFCell areasCell = dataRow.createCell(3);
            for (ProvidersByAreas area : providers.get(i).getProviderAreas()) {
                areasCell.setCellValue(areasCell + area.getProviderArea().getName() + ", ");
            }

            // Centros
            HSSFCell centers = dataRow.createCell(4);
            for (WorkCenter workCenter : providers.get(i).getWorkCenters()) {
                centers.setCellValue(centers + workCenter.getName() + ", ");
            }

            // Province
            HSSFCell province = dataRow.createCell(5);
            province.setCellValue(providers.get(i).getCity().getProvince().getName());

            // Start date
            HSSFCell startDate = dataRow.createCell(6);
            startDate.setCellValue(providers.get(i).getServiceStartDate());
            startDate.setCellStyle(cellStyleData);

            // Status
            HSSFCell status = dataRow.createCell(7);
            if (providers.get(i).getActive()) {
                status.setCellValue("Activo");
            } else {
                status.setCellValue("Inactivo");
            }
        }

        // Ajustamos columnas
        for (int i = 0; i < titulos.length; i++) {
            hoja.autoSizeColumn(i);
        }

        try {
            String nombreFichero = "reporte-actuaciones";
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
        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> downloadProviderDoc (HttpServletRequest request,int workCenterId, int providerId){

        Provider provider = null;
        File file = null;
        byte[] content = null;

        try {
            String docUrl = this.providerCustomRepository.findDocUrlByProviderId(providerId, workCenterId);

            file = new File(docUrl);
            if (file.exists()) {
                content = Files.readAllBytes(file.toPath());
            } else {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }

    @Override
    public List<Provider> getProvidersByWorkCenter(int workCenterId) {

        List<Provider> providers = providerCustomRepository.getProvidersByWorkCenter(workCenterId);

        return providers;
    }
}


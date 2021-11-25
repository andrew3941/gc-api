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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private static final int PROVIDER_DOCUMENTS = 2;
    private static final boolean ACTIVE = true;
    private static final boolean INACTIVE = false;

    @Override
    public List<Provider> getProviders(int workCenterId, ProviderFilter providerFilter) {
        List<Provider> providers = this.providerCustomRepository.getProviders(workCenterId, providerFilter);

        for (Provider provider: providers) {
            // Buscar los centros por proveedorId
            List<ProvidersByWorkCenters> providersByWorkCenters = providersByWorkCentersRepository.findAllByProvider(provider);

            for (ProvidersByWorkCenters provByWorkCenters : providersByWorkCenters) {
                // Obtener los datos del centro por id
                WorkCenter workCenter = this.workCentersRepository.findWorkCenterById(provByWorkCenters.getWorkCenter().getId());

                // Meter los centros en la lista de proveedores
                provider.getWorkCenters().add(workCenter);


                if (provByWorkCenters.getWorkCenter().getId() == workCenterId){

                    ProvidersCommonDetails details = providersCommonDetailsRepository.findAllByProvDelegacionId(provByWorkCenters.getId());

                    provider.setProvidersCommonDetails(details);
                } else {
                    // Get details data
                    ProvidersCommonDetails details = providersCommonDetailsRepository.findAllByProvDelegacionId(provByWorkCenters.getId());

                    // Fill the object inside provider
                    provider.setProvidersCommonDetails(details);
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
                providersCommonDetails.getExpenditurePeriod().setId(provider.getProvidersCommonDetails().getExpenditurePeriod().getId());
                providersCommonDetails.setProvDelegacionId(providersByWorkCenters.getId());
                providersCommonDetails.setAnualSpending(provider.getProvidersCommonDetails().getAnualSpending());
                providersCommonDetails.setSpending(provider.getProvidersCommonDetails().getSpending());

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

                // Guardamos los datos específicos por proveedor tipo
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

        // Buscar las delegaciones por proveedorId
        List<ProvidersByWorkCenters> providersByWorkCenters = providersByWorkCentersRepository.findAllByProvider(myProvider);

        for (ProvidersByWorkCenters provByWorkCenters : providersByWorkCenters) {
            WorkCenter workCenter = new WorkCenter();

            workCenter.setId(provByWorkCenters.getWorkCenter().getId());
            workCenter.setName(provByWorkCenters.getWorkCenter().getName());

            // Meter los centros en la lista de proveedores
            myProvider.getWorkCenters().add(workCenter);

            if ((workCenterId != 0) && (provByWorkCenters.getProvider().getId() == providerId) && (provByWorkCenters.getWorkCenter().getId() == workCenterId)){
                ProvidersCommonDetails details = providersCommonDetailsRepository.findAllByProvDelegacionId(provByWorkCenters.getId());

                myProvider.setProvidersCommonDetails(details);
            }

            // TODO adaptar para la lista de detalles
            if ((provByWorkCenters.getProvider().getId() == providerId) && (workCenterId == 0) ){

                ProvidersCommonDetails details = providersCommonDetailsRepository.findAllByProvDelegacionId(provByWorkCenters.getId());
                myProvider.setProvidersCommonDetails(details);

                // Setting specific details to the provider
                List<ProviderDetail> specificDetails = providerDetailsRepository.findByProvidersByWorkCentersId(provByWorkCenters.getId());
                myProvider.setProviderDetails(specificDetails);

            }

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

        // Obtenemos todos los registros de detalles del proveedor
        List<ProvidersByWorkCenters> provByWorkCenters = providersByWorkCentersRepository.findAllByProvider(provider);

        for (ProvidersByWorkCenters provByWorkCenter: provByWorkCenters) {

            // Borramos los detalles comunes del proveedor
            providersCommonDetailsRepository.deleteAllByProvDelegacionId(provByWorkCenter.getId());

            // Borramos los detalles específicos del proveedor
            providerDetailsRepository.deleteAllByProvidersByWorkCentersId(provByWorkCenter.getId());

        }

        // Borramos todas las delegaciones por proveedor con el id de proveedor
        providersByWorkCentersRepository.deleteAllByProvider_Id(provider.getId());

        try {
            if (workCenterId == 0){

                for(WorkCenter workCenter : provider.getWorkCenters()) {
                    // Seteamos los valores del objeto
                    ProvidersByWorkCenters providersByWorkCenters = new ProvidersByWorkCenters();
                    providersByWorkCenters.getProvider().setId(provider.getId());
                    providersByWorkCenters.getWorkCenter().setId(workCenter.getId());

                    providersByWorkCentersRepository.save(providersByWorkCenters);

                    ProvidersCommonDetails providersCommonDetails = new ProvidersCommonDetails();

                    if (attachedFile != null) {
                        providersCommonDetails.setDocName(attachedFile.getOriginalFilename());
                        providersCommonDetails.setDocContentType(attachedFile.getContentType());
                    }
                    providersCommonDetails.setCreated(new Date());
                    providersCommonDetails.getCreatedBy().setId(userId);
                    providersCommonDetails.setServiceDetails(provider.getProvidersCommonDetails().getServiceDetails());
                    providersCommonDetails.setServiceStartDate(provider.getProvidersCommonDetails().getServiceStartDate());
                    providersCommonDetails.setServiceEndDate(provider.getProvidersCommonDetails().getServiceEndDate());
                    providersCommonDetails.getExpenditurePeriod().setId(provider.getProvidersCommonDetails().getExpenditurePeriod().getId());
                    providersCommonDetails.setProvDelegacionId(providersByWorkCenters.getId());
                    providersCommonDetails.setAnualSpending(provider.getProvidersCommonDetails().getAnualSpending());
                    providersCommonDetails.setSpending(provider.getProvidersCommonDetails().getSpending());
                    providersCommonDetails.setDocName(provider.getProvidersCommonDetails().getDocName());
                    providersCommonDetails.setDocUrl(provider.getProvidersCommonDetails().getDocUrl());
                    providersCommonDetails.setDocContentType(provider.getProvidersCommonDetails().getDocContentType());

                    ProvidersCommonDetails providersComDetails = this.providersCommonDetailsRepository.save(providersCommonDetails);

                    if (attachedFile != null) {
                        //Borramos el documento anterior del servidor
                        commonService.deleteDocumentServer(workCenter.getId(), providersComDetails.getId(), PROVIDER_DOCUMENTS);

                        String url = null;

                        // Guardamos documento en el server
                        url = commonService.saveDocumentServer(workCenter.getId(), providerId, attachedFile, PROVIDER_DOCUMENTS);

                        // Actualizamos la ruta del documento guardado
                        if (url != null) {
                            this.providersCommonDetailsRepository.updateProviderDocUrl(providersComDetails.getId(), url);
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

            }

            if (workCenterId != 0) {
                // Seteamos los valores del objeto
                ProvidersByWorkCenters providersByWorkCenters = new ProvidersByWorkCenters();
                providersByWorkCenters.getProvider().setId(provider.getId());
                providersByWorkCenters.getWorkCenter().setId(workCenterId);

                providersByWorkCentersRepository.save(providersByWorkCenters);

                ProvidersCommonDetails commonDetails = new ProvidersCommonDetails();

                if (attachedFile != null) {
                    commonDetails.setDocName(attachedFile.getOriginalFilename());
                    commonDetails.setDocContentType(attachedFile.getContentType());
                }
                commonDetails.setCreated(new Date());
                commonDetails.getCreatedBy().setId(userId);
                commonDetails.setDocUrl(provider.getProvidersCommonDetails().getDocUrl());
                commonDetails.setDocName(provider.getProvidersCommonDetails().getDocName());
                commonDetails.setServiceDetails(provider.getProvidersCommonDetails().getServiceDetails());
                commonDetails.setServiceStartDate(provider.getProvidersCommonDetails().getServiceStartDate());
                commonDetails.setServiceEndDate(provider.getProvidersCommonDetails().getServiceEndDate());
                commonDetails.getExpenditurePeriod().setId(provider.getProvidersCommonDetails().getExpenditurePeriod().getId());
                commonDetails.setProvDelegacionId(providersByWorkCenters.getId());
                commonDetails.setAnualSpending(provider.getProvidersCommonDetails().getAnualSpending());
                commonDetails.setSpending(provider.getProvidersCommonDetails().getSpending());

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
    public ResponseEntity<?> downloadProviderDoc(HttpServletRequest request, int workCenterId, int providerId) {

        Provider provider = null;
        File file = null;
        byte[] content=null;

        try {
            String docUrl = this.providerCustomRepository.findDocUrlByProviderId(providerId, workCenterId);

            file = new File(docUrl);
            if (file.exists()) {
                content = Files.readAllBytes(file.toPath());
            }else{
                return new ResponseEntity<>("File not found",HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<byte[]>(content, HttpStatus.OK);
    }

   }

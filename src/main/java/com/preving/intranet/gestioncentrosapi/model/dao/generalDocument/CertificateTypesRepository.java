package com.preving.intranet.gestioncentrosapi.model.dao.generalDocument;

import com.preving.intranet.gestioncentrosapi.model.domain.generalDocumentation.CertificateTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateTypesRepository extends JpaRepository<CertificateTypes, Integer> {
 List<CertificateTypes> findAll();
}

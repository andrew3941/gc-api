package com.preving.intranet.gestioncentrosapi.model.dao.workers;


import com.preving.intranet.gestioncentrosapi.model.domain.workers.Employees;
import com.preving.intranet.gestioncentrosapi.model.dto.workers.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkersRepository extends JpaRepository<Employees, Long> {
    Optional<Employees> findById(Long id);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionId(int delegacionId);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionIdAndIdAndEmpLabHistoryAreaDepartmentId(int delegacionId,Long idEmployee,int idDepartment);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionIdAndId(int delegacionId,Long idEmployee);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionIdAndEmpLabHistoryAreaDepartmentId(int delegacionId,int idDepartment);
    List<Employees> findAllByName(String name);


}

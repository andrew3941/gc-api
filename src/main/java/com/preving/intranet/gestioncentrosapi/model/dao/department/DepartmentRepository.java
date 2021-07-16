package com.preving.intranet.gestioncentrosapi.model.dao.department;

import com.preving.intranet.gestioncentrosapi.model.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAllByOrderByName();
}

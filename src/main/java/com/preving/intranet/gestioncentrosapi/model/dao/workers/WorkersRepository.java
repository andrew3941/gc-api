package com.preving.intranet.gestioncentrosapi.model.dao.workers;


import com.preving.intranet.gestioncentrosapi.model.domain.workers.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkersRepository extends JpaRepository<Employees, Integer> {

    List<Employees> findAllByName(String name);


}

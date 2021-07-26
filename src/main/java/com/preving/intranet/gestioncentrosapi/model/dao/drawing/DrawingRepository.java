package com.preving.intranet.gestioncentrosapi.model.dao.drawing;

import com.preving.intranet.gestioncentrosapi.model.domain.Drawing;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrawingRepository extends JpaRepository<Drawing, Integer> {

    List<Drawing> findByWorkCenter(WorkCenter workCenter);

    void deleteByWorkCenter(WorkCenter workCenter);
}

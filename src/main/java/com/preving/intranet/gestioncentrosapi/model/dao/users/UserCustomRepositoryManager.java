package com.preving.intranet.gestioncentrosapi.model.dao.users;

import com.preving.intranet.gestioncentrosapi.model.domain.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCustomRepositoryManager implements UserCustomRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<User> findUserByCriterion(String criterion) {

        String sql = "" +
                "SELECT ID, NOMBRE, APELLIDOS," +
                "   APELLIDOS || ', ' || NOMBRE NOMBRE_COMPLETO " +
                "FROM GC2006_RELEASE.PC_USUARIOS " +
                "WHERE ACTIVO = 1 AND DESHABILITADO = 0 ";

        if(criterion != null && !criterion.equals("")) {

            sql += "AND LOWER(TRANSLATE(NOMBRE, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) " +
                    "LIKE LOWER(TRANSLATE(:criterion, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) " +
                    "OR LOWER(TRANSLATE(APELLIDOS, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) " +
                    "LIKE LOWER(TRANSLATE(:criterion, '·ÈÌÛ˙Ò¡…Õ”⁄—', 'aeiounAEIOUN')) ";

        }

        sql += "ORDER BY TRIM(LEADING FROM APELLIDOS)";

        Query query = manager.createNativeQuery(sql);

        if(criterion != null && !criterion.equals("")) query.setParameter("criterion", "%" + criterion + "%");

        return mappingUsers(query.getResultList());

    }

    @Override
    public List<User> findAdminsAndManagers(){
        String sql = "" +
                "SELECT u.ID, u.NOMBRE, u.APELLIDOS," +
                " u.APELLIDOS || ', ' || u.NOMBRE NOMBRE_COMPLETO , u.EMAIL " +
                "FROM GC2006_RELEASE.PC_USUARIOS u " +
                "INNER JOIN GC2006_RELEASE.PC_USUARIOSGRUPOS ug " +
                "ON u.id = ug.usuario_id " +
                " WHERE  ug.grupo_id = 25102 OR ug.grupo_id = 25103";

        Query query = manager.createNativeQuery(sql);

        return mappingUserWithEmail(query.getResultList());
    }

    private List<User> mappingUsers(List<Object[]> users) {

        List<User> mappedUsers = new ArrayList<>();

        users.forEach((record) -> {
            User user = new User(((BigDecimal) record[0]).longValue(), ((String) record[1]), (String) record[2], (String) record[3]);
            mappedUsers.add(user);
        });

        return mappedUsers;

    }

    private List<User> mappingUserWithEmail(List<Object[]> users){
        List<User> mappedUsers = new ArrayList<>();

        users.forEach((record) -> {
            User user = new User(((BigDecimal) record[0]).longValue(), ((String) record[1]), (String) record[2], (String) record[3],(String) record[4]);
            mappedUsers.add(user);
        });
        return mappedUsers;
    }

}

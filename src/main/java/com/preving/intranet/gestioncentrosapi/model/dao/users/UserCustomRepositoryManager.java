package com.preving.intranet.gestioncentrosapi.model.dao.users;

import com.preving.intranet.gestioncentrosapi.model.domain.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserCustomRepositoryManager implements UserCustomRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<User> findUserByCriterion(String criterion) {

        String sql = "" +
                "SELECT ID, FIRSTNAME, LASTNAME, EMAIL, USERNAME " +
                "   FROM gc2006_release.pc_usuarios " +
                "   WHERE LOWER(TRANSLATE(USERNAME, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN')) " +
                "       LIKE LOWER(TRANSLATE(:criterion, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN'))  " +
                "       OR LOWER(TRANSLATE(FIRSTNAME, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN')) " +
                "       LIKE LOWER(TRANSLATE(:criterion, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN')) " +
                "       OR LOWER(TRANSLATE(LASTNAME, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN')) " +
                "       LIKE LOWER(TRANSLATE(:criterion, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN'))  " +
                "       OR LOWER(TRANSLATE(EMAIL, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN')) " +
                "       LIKE LOWER(TRANSLATE(:criterion, 'αινσϊρΑΙΝΣΪΡ', 'aeiounAEIOUN')) ";

                Query query = manager.createNativeQuery(sql).setParameter("criterion", "%" + criterion + "%");

        return query.getResultList();

    }

}

package com.preving.intranet.gestioncentrosapi.model.dao.users;

import com.preving.intranet.gestioncentrosapi.model.domain.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> findUserByCriterion(String criterion);

}

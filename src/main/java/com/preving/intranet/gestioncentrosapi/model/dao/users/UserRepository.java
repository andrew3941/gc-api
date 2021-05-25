package com.preving.intranet.gestioncentrosapi.model.dao.users;

import com.preving.intranet.gestioncentrosapi.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Long userId);


}

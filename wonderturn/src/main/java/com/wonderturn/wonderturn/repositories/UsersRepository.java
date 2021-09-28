package com.wonderturn.wonderturn.repositories;

import com.wonderturn.wonderturn.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}

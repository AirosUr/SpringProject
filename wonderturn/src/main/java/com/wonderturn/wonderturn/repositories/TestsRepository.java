package com.wonderturn.wonderturn.repositories;

import com.wonderturn.wonderturn.models.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestsRepository extends CrudRepository<Test, Long> {
}
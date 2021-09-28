package com.wonderturn.wonderturn.repositories;

import com.wonderturn.wonderturn.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends CrudRepository<Question, Long> {
}

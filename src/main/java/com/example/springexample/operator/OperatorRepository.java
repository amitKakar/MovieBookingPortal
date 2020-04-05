package com.example.springexample.operator;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.springexample.operator.Operator;


public interface OperatorRepository extends CrudRepository<Operator, Integer>{


	@Query("SELECT operatorId FROM Operator u WHERE u.operatorName = :operatorName")
	public List<Operator> findIdByOperatorName(@Param("operatorName") String operatorName);

	@Modifying
	@Query("UPDATE Operator u SET  u.password=:password WHERE u.operatorId=:operatorId")
	public void updateOperator(@Param("operatorId") int userId, @Param("password") String password);
}

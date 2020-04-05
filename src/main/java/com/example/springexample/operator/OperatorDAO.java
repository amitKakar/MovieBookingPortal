package com.example.springexample.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springexample.global.Globals;
@Service
public class OperatorDAO {
	@Autowired
	OperatorRepository operatorRepository;
	private Integer operatorId;
	
	public Operator addOperator(Operator operator)
	{
		if(Globals.operatorMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		String upperCaseOperatorName = operator.getOperatorName().toUpperCase();
		operator.setOperatorName(upperCaseOperatorName);
		
		Operator savedOperator = operatorRepository.save(operator);
		Globals.operatorMap.put(operator.getOperatorName(), operator.getOperatorId());
		
		return savedOperator;
	}
	
	public void populateMapFromDatabase()
	{
		List<Operator> allOperatorsList = getAllOperator();
		if(null != allOperatorsList)
		{
			for(Operator operator: allOperatorsList) 
			{
				Globals.operatorMap.put(operator.getOperatorName(), operator.getOperatorId());
			}
		}
		
	}
	
	 public List<Operator> getAllOperator( ) {
		
		List<Operator>operatorList=new ArrayList<>();
		
		operatorRepository.findAll().forEach(operatorList::add);
		
		
		return operatorList;
	}
	
	public Operator getOperatorById(int operatorId)
	{
		Optional<Operator> operatorContainer = operatorRepository.findById(operatorId);
		return operatorContainer.get();
	}
	
	public Operator getOperatorByOperatorName(String operatorName)
	{
		
		if(Globals.operatorMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		Integer operatorId=Globals.operatorMap.get(operatorName);
		if(null != operatorId)
		{
			return getOperatorById(operatorId);
		}
		
		return null;
	}
	
	@Transactional
	public void updateOperator(String operatorName, Operator updatedOperator)
	{
		if(Globals.operatorMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		operatorId = Globals.operatorMap.get(operatorName);
		if(null==operatorId)
		{
			operatorRepository.updateOperator(operatorId, updatedOperator.getPassword());
		}
		operatorRepository.updateOperator(operatorId, updatedOperator.getPassword());
	}
	
	public void deleteOperatorById(int operatorId)
	{
		operatorRepository.deleteById(operatorId);
	}
	
	public void deleteOperatorByName(String operatorName)
	{
		if(Globals.operatorMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		Integer operatorId = Globals.userMap.get(operatorName);
		if(null != operatorId)
		{
			deleteOperatorById(operatorId);
			Globals.userMap.remove(operatorName);
		}
	}
	
	//*******for custom queries********

		public List<Operator> findIdByOperatorName(String operatorName) {

		return operatorRepository.findIdByOperatorName(operatorName);
	}

	//Not Working	
		
		public Operator authenticate( Operator operator) {
			String upperCaseOperatorName = operator.getOperatorName().toUpperCase();
			operator.setOperatorName(upperCaseOperatorName);
			Operator DBUser = getOperatorByUserName(operator.getOperatorName());
			if(DBUser != null)
			{
				if(DBUser.getPassword().equals(operator.getPassword()))
				{
					//setStatusBitInDB(DBUser.getUserId());
					return DBUser;
				}
				else
				{
					return null;
				}
			}
			
			return null;
		}
		 
		public Operator getOperatorByUserName(String opearatorName)
		{
			if(Globals.operatorMap.isEmpty())
			{
				populateMapFromDatabase();
			}
			
			Integer operatorId=Globals.operatorMap.get(opearatorName);
			if(null != operatorId)
			{ 
				return getOpearatorById(operatorId);
			}
			return null;
		}
		
		public Operator getOpearatorById(int operatorId)
		{
			Optional<Operator> operatorContainer = operatorRepository.findById(operatorId);
			return operatorContainer.get();
		}
}
	
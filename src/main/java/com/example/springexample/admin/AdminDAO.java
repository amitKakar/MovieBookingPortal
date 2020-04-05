package com.example.springexample.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springexample.global.Globals;

@Service
public class AdminDAO {
	
	@Autowired
	AdminRepository adminRepository;
	
	
	public void populateMapFromDatabase() 
	{
		List<Admin> allAdminList = getAllAdmin();
		if (null != allAdminList) 
		{
			for (Admin admin : allAdminList) {
				Globals.adminMap.put(admin.getAdminName(), admin.getAdminId());
			}
		}

	}
	
	public Admin addAdmin(Admin admin)
	{
		if(Globals.adminMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		String upperCaseAdminName = admin.getAdminName().toUpperCase();
		admin.setAdminName(upperCaseAdminName);
		
		Admin savedAdmin = adminRepository.save(admin);
		Globals.adminMap.put(admin.getAdminName(), admin.getAdminId());
		
		return savedAdmin;
	}
	
	public Admin getAdminById(int adminId) {
		Optional<Admin> adminContainer = adminRepository.findById(adminId);
		return adminContainer.get();
	}
	
	public Admin getAdminByName(String adminName) {
		
		if (Globals.adminMap.isEmpty()) 
		{
			populateMapFromDatabase();
		}

		Integer adminId = Globals.adminMap.get(adminName);
		if (null != adminId) {
			return getAdminById(adminId);
		}
		return null;
	}

	public List<Admin> getAllAdmin() {

		List<Admin> adminList = new ArrayList<>();

		adminRepository.findAll().forEach(adminList::add);

		return adminList;
	}
	
	public Admin authenticate(Admin admin) {

		if (null == admin) {
			return null;
		}
		
		String upperCaseAdminName = admin.getAdminName().toUpperCase();
		admin.setAdminName(upperCaseAdminName);
		
		Admin DBUser = getAdminByName(admin.getAdminName());
		
		if (DBUser != null) {
			if (DBUser.getPassword().equals(admin.getPassword())) {

				return DBUser;
			} 
			else 
			{
				return null;
			}
		}

		return null;
	}

	
}

package com.example.demo.Service;

import java.util.List;

import com.example.demo.dtos.UserDto;
import com.example.demo.modeles.User;



public interface DoctorService {
	// Read operation
		List<User> Doctor();
		
	// Save operation
		UserDto saveDoctor(User doctor);
		
	// Update operation
		User updateCategories(User alimentation, Long id);

	// Delete operation
		void deleteDoctorByRollNumber (long Id);

		List<User> client();

		int NBdoctor();

}

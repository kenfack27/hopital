package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UserDto;
import com.example.demo.enums.UserRole;
import com.example.demo.modeles.User;
import com.example.demo.repository.UserRepository;
@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private UserRepository userRepository ;

	@Override
	public List<User> Doctor() {
		return (List<User>)
				userRepository.showMedecin();	}
	

	@Override
	public UserDto saveDoctor(User doctor) {
		User user = new User();
		user.setName(doctor.getName());
	    user.setEmail(doctor.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
		user.setRole(doctor.getRole());
		user.setUserRole(UserRole.DOCTOR);
		User createdUser = userRepository.save(user);
		UserDto createdUserDto = new UserDto();
		createdUserDto = new UserDto();
		createdUserDto.setId(createdUser.getId());
		createdUserDto.setName(createdUser.getName());
		createdUserDto.setEmail(createdUser.getEmail());
		createdUserDto.setRole(createdUser.getRole());
		createdUserDto.setUserRole(createdUser.getUserRole());
		return createdUserDto;

	}

	@Override
	public User updateCategories(User alimentation, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDoctorByRollNumber(long Id) {
		// TODO Auto-generated method stub
		userRepository .deleteById(Id);

	}


	@Override
	public List<User> client() {
		return (List<User>)
				userRepository.showClient();	}
	@Override
	public int  NBdoctor() {
		return 
				userRepository.showDoctor();	}

}

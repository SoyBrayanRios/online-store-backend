package com.app.store;

import com.app.store.entity.Role;
import com.app.store.entity.User;
import com.app.store.entity.UserRole;
import com.app.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OnlineStoreBackendApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*try{
			User user = new User();
			user.setName("Brian");
			user.setLastName("Rios");
			user.setUsername("BrianAdmin");
			user.setPassword(bCryptPasswordEncoder.encode("AdBriShop1*"));
			user.setEmail("soybrayanrios@gmail.com");
			user.setPhone("3228189019");
			user.setProfile("photo.png");
			Role role = new Role();
			role.setId(1L);
			role.setRoleName("ADMIN");
			Set<UserRole> userRoles = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);
			userRoles.add(userRole);
			userService.saveUser(user, userRoles);
		}catch (Exception exception){
			exception.printStackTrace();
		}*/
	}

}

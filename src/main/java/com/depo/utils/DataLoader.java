package com.depo.utils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.depo.domain.Role;
import com.depo.domain.User;
import com.depo.enums.RoleType;
import com.depo.repository.RoleRepository;
import com.depo.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		loadData();
	}

	public DataLoader(@Lazy PasswordEncoder passwordEncoder) {
		super();

		this.passwordEncoder = passwordEncoder;

	}

	private void loadData() {

		Role role1 = new Role();
		role1.setId(1);
		role1.setType(RoleType.ROLE_ADMIN);
		roleRepository.save(role1);
	

		Role role2 = new Role();
		role2.setId(2);
		role2.setType(RoleType.ROLE_CUSTOMER);
		roleRepository.save(role2);



		User admin = new User();

		Set<Role> roles1 = new HashSet<>();
		roles1.add(role1);
		
		admin.setFirst_name("Admin");
		admin.setLast_name("Admin");
		admin.setPhone("555-555-5555");		
		admin.setEmail("admin@warehouse.com");
		admin.setStatus((byte) 1);
		admin.setCreate_at(LocalDate.now());		
		admin.setRoles(roles1);
		admin.setBuilt_in(true);
		String encodedPassword = passwordEncoder.encode("12345");
		admin.setPassword(encodedPassword);

		userRepository.save(admin);
		
		///////////////////////////////
		
		User customer = new User();

		Set<Role> roles2 = new HashSet<>();
		roles2.add(role2);

		customer.setFirst_name("Customer");
		customer.setLast_name("customer");
		customer.setPhone("555-555-5555");		
		customer.setEmail("customer@warehouse.com");
		customer.setStatus((byte) 1);
		customer.setCreate_at(LocalDate.now());
		customer.setRoles(roles2);
		customer.setBuilt_in(false);
		String encodedPassword2 = passwordEncoder.encode("12345");
		customer.setPassword(encodedPassword2);

		userRepository.save(customer);

	}

}

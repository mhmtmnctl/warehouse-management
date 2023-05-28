package com.depo.service;


import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.depo.domain.Role;
import com.depo.domain.User;
import com.depo.enums.RoleType;
import com.depo.mapper.UserMapper;
import com.depo.repository.UserRepository;
import com.depo.requestDTO.UserRequestDTO;
import com.depo.responseDTO.UserResponseDTO;


public class TestUserService {

	    @Mock
	    private UserRepository userRepository;

	    @Mock
	    private RoleServiceImpl roleService;

	    @Mock
	    private PasswordEncoder passwordEncoder;

	    @Mock
	    private UserMapper userMapper;

	    @InjectMocks
	    private UserServiceImpl userService;


	  

		@Test
	    public void testGetUserByEmail() {
	        // Test için kullanılacak e-posta
	        String email = "admin@warehouse";
	        
	        Role role = roleService.findByType(RoleType.ROLE_ADMIN);
			Set<Role> roles = new HashSet<>();
			roles.add(role);

	        // Mock userRepository kullanarak findByEmail metodunun yanıtını ayarla
	        User mockUser = new User();
	        mockUser.setId(1L);
	        mockUser.setEmail(email);
	        mockUser.setFirst_name("Admin");
	        mockUser.setLast_name("Admin");
	        mockUser.setBuiltIn(true);
	        mockUser.setPassword("12345");
	        mockUser.setRoles(roles);
	        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

	        // getUserByEmail metodunu çağır ve sonucu doğrula
	        User user = userService.getUserByEmail(email);
	        assertEquals(mockUser, user);
	    }

	    @Test
	    public void testSaveUser() {
	        // Test için kullanılacak UserRequestDTO
	        UserRequestDTO requestDTO = new UserRequestDTO();
	        requestDTO.setEmail("testuser@example.com");
	        requestDTO.setPassword("password");
	        requestDTO.setFirst_name("test");
	        requestDTO.setLast_name("test");
	        requestDTO.setPassword("Password1!");
	        requestDTO.setPhone("555-555-5555");

	        // Mock userRepository.existsByEmail metodunun yanıtını ayarla
	        when(userRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);

	        // Mock roleService.findByType metodunun yanıtını ayarla
	        Role role = new Role();
	        role.setId(1);
	        role.setType(RoleType.ROLE_CUSTOMER);
	        when(roleService.findByType(RoleType.ROLE_CUSTOMER)).thenReturn(role);

	        // Mock passwordEncoder.encode metodunun yanıtını ayarla
	        String encodedPassword = "encodedPassword";
	        when(passwordEncoder.encode(requestDTO.getPassword())).thenReturn(encodedPassword);

	        // Mock userMapper.userRequestDTOToUser metodunun yanıtını ayarla
	        User mockUser = new User();
	        mockUser.setId(1L);
	        mockUser.setEmail(requestDTO.getEmail());
	        when(userMapper.userRequestDTOToUser(requestDTO)).thenReturn(mockUser);

	        // Mock userRepository.save metodunu kontrol etmek için bir Answer nesnesi oluştur
	        doAnswer(invocation -> {
	            User savedUser = invocation.getArgument(0);
	            savedUser.setId(1L); // savedUser'ın ID'sini ayarla
	            return savedUser;
	        }).when(userRepository).save(mockUser);

	        // saveUser metodunu çağır ve sonucu doğrula
	        UserResponseDTO responseDTO = userService.saveUser(requestDTO);
	        assertNotNull(responseDTO);
	        assertEquals(mockUser.getId(), responseDTO.getId());
	        assertEquals(mockUser.getEmail(), responseDTO.getEmail());
	        // Diğer özellikleri de doğrulayabilirsiniz
	    }

	    @Test
	    public void testGetUserByIdAdmin() {
	        // Test için kullanılacak kullanıcı ID'si
	        Long userId = 1L;

	        // Mock userRepository kullanarak findById metodunun yanıtını ayarla
	        User mockUser = new User();
	        mockUser.setId(userId);
	        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

	        // getUserByIdAdmin metodunu çağır ve sonucu doğrula
	        UserResponseDTO responseDTO = userService.getUserByIdAdmin(userId);
	        assertNotNull(responseDTO);
	        assertEquals(mockUser.getId(), responseDTO.getId());
	        // Diğer özellikleri doğrulayabilirsiniz
	    }

	    @Test
	    public void testUpdateAuthUser() {
	        // Mock getCurrentUser metodu için kullanıcıyı oluştur
	        User currentUser = new User();
	        currentUser.setId(1L);

	        // Mock getCurrentUser metodunu kullanarak kullanıcının döndürülmesini sağla
	        when(userService.getCurrentUser()).thenReturn(currentUser);

	        // Test için kullanılacak UserRequestDTO
	        UserRequestDTO requestDTO = new UserRequestDTO();
	        requestDTO.setEmail("updateduser@example.com");
	        requestDTO.setFirst_name("Updated");
	        requestDTO.setLast_name("User");
	        requestDTO.setPhone("1234567890");

	        // updateAuthUser metodunu çağır
	        UserResponseDTO responseDTO = userService.updateAuthUser(requestDTO);

	        // Sonucu doğrula
	        assertNotNull(responseDTO);
	        assertEquals(currentUser.getId(), responseDTO.getId());
	        assertEquals(requestDTO.getEmail(), responseDTO.getEmail());
	        assertEquals(requestDTO.getFirst_name(), responseDTO.getFirst_name());
	        assertEquals(requestDTO.getLast_name(), responseDTO.getLast_name());
	        assertEquals(requestDTO.getPhone(), responseDTO.getPhone());
	        // Diğer özellikleri doğrulayabilirsiniz
	    }

	    @Test
	    public void testDeleteAuthUserById() {
	        // Mock getCurrentUser metodu için kullanıcıyı oluştur
	        User currentUser = new User();
	        currentUser.setId(1L);

	        // Mock getCurrentUser metodunu kullanarak kullanıcının döndürülmesini sağla
	        when(userService.getCurrentUser()).thenReturn(currentUser);

	        // deleteAuthUserById metodunu çağır ve doğrula
	        assertDoesNotThrow(() -> userService.deleteAuthUserById());
	        verify(userRepository, times(1)).delete(currentUser);
	    }
}

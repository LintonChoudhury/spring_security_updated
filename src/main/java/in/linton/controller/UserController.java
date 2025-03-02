package in.linton.controller;

import java.util.Optional;

import org.aspectj.apache.bcel.generic.RET;
import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.linton.entity.AuthRequest;
import in.linton.entity.UserInfo;
import in.linton.service.JwtService;
import in.linton.service.UserInfoService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserInfoService service;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}

	@PostMapping("/resister")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}

	@GetMapping("/user/userProfile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userProfile() {
		return "Welcome to User Profile";
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminProfile() {
		return "Welcome to Admin Profile";
	}


	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestBody UserInfo u){
		
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword());
		
		try {
			Authentication authenticate = authManager.authenticate(token);
			if(authenticate.isAuthenticated()) {
				String jwtToken = jwtService.generateToken(u.getUsername());
				return new ResponseEntity<>(jwtToken,HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<String>("Invalid Credentials",HttpStatus.BAD_REQUEST);
	}
	

}

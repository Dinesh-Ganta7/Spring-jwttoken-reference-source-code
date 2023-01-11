package com.alibou.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibou.security.dto.UserDTO;
import com.alibou.security.service.UserService;
import com.alibou.security.user.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
	private final UserService userService;

	@PostMapping("/getUser")
	public ResponseEntity<UserDTO> getUser(@RequestBody UserDTO userDto) throws UsernameNotFoundException {

		UserDTO userResponse = userService.getUser(userDto.getEmail());
		if (userResponse == null) {
			throw new UsernameNotFoundException("User Not Found!");
		}

		return new ResponseEntity<UserDTO>(userResponse, HttpStatus.OK);

	}

	@GetMapping("/username")
	public ResponseEntity<Username> currentUsername(HttpServletRequest request) {
		Username username = Username.builder().build();
		username.setUsername(request.getUserPrincipal().getName());
		return new ResponseEntity<Username>(username, HttpStatus.OK);

	}

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Username {
	private String Username;
}

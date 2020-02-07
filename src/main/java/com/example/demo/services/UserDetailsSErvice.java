package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserDetailsSErvice implements UserDetailsService {

	@Autowired
	private UserRepository userREpo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Users> user = userREpo.findAll();
		if (user == null)
			throw new UsernameNotFoundException("User Not FOund");

		Users filteredUser = user.stream().filter((us) -> username.equals(us.getUsername())).findFirst().get();
		return new UserPrincipal(filteredUser);
	}

}

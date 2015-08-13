package demo.sample.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.sample.dao.LoginDAO;
import demo.sample.pojo.UserPOJO;


@Service
public class LoginServiceManager implements UserDetailsService {

	@Autowired
	LoginDAO loginDao;
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPOJO user = loginDao.authenticateUser(username);
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getAuthority());
		return buildUserForAuthentication(user, authorities);
	}
	
	private User buildUserForAuthentication(UserPOJO user,List<GrantedAuthority> authorities) {
		return new User(user.getUsername(),user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
	 
	private List<GrantedAuthority> buildUserAuthority(String userRole) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRole));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
}

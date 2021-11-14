package com.booklibrary.demo.security;

import com.booklibrary.demo.constant.EntityStatus;
import com.booklibrary.demo.constant.MessageConstant;
import com.booklibrary.demo.exception.ForbiddenException;
import com.booklibrary.demo.exception.NotFoundException;
import com.booklibrary.demo.model.Useraccount;
import com.booklibrary.demo.repo.UseraccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@Qualifier("customUserDetailsService")
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private UseraccountRepo useraccountRepo;



	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Useraccount account = useraccountRepo.findByEmail(email);

		// validate account
		if (account == null)
			throw new NotFoundException(MessageConstant.INVALID_CREDENTIALS);


		if (!account.getStatus().equals(EntityStatus.ACTIVE)) {
			throw new ForbiddenException(MessageConstant.INACTIVE_ACCOUNT);
		}

		else {
			String role = account.getRole().name();

			Set<GrantedAuthority> grantedAuthorities = new HashSet();
			grantedAuthorities.add(new SimpleGrantedAuthority(role));

			return new User(account.getEmail(), account.getPassword(),
					grantedAuthorities);
		}
	}

}

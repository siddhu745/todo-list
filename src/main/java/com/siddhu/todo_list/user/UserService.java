package com.siddhu.todo_list.user;

import com.siddhu.todo_list.exception.DuplicateResourceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDto userDto) throws Exception {
        if(userDao.existsByEmail(userDto.email())) {
            throw new DuplicateResourceException("email already exists");
        }
        User user = new User(
                userDto.username(),
                userDto.email(),
                passwordEncoder.encode(userDto.password())
        );
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}

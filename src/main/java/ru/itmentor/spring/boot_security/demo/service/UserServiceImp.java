package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Role getRoleById(long id) {
        return userDao.getRoleById(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public Set<Role> getRoles() {
        return userDao.getRoles();
    }

    @Transactional
    @Override
    public Set<Role> getRole(Set<String> rolesId) {
        Set<Role> roles = new HashSet<>();
        for (String id : rolesId) {
            roles.add(getRoleById(Long.parseLong(id)));
        }
        return roles;
    }

    @Transactional
    @Override
    public void updateUser(long id, User updatedUser){
        userDao.updateUser(id, updatedUser);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        userDao.saveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", login));
        }
        return user;
    }
}
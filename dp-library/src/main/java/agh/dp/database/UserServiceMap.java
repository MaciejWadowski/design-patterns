package agh.dp.database;

import agh.dp.facade.RoleWithPermissionsFacade;
import agh.dp.models.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceMap implements UserService {
    private final UserRepository userRepository;

    public UserServiceMap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String userName) {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users.stream().
                filter(user -> user.getUserName().equals(userName)).
                findFirst().orElse(null);
    }

    @Override
    public List<User> findAllByUserNameLike(String lastName) {
        return null;
    }
}

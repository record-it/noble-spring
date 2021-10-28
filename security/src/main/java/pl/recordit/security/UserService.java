package pl.recordit.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class UserService implements UserDetailsService {
    private Map<String, User> users = Map.of(
            "adam", User.builder().username("adam").password("").build(),
            "ewa", User.builder().username("ewa").password("$2a$12$SFl2qCo8u55ocMl4q1V2oua7w5garz1vFkqb6Yah/t1vMzI7yJ8hG").build()
    );
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return users.get(s);
    }
}

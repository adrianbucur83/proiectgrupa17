package ro.siit.proiectgrupa17.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.siit.proiectgrupa17.model.User;
import ro.siit.proiectgrupa17.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDatabase = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new MyUserDetails(userFromDatabase);
    }
}

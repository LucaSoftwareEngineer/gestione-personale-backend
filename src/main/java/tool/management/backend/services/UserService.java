package tool.management.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tool.management.backend.dto.RegisterRequest;
import tool.management.backend.models.Company;
import tool.management.backend.models.User;
import tool.management.backend.repositories.CompanyRepository;
import tool.management.backend.repositories.UserRepository;
import tool.management.backend.security.JwtUtil;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    public void save(RegisterRequest request) {
        Company company = companyRepository.findById(request.getIdCompany()).get();
        String hashedPassword = passwordEncoder.encode(request.getRawPassword());
        User user = new User(null, request.getUsername(), hashedPassword, request.getRole(), request.getName(),
                request.getSurname(), company);
        userRepository.save(user);
    }

    public Company getCompanyOfUser(String token) {
        String username = jwtUtil.extractUsername(token);
        User user = userRepository.findByUsername(username).get();
        return user.getCompany();
    }

}

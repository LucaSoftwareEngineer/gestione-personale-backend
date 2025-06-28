package tool.management.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String username;
    private String rawPassword;
    private String role;
    private String name;
    private String surname;
    private Long idCompany;

}

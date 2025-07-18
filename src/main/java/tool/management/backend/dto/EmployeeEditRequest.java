package tool.management.backend.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeEditRequest {

    private String nome;
    private String cognome;
    private Date dataNascita;
    private Date dataAssunzione;
    private float ral;
    private Date dataLicenziamento;

}

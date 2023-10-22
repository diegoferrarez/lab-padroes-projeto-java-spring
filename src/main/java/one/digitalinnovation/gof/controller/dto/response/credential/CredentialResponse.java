package one.digitalinnovation.gof.controller.dto.response.credential;

import one.digitalinnovation.gof.model.Token;
import one.digitalinnovation.gof.model.enums.StatusLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialResponse {

    private String id;
    private String unit;
    private String serialNumberUnit;
    private Token credential;
    private StatusLogin statusCorp;
}

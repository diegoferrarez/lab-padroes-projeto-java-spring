package one.digitalinnovation.gof.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credential {
    private String unit;
    private String serialNumberUnit;
}

package api.data.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TariffPlan {

    private String code;
    private String name;
    private Object systemName;
    private Object minAmount;
    private Object maxAmount;
}

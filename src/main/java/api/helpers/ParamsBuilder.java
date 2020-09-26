package api.helpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParamsBuilder {

    private String product;
    private String design;
    private String paymentSystem;

    public static Map<String, Object> paramsToMap(ParamsBuilder paramsBuilder){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(paramsBuilder, Map.class);
    }
}

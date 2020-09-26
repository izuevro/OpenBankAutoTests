package api.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityData {
    private int id;
    private String title;
    private String code;
    private String regionTitle;

    @Step("Создать объект с id:\"{id}\", title:\"{city}\"")
    public static CityData createCityData(int id, String city) {
        return CityData.builder()
                .id(id)
                .title(city)
                .build();
    }
}

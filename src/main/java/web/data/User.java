package web.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataconfig.DataConfig;
import io.qameta.allure.Step;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String surname;
    private String name;
    private String patronymic;
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private String cardExpDate;
    private String cardCvc;
    private String amount;
    private String cityDelivery;

    /**
     * Метод создания POJO из json-файла
     */

    @Step("Создать объект класса User из json-файла")
    public User getUserFromConfig(DataConfig testDataConfig) {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(testDataConfig.userData());
        try {
            return mapper.readValue(jsonFile, User.class);
        } catch (IOException e) {
            throw new IllegalStateException("Can't read file");
        }
    }
}

package web.data;

import api.data.MobileProviderData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dataconfig.DataConfig;
import io.qameta.allure.Step;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

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
    public User getUserFromConfig(DataConfig dataConfig) {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(dataConfig.userData());
        try {
            return mapper.readValue(jsonFile, User.class);
        } catch (IOException e) {
            throw new IllegalStateException("Can't read file");
        }
    }

    @Step("Создать рандомный объект класса User из json-файла")
    public User getRandomUserFromConfig(DataConfig dataConfig){
        Random rn = new Random();
        int id = rn.nextInt(2) + 1;
        try {
            String content = new String(Files.readAllBytes(Paths.get(dataConfig.userData())), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            List<?> list = JsonPath.parse(content).read("$[?(@.id=='" + id + "')]");
            return mapper.convertValue(list.get(0), User.class);
        } catch (IOException e) {
            throw new IllegalStateException("Can't read file");
        }
    }
}

package api.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dataconfig.DataConfig;
import io.qameta.allure.Step;
import lombok.Data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileProviderData {

    private String serviceId;
    private String payeeTitle;
    private String subline;
    private String icon;
    private String inn;
    private String processPaymentType;
    private String checkNecessity;
    private boolean payeeHidden;

    /**
     * Считываем json-файл в String "content"
     * Парсим "content", используя JsonPath. Находим объект с нужным serviceId из массива
     * Конвертируем полученный объект через ObjectMapper в POJO
     */

    @Step("Создать объект класса MobileProviderData для serviceId=\"{serviceId}\" из json-файла")
    public static MobileProviderData getMobileProviderFromConfig(DataConfig dataConfig, String serviceId) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(dataConfig.MobileProviderData())), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            List<?> list = JsonPath.parse(content).read("$[?(@.serviceId=='" + serviceId + "')]");
            return objectMapper.convertValue(list.get(0), MobileProviderData.class);
        } catch (IOException e) {
            throw new IllegalStateException("Can't read file");
        }
    }
}

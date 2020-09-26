package api.data.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardsData {

    private int id;
    private Product product;
    private CardType cardType;
    private TariffPlan tariffPlan;
    private CardDesign cardDesign;
    private List<String> currencies;

    /**
     * Считываем json-файл в String "content"
     * Парсим "content", используя JsonPath. Находим объект с нужным Id из массива
     * Конвертируем полученный объект через ObjectMapper в POJO
     */

    @Step("Создать объект класса CardsData для вида карт \"{type}\" и id=\"{id}\" из json-файла")
    public static CardsData getCardsFromConfig(DataConfig dataConfig, String type, int id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if ("debit".equals(type)) {
                String content = new String(Files.readAllBytes(Paths.get(dataConfig.DebitCardsData())), StandardCharsets.UTF_8);
                List<?> list = JsonPath.parse(content).read("$[?(@.id=='" + id + "')]");
                return mapper.convertValue(list.get(0), CardsData.class);
            } else {
                String content = new String(Files.readAllBytes(Paths.get(dataConfig.CreditCardsData())), StandardCharsets.UTF_8);
                List<?> list = JsonPath.parse(content).read("$[?(@.id=='" + id + "')]");
                return mapper.convertValue(list.get(0), CardsData.class);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't read file");
        }
    }

}

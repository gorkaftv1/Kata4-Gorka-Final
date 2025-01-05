package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Wood;

import java.util.ArrayList;
import java.util.List;

public class TsvWoodDeserializer implements Deserializer {
    @Override
    public Wood deserialize(String line) {
        String[] fields = line.split("\t");
        return new Wood(
                fields[0],
                fields[1],
                getContinentOf(normalize(fields[2])),
                getTone(normalize(fields[3])),
                getCountry(normalize(fields[4])),
                getQuality(normalize(fields[5])),
                getPrice(fields[6]),
                getExportCountries(normalize(fields[7]))
        );
    }

    protected static List<Wood.Country> getExportCountries(String normalize) {
        String[] stringCountries = normalize.split(",");
        List<Wood.Country> exports = new ArrayList<>();
        for (String c : stringCountries) exports.add(getCountry(c.trim()));
        return exports;
    }

    private Wood.Quality getQuality(String field) {
        return Wood.Quality.valueOf(field);
    }

    private Wood.Continent getContinentOf(String field) {
        return Wood.Continent.valueOf(normalize(field));
    }

    private String normalize(String field) {
        return field.replace(" ", "");
    }

    private Float getPrice(String field) {
        return Float.valueOf(field);
    }

    private static Wood.Country getCountry(String field) {
        return (field.isEmpty())  ? Wood.Country.valueOf("Canada") : Wood.Country.valueOf(field) ;
    }

    private Wood.ToneColor getTone(String field) {
        return Wood.ToneColor.valueOf(field);
    }
}

package software.ulpgc.arquitecture.model;

import java.util.List;

public record Wood(String id,
                   String name,
                   Continent continent,
                   ToneColor tone,
                   Country country,
                   Quality quality,
                   Float pricePerSquareMeters,
                   List<Country> exportCountry) {

    public enum Continent {
        NorthAmerica,
        Europe,
        SouthAsia,
        Asia,
        CentralAmerica
    }
    public enum Country {
        USA,
        Brazil,
        Germany,
        SouthAfrica,
        Finland,
        Australia,
        Sweden,
        Netherlands,
        Canada,
        France,
        Portugal,
        Nepal,
        Morocco,
        Lebanon,
        Honduras,
        Spain,
        India,
        Japan,
        Italy,
        Poland,
        SouthKorea;
    }
    public enum ToneColor {
        White,
        DarkBrown,
        LightBrown,
        GoldenBrown,
        ReddishBrown,
        RedBrown,
        Yellow,
        LightYellow,
        Pale
    }
    public enum Quality {
        Standard,Medium,High,Premium
    }
}



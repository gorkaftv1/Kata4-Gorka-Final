package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Wood;

public interface Deserializer {
    Wood deserialize(String line);
}

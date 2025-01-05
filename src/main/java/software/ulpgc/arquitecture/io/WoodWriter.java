package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Wood;

public interface WoodWriter extends AutoCloseable{
    void write(Wood wood);
}

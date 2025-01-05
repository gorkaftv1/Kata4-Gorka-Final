package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Wood;

import java.util.Optional;

public interface WoodReader extends AutoCloseable{
    Optional<Wood> read();
}

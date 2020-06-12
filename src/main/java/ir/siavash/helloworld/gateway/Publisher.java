package ir.siavash.helloworld.gateway;

import java.io.IOException;

public interface Publisher {
    void publishDirectCommand(String message) throws IOException;

    void publishSpeedCommand(String message) throws IOException;
}

package view;

import java.io.IOException;

public interface ImageView {
    void renderMessage(String message) throws IOException;

    void displayWelcomeMessage() throws IOException;
}

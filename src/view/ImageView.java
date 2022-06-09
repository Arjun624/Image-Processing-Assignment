package view;

import java.io.IOException;

/**
 * Interface for how the user can view messages given by the program.
 */
public interface ImageView {
    /**
     * Renders the inputted message.
     * @param message the inputted message
     * @throws IOException if the program can't read the input or write the output
     */
    void renderMessage(String message) throws IOException;

    /**
     * Displays the welcome message.
     * @throws IOException if the program can't read the input or write the output
     */
    void displayWelcomeMessage() throws IOException;
}

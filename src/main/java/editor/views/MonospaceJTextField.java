package editor.views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * MonospaceJTextField is an extension of the JTextField that has a monospaced
 * font.
 */
public class MonospaceJTextField extends JTextField {
    private final String FONT_FILE_NAME = "Inconsolata.ttf";
    private static Font font;

    public MonospaceJTextField(String val, int len) {
        super(val, len);

        ClassLoader classLoader = getClass().getClassLoader();
        File fontFile;
        String fileName = FONT_FILE_NAME;
        try {
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        setFont(font.deriveFont(14f));
    }
}

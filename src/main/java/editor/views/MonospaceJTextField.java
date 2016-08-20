/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

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

/*
 * EditSomeXML is a graphical XML editor
 *
 * Copyright (C) 2014  David Jackson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Logger is a singleton object that is used to write to a log file
 */
public class Logger {
    private final String LOG_FILE_NAME = "EditSomeXML.log";
    private static Logger instance = null;
    private BufferedWriter writer;

    /**
     * Construct the Logger and open the log file
     */
    private Logger() {
        try {
            writer = new BufferedWriter(new FileWriter(LOG_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get an instance of the (singleton) logger
     * @return the instance of the logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Write to the log
     * @param mesg The message to write into the log
     */
    public void write(final String mesg) {
        try {
            writer.write(mesg + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the log file
     */
    public void closeLog() {
        if (instance != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

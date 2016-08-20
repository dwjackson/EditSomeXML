/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
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

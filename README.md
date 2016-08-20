<!--
This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
-->

EditSomeXML
===========

EditSomeXML is a graphical XML editor for those occasions when the verbosity
of XML is just too annoying to deal with by hand.

Build Instructions
------------------

EditSomeXML is written in Java and you'll need Java 1.6 or higher in order to
build it.

EditSomeXML uses Maven to build itself. Running the following should yield you
a runnable JAR:

    mvn package

Once you've got the JAR, you can either double-click it from a file browser or
run

    java -jar EditSomeXML.jar

If you'd like to run the unit tests, run

    mvn test

License
-------

Copyright 2014-2016 David Jackson

This project is licensed under the
[Mozilla Public License v2](https://www.mozilla.org/en-US/MPL/2.0/). A copy of
the MPL is included in the LICENSE file. If you are unfamiliar with the MPLv2,
there is an [FAQ](https://www.mozilla.org/en-US/MPL/2.0/FAQ/).

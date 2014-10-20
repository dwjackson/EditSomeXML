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

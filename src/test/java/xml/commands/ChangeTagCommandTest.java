package xml.commands;

import junit.framework.TestCase;
import org.junit.Test;
import xml.Element;

public class ChangeTagCommandTest extends TestCase {
    @Test
    public void testChangeTag() {
        Element elem = new Element("root");
        ChangeTagCommand cmd1 = new ChangeTagCommand(elem, "roo");
        elem.performCommand(cmd1);
        assertEquals("Tag modified incorrectly by first change", "roo", elem.getTag());

        ChangeTagCommand cmd2 = new ChangeTagCommand(elem, "ro");
        elem.performCommand(cmd2);
        assertEquals("Tag modified incorrectly by second change", "ro", elem.getTag());

        elem.undo();
        assertEquals("Combined undo tag incorrect", "root", elem.getTag());
    }
}
package xml.commands;

import utility.Command;
import xml.Element;

public class MirrorElementCommand implements Command {
    private Element parent;
    private Element mirroredElement;
    private Element mirroringElement;
    
    public MirrorElementCommand(Element elementToMirror) {
        parent = elementToMirror.getParent();
        mirroredElement = elementToMirror;
        mirroringElement = null;
    }

    @Override
    public void perform() {
        mirroringElement = new Element();
        mirroringElement.mirrorElement(mirroredElement);
        parent.addChild(mirroringElement);
    }

    @Override
    public void undo() {
        parent.deleteChild(mirroringElement);
        mirroringElement = null;
    }

}

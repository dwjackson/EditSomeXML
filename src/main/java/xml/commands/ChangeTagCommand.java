package xml.commands;

import utility.Command;
import xml.Element;

public class ChangeTagCommand implements Command {
	private Element element;
	private String tag;
	private String oldTag;
	
	public ChangeTagCommand(Element element, String tag) {
		this.element = element;
		this.tag = tag;
		oldTag = new String(element.getTag());
	}

	@Override
	public void perform() {
		element.setTag(tag);
	}

	@Override
	public void undo() {
		element.setTag(oldTag);
	}

}

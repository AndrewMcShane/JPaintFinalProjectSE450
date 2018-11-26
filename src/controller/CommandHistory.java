package controller;

import java.util.Stack;

class CommandHistory {
	private static final Stack<ICommand> undoStack = new Stack<ICommand>();
	private static final Stack<ICommand> redoStack = new Stack<ICommand>();

	public static void add(ICommand cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			ICommand c = undoStack.pop();
			redoStack.push(c);
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			ICommand c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		return result;
	}

	// For testing
	ICommand topUndoCommand() {
		if (undoStack.empty())
			return null;
		else
			return undoStack.peek();
	}
	// For testing
	ICommand topRedoCommand() {
		if (redoStack.empty())
			return null;
		else
			return redoStack.peek();
	}
}

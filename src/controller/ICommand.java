package controller;

interface ICommand {
	void execute();
	boolean undo();
	boolean redo();
}
package main;

import controller.IJPaintController;
import controller.IObserver;
import controller.JPaintController;
import controller.MouseHandler;
import controller.MouseHandlerFacade;
import model.ShapeClipboard;
import model.ShapeFactory;
import model.ShapeList;
import model.dialogs.DialogProvider;
import model.interfaces.IDialogProvider;
import model.interfaces.IShapeFactory;
import model.persistence.ApplicationState;
import view.DrawShapeHandler;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args){
        PaintCanvas paintCanvas = new PaintCanvas();
        MouseHandler mouseHandler = new MouseHandler(); 
        ShapeList shapeList = new ShapeList();
        ShapeClipboard clipboard = new ShapeClipboard();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState, shapeList, clipboard);
        IShapeFactory shapeFactory = new ShapeFactory(appState);
        DrawShapeHandler drawShapeHandler = new DrawShapeHandler(paintCanvas);
        
        IObserver modal = new MouseHandlerFacade(appState, shapeFactory, shapeList);
        
        paintCanvas.addMouseListener(mouseHandler);
        shapeList.attach(drawShapeHandler);
        mouseHandler.attach(modal);
        
        controller.setup();
        
    }
}

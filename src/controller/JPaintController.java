package controller;

import model.ShapeClipboard;
import model.ShapeList;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ShapeList shapeList;
    private final ShapeClipboard clipboard;
    
    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapeList, ShapeClipboard clipboard) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.clipboard = clipboard;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO,() -> CommandHistory.undo());
        uiModule.addEvent(EventName.REDO, () -> CommandHistory.redo());
        uiModule.addEvent(EventName.DELETE,  () -> 
        	{
        		ICommand cmd = new DeleteCommand(shapeList, shapeList.getSelection()); 
        		cmd.execute();
        		CommandHistory.add(cmd);
        	}
        );
        uiModule.addEvent(EventName.COPY, () ->
        	{
        		ICommand cmd = new CopyCommand(shapeList, clipboard);
        		cmd.execute();
        	}
        );
        uiModule.addEvent(EventName.PASTE, () ->
        	{
        		ICommand cmd = new PasteCommand(shapeList, clipboard);
        		cmd.execute();
        		CommandHistory.add(cmd);
        	}   	
        );
        
        
    }
}

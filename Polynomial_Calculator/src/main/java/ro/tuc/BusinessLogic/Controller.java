package ro.tuc.BusinessLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import ro.tuc.GraphicalUserInterface.*;
import ro.tuc.DataModels.Polynomial;
public class Controller {
    protected View theView;
    protected Model theModel;

    public Controller(View theView, Model theModel){
        this.theView = theView;
        this.theModel = theModel;
        this.theView.equalListener(new EqualListener(this, theModel, theView));
        this.theView.instrListener(new InstructionListener(this));
    }

}

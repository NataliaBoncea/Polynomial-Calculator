package ro.tuc.Main;
import ro.tuc.BusinessLogic.*;
import ro.tuc.DataModels.Polynomial;
import ro.tuc.GraphicalUserInterface.*;

public class Main {
    public static void main(String[] args) {

        View theView = new View();
        Model theModel = new Model();
        Controller theController = new Controller(theView, theModel);

        //String s1 = "x^3-2x^2+6x-5";
        //String s2 = "x^2-1";

    }
}
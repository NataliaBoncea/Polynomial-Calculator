package ro.tuc.BusinessLogic;

import ro.tuc.DataModels.Polynomial;
import ro.tuc.GraphicalUserInterface.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EqualListener implements ActionListener {
    private final Controller theController;
    private final View theView;
    private final Model theModel;

    public EqualListener (Controller theController, Model theModel, View theView){
        this.theController = theController;
        this.theModel = theModel;
        this.theView = theView;
    };

    public void actionPerformed(ActionEvent e){
        Polynomial pol1 = new Polynomial();
        Polynomial pol2 = new Polynomial();
        Polynomial result = new Polynomial();
        int op = theView.getOperation();
        boolean ok = true;

        if(checkFirstPolynomial()!=null){
            pol1.addPolynomial(checkFirstPolynomial());
        }
        else { ok = false; };
        if(checkSecondPolynomial()!=null){
            pol2.addPolynomial(checkSecondPolynomial());
        }
        else if(op<5){ ok = false; };
        if(ok){
            if((pol2.toString().equals("0") || pol1.toString().equals("0")) && (op==3 || op==4)){
                theView.impartireLaZero();
                theView.setSolution("");
            }
            else{
                opSwitch(pol1, pol2, result);
                theView.setSolution(result.toString());
            }
        }
        else{theView.setSolution("");};
    }

    private String checkFirstPolynomial(){
        String polynomial1 = theView.getFirstPolynomial();
        if(theModel.verifInput(polynomial1)){
            theView.changeFirstColorError();
        }
        else{
            theView.changeFirstColorGood();
            return polynomial1;
        }
        return null;
    }

    private String checkSecondPolynomial(){
        String polynomial2 = theView.getSecondPolynomial();
        int op = theView.getOperation();
        if(theModel.verifInput(polynomial2) && op<5){
            theView.changeSecondColorError();
        }
        else if(op<5){
            theView.changeSecondColorGood();
            return polynomial2;
        }
        return null;
    }

    private void opSwitch(Polynomial pol1, Polynomial pol2, Polynomial result){
        int op = theView.getOperation();
        switch (op) {
            case 0:
                theModel.adunare(pol1.getMonomials(), pol2.getMonomials(), result.getMonomials());
                break;
            case 1:
                theModel.scadere(pol1.getMonomials(), pol2.getMonomials(), result.getMonomials());
                break;
            case 2:
                theModel.inmultire(pol1.getMonomials(), pol2.getMonomials(), result.getMonomials());
                break;
            case 3:
                theModel.impartire(pol1.getMonomials(), pol2.getMonomials(), result.getMonomials());
                break;
            case 4:
                theModel.modulo(pol1.getMonomials(), pol2.getMonomials(), result.getMonomials());
                break;
            case 5:
                theModel.derivare(pol1.getMonomials(), result.getMonomials());
                break;
            case 6:
                theModel.integrare(pol1.getMonomials(), result.getMonomials());
                break;
        }
    }
}

package ro.tuc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.tuc.BusinessLogic.Model;
import ro.tuc.DataModels.Polynomial;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestOperation {
    String s1 = "x^3-2x^2+6x-5";
    String s2 = "x^2-1";
    Polynomial p1, p2, res, rTest;
    Model theModel = new Model();
    @BeforeEach
    public void setUp(){
        p1 = new Polynomial();
        p2 = new Polynomial();
        p1.addPolynomial(s1);
        p2.addPolynomial(s2);
        res = new Polynomial();
        rTest = new Polynomial();
    }

    @Test
    public void adunareTest(){
        rTest.addPolynomial("x^3-x^2+6x-6");
        theModel.adunare(p1.getMonomials(), p2.getMonomials(), res.getMonomials());
        assertEquals(rTest.getMonomials(), res.getMonomials());
    }

    @Test
    public void scadereTest(){
        rTest.addPolynomial("x^3-3x^2+6x-4");
        theModel.scadere(p1.getMonomials(), p2.getMonomials(), res.getMonomials());
        assertEquals(rTest.getMonomials(), res.getMonomials());
    }

    @Test
    public void inmultireTest(){
        rTest.addPolynomial("x^5-2x^4+5x^3-3x^2-6x+5");
        theModel.inmultire(p1.getMonomials(), p2.getMonomials(), res.getMonomials());
        assertEquals(rTest.getMonomials(), res.getMonomials());
    }

    @Test
    public void impartireTest(){
        rTest.addPolynomial("x-2");
        theModel.impartire(p1.getMonomials(), p2.getMonomials(), res.getMonomials());
        assertEquals(rTest.getMonomials(), res.getMonomials());
    }

    @Test
    public void moduloTest(){
        rTest.addPolynomial("7x-7");
        rTest.getMonomials().put(3, 0.0);
        rTest.getMonomials().put(2, 0.0);
        theModel.modulo(p1.getMonomials(), p2.getMonomials(), res.getMonomials());
        assertEquals(rTest.getMonomials(), res.getMonomials());
    }

    @Test
    public void derivareTest(){
        rTest.addPolynomial("3x^2-4x+6");
        theModel.derivare(p1.getMonomials(), res.getMonomials());
        assertEquals(rTest.getMonomials(), res.getMonomials());
    }

    @Test
    public void integrareTest(){
        rTest.getMonomials().put(4, 1/4.0);
        rTest.getMonomials().put(3, -2/3.0);
        rTest.getMonomials().put(2, 6/2.0);
        rTest.getMonomials().put(1, -5.0);
        theModel.integrare(p1.getMonomials(), res.getMonomials());
        assertEquals(rTest.getMonomials(), res.getMonomials());
    }

}

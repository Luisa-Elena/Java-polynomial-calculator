import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.example.logic.Operations;
import org.example.model.Polynomial;

public class OperationsTest {

    @Test
    public void addTest() {
        try {
            Polynomial p1 = Polynomial.parsePolynomial("3x^3+2x^2+x-5");
            Polynomial p2 = Polynomial.parsePolynomial("x^2-2x+1");
            Polynomial expectedResult = Polynomial.parsePolynomial("3x^3+3x^2-x-4");

            assertEquals(expectedResult.polynomialToString(), Operations.add(p1, p2).polynomialToString());
        } catch (Exception e) {
            System.out.println("Invalid test input for addition");
        }
    }

    @Test
    public void subtractTest() {
        try {
            Polynomial p1 = Polynomial.parsePolynomial("3x^3+2x^2+x-5");
            Polynomial p2 = Polynomial.parsePolynomial("x^2-2x+1");
            Polynomial expectedResult = Polynomial.parsePolynomial("3x^3+x^2+3x-6");

            assertEquals(expectedResult.polynomialToString(), Operations.subtract(p1, p2).polynomialToString());
        } catch (Exception e) {
            System.out.println("Invalid test input for substraction");
        }
    }

    @Test
    public void multiplyTest() {
        try {
            Polynomial p1 = Polynomial.parsePolynomial("3x^3+2x^2+x-5");
            Polynomial p2 = Polynomial.parsePolynomial("x^2-2x+1");
            Polynomial expectedResult = Polynomial.parsePolynomial("3x^5-4x^4-5x^2+11x-5");

            assertEquals(expectedResult.polynomialToString(), Operations.multiply(p1, p2).polynomialToString());
        } catch (Exception e) {
            System.out.println("Invalid test input for multiplication");
        }
    }

    @Test
    public void divideTest() {
        try {
            Polynomial p1 = Polynomial.parsePolynomial("x^3-2x^2+6x-5");
            Polynomial p2 = Polynomial.parsePolynomial("x^2-1");

            String expectedResult = "Q = 1.0x^1-2.0x^0 and R = 7.0x^1-7.0x^0";

            assertEquals(expectedResult, Operations.divide(p1, p2));
        } catch (Exception e) {
            System.out.println("Invalid test input for division");
        }
    }

    @Test
    public void differentiationTest() {
        try {
            Polynomial p = Polynomial.parsePolynomial("3x^3+2x^2+x-5");
            Polynomial expectedResult = Polynomial.parsePolynomial("9x^2+4x+1");

            assertEquals(expectedResult.polynomialToString(), Operations.differentiation(p));
        } catch (Exception e) {
            System.out.println("Invalid test input for differentiation");
        }
    }

    @Test
    public void integrationTest() {
        try {
            Polynomial p = Polynomial.parsePolynomial("4x^3+3x^2+x-5");
            String expectedResult = "1.0x^4+1.0x^3+0.5x^2-5.0x^1 + C";

            assertEquals(expectedResult, Operations.integrate(p));
        } catch (Exception e) {
            System.out.println("Invalid test input for integration");
        }
    }
}

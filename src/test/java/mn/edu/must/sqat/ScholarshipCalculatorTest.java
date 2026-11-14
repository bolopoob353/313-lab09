package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test
    void letterGradeA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void gpaTwoScores() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.001);
    }

    @Test
    void scholarshipFull() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipSeventy() {
        assertEquals(70, calc.scholarship(3.5, 12, false));
    }

    @Test
    void scholarshipViolationZero() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }
    
    // ===== Part B: letterGrade =====

    @Test
    void letterGradeB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    void letterGradeC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    void letterGradeD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    void letterGradeF() {
        assertEquals("F", calc.letterGrade(30));
    }

    @Test
    void letterGradeNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    void letterGradeAbove100Throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    // ===== Part B: gpa =====

    @Test
    void gpaNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpaEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    @Test
    void gpaAllGradeTypes() {
        // A=4, B=3, C=2, D=1, F=0 -> 10 / 5 = 2.0
        assertEquals(2.0, calc.gpa(new double[]{95, 85, 75, 65, 30}), 0.001);
    }

    // ===== Part B: scholarship =====

    @Test
    void scholarshipNegativeGpaThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 15, false));
    }

    @Test
    void scholarshipGpaAbove4Throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 15, false));
    }

    @Test
    void scholarshipNegativeCreditsThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false));
    }

    @Test
    void scholarshipHighGpaLowCredits() {
        // gpa >= 3.8 боловч credits < 15 -> 70 хувийн шатанд орно
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    @Test
    void scholarshipMidGpaLowCredits() {
        // gpa >= 3.4 боловч credits < 12 -> 50
        assertEquals(50, calc.scholarship(3.5, 11, false));
    }

    @Test
    void scholarshipGpaOnlyFifty() {
        assertEquals(50, calc.scholarship(3.2, 20, false));
    }

    @Test
    void scholarshipLowGpaZero() {
        assertEquals(0, calc.scholarship(2.5, 20, false));
    }
    
    
    // ===== Part C: gpa loop boundaries (0 / 1 / 2+) =====
    // 0 удаа: gpaNullThrows ба gpaEmptyThrows 

    @Test
    void gpaSingleElement() {
        // 1 удаа: давталт яг нэг удаа ажиллана
        assertEquals(4.0, calc.gpa(new double[]{95}), 0.001);
    }

    @Test
    void gpaFourElements() {
        // 2+ удаа: A=4, B=3, C=2, D=1 -> 10 / 4 = 2.5
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001);
    }
    
    
    
    // ===== Part D: boundary values for letterGrade =====

    @Test
    void score0IsF()       { assertEquals("F", calc.letterGrade(0)); }

    @Test
    void score59_99IsF()   { assertEquals("F", calc.letterGrade(59.99)); }

    @Test
    void score60IsD()      { assertEquals("D", calc.letterGrade(60)); }

    @Test
    void score69_99IsD()   { assertEquals("D", calc.letterGrade(69.99)); }

    @Test
    void score70IsC()      { assertEquals("C", calc.letterGrade(70)); }

    @Test
    void score79_99IsC()   { assertEquals("C", calc.letterGrade(79.99)); }

    @Test
    void score80IsB()      { assertEquals("B", calc.letterGrade(80)); }

    @Test
    void score89_99IsB()   { assertEquals("B", calc.letterGrade(89.99)); }

    @Test
    void score90IsA()      { assertEquals("A", calc.letterGrade(90)); }

    @Test
    void score100IsA()     { assertEquals("A", calc.letterGrade(100)); }
}

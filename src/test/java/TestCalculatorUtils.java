import com.pavelmakhov.utils.CalculatorUtils;
import com.pavelmakhov.utils.Instruction;
import com.pavelmakhov.operation.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestCalculatorUtils {

    CalculatorUtils utils;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        utils = new CalculatorUtils();
    }

    @Test
    public void testInvalidInstruction(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Wrong instruction ads asd 3");
        utils.parseInstruction("ads asd 3");
    }

    @Test
    public void testInvalidOperation(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Not supported operation asd");
        utils.parseInstruction("asd 3");
    }

    @Test
    public void testInvalidOperand(){
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("For input string: \"3w\"");
        utils.parseInstruction("add 3w");
    }

    @Test
    public void testValidInstructionAdd(){
        Instruction expected = new Instruction(new AddOperation(), 4.0);
        Instruction actual = utils.parseInstruction("add 4");
        assertEquals(expected, actual);
    }
    @Test
    public void testValidInstructionSubtract(){
        Instruction expected = new Instruction(new SubtractOperation(), 4.0);
        Instruction actual = utils.parseInstruction("subtract 4");
        assertEquals(expected, actual);
    }
    @Test
    public void testValidInstructionMultiply(){
        Instruction expected = new Instruction(new MultiplyOperation(), 4.0);
        Instruction actual = utils.parseInstruction("multiply 4");
        assertEquals(expected, actual);
    }
    @Test
    public void testValidInstructionDivide(){
        Instruction expected = new Instruction(new DivideOperation(), 4.0);
        Instruction actual = utils.parseInstruction("divide 4");
        assertEquals(expected, actual);
    }

    @Test
    public void testValidInstructionApply(){
        Instruction expected = new Instruction(new ApplyOperation(), 4.0);
        Instruction actual = utils.parseInstruction("apply 4");
        assertEquals(expected, actual);
    }

    @Test
    public void testReadInstructions(){
        List<String> instructions = new ArrayList<String>() {{
            add("add 4");
            add("add 3");
            add("apply 1");
        }};

        List<Instruction> expected = new ArrayList<Instruction>() {{
            add(new Instruction(new AddOperation(), 4.0));
            add(new Instruction(new AddOperation(), 3.0));
            add(new Instruction(new ApplyOperation(), 1.0));
        }};
        List<Instruction> actual = utils.readInstructions(instructions);
        assertEquals(expected, actual);
    }
}

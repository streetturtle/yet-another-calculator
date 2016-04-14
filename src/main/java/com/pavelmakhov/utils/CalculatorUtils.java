package com.pavelmakhov.utils;

import com.pavelmakhov.operation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorUtils {

    /**
     * Map of supported operations
     */
    private Map<String, Operation> processor = new HashMap<String, Operation>() {
        {
            put("apply", new ApplyOperation());
            put("add", new AddOperation());
            put("subtract", new SubtractOperation());
            put("multiply", new MultiplyOperation());
            put("divide", new DivideOperation());
        }
    };

    /**
     *  Reads list on string instructions and converts them to list of {@link Instruction} objects
     * @param instructions list of instructions
     * @return list of {@link Instruction} objects
     */
    public List<Instruction> readInstructions(List<String> instructions){
        List<Instruction> result = new ArrayList<>();

        for (String instruction : instructions)
            result.add(parseInstruction(instruction));

        return result;
    }

    /**
     * Parses string representation of instruction and returns {@Instruction} class
     * @param instruction single instruction, should consist of operation name and operand separated by space character
     * @return {@link Instruction} representation of passed instruction string
     */
    public Instruction parseInstruction(String instruction){
        String[] tokens = instruction.split("\\s");

        if (tokens.length != 2)
            throw new IllegalArgumentException("Wrong instruction " + instruction);

        String operationStr = tokens[0].trim();
        Operation operator = processor.get(operationStr);

        if (operator == null)
            throw new IllegalArgumentException("Not supported operation " + operationStr);

        Double operand = Double.parseDouble(tokens[1].trim());

        return new Instruction(operator, operand);
    }
}

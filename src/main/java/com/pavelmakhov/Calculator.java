package com.pavelmakhov;

import com.pavelmakhov.utils.CalculatorUtils;
import com.pavelmakhov.utils.Instruction;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;


public class Calculator {

    private final CalculatorUtils utils;
    private List<Instruction> instructions;
    private Double currentResult;

    public Calculator(String path) throws IOException {
        CalculatorUtils utils = new CalculatorUtils();
        this.utils = utils;
        this.currentResult = 0.0;
        List<String> instructions = FileUtils.readLines(new File(path));
        this.instructions = utils.readInstructions(instructions);
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to the file with list of instructions: ");

        String path = scanner.next();

        Calculator calc = new Calculator(path);

        calc.instructions.add(0, calc.instructions.get(calc.instructions.size() - 1));
        calc.instructions.remove(calc.instructions.size() - 1);

        for (Instruction instruction : calc.instructions)
            calc.currentResult = instruction.calculate(calc.currentResult);

        System.out.println(new DecimalFormat("#.##").format(calc.currentResult));
    }
}

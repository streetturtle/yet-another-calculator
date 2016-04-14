package com.pavelmakhov.utils;

import com.pavelmakhov.operation.Operation;

public class Instruction {
    private Operation operator;
    private Double operand;

    public Instruction(Operation operator, Double operand) {
        this.operator = operator;
        this.operand = operand;
    }

    /**
     * Performs operator on operand
     * @param op2 operand
     * @return result of the operation
     */
    public Double calculate(Double op2){
        return operator.calculate(operand, op2);
    }

    @Override
    public boolean equals(Object o) {
        Instruction that = (Instruction) o;
        return (this.operand.equals(that.operand)) && (this.operator.getClass().equals(that.operator.getClass()));
    }

    @Override
    public int hashCode() {
        int result = operator != null ? operator.hashCode() : 0;
        result = 31 * result + (operand != null ? operand.hashCode() : 0);
        return result;
    }
}

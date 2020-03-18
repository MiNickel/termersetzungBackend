package com.termersetzung.termersetzung.model.dto;

/**
 * StepCheckDto
 */
public class StepCheckDto {

    private String step;

    private String conversion;

    private boolean isCorrect;

    public StepCheckDto() {

    }

    public StepCheckDto(String step, String conversion, boolean isCorrect) {
        this.step = step;
        this.conversion = conversion;
        this.isCorrect = isCorrect;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
}
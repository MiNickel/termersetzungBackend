package com.termersetzung.termersetzung.model.dto;

/**
 * StepCheckDto
 */
public class StepCheckDto {

    private String startEquation;

    private String rule;

    private String targetEquation;

    private boolean isCorrect;

    public StepCheckDto() {

    }

    public StepCheckDto(String startEquation, String rule, String targetEquation, boolean isCorrect) {
        this.startEquation = startEquation;
        this.rule = rule;
        this.targetEquation = targetEquation;
        this.isCorrect = isCorrect;
    }

    public String getStartEquation() {
        return startEquation;
    }

    public void setStartEquation(String startEquation) {
        this.startEquation = startEquation;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getTargetEquation() {
        return targetEquation;
    }

    public void setTargetEquation(String targetEquation) {
        this.targetEquation = targetEquation;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}

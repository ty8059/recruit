package com.admn.recruit.applicationrecord;

import java.io.Serializable;

public class ApplicationRecord implements Serializable {

    private String positionName;

    private Long positionSalary;

    private String positionWorkType;

    private String positionIsPassed;

    // 00=月薪 01=日薪
    private String salaryType;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getPositionSalary() {
        return positionSalary;
    }

    public void setPositionSalary(Long positionSalary) {
        this.positionSalary = positionSalary;
    }

    public String getPositionWorkType() {
        return positionWorkType;
    }

    public void setPositionWorkType(String positionWorkType) {
        this.positionWorkType = positionWorkType;
    }

    public String getPositionIsPassed() {
        return positionIsPassed;
    }

    public void setPositionIsPassed(String positionIsPassed) {
        this.positionIsPassed = positionIsPassed;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    @Override
    public String toString() {
        return "ApplicationRecord{" +
                "positionName='" + positionName + '\'' +
                ", positionSalary=" + positionSalary +
                ", positionWorkType='" + positionWorkType + '\'' +
                ", positionIsPassed='" + positionIsPassed + '\'' +
                ", salaryType='" + salaryType + '\'' +
                '}';
    }
}

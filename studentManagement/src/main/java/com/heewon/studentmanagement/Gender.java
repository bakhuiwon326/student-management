package com.heewon.studentmanagement;

public enum Gender {
    M("남성"), F("여성");

    private final String label;
    Gender(String label){
        this.label = label;
    }
    public String label(){
        return label;
    }
}

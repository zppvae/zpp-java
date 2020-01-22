package org.java.basic.copy;

public class Experience {  
      
    private String educationBackground;  
    private String skills;  
      
    public void setExperience(String educationBackground, String skills) {  
        // TODO Auto-generated constructor stub  
        this.educationBackground = educationBackground;  
        this.skills = skills;  
    }  
    public String toString() {  
        return educationBackground + skills;  
    }  
} 
package com.example.vedantmehra.homepage2;



public class UserProfile {
    public String name, userEmail;// userBio;
    public Integer userType;
    public String occupation, company;
    public String school, degree, graduation, branch;

    public UserProfile() {
    }

    public UserProfile(String name, String userEmail, Integer userType,
                       String occupation, String company,
                       String school, String degree, String graduation, String branch) {
        this.userEmail = userEmail;
        this.name = name;
        this.userType = userType;

        this.occupation = occupation;
        this.company = company;

        this.school = school;
        this.degree = degree;
        this.graduation = graduation;
        this.branch = branch;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    //public void setUserBio(String userBio) {
      //  this.userBio = userBio;
    //}

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    //public String getUserBio() {
      //  return userBio;
    //}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}


/*public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;

    public UserProfile(String name, String email, int i, String occupation, String company, String s, String s1, String s2, String s3){
    }

    public UserProfile(String userAge, String userEmail, String userName) {

        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}*/

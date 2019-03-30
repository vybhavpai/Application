package com.example.vedantmehra.homepage2;



public class UserProfile {
    public String name, userEmail;// userBio;
    public Integer userType;
    public String occupation, company;
    public String school, degree, graduation, branch;
    public String area, city, state, country;

    public UserProfile() {
    }

    public UserProfile(String name, String userEmail, Integer userType,
                       String occupation, String company,
                       String school, String degree, String graduation, String branch,
                       String area, String city, String state, String country) {
        this.userEmail = userEmail;
        this.name = name;
        this.userType = userType;

        this.occupation = occupation;
        this.company = company;

        this.school = school;
        this.degree = degree;
        this.graduation = graduation;
        this.branch = branch;

        this.area = area;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

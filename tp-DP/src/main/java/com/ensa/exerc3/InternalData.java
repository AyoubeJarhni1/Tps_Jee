package com.ensa.exerc3;

class InternalData {
    private String firstName;
    private String lastName;
    private int age;

    public InternalData(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
}

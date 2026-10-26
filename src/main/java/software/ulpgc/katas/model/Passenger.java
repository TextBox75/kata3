package software.ulpgc.katas.model;

public record Passenger (String name, PassengerClass passengerClass, float age, Gender gender, int survived) { }
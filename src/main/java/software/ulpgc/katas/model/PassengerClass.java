package software.ulpgc.katas.model;

public enum PassengerClass {
    First("1st"),
    Second("2nd"),
    Third("3rd");

    private String value;

    public String getValue() {
        return value;
    }

    PassengerClass(String s) {
        this.value = s;
    }

    public static PassengerClass fromString(String s) {
        for (PassengerClass pc : PassengerClass.values()) {
            if (pc.getValue().equals(s)) {
                return pc;
            }
        }
        return null;
    }
}
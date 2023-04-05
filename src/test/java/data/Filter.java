package data;

public enum Filter {
    LIGHT("Светлая"),
    MIDDLE("Средняя");
    private final String desc;
    Filter(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
}

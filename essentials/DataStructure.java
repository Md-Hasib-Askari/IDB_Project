package essentials;

public class DataStructure {
    private String column;
    private Object value;

    public DataStructure(String column, Object value) {
        this.column = column;
        this.value = value;
    }
    public String getColumn() {
        return column;
    }
    public Object getValue() {
        return value;
    }
}

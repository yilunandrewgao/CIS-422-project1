package AddressBook;

/**
 * Created by yilungao on 10/20/16.
 */
public class lastClickedHeader {

    private int col;
    private String order;

    public lastClickedHeader(int _col, String _order) {
        col = _col;
        order = _order;
    }

    public void update(int colNum) {
        if (colNum == col) {
            if (order == "ASCENDING") {
                order = "DESCENDING";
            }
            else if (order == "DESCENDING") {
                order = "ASCENDING";
            }
        }
        else {
            col = colNum;
            order = "ASCENDING";
        }
    }

    public String getOrder() {
        return order;
    }
}

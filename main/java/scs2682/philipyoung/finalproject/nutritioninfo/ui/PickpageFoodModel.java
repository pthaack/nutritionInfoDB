package scs2682.philipyoung.finalproject.nutritioninfo.ui;

/**
 * Created by Philip Young on 2016-04-04.
 */
public class PickpageFoodModel {
    public final int layoutId;
    public final String department;
    public final String product;
    public final String quantity;
//    public final Double quantity;// too complex at this time - add and remove only
//    public final String unitofmeasure;
    public final String price;
//    public final Double price;// too complex at this time - add and remove only
    public final String extprice;
//    public final Double extprice;// too complex at this time - add and remove only

    public PickpageFoodModel( int layoutId, String department, String product, String quantity,
                              String price, String extprice) {
        this.layoutId = layoutId > 0 ? layoutId : 0;
        this.department = department != null ? department : "";
        this.product = product != null ? product : "";
//        this.quantity = quantity > 0 ? quantity : 0; // May also be extprice/unitprice, if possible
        this.quantity = quantity != null ? quantity : "";
//        this.unitofmeasure = unitofmeasure != null ? unitofmeasure : "each";
//        this.price = price > 0 ? price : 0; // May also be extprice/quantity, if possible
        this.price = price != null ? price : "";
//        this.extprice = extprice > 0 ? extprice : 0; // May also be price × unitprice, if possible
        this.extprice = extprice != null ? extprice : "";

    }
}

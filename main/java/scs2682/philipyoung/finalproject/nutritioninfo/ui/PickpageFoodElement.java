package scs2682.philipyoung.finalproject.nutritioninfo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import scs2682.philipyoung.finalproject.nutritioninfo.R;

/**
 * Created by Philip Young on 2016-04-04.
 *
 * pickpage_food_element.xml is an Item element of the ListView object in pickpage_food.xml
 *
 */
public class PickpageFoodElement extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView department;
    public final TextView product;
    public final TextView quantity;
//    public final Spinner unitofmeasure;  //  too complex at this time - add and remove only
    public final TextView price;
    public final TextView extprice;

    public PickpageFoodElement(View itemView) {
        super(itemView);

        department = (TextView) itemView.findViewById(R.id.food_list_department);
        product = (TextView) itemView.findViewById(R.id.food_list_product);
        quantity = (TextView) itemView.findViewById(R.id.food_list_quantity);
        // unitofmeasure = (Spinner) itemView.findViewById(R.id.food_list_uom);  //  too complex at this time - add and remove only
        price = (TextView) itemView.findViewById(R.id.food_list_unit_price);
        extprice = (TextView) itemView.findViewById(R.id.food_list_ext_price);
    }

    /*
    public PickpageFoodElement(Context context) { this( context, null, 0, 0); }
    public PickpageFoodElement(Context context, AttributeSet attrs) { this( context, attrs, 0, 0); }
    public PickpageFoodElement(Context context, AttributeSet attrs, int defStyleAttr) { this( context, attrs, defStyleAttr, 0); }

    public PickpageFoodElement(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        department = context.getResources().
    }
*/

    public void update(PickpageFoodModel model) {
        if( model == null || model.layoutId != R.layout.pickpage_food_element ) {
            return;
        }

        department.setText( model.department );
        product.setText( model.product );
        quantity.setText( String.valueOf(model.quantity) );
//        unitofmeasure.setSelected(); //  match the UOM spinner on rotation
// too complex at this time - add and remove only
/*        if( unitofmeasure.getCount()>0 && model.unitofmeasure != null )
        {
            final int uomCount = unitofmeasure.getCount();
            for( int i = 0; i < uomCount; i++ )
            {
                if(Objects.equals(unitofmeasure.getItemAtPosition(i).toString(), model.unitofmeasure))
                {
                    unitofmeasure.setSelection(i);
                }
            }
        }
        */
        price.setText(String.valueOf(model.price));
        extprice.setText(String.valueOf(model.extprice));

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),
                product.getText(),
                Toast.LENGTH_SHORT)
                .show();
    }
}

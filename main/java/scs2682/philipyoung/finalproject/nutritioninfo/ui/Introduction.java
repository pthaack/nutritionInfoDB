package scs2682.philipyoung.finalproject.nutritioninfo.ui;

import scs2682.philipyoung.finalproject.nutritioninfo.R;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Philip Young on 2016-03-28.
 */
public final class Introduction extends Fragment implements OnClickListener {
    public static final String NAME = Introduction.class.getSimpleName();

    private Button button;

    @Override
    public void onClick(View view) {
        if (button.equals(view)) {
            // button was clicked
            Toast.makeText(getActivity(),"Clicked button",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setTargetFragment( this, R.layout.introduction);
        Toast.makeText(getContext(),"Fragment open",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.w(NAME, NAME + ".onActivityCreated()");

        // TODO: 2016-03-30 What is the value of ''View view'' ? 

        Toast.makeText(view.getContext(),"Fragment open",Toast.LENGTH_SHORT).show();
        // set references to the needed views
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Button clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }


}

package scs2682.philipyoung.finalproject.nutritioninfo.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import scs2682.philipyoung.finalproject.nutritioninfo.ApplicationActivity;
import scs2682.philipyoung.finalproject.nutritioninfo.R;

public class PickpagePeople extends Fragment {
    public static final String NAME = PickpagePeople.class.getSimpleName();

    private static final String PERSONS_PAGE_KEY = "persons";
    private static final String PERSONS_ARRAY_KEY = "persons";
    private static final String PERSONS_ELEMENT_KEY = "person";

    private static final String FOOD_GUIDE_FOOD_FRUIT_VEG = "fruitandveg";
    private static final String FOOD_GUIDE_FOOD_GRAINS = "grains";
    private static final String FOOD_GUIDE_FOOD_DAIRY = "dairy";
    private static final String FOOD_GUIDE_FOOD_MEAT = "meat";
    private static final String FOOD_GUIDE_AGE1 = "toddler";
    private static final String FOOD_GUIDE_AGE2 = "child";
    private static final String FOOD_GUIDE_AGE3 = "youth";
    private static final String FOOD_GUIDE_AGE4F = "womanteen";
    private static final String FOOD_GUIDE_AGE5F = "womanadult";
    private static final String FOOD_GUIDE_AGE6F = "womanelderly";
    private static final String FOOD_GUIDE_AGE4M = "manteen";
    private static final String FOOD_GUIDE_AGE5M = "manadult";
    private static final String FOOD_GUIDE_AGE6M = "manelderly";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * A function to return the number of adults and children in an array
     * @return
     */
    public void setPeopleCount(TextView textView) {
        Integer[] pc = {0,0};
        textView.setText(getString(R.string.nutrition_facts_people_count,pc[0],pc[1]));
        return;
    }

    /**
     * A function to return the number of servings by food group in an array.
     * @return
     */
    public void setServingCount(TextView textView) {
        // Double[] sc = {0d,0d,0d,0d};
        final SharedPreferences preferences = getActivity().getSharedPreferences(ApplicationActivity.NAME, Context.MODE_PRIVATE);
        final String strJperson = preferences.getString(PERSONS_PAGE_KEY,
                "{persons:" +
                        "[{\"person\":0},{\"person\":0},{\"person\":0}"
                        +",{\"person\":0},{\"person\":0},{\"person\":0}"
                        +",{\"person\":0},{\"person\":0},{\"person\":0}"
                        +",{\"person\":0},{\"person\":0},{\"person\":0}]}");
        final String strJfoodGuide =
                "{\"fruitandveg\":{\"toddler\":4,\"child\":5,\"youth\":6,\"womanteen\":7,\"manteen\":8,\"womanadult\":7.5,\"manadult\":9,\"womanelderly\":7,\"manelderly\":7},"
                        + "\"grains\":{\"toddler\":3,\"child\":4,\"youth\":6,\"womanteen\":6,\"manteen\":7,\"womanadult\":6.5,\"manadult\":8,\"womanelderly\":6,\"manelderly\":7},"
                        + "\"dairy\":{\"toddler\":2,\"child\":2,\"youth\":3.5,\"womanteen\":3.5,\"manteen\":3.5,\"womanadult\":2,\"manadult\":2,\"womanelderly\":3,\"manelderly\":3},"
                        + "\"meat\":{\"toddler\":1,\"child\":1,\"youth\":1.5,\"womanteen\":2,\"manteen\":3,\"womanadult\":2,\"manadult\":3,\"womanelderly\":2,\"manelderly\":3}}";
        final JSONObject jsonObject;
        JSONObject jsonFoodGuide = null;
        final Double[] arrFoodGuide = new Double[]{0d,0d,0d,0d};
        JSONArray jsonArray = null;
        Integer arrayLength = 0;
        try {
            jsonObject = new JSONObject(strJperson);
            jsonFoodGuide = new JSONObject(strJfoodGuide);
            jsonArray = jsonObject.optJSONArray(PERSONS_ARRAY_KEY);
            if( arrayLength != (jsonArray != null ? jsonArray.length():0) ) {
                arrayLength = 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            arrayLength = 0;
        }

        if( arrayLength>0 ){
            for (Integer intI = 0 ; intI < arrayLength; intI++ ){
                JSONObject objElement = jsonArray.optJSONObject(intI);

                // Get food guide values, too
                Double dblFV = 0d;
                Double dblG = 0d;
                Double dblD = 0d;
                Double dblM = 0d;
                switch ( intI ){
                    case 0:
                    case 6:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE1);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE1);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE1);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                    case 7:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE2);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE2);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE2);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                    case 8:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE3);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE3);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE3);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE4F);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE4F);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE4F);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE4F);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE5F);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE5F);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE5F);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE5F);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE6F);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE6F);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE6F);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE6F);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 9:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE4M);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE4M);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE4M);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE4M);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 10:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE5M);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE5M);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE5M);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE5M);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 11:
                        try {
                            dblFV = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_FRUIT_VEG).getDouble(FOOD_GUIDE_AGE6M);
                            dblG = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_GRAINS).getDouble(FOOD_GUIDE_AGE6M);
                            dblD = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_DAIRY).getDouble(FOOD_GUIDE_AGE6M);
                            dblM = jsonFoodGuide.getJSONObject(FOOD_GUIDE_FOOD_MEAT).getDouble(FOOD_GUIDE_AGE6M);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        dblFV = 0d;
                        dblG = 0d;
                        dblD = 0d;
                        dblM = 0d;
                        break;
                }
                arrFoodGuide[0] += dblFV * objElement.optInt(PERSONS_ELEMENT_KEY);
                arrFoodGuide[1] += dblG * objElement.optInt(PERSONS_ELEMENT_KEY);
                arrFoodGuide[2] += dblD * objElement.optInt(PERSONS_ELEMENT_KEY);
                arrFoodGuide[3] += dblM * objElement.optInt(PERSONS_ELEMENT_KEY);
                // Toast.makeText(getContext(), String.valueOf(dblFV), Toast.LENGTH_SHORT).show();
            }
        }
        textView.setText(getString(R.string.nutrition_facts_servings_count,arrFoodGuide[0],arrFoodGuide[1],arrFoodGuide[2],arrFoodGuide[3]));
        return;
    }


}

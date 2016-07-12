package scs2682.philipyoung.finalproject.nutritioninfo;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
//import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
//import android.support.v4.view.onclicklistener
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ApplicationActivity extends AppCompatActivity {
    public static final String NAME = ApplicationActivity.class.getSimpleName();

    private static final String PERSONS_PAGE_KEY = "persons";
    private static final String PERSONS_ARRAY_KEY = "persons";
    private static final String PERSONS_ELEMENT_KEY = "person";

    private static final String NUTRIENT_PAGE_KEY = "nutrients";
    private static final String NUTRIENT_LIST_FOOD_KEY = "productlist";
    private static final String NUTRIENT_LIST_DEPARTMENT_KEY = "department";
    private static final String NUTRIENT_LIST_PRODUCT_KEY = "product";
    private static final String NUTRIENT_LIST_QUANTITY_KEY = "quantity";
    private static final String NUTRIENT_LIST_SERVING_KEY = "servings";
    private static final String NUTRIENT_LIST_KEY = "nutrientlist";
    private static final String NUTRIENT_PRODUCT_KEY = "product";
    private static final String NUTRIENT_CALORIE_KEY = "energykcal";
    private static final String NUTRIENT_CARBS_KEY = "carbohydrate";
    private static final String NUTRIENT_PROTEIN_KEY = "protein";
    private static final String NUTRIENT_FATS_KEY = "totalfat";
    private static final String NUTRIENT_SATFATS_KEY = "saturatedfat";
    private static final String NUTRIENT_TRANSFATS_KEY = "transfat";
    private static final String NUTRIENT_CHOLESTEROL_KEY = "cholesterol";
    private static final String NUTRIENT_SODIUM_KEY = "sodium";
    private static final String NUTRIENT_FIBRE_KEY = "totaldietaryfibre";
    private static final String NUTRIENT_SUGARS_KEY = "sugars";
    private static final String NUTRIENT_VITAMIN_A_KEY = "vitamina";
    private static final String NUTRIENT_VITAMIN_C_KEY = "vitaminc";
    private static final String NUTRIENT_CALCIUM_KEY = "calcium";
    private static final String NUTRIENT_IRON_KEY = "iron";

    private static final String NUTRIENT_SERVING_SIZE_KEY = "servingmeasure";
    private static final String NUTRIENT_PRICE_KEY = "price2015";
    private static final String NUTRIENT_SERVING_PER_KEY = "servings";
    private static final String NUTRIENT_PRICE_PER_KEY = "unit";

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

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * A class to change the message string.
     */
    private static final class AdapterModel {
        private final int layoutId;
        private final String title;

        private AdapterModel(int layoutId, String title) {
            this.layoutId = layoutId;
            this.title = title != null ? title : "";
        }

    }

    /**
     * A class to instantiate the page screens
     */
    private static final class Adapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private final List<AdapterModel> items;

        private Adapter(@NonNull Context context, List<AdapterModel> items) {
            layoutInflater = LayoutInflater.from(context);
            this.items = items != null ? items : Collections.<AdapterModel>emptyList();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            // first you need to inflate the page (the view) and then to return it
            View view = layoutInflater.inflate(items.get(position).layoutId, container, false);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(final ViewGroup container, final int position, final Object object) {
            if (object instanceof View) {
                container.removeView((View) object);
            }
        }

        @Override
        public int getItemPosition(final Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
         public boolean isViewFromObject(final View view, final Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(final int position) {
            return items.get(position).title;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicationactivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, R.string.introduction_help_msg, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Snackbar.make(view, fabMessage() ,Snackbar.LENGTH_LONG)
//                        .setAction("Action",null).show();
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.applicationmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View   rootView;
            Button button;
            // So like, this is where the tab fragment object gets activated.
            // TODO: Activate different fragment objects
            /**
             * Return to
            if( getArguments().getInt(ARG_SECTION_NUMBER) <= 1 ) {
                rootView = inflater.inflate(R.layout.introduction, container, false);
            }
            else {
                rootView = inflater.inflate(R.layout.applicationfragment, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            }
             */
            rootView = inflater.inflate(R.layout.applicationfragment, container, false);
            // FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);// TODO: implement info button
            TextView textElement;
            final List<Integer> elementList;
            Integer arrayLength;

            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1:  // Introduction and instructions
                    rootView = inflater.inflate(R.layout.introduction, container, false);
                    /* button = (Button) rootView.findViewById(R.id.button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar.make(v, "Button 1 works", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }); */  // TODO: 2016-03-30 look at fab.setOnClickListener(new View.OnClickListener() {
                     /*  Can I redefine the click listener?
                   fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar.make(v, "Button 1 works", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            Dialog dialog = null;
                            if( dialog != null && dialog.isShowing()) {
                                return;
                            }

                            dialog = new  AlertDialog.Builder(v.getContext())
                                    .setTitle(R.string.intropage_title)
                                    .setMessage(R.string.introduction_help_msg)
                                    .setCancelable(false)
                                    .create();
                            dialog.show();
                        }
                    });
        */
                    break;
                case 4:  // Nutrition facts
                    rootView = inflater.inflate(R.layout.nutrition_facts, container, false);
//                    button = (Button) rootView.findViewById(R.id.button4);
//                    button.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Snackbar.make(v, "Button 4 works", Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show();
//                        }
//                    });  // TODO: 2016-03-30 look at fab.setOnClickListener(new View.OnClickListener() {
                    // todo: rootView.findViewById(R.id.serving_size).settext
                    elementList = new ArrayList<>(1);
                    elementList.add(R.id.serving_size);
                    elementList.add(R.id.servings_per);
                    elementList.add(R.id.calories);
                    elementList.add(R.id.calories_fat);
                    elementList.add(R.id.total_fat);
                    elementList.add(R.id.total_fat_percent);
                    elementList.add(R.id.saturated_fat);
                    elementList.add(R.id.saturated_fat_percent);
                    elementList.add(R.id.trans_fat);
                    elementList.add(R.id.trans_fat_percent);
                    elementList.add(R.id.cholesterol);
                    elementList.add(R.id.cholesterol_percent);
                    elementList.add(R.id.sodium);
                    elementList.add(R.id.sodium_percent);
                    elementList.add(R.id.total_carbohydrate);
                    elementList.add(R.id.total_carbohydrate_percent);
                    elementList.add(R.id.dietary_fibre);
                    elementList.add(R.id.dietary_fibre_percent);
                    elementList.add(R.id.sugars);
                    elementList.add(R.id.sugars_percent);
                    elementList.add(R.id.protein);
                    elementList.add(R.id.protein_percent);
                    elementList.add(R.id.vitamin_a);
                    elementList.add(R.id.vitamin_c);
                    elementList.add(R.id.calcium);
                    elementList.add(R.id.iron);
                    /**
                     * Make the field display the number of servings in the grocery list by food group
                     */
                    textElement = (TextView) rootView.findViewById(R.id.serving_size);
                    // Double[] arrServingsOf = (Double[])   PickpagePeople.servingCount();
                    // Double[] arrServingsOf = {60.5d,52.5d,24.5d,17.5d};
                {
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
                    final String strJgroceryList = preferences.getString(ApplicationActivity.NUTRIENT_PAGE_KEY,
                            "{\"productlist\":[]}");
                    final String strFoodNutrients = "{\"nutrientlist\":[" +
                            "{\"product\":\"Round steak\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"13.71\", \"price2012\":\"14.09\", \"price2013\":\"14.33\", \"price2014\":\"16.49\", \"price2015\":\"19.09\", \"priceserving\":\"1.43175\", \"usdaproduct\":\"Eye of round steak, lean + fat, braised\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"171\", \"energykj\":\"715\", \"protein\":\"28\", \"carbohydrate\":\"0\", \"totalfat\":\"5\", \"saturatedfat\":\"2.2\", \"cholesterol\":\"61\"	, \"iron\":\"1.8\", \"sodium\":\"35\", \"potassium\":\"243\", \"magnesium\":\"19\", \"phosphorus\":\"152\", \"folate\":\"5\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"1.38\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"0.3\", \"polyunsaturatedfat3\":\"0.3\", \"saturatedfat3\":\"2.2\", \"monounsaturatedfat\":\"2.8\", \"monounsaturatedfat3\":\"2.8\" },"
                            + "{\"product\":\"Sirloin steak\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"16.37\", \"price2012\":\"17.13\", \"price2013\":\"17.34\", \"price2014\":\"19.86\", \"price2015\":\"24.61\", \"priceserving\":\"1.84575\", \"usdaproduct\":\"Top sirloin steak, lean + fat, broiled\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"146\", \"energykj\":\"611\", \"protein\":\"21\", \"carbohydrate\":\"0\", \"totalfat\":\"6\", \"saturatedfat\":\"2.6\", \"cholesterol\":\"52\"	, \"iron\":\"2\", \"sodium\":\"43\", \"potassium\":\"257\", \"magnesium\":\"20\", \"phosphorus\":\"160\", \"folate\":\"5\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"2.33\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"0.3\", \"polyunsaturatedfat3\":\"0.3\", \"saturatedfat3\":\"2.6\", \"monounsaturatedfat\":\"3.1\", \"monounsaturatedfat3\":\"3.1\" },"
                            + "{\"product\":\"Prime rib roast\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"22.89\", \"price2012\":\"23.98\", \"price2013\":\"22.67\", \"price2014\":\"26.35\", \"price2015\":\"31.19\", \"priceserving\":\"2.33925\", \"usdaproduct\":\"Standing rib roast, lean + fat, roasted\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"237\", \"energykj\":\"992\", \"protein\":\"21\", \"carbohydrate\":\"0\", \"totalfat\":\"16\", \"saturatedfat\":\"6.9\", \"cholesterol\":\"55\"	, \"iron\":\"1.7\", \"sodium\":\"53\", \"potassium\":\"243\", \"magnesium\":\"19\", \"phosphorus\":\"152\", \"folate\":\"5\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"1.73\", \"vitamine\":\"0.5\"   , \"polyunsaturatedfatg\":\"0.5\", \"polyunsaturatedfat3\":\"0.5\", \"saturatedfat3\":\"6.9\", \"monounsaturatedfat\":\"8\", \"monounsaturatedfat3\":\"8\" },"
                            + "{\"product\":\"Blade roast\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"10.95\", \"price2012\":\"11.2\", \"price2013\":\"12.11\", \"price2014\":\"14.3\", \"price2015\":\"16.44\", \"priceserving\":\"1.233\", \"usdaproduct\":\"Blade roast, lean + fat, braised\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"200\", \"energykj\":\"834\", \"protein\":\"26\", \"carbohydrate\":\"0\", \"totalfat\":\"10\", \"saturatedfat\":\"4\", \"cholesterol\":\"71\"	, \"iron\":\"2.5\", \"sodium\":\"46\", \"potassium\":\"202\", \"magnesium\":\"18\", \"phosphorus\":\"142\", \"folate\":\"4\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"2.38\", \"vitamine\":\"0.1\"   , \"polyunsaturatedfatg\":\"0.4\", \"polyunsaturatedfat3\":\"0.4\", \"saturatedfat3\":\"4\", \"monounsaturatedfat\":\"5\", \"monounsaturatedfat3\":\"5\" },"
                            + "{\"product\":\"Stewing beef\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"10.57\", \"price2012\":\"11.33\", \"price2013\":\"11.64\", \"price2014\":\"14.4\", \"price2015\":\"17.12\", \"priceserving\":\"1.284\", \"usdaproduct\":\"Stewing beef, lean, simmered\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"183\", \"energykj\":\"765\", \"protein\":\"28\", \"carbohydrate\":\"0\", \"totalfat\":\"7\", \"saturatedfat\":\"2.9\", \"cholesterol\":\"68\"	, \"iron\":\"2.6\", \"sodium\":\"45\", \"potassium\":\"214\", \"magnesium\":\"19\", \"phosphorus\":\"150\", \"folate\":\"4\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"1.8\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"N/A\", \"polyunsaturatedfat3\":\"N/A\", \"saturatedfat3\":\"2.9\", \"monounsaturatedfat\":\"N/A\", \"monounsaturatedfat3\":\"N/A\" },"
                            + "{\"product\":\"Ground beef, regular\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"8.23\", \"price2012\":\"9.24\", \"price2013\":\"9.53\", \"price2014\":\"11\", \"price2015\":\"12.75\", \"priceserving\":\"0.95625\", \"usdaproduct\":\"Ground, regular, crumbled, pan-fried\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"243\", \"energykj\":\"1016\", \"protein\":\"22\", \"carbohydrate\":\"0\", \"totalfat\":\"17\", \"saturatedfat\":\"7\", \"cholesterol\":\"63\"	, \"iron\":\"2.2\", \"sodium\":\"78\", \"potassium\":\"301\", \"magnesium\":\"21\", \"phosphorus\":\"178\", \"folate\":\"0\" , \"vitamina\":\"0\", \"vitamind\":\"0.8\", \"vitaminb12\":\"2.72\", \"vitamine\":\"N/A\"   , \"polyunsaturatedfatg\":\"0.4\", \"polyunsaturatedfat3\":\"0.4\", \"saturatedfat3\":\"7\", \"monounsaturatedfat\":\"7.9\", \"monounsaturatedfat3\":\"7.9\" },"
                            + "{\"product\":\"Pork chops\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"9.97\", \"price2012\":\"10.37\", \"price2013\":\"10.89\", \"price2014\":\"12.88\", \"price2015\":\"12.72\", \"priceserving\":\"0.954\", \"usdaproduct\":\"Loin, rib end, lean + fat, pan-fried\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"199\", \"energykj\":\"831\", \"protein\":\"20\", \"carbohydrate\":\"0\", \"totalfat\":\"13\", \"saturatedfat\":\"4.8\", \"cholesterol\":\"55\"	, \"iron\":\"0.5\", \"sodium\":\"38\", \"potassium\":\"323\", \"magnesium\":\"19\", \"phosphorus\":\"177\", \"folate\":\"2\" , \"vitamina\":\"2\", \"vitamind\":\"0.5\", \"vitaminb12\":\"0.53\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"1.4\", \"polyunsaturatedfat3\":\"1.4\", \"saturatedfat3\":\"4.8\", \"monounsaturatedfat\":\"5.6\", \"monounsaturatedfat3\":\"5.6\" },"
                            + "{\"product\":\"Chicken\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"6.7\", \"price2012\":\"7.04\", \"price2013\":\"7.02\", \"price2014\":\"7.09\", \"price2015\":\"7.58\", \"priceserving\":\"0.5685\", \"usdaproduct\":\"Chicken, broiler, flesh and skin, roasted\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"179\", \"energykj\":\"749\", \"protein\":\"20\", \"carbohydrate\":\"0\", \"totalfat\":\"10\", \"saturatedfat\":\"2.8\", \"cholesterol\":\"66\"	, \"iron\":\"0.9\", \"sodium\":\"62\", \"potassium\":\"167\", \"magnesium\":\"17\", \"phosphorus\":\"137\", \"folate\":\"4\" , \"vitamina\":\"35\", \"vitamind\":\"0.2\", \"vitaminb12\":\"0.23\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"2.2\", \"polyunsaturatedfat3\":\"2.2\", \"saturatedfat3\":\"2.8\", \"monounsaturatedfat\":\"4\", \"monounsaturatedfat3\":\"4\" },"
                            + "{\"product\":\"Bacon\", \"unit\":\"500 g\",\"servings\":\"20.8333333333333\", \"price2011\":\"5.11\", \"price2012\":\"5.08\", \"price2013\":\"5.19\", \"price2014\":\"6.58\", \"price2015\":\"6.41\", \"priceserving\":\"0.30768\", \"usdaproduct\":\"Bacon, pork, broiled, pan-fried or roasted\", \"servingmeasure\":\"3 slices\", \"servingweight\":\"24\", \"energykcal\":\"130\", \"energykj\":\"543\", \"protein\":\"9\", \"carbohydrate\":\"tr\", \"totalfat\":\"10\", \"saturatedfat\":\"3.3\", \"cholesterol\":\"26\"	, \"iron\":\"0.3\", \"sodium\":\"554\", \"potassium\":\"136\", \"magnesium\":\"8\", \"phosphorus\":\"128\", \"folate\":\"tr\" , \"vitamina\":\"3\", \"vitamind\":\"0.4\", \"vitaminb12\":\"0.3\", \"vitamine\":\"tr\"   , \"polyunsaturatedfatg\":\"1.1\", \"polyunsaturatedfat3\":\"1.1\", \"saturatedfat3\":\"3.3\", \"monounsaturatedfat\":\"4.4\", \"monounsaturatedfat3\":\"4.4\" },"
                            + "{\"product\":\"Wieners\", \"unit\":\"450 g\",\"servings\":\"11.8421052631579\", \"price2011\":\"3.28\", \"price2012\":\"3.47\", \"price2013\":\"3.41\", \"price2014\":\"4.01\", \"price2015\":\"4.26\", \"priceserving\":\"0.359733333333333\", \"usdaproduct\":\"Wiener (frankfurter), beef\", \"servingmeasure\":\"1\", \"servingweight\":\"38\", \"energykcal\":\"104\", \"energykj\":\"436\", \"protein\":\"5\", \"carbohydrate\":\"2\", \"totalfat\":\"8\", \"saturatedfat\":\"3.3\", \"cholesterol\":\"23\"	, \"iron\":\"0.6\", \"sodium\":\"343\", \"potassium\":\"54\", \"magnesium\":\"5\", \"phosphorus\":\"60\", \"folate\":\"2\" , \"vitamina\":\"0\", \"vitamind\":\"0.3\", \"vitaminb12\":\"0.65\", \"vitamine\":\"0.1\"   , \"polyunsaturatedfatg\":\"0.5\", \"polyunsaturatedfat3\":\"0.5\", \"saturatedfat3\":\"3.3\", \"monounsaturatedfat\":\"4\", \"monounsaturatedfat3\":\"4\" },"
                            + "{\"product\":\"Canned sockeye salmon\", \"unit\":\"213 g\",\"servings\":\"2.84\", \"price2011\":\"3.1\", \"price2012\":\"3.65\", \"price2013\":\"3.75\", \"price2014\":\"4.62\", \"price2015\":\"4.63\", \"priceserving\":\"1.63028169014085\", \"usdaproduct\":\"Salmon, pink, canned, drained with bones\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"102\", \"energykj\":\"427\", \"protein\":\"17\", \"carbohydrate\":\"0\", \"totalfat\":\"4\", \"saturatedfat\":\"0.6\", \"cholesterol\":\"62\", \"calcium\":\"208\", \"iron\":\"0.7\", \"sodium\":\"299\", \"potassium\":\"233\"	, \"phosphorus\":\"274\"  , \"dha\":\"0.52\", \"epa\":\"0.27\", \"vitamina\":\"17\", \"vitamind\":\"N/A\", \"vitaminb12\":\"3.71\", \"vitamine\":\"1\"   , \"polyunsaturatedfatg\":\"1.1\", \"polyunsaturatedfat3\":\"1.1\", \"saturatedfat3\":\"0.6\"  },"
                            + "{\"product\":\"Homogenized milk\", \"unit\":\"1 l\",\"servings\":\"4\", \"price2011\":\"2.27\", \"price2012\":\"2.41\", \"price2013\":\"2.44\", \"price2014\":\"2.47\", \"price2015\":\"2.49\", \"priceserving\":\"0.6225\", \"usdaproduct\":\"Milk, whole, 3.3% M.F.\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"258\", \"energykcal\":\"155\", \"energykj\":\"647\", \"protein\":\"8\", \"carbohydrate\":\"12\", \"totalsugar\":\"14\", \"saturatedfat\":\"5.4\"	, \"calcium\":\"291\", \"iron\":\"0.1\", \"sodium\":\"103\", \"potassium\":\"369\", \"magnesium\":\"26\", \"phosphorus\":\"235\"	, \"riboflavin\":\"0.47\"	, \"folate\":\"13\" , \"vitamina\":\"72\", \"vitamind\":\"2.7\", \"vitaminb12\":\"1.13\"  , \"cholesterol\":\"26\", \"totalfat\":\"8\", \"saturatedfat3\":\"5.4\"  },"
                            + "{\"product\":\"Partly skimmed milk\", \"unit\":\"1 l\",\"servings\":\"4\", \"price2011\":\"2.2\", \"price2012\":\"2.3\", \"price2013\":\"2.29\", \"price2014\":\"2.31\", \"price2015\":\"2.33\", \"priceserving\":\"0.5825\", \"usdaproduct\":\"Milk, partly skimmed, 2% M.F.\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"258\", \"energykcal\":\"129\", \"energykj\":\"539\", \"protein\":\"9\", \"carbohydrate\":\"12\", \"totalsugar\":\"13\", \"saturatedfat\":\"3.3\"	, \"calcium\":\"302\", \"iron\":\"0.1\", \"sodium\":\"106\", \"potassium\":\"387\", \"magnesium\":\"28\", \"phosphorus\":\"242\"	, \"riboflavin\":\"0.48\"	, \"folate\":\"13\" , \"vitamina\":\"142\", \"vitamind\":\"2.8\", \"vitaminb12\":\"1.19\"  , \"cholesterol\":\"21\", \"totalfat\":\"5\", \"saturatedfat3\":\"3.3\"  },"
                            + "{\"product\":\"Butter\", \"unit\":\"454 g\", \"price2011\":\"4.33\",\"servings\":\"90.8\", \"price2012\":\"4.37\", \"price2013\":\"4.39\", \"price2014\":\"4.51\", \"price2015\":\"4.54\", \"priceserving\":\"0.05\", \"usdaproduct\":\"Butter\", \"servingmeasure\":\"5 mL\", \"servingweight\":\"5\", \"energykcal\":\"34\", \"energykj\":\"144\", \"protein\":\"tr\", \"carbohydrate\":\"tr\", \"totalfat\":\"4\", \"saturatedfat\":\"2.5\", \"cholesterol\":\"10\", \"calcium\":\"1\", \"iron\":\"tr\", \"sodium\":\"28\", \"potassium\":\"1\", \"magnesium\":\"tr\", \"phosphorus\":\"1\"   , \"vitamina\":\"33\", \"vitamind\":\"tr\"	, \"vitamine\":\"0.1\"   , \"polyunsaturatedfatg\":\"0.1\", \"polyunsaturatedfat3\":\"0.1\", \"saturatedfat3\":\"2.5\", \"monounsaturatedfat\":\"1\", \"monounsaturatedfat3\":\"1\", \"transfat\":\"0.2\"	},"
                            + "{\"product\":\"Processed cheese food slices\", \"unit\":\"250 g\",\"servings\":\"11.9047619047619\", \"price2011\":\"2.79\", \"price2012\":\"2.78\", \"price2013\":\"2.73\", \"price2014\":\"2.81\", \"price2015\":\"2.8\", \"priceserving\":\"0.2352\", \"usdaproduct\":\"Processed cheese food, thin slices\", \"servingmeasure\":\"1\", \"servingweight\":\"21\", \"energykcal\":\"78\", \"energykj\":\"327\", \"protein\":\"5\", \"carbohydrate\":\"tr\", \"totalsugar\":\"tr\", \"saturatedfat\":\"4.1\"	, \"calcium\":\"115\", \"iron\":\"tr\", \"sodium\":\"310\", \"potassium\":\"35\", \"magnesium\":\"6\", \"phosphorus\":\"107\"	, \"riboflavin\":\"0.07\"	, \"folate\":\"2\" , \"vitamina\":\"53\", \"vitamind\":\"tr\", \"vitaminb12\":\"0.15\"  , \"cholesterol\":\"20\", \"totalfat\":\"7\", \"saturatedfat3\":\"4.1\"  },"
                            + "{\"product\":\"Evaporated milk\", \"unit\":\"385 ml\",\"servings\":\"25.6666666666667\", \"price2011\":\"1.99\", \"price2012\":\"1.93\", \"price2013\":\"1.93\", \"price2014\":\"1.87\", \"price2015\":\"1.91\", \"priceserving\":\"0.0744155844155844\", \"usdaproduct\":\"Milk, evaporated, whole, canned, undiluted, 7.8% M.F.\", \"servingmeasure\":\"15 mL\", \"servingweight\":\"16\", \"energykcal\":\"21\", \"energykj\":\"89\", \"protein\":\"1\", \"carbohydrate\":\"2\", \"totalsugar\":\"2\", \"saturatedfat\":\"0.8\"	, \"calcium\":\"42\", \"iron\":\"tr\", \"sodium\":\"17\", \"potassium\":\"48\", \"magnesium\":\"4\", \"phosphorus\":\"32\"	, \"riboflavin\":\"0.05\"	, \"folate\":\"1\" , \"vitamina\":\"10\", \"vitamind\":\"0.3\", \"vitaminb12\":\"0.03\"  , \"cholesterol\":\"5\", \"totalfat\":\"1\", \"saturatedfat3\":\"0.8\"  },"
                            + "{\"product\":\"Eggs\", \"unit\":\"1 dz\",\"servings\":\"12\", \"price2011\":\"2.85\", \"price2012\":\"3.08\", \"price2013\":\"3.24\", \"price2014\":\"3.27\", \"price2015\":\"3.29\", \"priceserving\":\"0.274166666666667\", \"usdaproduct\":\"Egg, poached\", \"servingmeasure\":\"1 large\", \"servingweight\":\"50\", \"energykcal\":\"74\", \"energykj\":\"308\", \"protein\":\"6\", \"carbohydrate\":\"tr\", \"totalfat\":\"5\"	, \"cholesterol\":\"215\", \"calcium\":\"27\", \"iron\":\"0.9\", \"sodium\":\"147\", \"potassium\":\"67\"	, \"phosphorus\":\"95\", \"folate\":\"24\" , \"vitamina\":\"70\", \"vitamind\":\"0.4\", \"vitaminb12\":\"0.64\", \"vitamine\":\"0.5\"  , \"polyunsaturatedfat3\":\"0.7\", \"saturatedfat3\":\"1.5\"	, \"monounsaturatedfat3\":\"1.9\" },"
                            + "{\"product\":\"Bread\", \"unit\":\"675 g\",\"servings\":\"19.2857142857143\", \"price2011\":\"2.85\", \"price2012\":\"2.77\", \"price2013\":\"2.87\", \"price2014\":\"2.84\", \"price2015\":\"2.96\", \"priceserving\":\"0.153481481481481\", \"usdaproduct\":\"Bread, whole wheat, commercial\", \"servingmeasure\":\"1 slice\", \"servingweight\":\"35\", \"energykcal\":\"86\", \"energykj\":\"360\", \"protein\":\"3\", \"carbohydrate\":\"16\", \"totalsugar\":\"7\", \"totaldietaryfibre\":\"2.4\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.3\", \"cholesterol\":\"0\", \"calcium\":\"25\", \"iron\":\"1.2\", \"sodium\":\"184\", \"potassium\":\"88\", \"magnesium\":\"30\", \"phosphorus\":\"80\", \"thiamin\":\"0.1\", \"riboflavin\":\"0.07\", \"niacin\":\"2.2\", \"folate\":\"18\"       , \"saturatedfat3\":\"0.3\"  },"
                            + "{\"product\":\"Soda crackers\", \"unit\":\"450 g\",\"servings\":\"37.5\", \"price2011\":\"2.69\", \"price2012\":\"2.73\", \"price2013\":\"2.73\", \"price2014\":\"2.7\", \"price2015\":\"2.99\", \"priceserving\":\"0.0797333333333333\", \"usdaproduct\":\"Saltine (oyster, soda, soup)\", \"servingmeasure\":\"4\", \"servingweight\":\"12\", \"energykcal\":\"51\", \"energykj\":\"215\", \"protein\":\"1\", \"carbohydrate\":\"9\", \"totalsugar\":\"tr\", \"totaldietaryfibre\":\"0.4\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.2\", \"cholesterol\":\"0\", \"calcium\":\"8\", \"iron\":\"0.7\", \"sodium\":\"129\", \"potassium\":\"18\", \"magnesium\":\"3\", \"phosphorus\":\"12\", \"thiamin\":\"tr\", \"riboflavin\":\"0.05\", \"niacin\":\"0.9\", \"folate\":\"26\"       , \"saturatedfat3\":\"0.2\"  },"
                            + "{\"product\":\"Macaroni\", \"unit\":\"500 g\",\"servings\":\"3.37837837837838\", \"price2011\":\"1.43\", \"price2012\":\"1.46\", \"price2013\":\"1.4\", \"price2014\":\"1.42\", \"price2015\":\"1.63\", \"priceserving\":\"0.48248\", \"usdaproduct\":\"Macaroni, cooked\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"148\", \"energykcal\":\"209\", \"energykj\":\"873\", \"protein\":\"7\", \"carbohydrate\":\"42\", \"totalsugar\":\"1\", \"totaldietaryfibre\":\"1.8\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.1\", \"cholesterol\":\"0\", \"calcium\":\"10\", \"iron\":\"2.1\", \"sodium\":\"1\", \"potassium\":\"46\", \"magnesium\":\"27\", \"phosphorus\":\"80\", \"thiamin\":\"0.3\", \"riboflavin\":\"0.14\", \"niacin\":\"4\", \"folate\":\"184\"       , \"saturatedfat3\":\"0.1\"  },"
                            + "{\"product\":\"Flour\", \"unit\":\"2.5 kg\",\"servings\":\"20\", \"price2011\":\"5.15\", \"price2012\":\"5.19\", \"price2013\":\"5.09\", \"price2014\":\"4.95\", \"price2015\":\"5.13\", \"priceserving\":\"0.147744\", \"usdaproduct\":\"Wheat flour, bread\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"72\", \"energykcal\":\"261\", \"energykj\":\"1093\", \"protein\":\"9\", \"carbohydrate\":\"52\", \"totalsugar\":\"tr\", \"totaldietaryfibre\":\"1.7\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.2\", \"cholesterol\":\"0\", \"calcium\":\"11\", \"iron\":\"3.2\", \"sodium\":\"1\", \"potassium\":\"72\", \"magnesium\":\"18\", \"phosphorus\":\"70\", \"thiamin\":\"0.6\", \"riboflavin\":\"0.37\", \"niacin\":\"7.1\", \"folate\":\"208\"       , \"saturatedfat3\":\"0.2\"  },"
                            + "{\"product\":\"Corn flakes\", \"unit\":\"675 g\",\"servings\":\"25.9615384615385\", \"price2011\":\"4.5\", \"price2012\":\"4.98\", \"price2013\":\"4.85\", \"price2014\":\"5.1\", \"price2015\":\"4.77\", \"priceserving\":\"0.183733333333333\", \"usdaproduct\":\"Corn Flakes, Kellogg's™\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"26\", \"energykcal\":\"103\", \"energykj\":\"430\", \"protein\":\"2\", \"carbohydrate\":\"23\", \"totalsugar\":\"2\", \"totaldietaryfibre\":\"0.7\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"1\", \"iron\":\"3.5\", \"sodium\":\"190\", \"potassium\":\"28\", \"magnesium\":\"2\", \"phosphorus\":\"12\", \"thiamin\":\"0.5\", \"riboflavin\":\"0.71\", \"niacin\":\"1.6\", \"folate\":\"32\"       , \"saturatedfat3\":\"0\"  },"
                            + "{\"product\":\"Apples\", \"unit\":\"1 kg\",\"servings\":\"7.2463768115942\", \"price2011\":\"3.36\", \"price2012\":\"3.51\", \"price2013\":\"3.89\", \"price2014\":\"3.97\", \"price2015\":\"3.8\", \"priceserving\":\"0.5244\", \"usdaproduct\":\"Apple with skin (7 cm.diam)\", \"servingmeasure\":\"1\", \"servingweight\":\"138\", \"energykcal\":\"72\", \"energykj\":\"300\", \"protein\":\"tr\", \"carbohydrate\":\"19\", \"totalsugar\":\"14\", \"totaldietaryfibre\":\"2.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"8\", \"iron\":\"0.2\", \"sodium\":\"1\", \"potassium\":\"148\", \"magnesium\":\"7\", \"phosphorus\":\"15\", \"folate\":\"4\" , \"vitamina\":\"4\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"37\", \"lycopene\":\"0\", \"vitaminc\":\"6\"     },"
                            + "{\"product\":\"Bananas\", \"unit\":\"1 kg\",\"servings\":\"8.47457627118644\", \"price2011\":\"1.72\", \"price2012\":\"1.7\", \"price2013\":\"1.66\", \"price2014\":\"1.67\", \"price2015\":\"1.72\", \"priceserving\":\"0.20296\", \"usdaproduct\":\"Banana\", \"servingmeasure\":\"1\", \"servingweight\":\"118\", \"energykcal\":\"105\", \"energykj\":\"439\", \"protein\":\"1\", \"carbohydrate\":\"27\", \"totalsugar\":\"14\", \"totaldietaryfibre\":\"2.1\"	, \"totalfat\":\"tr\" , \"calcium\":\"6\", \"iron\":\"0.3\", \"sodium\":\"1\", \"potassium\":\"422\", \"magnesium\":\"32\", \"phosphorus\":\"26\", \"folate\":\"24\" , \"vitamina\":\"4\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"31\", \"lycopene\":\"0\", \"vitaminc\":\"10\"     },"
                            + "{\"product\":\"Grapefruits\", \"unit\":\"1 kg\",\"servings\":\"8.13008130081301\", \"price2011\":\"2.81\", \"price2012\":\"2.96\", \"price2013\":\"2.96\", \"price2014\":\"2.9\", \"price2015\":\"3.35\", \"priceserving\":\"0.41205\", \"usdaproduct\":\"Grapefruit, pink or red\", \"servingmeasure\":\"½\", \"servingweight\":\"123\", \"energykcal\":\"52\", \"energykj\":\"216\", \"protein\":\"1\", \"carbohydrate\":\"13\", \"totalsugar\":\"8\", \"totaldietaryfibre\":\"2\"	, \"totalfat\":\"tr\" , \"calcium\":\"27\", \"iron\":\"0.1\", \"sodium\":\"0\", \"potassium\":\"166\", \"magnesium\":\"11\", \"phosphorus\":\"22\", \"folate\":\"16\" , \"vitamina\":\"71\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"844\", \"lycopene\":\"1745\", \"vitaminc\":\"38\"     },"
                            + "{\"product\":\"Oranges\", \"unit\":\"1 kg\",\"servings\":\"7.63358778625954\", \"price2011\":\"2.69\", \"price2012\":\"2.89\", \"price2013\":\"3.15\", \"price2014\":\"3.54\", \"price2015\":\"3.56\", \"priceserving\":\"0.46636\", \"usdaproduct\":\"Orange\", \"servingmeasure\":\"1\", \"servingweight\":\"131\", \"energykcal\":\"62\", \"energykj\":\"258\", \"protein\":\"1\", \"carbohydrate\":\"15\", \"totalsugar\":\"12\", \"totaldietaryfibre\":\"2.3\"	, \"totalfat\":\"tr\" , \"calcium\":\"52\", \"iron\":\"0.1\", \"sodium\":\"0\", \"potassium\":\"237\", \"magnesium\":\"13\", \"phosphorus\":\"18\", \"folate\":\"39\" , \"vitamina\":\"8\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"93\", \"lycopene\":\"0\", \"vitaminc\":\"70\"     },"
                            + "{\"product\":\"Apple juice, canned\", \"unit\":\"1.36 l\",\"servings\":\"10.88\", \"price2011\":\"2.06\", \"price2012\":\"2.13\", \"price2013\":\"2.1\", \"price2014\":\"2.07\", \"price2015\":\"2.08\", \"priceserving\":\"0.191176470588235\", \"usdaproduct\":\"Apple juice, ready-to-drink, vitamin C added\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"126\", \"energykcal\":\"59\", \"energykj\":\"249\", \"protein\":\"tr\", \"carbohydrate\":\"15\", \"totalsugar\":\"14\", \"totaldietaryfibre\":\"0.1\"	, \"totalfat\":\"tr\" , \"calcium\":\"9\", \"iron\":\"0.5\", \"sodium\":\"4\", \"potassium\":\"150\", \"magnesium\":\"4\", \"phosphorus\":\"9\", \"folate\":\"tr\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"0\", \"lycopene\":\"0\", \"vitaminc\":\"52\"     },"
                            + "{\"product\":\"Orange juice, tetra-brick\", \"unit\":\"1 l\",\"servings\":\"8\", \"price2011\":\"4.01\", \"price2012\":\"3.94\", \"price2013\":\"3.95\", \"price2014\":\"3.97\", \"price2015\":\"4.2\", \"priceserving\":\"0.525\", \"usdaproduct\":\"Orange juice, ready-to-drink\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"132\", \"energykcal\":\"58\", \"energykj\":\"242\", \"protein\":\"1\", \"carbohydrate\":\"13\", \"totalsugar\":\"N/A\", \"totaldietaryfibre\":\"0.3\"	, \"totalfat\":\"tr\" , \"calcium\":\"13\", \"iron\":\"0.2\", \"sodium\":\"1\", \"potassium\":\"250\", \"magnesium\":\"14\", \"phosphorus\":\"14\", \"folate\":\"24\" , \"vitamina\":\"5\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"61\", \"lycopene\":\"0\", \"vitaminc\":\"43\"     },"
                            + "{\"product\":\"Carrots\", \"unit\":\"1 kg\", \"price2011\":\"2.29\",\"servings\":\"12.5\", \"price2012\":\"1.86\", \"price2013\":\"1.84\", \"price2014\":\"2.02\", \"price2015\":\"2.17\", \"priceserving\":\"0.1736\", \"usdaproduct\":\"Carrots, baby, raw\", \"servingmeasure\":\"8\", \"servingweight\":\"80\", \"energykcal\":\"28\", \"energykj\":\"117\", \"protein\":\"1\", \"carbohydrate\":\"7\", \"totalsugar\":\"4\", \"totaldietaryfibre\":\"1.4\"	, \"totalfat\":\"tr\" , \"calcium\":\"26\", \"iron\":\"0.7\", \"sodium\":\"62\", \"potassium\":\"190\", \"magnesium\":\"8\", \"phosphorus\":\"22\", \"folate\":\"26\" , \"vitamina\":\"552\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"5113\", \"lycopene\":\"0\", \"vitaminc\":\"7\"     },"
                            + "{\"product\":\"Celery\", \"unit\":\"1 kg\",\"servings\":\"25\", \"price2011\":\"2.85\", \"price2012\":\"2.28\", \"price2013\":\"2.99\", \"price2014\":\"2.37\", \"price2015\":\"2.79\", \"priceserving\":\"0.1116\", \"usdaproduct\":\"Celery, raw\", \"servingmeasure\":\"1 stalk\", \"servingweight\":\"40\", \"energykcal\":\"6\", \"energykj\":\"24\", \"protein\":\"tr\", \"carbohydrate\":\"1\", \"totalsugar\":\"1\", \"totaldietaryfibre\":\"0.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"16\", \"iron\":\"0.1\", \"sodium\":\"32\", \"potassium\":\"104\", \"magnesium\":\"4\", \"phosphorus\":\"10\", \"folate\":\"14\" , \"vitamina\":\"9\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"108\", \"lycopene\":\"0\", \"vitaminc\":\"1\"     },"
                            + "{\"product\":\"Mushrooms\", \"unit\":\"1 kg\",\"servings\":\"18.5185185185185\", \"price2011\":\"8.09\", \"price2012\":\"8.13\", \"price2013\":\"8.04\", \"price2014\":\"8.15\", \"price2015\":\"8.75\", \"priceserving\":\"0.4725\", \"usdaproduct\":\"Mushrooms, raw\", \"servingmeasure\":\"3 medium\", \"servingweight\":\"54\", \"energykcal\":\"12\", \"energykj\":\"50\", \"protein\":\"2\", \"carbohydrate\":\"2\", \"totalsugar\":\"1\", \"totaldietaryfibre\":\"0.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"2\", \"iron\":\"0.3\", \"sodium\":\"2\", \"potassium\":\"170\", \"magnesium\":\"5\", \"phosphorus\":\"46\", \"folate\":\"9\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0.02\"	, \"betacarotene\":\"0\", \"lycopene\":\"0\", \"vitaminc\":\"1\"     },"
                            + "{\"product\":\"Onions\", \"unit\":\"1 kg\",\"servings\":\"16.6666666666667\", \"price2011\":\"1.71\", \"price2012\":\"1.53\", \"price2013\":\"1.88\", \"price2014\":\"2.21\", \"price2015\":\"1.7\", \"priceserving\":\"0.0697\", \"usdaproduct\":\"Onions, yellow, chopped, raw\", \"servingmeasure\":\"60 mL\", \"servingweight\":\"41\", \"energykcal\":\"17\", \"energykj\":\"71\", \"protein\":\"tr\", \"carbohydrate\":\"4\", \"totalsugar\":\"2\", \"totaldietaryfibre\":\"0.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"9\", \"iron\":\"0.1\", \"sodium\":\"1\", \"potassium\":\"58\", \"magnesium\":\"4\", \"phosphorus\":\"11\", \"folate\":\"8\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"tr\", \"lycopene\":\"0\", \"vitaminc\":\"3\"     },"
                            + "{\"product\":\"Potatoes\", \"unit\":\"4.54 kg\",\"servings\":\"26.242774566474\", \"price2011\":\"5.24\", \"price2012\":\"5.65\", \"price2013\":\"5.52\", \"price2014\":\"5.74\", \"price2015\":\"5.66\", \"priceserving\":\"0.215678414096916\", \"usdaproduct\":\"Potato, baked, flesh and skin\", \"servingmeasure\":\"1\", \"servingweight\":\"173\", \"energykcal\":\"161\", \"energykj\":\"673\", \"protein\":\"4\", \"carbohydrate\":\"37\", \"totalsugar\":\"2\", \"totaldietaryfibre\":\"3.8\"	, \"totalfat\":\"tr\" , \"calcium\":\"26\", \"iron\":\"1.9\", \"sodium\":\"17\", \"potassium\":\"926\", \"magnesium\":\"48\", \"phosphorus\":\"121\", \"folate\":\"48\" , \"vitamina\":\"2\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"10\", \"lycopene\":\"0\", \"vitaminc\":\"17\"     },"
                            + "{\"product\":\"French fried potatoes, frozen\", \"unit\":\"1 kg\",\"servings\":\"20.8333333333333\", \"price2011\":\"2.24\", \"price2012\":\"2.47\", \"price2013\":\"2.43\", \"price2014\":\"2.5\", \"price2015\":\"2.71\", \"priceserving\":\"0.13008\", \"usdaproduct\":\"Potatoes, French fried, frozen, home-prepared in oven\", \"servingmeasure\":\"20 strips\", \"servingweight\":\"48\", \"energykcal\":\"96\", \"energykj\":\"403\", \"protein\":\"2\", \"carbohydrate\":\"15\", \"totalsugar\":\"tr\", \"totaldietaryfibre\":\"1.6\"	, \"totalfat\":\"4\" , \"calcium\":\"4\", \"iron\":\"0.6\", \"sodium\":\"14\", \"potassium\":\"201\", \"magnesium\":\"11\", \"phosphorus\":\"40\", \"folate\":\"6\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"1\", \"lycopene\":\"0\", \"vitaminc\":\"5\"     },"
                            + "{\"product\":\"Olives\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Olives, pickled, canned or bottled\"	,\"servingmeasure\":\"4\"	,\"servingweight\":\"16\"	,\"energykcal\":\"23\"	,\"energykj\":\"97\"	,\"protein\":\"tr\"	,\"carbohydrate\":\"1\"	,\"totalsugar\":\"tr\"	,\"totaldietaryfibre\":\"2\"	,\"totaldaietaryfibre\":\"0.3\"	,\"totalfat\":\"1.8\"	,\"saturatedfat\":\"0.2\"	,\"cholesterol\":\"0\"	,\"calcium\":\"8\"	,\"iron\":\"0.1\"	,\"sodium\":\"249\"	,\"potassium\":\"7\"	,\"magnesium\":\"2\"	,\"phosphorus\":\"1\"	,\"thiamin\":\"3\"	,\"riboflavin\":\"0\"	,\"niacin\":\"tr\"																				},"
                            + "{\"product\":\"Corned beef\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Corned beef, brisket, cooked\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"101\"	,\"energykj\":\"422\"	,\"protein\":\"11\"	,\"carbohydrate\":\"1\"	,\"totalsugar\":\"6\"	,\"totaldietaryfibre\":\"2\"	,\"totaldaietaryfibre\":\"2.9\"	,\"totalfat\":\"0.2\"	,\"saturatedfat\":\"55\"	,\"cholesterol\":\"1\"	,\"calcium\":\"653\"	,\"iron\":\"112\"	,\"sodium\":\"7\"	,\"potassium\":\"70\"	,\"magnesium\":\"0\"	,\"phosphorus\":\"0.2\"	,\"thiamin\":\"0.91\"	,\"riboflavin\":\"3\"	,\"niacin\":\"0.1\"																				},"
                            + "{\"product\":\"Bologna\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Bologna (baloney), beef and pork\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"153\"	,\"energykj\":\"640\"	,\"protein\":\"7\"	,\"carbohydrate\":\"3\"	,\"totalsugar\":\"13\"	,\"totaldietaryfibre\":\"4.7\"	,\"totaldaietaryfibre\":\"5.9\"	,\"totalfat\":\"1.1\"	,\"saturatedfat\":\"31\"	,\"cholesterol\":\"0.8\"	,\"calcium\":\"549\"	,\"iron\":\"132\"	,\"sodium\":\"6\"	,\"potassium\":\"51\"	,\"magnesium\":\"14\"	,\"phosphorus\":\"0.6\"	,\"thiamin\":\"0.74\"	,\"riboflavin\":\"3\"	,\"niacin\":\"0.2\"																				},"
                            + "{\"product\":\"Deli meat, ham\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Deli meat, ham, regular (11% fat)\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"91\"	,\"energykj\":\"382\"	,\"protein\":\"9\"	,\"carbohydrate\":\"2\"	,\"totalsugar\":\"5\"	,\"totaldietaryfibre\":\"1.6\"	,\"totaldaietaryfibre\":\"2.4\"	,\"totalfat\":\"0.4\"	,\"saturatedfat\":\"32\"	,\"cholesterol\":\"0.6\"	,\"calcium\":\"730\"	,\"iron\":\"161\"	,\"sodium\":\"12\"	,\"potassium\":\"86\"	,\"magnesium\":\"0\"	,\"phosphorus\":\"0.1\"	,\"thiamin\":\"0.24\"	,\"riboflavin\":\"4\"	,\"niacin\":\"tr\"																				},"
                            + "{\"product\":\"Deli meat, turkey\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Deli meat, turkey breast\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"63\"	,\"energykj\":\"262\"	,\"protein\":\"8\"	,\"carbohydrate\":\"4\"	,\"totalsugar\":\"2\"	,\"totaldietaryfibre\":\"0.1\"	,\"totaldaietaryfibre\":\"0.3\"	,\"totalfat\":\"0.2\"	,\"saturatedfat\":\"31\"	,\"cholesterol\":\"1.2\"	,\"calcium\":\"672\"	,\"iron\":\"195\"	,\"sodium\":\"11\"	,\"potassium\":\"88\"	,\"magnesium\":\"0\"	,\"phosphorus\":\"0\"	,\"thiamin\":\"0.12\"	,\"riboflavin\":\"2\"	,\"niacin\":\"0.1\"																				},"
                            + "{\"product\":\"Baked beans, canned\",\"servings\":\"2.27428571428571\", \"unit\":\"398 ml\", \"price2011\":\"1.11\", \"price2012\":\"1.23\", \"price2013\":\"1.19\", \"price2014\":\"1.27\", \"price2015\":\"1.33\", \"priceserving\":\"0.584798994974874\", \"usdaproduct\":\"Beans, baked, with pork, canned\", \"servingmeasure\":\"175 mL\", \"servingweight\":\"187\", \"energykcal\":\"198\", \"energykj\":\"829\", \"protein\":\"10\", \"carbohydrate\":\"37\", \"totalsugar\":\"N/A\"	, \"totaldaietaryfibre\":\"10.4\", \"totalfat\":\"3\", \"saturatedfat\":\"1.1\"	, \"calcium\":\"99\", \"iron\":\"3.2\", \"sodium\":\"775\", \"potassium\":\"578\", \"magnesium\":\"64\", \"phosphorus\":\"202\", \"folate\":\"67\"  , \"vitaminb12\":\"0\", \"vitamine\":\"N/A\"   , \"polyunsaturatedfatg\":\"0.4\", \"polyunsaturatedfat3\":\"0.4\", \"saturatedfat3\":\"1.1\", \"monounsaturatedfat\":\"1.3\", \"monounsaturatedfat3\":\"1.3\" },"
                            + "{\"product\":\"Tomatoes, canned\", \"unit\":\"796 ml\",\"servings\":\"6.368\", \"price2011\":\"1.58\", \"price2012\":\"1.52\", \"price2013\":\"1.49\", \"price2014\":\"1.54\", \"price2015\":\"1.64\", \"priceserving\":\"0.257537688442211\", \"usdaproduct\":\"Tomatoes, canned, stewed\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"135\", \"energykcal\":\"35\", \"energykj\":\"147\", \"protein\":\"1\", \"carbohydrate\":\"8\", \"totalsugar\":\"6\", \"totaldietaryfibre\":\"1.4\"	, \"totalfat\":\"tr\" , \"calcium\":\"46\", \"iron\":\"1.8\", \"sodium\":\"298\", \"potassium\":\"279\", \"magnesium\":\"16\", \"phosphorus\":\"27\", \"folate\":\"7\" , \"vitamina\":\"12\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"140\", \"lycopene\":\"5436\", \"vitaminc\":\"11\"     },"
                            + "{\"product\":\"Tomato juice, canned\", \"unit\":\"1.36 l\",\"servings\":\"10.88\", \"price2011\":\"2.16\", \"price2012\":\"2.41\", \"price2013\":\"2.42\", \"price2014\":\"2.49\", \"price2015\":\"2.54\", \"priceserving\":\"0.233455882352941\", \"usdaproduct\":\"Tomato juice\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"128\", \"energykcal\":\"22\", \"energykj\":\"91\", \"protein\":\"1\", \"carbohydrate\":\"5\", \"totalsugar\":\"5\", \"totaldietaryfibre\":\"0.9\"	, \"totalfat\":\"tr\" , \"calcium\":\"13\", \"iron\":\"0.6\", \"sodium\":\"345\", \"potassium\":\"294\", \"magnesium\":\"14\", \"phosphorus\":\"23\", \"folate\":\"26\" , \"vitamina\":\"30\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"347\", \"lycopene\":\"11602\", \"vitaminc\":\"10\"     },"
                            + "{\"product\":\"Ketchup\", \"unit\":\"1 l\",\"servings\":\"66.6666666666667\", \"price2011\":\"3\", \"price2012\":\"3.3\", \"price2013\":\"3.09\", \"price2014\":\"3.19\", \"price2015\":\"3.32\", \"priceserving\":\"0.0498\", \"usdaproduct\":\"Ketchup\", \"servingmeasure\":\"15 mL\", \"servingweight\":\"15\", \"energykcal\":\"15\", \"energykj\":\"64\", \"protein\":\"tr\", \"carbohydrate\":\"4\", \"totalsugar\":\"3\" , \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"3\", \"iron\":\"0.1\", \"sodium\":\"169\", \"potassium\":\"57\", \"magnesium\":\"3\", \"phosphorus\":\"5\", \"folate\":\"2\" , \"vitamina\":\"7\"  , \"lycopene\":\"2586\", \"polyunsaturatedfat\":\"tr\" , \"saturatedfat3\":\"tr\", \"monounsaturatedfat\":\"tr\", \"monounsaturatedfat3\":\"tr\" },"
                            + "{\"product\":\"Sugar, white\", \"unit\":\"2 kg\",\"servings\":\"400\", \"price2011\":\"3.19\", \"price2012\":\"3.16\", \"price2013\":\"3.03\", \"price2014\":\"2.76\", \"price2015\":\"2.79\", \"priceserving\":\"0.00558\", \"usdaproduct\":\"White sugar (granulated)\", \"servingmeasure\":\"5 mL\", \"servingweight\":\"4\", \"energykcal\":\"16\", \"energykj\":\"68\", \"protein\":\"0\", \"carbohydrate\":\"4\", \"totalsugar\":\"4\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"0\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"tr\", \"iron\":\"0\", \"sodium\":\"0\", \"potassium\":\"0\", \"magnesium\":\"0\", \"phosphorus\":\"0\"   , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\", \"vitaminc\":\"0\" , \"saturatedfat3\":\"0\"  },"
                            + "{\"product\":\"Coffee, roasted\", \"unit\":\"300 g\",\"servings\":\"60\", \"price2011\":\"5.79\", \"price2012\":\"6.12\", \"price2013\":\"5.88\", \"price2014\":\"5.56\", \"price2015\":\"6.53\", \"priceserving\":\"5.44166666666667\", \"usdaproduct\":\"Coffee, brewed\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"250\", \"energykcal\":\"3\", \"energykj\":\"10\", \"protein\":\"tr\", \"carbohydrate\":\"0\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"5\", \"iron\":\"tr\", \"sodium\":\"5\", \"potassium\":\"123\", \"magnesium\":\"8\", \"phosphorus\":\"8\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                            + "{\"product\":\"Coffee, instant\", \"unit\":\"200 g\",\"servings\":\"25\", \"price2011\":\"6.26\", \"price2012\":\"6.6\", \"price2013\":\"6.62\", \"price2014\":\"6.26\", \"price2015\":\"6.93\", \"priceserving\":\"8.76645\", \"usdaproduct\":\"Coffee, instant, regular, powder + water\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"253\", \"energykcal\":\"5\", \"energykj\":\"20\", \"protein\":\"tr\", \"carbohydrate\":\"1\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"10\", \"iron\":\"0.1\", \"sodium\":\"5\", \"potassium\":\"76\", \"magnesium\":\"8\", \"phosphorus\":\"8\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                            + "{\"product\":\"Tea (bags)\", \"unit\":\"72\",\"servings\":\"72\", \"price2011\":\"4.4\", \"price2012\":\"4.42\", \"price2013\":\"4.32\", \"price2014\":\"4.4\", \"price2015\":\"4.51\", \"priceserving\":\"0.0626388888888889\", \"usdaproduct\":\"Tea, brewed\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"250\", \"energykcal\":\"3\", \"energykj\":\"10\", \"protein\":\"0\", \"carbohydrate\":\"1\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"0\", \"iron\":\"0.1\", \"sodium\":\"8\", \"potassium\":\"93\", \"magnesium\":\"8\", \"phosphorus\":\"3\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                            + "{\"product\":\"Cooking or salad oil\", \"unit\":\"1 l\",\"servings\":\"66.6666666666667\", \"price2011\":\"4.14\", \"price2012\":\"4.17\", \"price2013\":\"4.16\", \"price2014\":\"4.07\", \"price2015\":\"4.18\", \"priceserving\":\"0.0627\", \"usdaproduct\":\"Canola\", \"servingmeasure\":\"15 mL\", \"servingweight\":\"14\", \"energykcal\":\"125\", \"energykj\":\"525\", \"protein\":\"0\", \"carbohydrate\":\"0\", \"totalfat\":\"14\", \"saturatedfat\":\"1\", \"cholesterol\":\"0\", \"calcium\":\"0\", \"iron\":\"0\", \"sodium\":\"0\", \"potassium\":\"0\", \"magnesium\":\"0\", \"phosphorus\":\"0\"   , \"vitamina\":\"0\", \"vitamind\":\"0\"	, \"vitamine\":\"2.4\"   , \"polyunsaturatedfatg\":\"4.2\", \"polyunsaturatedfat3\":\"4.2\", \"saturatedfat3\":\"1\", \"monounsaturatedfat\":\"8.4\", \"monounsaturatedfat3\":\"8.4\", \"transfat\":\"0.3\"	},"
                            + "{\"product\":\"Soup, canned\", \"unit\":\"284 ml\",\"servings\":\"1.136\", \"price2011\":\"1.03\", \"price2012\":\"1.07\", \"price2013\":\"1.04\", \"price2014\":\"1.03\", \"price2015\":\"1.13\", \"priceserving\":\"0.994718309859155\", \"usdaproduct\":\"Tomato\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"258\", \"energykcal\":\"90\", \"energykj\":\"376\", \"protein\":\"2\", \"carbohydrate\":\"18\", \"totalsugar\":\"9\", \"totaldietaryfibre\":\"1.2\"	, \"totalfat\":\"2\", \"saturatedfat\":\"0.4\", \"cholesterol\":\"0\", \"calcium\":\"13\", \"iron\":\"1.9\", \"sodium\":\"735\", \"potassium\":\"278\", \"magnesium\":\"8\", \"phosphorus\":\"36\", \"folate\":\"15\" , \"vitamina\":\"26\"	, \"vitaminb12\":\"0\", \"vitaminc\":\"3\" , \"saturatedfat3\":\"0.4\"  },"
                            + "{\"product\":\"Baby food\", \"unit\":\"128 ml\", \"price2011\":\"0.8\", \"price2012\":\"0.85\", \"price2013\":\"0.83\", \"price2014\":\"0.92\", \"price2015\":\"0.94\", \"priceserving\":\"\"},"
                            + "{\"product\":\"Peanut butter\", \"unit\":\"500 g\",\"servings\":\"16.6666666666667\", \"price2011\":\"3\", \"price2012\":\"3.84\", \"price2013\":\"3.59\", \"price2014\":\"3.48\", \"price2015\":\"3.57\", \"priceserving\":\"0.22848\", \"usdaproduct\":\"Peanut butter, chunk type, fat, sugar and salt added\", \"servingmeasure\":\"30 mL\", \"servingweight\":\"32\", \"energykcal\":\"191\", \"energykj\":\"799\", \"protein\":\"8\", \"carbohydrate\":\"7\", \"totalsugar\":\"3\"	, \"totaldaietaryfibre\":\"2.6\", \"totalfat\":\"16\", \"saturatedfat\":\"2.6\"	, \"calcium\":\"15\", \"iron\":\"0.6\", \"sodium\":\"158\", \"potassium\":\"242\", \"magnesium\":\"52\", \"phosphorus\":\"103\", \"folate\":\"30\"  , \"vitaminb12\":\"0\", \"vitamine\":\"2\"   , \"polyunsaturatedfatg\":\"4.8\", \"polyunsaturatedfat3\":\"4.8\", \"saturatedfat3\":\"2.6\", \"monounsaturatedfat\":\"8\", \"monounsaturatedfat3\":\"8\" },"
                            + "{\"product\":\"Fruit flavoured crystals\", \"unit\":\"2.25 l\",\"servings\":\"9\", \"price2011\":\"1.42\", \"price2012\":\"1.44\", \"price2013\":\"1.47\", \"price2014\":\"1.57\", \"price2015\":\"1.74\", \"priceserving\":\"0.193333333333333\", \"usdaproduct\":\"Fruit punch flavour drink, powder (Kool-Aid™) + water\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"276\", \"energykcal\":\"102\", \"energykj\":\"427\", \"protein\":\"0\", \"carbohydrate\":\"26\", \"totalsugar\":\"N/A\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"44\", \"iron\":\"0.1\", \"sodium\":\"39\", \"potassium\":\"3\", \"magnesium\":\"3\", \"phosphorus\":\"55\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                            + "{\"product\":\"Soft drinks, cola type\", \"unit\":\"2 l\", \"price2011\":\"1.66\",\"servings\":\"8\", \"price2012\":\"1.91\", \"price2013\":\"1.98\", \"price2014\":\"1.99\", \"price2015\":\"1.98\", \"priceserving\":\"0.2475\", \"usdaproduct\":\"Cola, aspartame sweetened\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"250\", \"energykcal\":\"3\", \"energykj\":\"10\", \"protein\":\"tr\", \"carbohydrate\":\"tr\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"0\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"8\", \"iron\":\"0.1\", \"sodium\":\"13\", \"potassium\":\"15\", \"magnesium\":\"3\", \"phosphorus\":\"28\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"0\"  },"
                            + "{\"product\":\"Soft drinks, lemon-lime type\", \"unit\":\"2 l\", \"price2011\":\"1.62\",\"servings\":\"8\", \"price2012\":\"1.82\", \"price2013\":\"1.86\", \"price2014\":\"1.92\", \"price2015\":\"1.91\", \"priceserving\":\"0.23875\", \"usdaproduct\":\"Lemon-lime soda\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"260\", \"energykcal\":\"104\", \"energykj\":\"433\", \"protein\":\"0\", \"carbohydrate\":\"27\", \"totalsugar\":\"23\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"0\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"5\", \"iron\":\"0.2\", \"sodium\":\"29\", \"potassium\":\"3\", \"magnesium\":\"3\", \"phosphorus\":\"0\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"0\"  }]}";
                    final JSONObject jsonObject;
                    JSONObject jsonFoodGuide = null;
                    JSONObject jsonGroceryList = null;
                    JSONObject jsonNutrientData = null;
                    final Double[] arrFoodGuide = new Double[]{0d,0d,0d,0d};
                    final Double[] arrFoodCount = new Double[]{0d,0d,0d,0d};
                    final Double[] arrFoodNutrient = new Double[]{0d,0d,0d,0d,0d,0d,0d,0d
                            ,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d,0d};  // The first two don't count
                    final String[] arrFactFormat = new String[]{
                            "%1$s","%1$s",
                            "%1$.0f","%1$.0f",
                            "%1$.1f g","%1$.0f%%",
                            "%1$.1f g","%1$.0f%%",
                            "%1$.1f g","%1$.0f%%",
                            "%1$.1f mg","%1$.0f%%",
                            "%1$.1f mg","%1$.0f%%",
                            "%1$.1f g","%1$.0f%%",
                            "%1$.1f g","%1$.0f%%",
                            "%1$.1f g","%1$.0f%%",
                            "%1$.1f g","%1$.0f%%",
                            "%1$.0f%%","%1$.0f%%",
                            "%1$.0f%%","%1$.0f%%"
                    };
                    final Integer[] arrPeopleCount = new Integer[]{0,0};
                    JSONArray jsonFGArray = null;
                    JSONArray jsonGLArray = null;
                    JSONArray jsonNDArray = null;
                    Integer arrFGLength = 12;
                    Integer arrGLLength = 0;
                    Integer arrNDLength = 0;
                    String strProduct = "";
                    try {
                        jsonObject = new JSONObject(strJperson);
                        jsonFoodGuide = new JSONObject(strJfoodGuide);
                        jsonGroceryList = new JSONObject(strJgroceryList);
                        jsonFGArray = jsonObject.optJSONArray(PERSONS_ARRAY_KEY);
                        if( arrFGLength != (jsonFGArray != null ? jsonFGArray.length():0) ) {
                            arrFGLength = 0;
                            Toast.makeText(getContext(),"Uh-oh" + String.valueOf(jsonFGArray.length()),Toast.LENGTH_SHORT).show();
                        }
                        jsonGLArray = jsonGroceryList.optJSONArray(NUTRIENT_LIST_FOOD_KEY);
                        arrGLLength = jsonGLArray.length();
                        jsonNutrientData =  new JSONObject(strFoodNutrients);
                        jsonNDArray = jsonNutrientData.optJSONArray(NUTRIENT_LIST_KEY);
                        arrNDLength = jsonNDArray.length();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        arrFGLength = 0;
                    }

                    if( arrGLLength > 0 ) {
                        for (Integer intI = 0; intI < arrGLLength; intI++) {
                            JSONObject objElement = jsonGLArray.optJSONObject(intI);
                            strProduct = "";
                            // Get grocery list serving equivalent values, then the nutrient values
                            try {
                                strProduct = objElement.getString(NUTRIENT_PRODUCT_KEY);
                                switch (objElement.getString(NUTRIENT_LIST_DEPARTMENT_KEY)) {
                                    case "Vegetable Department":
                                    case "Fruit Department":
                                        if (Objects.equals(strProduct, "Potatoes")) {
                                            arrFoodCount[1] += !Objects.equals(objElement.getString(NUTRIENT_LIST_SERVING_KEY), "")
                                                    ? Double.valueOf(objElement.getString(NUTRIENT_LIST_SERVING_KEY)) : 0;
                                        }
                                        else {
                                            arrFoodCount[0] += !Objects.equals(objElement.getString(NUTRIENT_LIST_SERVING_KEY), "")
                                                    ? Double.valueOf(objElement.getString(NUTRIENT_LIST_SERVING_KEY)) : 0;
                                        }
                                        break;
                                    case "Meat Department":
                                        arrFoodCount[3] += !Objects.equals(objElement.getString(NUTRIENT_LIST_SERVING_KEY), "")
                                                ? Double.valueOf(objElement.getString(NUTRIENT_LIST_SERVING_KEY)) : 0;
                                        break;
                                    //case "Delicatessen Department":
                                    case "Bakery Department":
                                        arrFoodCount[1] += !Objects.equals(objElement.getString(NUTRIENT_LIST_SERVING_KEY), "")
                                                ? Double.valueOf(objElement.getString(NUTRIENT_LIST_SERVING_KEY)) : 0;
                                        break;
                                    case "Dairy Department":
                                        if (Objects.equals(strProduct, "Eggs")) {
                                            arrFoodCount[3] += !Objects.equals(objElement.getString(NUTRIENT_LIST_SERVING_KEY), "")
                                                    ? Double.valueOf(objElement.getString(NUTRIENT_LIST_SERVING_KEY)) : 0;
                                        } else {
                                            arrFoodCount[2] += !Objects.equals(objElement.getString(NUTRIENT_LIST_SERVING_KEY), "")
                                                    ? Double.valueOf(objElement.getString(NUTRIENT_LIST_SERVING_KEY)) : 0;
                                        }
                                        break;
                                    //case "Frozen Food Department":
                                    //case "Miscellaneous Department":
                                    default:
                                        // Junkfood
                                        break;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // now get the aforementioned nutrient values
                            if( arrNDLength>0 ){
                                for (Integer intJ = 0 ; intJ < arrNDLength; intJ++ ){
                                    JSONObject objFood = jsonNDArray.optJSONObject(intJ);
                                    try {
                                        if(Objects.equals(objFood.getString(NUTRIENT_PRODUCT_KEY).toLowerCase(), strProduct.toLowerCase())) {
                                            // Toast.makeText( getContext(), "Product: " + objFood.getString(NUTRIENT_PRODUCT_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Price per " + objFood.getString(NUTRIENT_PRICE_PER_KEY) +":" + objFood.getString(NUTRIENT_PRICE_KEY),Toast.LENGTH_LONG).show();
                                            // Toast.makeText( getContext(), "Serving size: " + objFood.getString(NUTRIENT_SERVING_SIZE_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Servings per " + objFood.getString(NUTRIENT_PRICE_PER_KEY) +":" + objFood.getString(NUTRIENT_SERVING_PER_KEY),Toast.LENGTH_LONG).show();
                                            // Toast.makeText( getContext(), "Calories: " + objFood.getString(NUTRIENT_CALORIE_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Carbs: " + objFood.getString(NUTRIENT_CARBS_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Protein: " + objFood.getString(NUTRIENT_PROTEIN_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Fat: " + objFood.getString(NUTRIENT_FATS_KEY),Toast.LENGTH_SHORT).show();
                                            try {
                                                arrFoodNutrient[2] +=
                                                        !Objects.equals(objFood.getString(NUTRIENT_CALORIE_KEY), "tr")
                                                                && !Objects.equals(objFood.getString(NUTRIENT_CALORIE_KEY), "")
                                                                ? Double.valueOf(objFood.getString(NUTRIENT_CALORIE_KEY))
                                                                : 0;
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                            arrFoodNutrient[4] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_FATS_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_FATS_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_FATS_KEY))
                                                            : 0;
                                                arrFoodNutrient[5] = arrFoodNutrient[4] / 70.0 * 100.0; // RDA Fat
                                                arrFoodNutrient[3] = arrFoodNutrient[4]*9.0; // 9 calories per gram of fat
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        try {
                                            arrFoodNutrient[6] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_SATFATS_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_SATFATS_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_SATFATS_KEY))
                                                            : 0;
                                            arrFoodNutrient[7] = arrFoodNutrient[6] / 70.0 * 100.0; // RDA Saturated Fat
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                            try {
                                            arrFoodNutrient[10] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_CHOLESTEROL_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_CHOLESTEROL_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_CHOLESTEROL_KEY))
                                                            : 0;
                                            arrFoodNutrient[11] = arrFoodNutrient[10] / 300.0 * 100.0; // RDA cholesterol
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[12] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_SODIUM_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_SODIUM_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_SODIUM_KEY))
                                                            : 0;
                                            arrFoodNutrient[13] = arrFoodNutrient[12] / 2300.0 * 100.0; // RDA sodium
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[14] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_CARBS_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_CARBS_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_CARBS_KEY))
                                                            : 0;
                                            //Toast.makeText( getContext(), "Subtotal Carbs: " + String.valueOf( arrFoodNutrient[14] ),Toast.LENGTH_SHORT).show();
                                            arrFoodNutrient[15] = arrFoodNutrient[14] / 310.0 * 100.0; // RDA carbohydrates
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[16] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_FIBRE_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_FIBRE_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_FIBRE_KEY))
                                                            : 0;
                                            arrFoodNutrient[17] = arrFoodNutrient[16] / 28.0 * 100.0; // RDA fibre
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[18] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_SUGARS_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_SUGARS_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_SUGARS_KEY))
                                                            : 0;
                                            arrFoodNutrient[19] = arrFoodNutrient[18] / 70.0 * 100.0; // RDA sugar
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[20] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_PROTEIN_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_PROTEIN_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_PROTEIN_KEY))
                                                            : 0;
                                           // Toast.makeText( getContext(), "Subtotal Protein: " + String.valueOf( arrFoodNutrient[20] ),Toast.LENGTH_SHORT).show();
                                            arrFoodNutrient[21] = arrFoodNutrient[20] / 50.0 * 100.0; // RDA protein
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[22] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_VITAMIN_A_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_VITAMIN_A_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_VITAMIN_A_KEY))
                                                            : 0;
                                            arrFoodNutrient[26] = arrFoodNutrient[22] / 900.0 * 100.0; // RDA Vitamin A
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[23] +=
                                                        !Objects.equals(objFood.getString(NUTRIENT_VITAMIN_C_KEY), "tr")
                                                                && !Objects.equals(objFood.getString(NUTRIENT_VITAMIN_C_KEY),                                                                "")
                                                                ? Double.valueOf(objFood.getString(NUTRIENT_VITAMIN_C_KEY))
                                                                : 0;
                                            arrFoodNutrient[27] = arrFoodNutrient[23] / 90.0 * 100.0; // RDA Vitamin A
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[24] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_CALCIUM_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_CALCIUM_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_CALCIUM_KEY))
                                                            : 0;
                                            arrFoodNutrient[28] = arrFoodNutrient[24] / 1000.0 * 100.0; // RDA Calcium
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[25] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_IRON_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_IRON_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_IRON_KEY))
                                                            : 0;
                                            arrFoodNutrient[29] = arrFoodNutrient[25] / 20.5 * 100.0; // RDA Iron
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                arrFoodNutrient[8] +=
                                                    !Objects.equals(objFood.getString(NUTRIENT_TRANSFATS_KEY), "tr")
                                                            && !Objects.equals(objFood.getString(NUTRIENT_TRANSFATS_KEY), "")
                                                            ? Double.valueOf(objFood.getString(NUTRIENT_TRANSFATS_KEY))
                                                            : 0;
                                            arrFoodNutrient[9] = arrFoodNutrient[8] / 2.0 * 100.0; // RDA Trans Fat
                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
                    }
                    if( arrFGLength>0 ){
                        for (Integer intI = 0 ; intI < arrFGLength; intI++ ){
                            JSONObject objElement = jsonFGArray.optJSONObject(intI);

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
                                        arrPeopleCount[0] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[0] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[0] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[1] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[1] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[1] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[1] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[1] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                                        arrPeopleCount[1] += objElement.optInt(PERSONS_ELEMENT_KEY);
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
                            //Toast.makeText(getContext(), String.valueOf(dblFV), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {Toast.makeText(getContext(), "Uh-Oh! "+ String.valueOf(arrFGLength), Toast.LENGTH_SHORT).show();}
                    textElement.setText(getString(R.string.nutrition_facts_servings_count,arrFoodCount[0],arrFoodCount[1],arrFoodCount[2],arrFoodCount[3],100*arrFoodCount[0]/(arrFoodGuide[0]>0?arrFoodGuide[0]:1),100*arrFoodCount[1]/(arrFoodGuide[1]>0?arrFoodGuide[0]:1),100*arrFoodCount[2]/(arrFoodGuide[2]>0?arrFoodGuide[0]:1),100*arrFoodCount[3]/(arrFoodGuide[3]>0?arrFoodGuide[0]:1)));
                    textElement = (TextView) rootView.findViewById(R.id.servings_per);
                    textElement.setText(getString(R.string.nutrition_facts_people_count,arrPeopleCount[0],arrPeopleCount[1]));

                    // textElement = (TextView) rootView.findViewById(R.id.calories);
                    // textElement.setText(String.valueOf(arrFoodNutrient[2]));
                    // textElement = (TextView) rootView.findViewById(R.id.calories_fat);
                    // textElement.setText(String.valueOf(arrFoodNutrient[3]));
                    arrayLength = elementList.size();
                    for (Integer intI = 2 ; intI < arrayLength; intI++ ){
                        textElement = (TextView) rootView.findViewById(elementList.get(intI));
                        textElement.setText(String.format(arrFactFormat[intI],arrFoodNutrient[intI>=22 ? intI+4 : intI]));
//                        textElement.setText(String.valueOf(arrFoodNutrient[intI>=22 ? intI+4 : intI]));
                    }

                }
                    // textElement.setText(getString(R.string.nutrition_facts_servings_count,arrServingsOf[0],arrServingsOf[1],arrServingsOf[2],arrServingsOf[3]));
                    // PickpagePeople.setServingCount( textElement );
                    /**
                     * *******************************************************************************
                     */
                    /**
                     * Make the field display the number of people to be served by age group
                     */
//                    textElement = (TextView) rootView.findViewById(R.id.servings_per);
                    // Integer[] arrServingsPer = (Integer[]) PickpagePeople.peopleCount();
//                    Integer[] arrServingsPer = {3,6};
                    // {}
//                    textElement.setText(getString(R.string.nutrition_facts_people_count,arrServingsPer[0],arrP[1]));
                    // PickpagePeople.setPeopleCount( textElement );
                    /**
                     * *********************************************************************
                     */
                /**
                 * load up the nutrients  -- yeah, did that already
                 *//*
                arrayLength = elementList.size();
                    for (Integer intI = 2 ; intI < arrayLength; intI++ ){
                        textElement = (TextView) rootView.findViewById(elementList.get(intI));
                        textElement.setText( getString(Double.valueOf( )));
                    }*/
                    break;
                case 2:  // Pick Persons
                    rootView = inflater.inflate(R.layout.pickpage_people, container, false);
                    elementList = new ArrayList<>(1);
                    elementList.add(R.id.pickpage_ages_girl_toddlers);
                    elementList.add(R.id.pickpage_ages_girl_children);
                    elementList.add(R.id.pickpage_ages_girl_youths);
                    elementList.add(R.id.pickpage_ages_women_teens);
                    elementList.add(R.id.pickpage_ages_women_adults);
                    elementList.add(R.id.pickpage_ages_women_elderly);
                    elementList.add(R.id.pickpage_ages_boy_toddlers);
                    elementList.add(R.id.pickpage_ages_boy_children);
                    elementList.add(R.id.pickpage_ages_boy_youths);
                    elementList.add(R.id.pickpage_ages_men_teens);
                    elementList.add(R.id.pickpage_ages_men_adults);
                    elementList.add(R.id.pickpage_ages_men_elderly);
                    arrayLength = elementList.size();
                    button = (Button) rootView.findViewById(R.id.button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar.make(v, "Saving pick person page", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                            List<Integer> elements;
                            Integer arrayLength;
                            Integer elementValue;
                            JSONObject jPerson = new JSONObject();
                            JSONArray jPersons = new JSONArray();
                            Spinner spinner;
                            View thisView = v.getRootView();

                            elements = new ArrayList<>(1);
                            // Toast.makeText(v.getContext(), "Array Size :" + String.valueOf(elements.size()), Toast.LENGTH_LONG).show();
                            if( elements.size()>=1 ){
                                elements.remove(0);
                            }
                            elements.add(R.id.pickpage_ages_girl_toddlers);
                            elements.add(R.id.pickpage_ages_girl_children);
                            elements.add(R.id.pickpage_ages_girl_youths);
                            elements.add(R.id.pickpage_ages_women_teens);
                            elements.add(R.id.pickpage_ages_women_adults);
                            elements.add(R.id.pickpage_ages_women_elderly);
                            elements.add(R.id.pickpage_ages_boy_toddlers);
                            elements.add(R.id.pickpage_ages_boy_children);
                            elements.add(R.id.pickpage_ages_boy_youths);
                            elements.add(R.id.pickpage_ages_men_teens);
                            elements.add(R.id.pickpage_ages_men_adults);
                            elements.add(R.id.pickpage_ages_men_elderly);
                            arrayLength = elements.size();
                            Spinner myspinner = (Spinner) thisView.findViewById(R.id.pickpage_ages_men_teens);
                            // Toast.makeText(v.getContext(), "Some element :" + ( spinner != null ? "Not null" : "is null" ), Toast.LENGTH_LONG).show();
                            if( arrayLength > 0 ){
                                for( int i = 0 ; i < arrayLength ; i++ ) {
                                    JSONObject j = new JSONObject();
                                    try {
                                        spinner = (Spinner) thisView.findViewById(elements.get(i));
                                        elementValue = 0;
                                        if ( spinner != null ){
                                            // Toast.makeText(v.getContext(), "Spinner: "+ String.valueOf(spinner.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();
                                            elementValue = spinner.getSelectedItemPosition(); // *** this returns null until physically selected
                                        }
                                        j.put(ApplicationActivity.PERSONS_ELEMENT_KEY, elementValue);
                                        jPersons.put(j);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }/*
*/
                                }
                                try {
                                    jPerson.put(ApplicationActivity.PERSONS_ARRAY_KEY,jPersons);
                                    getActivity().getSharedPreferences(ApplicationActivity.NAME, Context.MODE_PRIVATE)
                                            .edit()
                                            .putString(ApplicationActivity.PERSONS_PAGE_KEY, jPerson.toString())
                                            .apply();
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }/*
 */

                            }
                        }
                    });  // TODO: 2016-03-30 look at fab.setOnClickListener(new View.OnClickListener() {

                    //rootView.findViewById(R.id.pickpage_ages_girl_toddlers).;
                    // TODO: 2016-03-31 set text values of the age group items
                    final SharedPreferences preferences = getActivity().getSharedPreferences(ApplicationActivity.NAME, Context.MODE_PRIVATE);
                    final String strJperson = preferences.getString(ApplicationActivity.PERSONS_PAGE_KEY,
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
                            final Spinner spinnerElement;
                            spinnerElement = (Spinner) rootView.findViewById(elementList.get(intI));
                            JSONObject objElement = jsonArray.optJSONObject(intI);
                            spinnerElement.setSelection(objElement.optInt(PERSONS_ELEMENT_KEY));

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
                    final TextView tFoodGuide = (TextView) rootView.findViewById(R.id.pickpage_ages_summary);
                    tFoodGuide.setText(getString( R.string.canada_food_guide_msg,
                            arrFoodGuide[0],
                            arrFoodGuide[1],
                            arrFoodGuide[2],
                            arrFoodGuide[3]));
                    break;
                case 3:  // pick groceries
                    rootView = inflater.inflate(R.layout.pickpage_food, container, false);
                    Spinner spinner = (Spinner) rootView.findViewById(R.id.pickpage_food_dept);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        // private final ArrayList<Integer>

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            /* Snackbar.make(view, "Clicked pick food department " + String.valueOf(position), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();  */
                            final List<Integer> deptList = new ArrayList<>(1);
                            if( view == null ) { return; }
                            final Spinner foodProducts = (Spinner) view.getRootView().findViewById(R.id.pickpage_food_product);  // TODO: 2016-04-02 This line fails on rotation.
                            /*{
                                @Override
                                public void add(int location, Integer object) {

                                }

                                @Override
                                public boolean add(Integer object) {
                                    return false;
                                }

                                @Override
                                public boolean addAll(int location, Collection<? extends Integer> collection) {
                                    return false;
                                }

                                @Override
                                public boolean addAll(Collection<? extends Integer> collection) {
                                    return false;
                                }

                                @Override
                                public void clear() {

                                }

                                @Override
                                public boolean contains(Object object) {
                                    return false;
                                }

                                @Override
                                public boolean containsAll(Collection<?> collection) {
                                    return false;
                                }

                                @Override
                                public Integer get(int location) {
                                    return null;
                                }

                                @Override
                                public int indexOf(Object object) {
                                    return 0;
                                }

                                @Override
                                public boolean isEmpty() {
                                    return false;
                                }

                                @NonNull
                                @Override
                                public Iterator<Integer> iterator() {
                                    return null;
                                }

                                @Override
                                public int lastIndexOf(Object object) {
                                    return 0;
                                }

                                @Override
                                public ListIterator<Integer> listIterator() {
                                    return null;
                                }

                                @NonNull
                                @Override
                                public ListIterator<Integer> listIterator(int location) {
                                    return null;
                                }

                                @Override
                                public Integer remove(int location) {
                                    return null;
                                }

                                @Override
                                public boolean remove(Object object) {
                                    return false;
                                }

                                @Override
                                public boolean removeAll(Collection<?> collection) {
                                    return false;
                                }

                                @Override
                                public boolean retainAll(Collection<?> collection) {
                                    return false;
                                }

                                @Override
                                public Integer set(int location, Integer object) {
                                    return null;
                                }

                                @Override
                                public int size() {
                                    return 0;
                                }

                                @NonNull
                                @Override
                                public List<Integer> subList(int start, int end) {
                                    return null;
                                }

                                @NonNull
                                @Override
                                public Object[] toArray() {
                                    return new Object[0];
                                }

                                @NonNull
                                @Override
                                public <T> T[] toArray(T[] array) {
                                    return null;
                                }
                            }; */ //{R.integer.pickpage_food_dept_vegetable,};
                            deptList.add(R.integer.pickpage_food_dept_vegetable_pick);
                            deptList.add(R.integer.pickpage_food_dept_fruit_pick);
                            deptList.add(R.integer.pickpage_food_dept_meat_pick);
                            deptList.add(R.integer.pickpage_food_dept_deli_pick);
                            deptList.add(R.integer.pickpage_food_dept_bakery_pick);
                            deptList.add(R.integer.pickpage_food_dept_dairy_pick);
                            deptList.add(R.integer.pickpage_food_dept_frozen_pick);
                            deptList.add(R.integer.pickpage_food_dept_misc_pick);
                            List<String> foodList;
                            Integer foodArrayLength;
                            final ArrayAdapter<String> arrayAdapter;

                            switch (deptList.get(position) ){
                                case R.integer.pickpage_food_dept_vegetable_pick:
                                    // Toast.makeText(parent.getContext(), "Vegetable",Toast.LENGTH_SHORT).show();
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_vegetable));
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                case R.integer.pickpage_food_dept_fruit_pick:
                                    //  Toast.makeText(view.getContext(), "Fruit" + String.valueOf( R.integer.pickpage_food_dept_fruit_pick),Toast.LENGTH_SHORT).show();
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_fruit));
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                case R.integer.pickpage_food_dept_meat_pick:
                                    // Toast.makeText(view.getContext(), "Meat",Toast.LENGTH_SHORT).show();
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_meat));
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                case R.integer.pickpage_food_dept_deli_pick:
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_deli));
                                    //foodArrayLength = foodList.size();
                                    //Toast.makeText(view.getContext(), "Delicatessen: " + String.valueOf(foodArrayLength),Toast.LENGTH_SHORT).show();
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                case R.integer.pickpage_food_dept_bakery_pick:
                                    // Toast.makeText(view.getContext(), "Bakery",Toast.LENGTH_SHORT).show();
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_bakery));
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                case R.integer.pickpage_food_dept_dairy_pick:
                                    // Toast.makeText(view.getContext(), "Dairy",Toast.LENGTH_SHORT).show();
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_dairy));
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                case R.integer.pickpage_food_dept_frozen_pick:
                                    // Toast.makeText(view.getContext(), "Frozen",Toast.LENGTH_SHORT).show();
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_frozen));
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                case R.integer.pickpage_food_dept_misc_pick:
//                                    Toast.makeText(view.getContext(), "Miscellaneous",Toast.LENGTH_SHORT).show();
                                    foodList = Arrays.asList(getResources().getStringArray(R.array.pickpage_food_product_list_misc));
                                    arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, foodList);
                                    foodProducts.setAdapter(arrayAdapter);
                                    break;
                                default:
//                                    Toast.makeText(view.getContext(), "Stuff " + String.valueOf(position),Toast.LENGTH_SHORT).show();
                                    foodProducts.setAdapter(null);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Snackbar.make(parent, "Nothing picked: food department", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                        }
                    });
                    /* spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           Snackbar.make(view, "Clicked pick food department", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }); */
                    button = (Button) rootView.findViewById(R.id.button);
                    // button.setOnClickListener(new View.OnClickListener() {
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Spinner sp1 = (Spinner) v.getRootView().findViewById(R.id.pickpage_food_dept);
                            Spinner sp2 = (Spinner) v.getRootView().findViewById(R.id.pickpage_food_product);

                            Snackbar.make(v, sp1.getSelectedItem().toString()+" : "+sp2.getSelectedItem().toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            //
                            // add the info on the spinners to the next line in the list
                            // add field for measure, unit spinner, price per, extended price
                            // save the data as JSON array with default quantities.  Prices are for later and optional.
                            // TODO: 2016-04-02  JSON array with default quantities
                            String strDepartment = sp1.getSelectedItem().toString();
                            String strProduct = sp2.getSelectedItem().toString();
                            String strFoodNutrients = "{\"nutrientlist\":[" +
                                    "{\"product\":\"Round steak\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"13.71\", \"price2012\":\"14.09\", \"price2013\":\"14.33\", \"price2014\":\"16.49\", \"price2015\":\"19.09\", \"priceserving\":\"1.43175\", \"usdaproduct\":\"Eye of round steak, lean + fat, braised\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"171\", \"energykj\":\"715\", \"protein\":\"28\", \"carbohydrate\":\"0\", \"totalfat\":\"5\", \"saturatedfat\":\"2.2\", \"cholesterol\":\"61\"	, \"iron\":\"1.8\", \"sodium\":\"35\", \"potassium\":\"243\", \"magnesium\":\"19\", \"phosphorus\":\"152\", \"folate\":\"5\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"1.38\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"0.3\", \"polyunsaturatedfat3\":\"0.3\", \"saturatedfat3\":\"2.2\", \"monounsaturatedfat\":\"2.8\", \"monounsaturatedfat3\":\"2.8\" },"
                                            + "{\"product\":\"Sirloin steak\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"16.37\", \"price2012\":\"17.13\", \"price2013\":\"17.34\", \"price2014\":\"19.86\", \"price2015\":\"24.61\", \"priceserving\":\"1.84575\", \"usdaproduct\":\"Top sirloin steak, lean + fat, broiled\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"146\", \"energykj\":\"611\", \"protein\":\"21\", \"carbohydrate\":\"0\", \"totalfat\":\"6\", \"saturatedfat\":\"2.6\", \"cholesterol\":\"52\"	, \"iron\":\"2\", \"sodium\":\"43\", \"potassium\":\"257\", \"magnesium\":\"20\", \"phosphorus\":\"160\", \"folate\":\"5\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"2.33\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"0.3\", \"polyunsaturatedfat3\":\"0.3\", \"saturatedfat3\":\"2.6\", \"monounsaturatedfat\":\"3.1\", \"monounsaturatedfat3\":\"3.1\" },"
                                            + "{\"product\":\"Prime rib roast\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"22.89\", \"price2012\":\"23.98\", \"price2013\":\"22.67\", \"price2014\":\"26.35\", \"price2015\":\"31.19\", \"priceserving\":\"2.33925\", \"usdaproduct\":\"Standing rib roast, lean + fat, roasted\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"237\", \"energykj\":\"992\", \"protein\":\"21\", \"carbohydrate\":\"0\", \"totalfat\":\"16\", \"saturatedfat\":\"6.9\", \"cholesterol\":\"55\"	, \"iron\":\"1.7\", \"sodium\":\"53\", \"potassium\":\"243\", \"magnesium\":\"19\", \"phosphorus\":\"152\", \"folate\":\"5\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"1.73\", \"vitamine\":\"0.5\"   , \"polyunsaturatedfatg\":\"0.5\", \"polyunsaturatedfat3\":\"0.5\", \"saturatedfat3\":\"6.9\", \"monounsaturatedfat\":\"8\", \"monounsaturatedfat3\":\"8\" },"
                                            + "{\"product\":\"Blade roast\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"10.95\", \"price2012\":\"11.2\", \"price2013\":\"12.11\", \"price2014\":\"14.3\", \"price2015\":\"16.44\", \"priceserving\":\"1.233\", \"usdaproduct\":\"Blade roast, lean + fat, braised\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"200\", \"energykj\":\"834\", \"protein\":\"26\", \"carbohydrate\":\"0\", \"totalfat\":\"10\", \"saturatedfat\":\"4\", \"cholesterol\":\"71\"	, \"iron\":\"2.5\", \"sodium\":\"46\", \"potassium\":\"202\", \"magnesium\":\"18\", \"phosphorus\":\"142\", \"folate\":\"4\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"2.38\", \"vitamine\":\"0.1\"   , \"polyunsaturatedfatg\":\"0.4\", \"polyunsaturatedfat3\":\"0.4\", \"saturatedfat3\":\"4\", \"monounsaturatedfat\":\"5\", \"monounsaturatedfat3\":\"5\" },"
                                            + "{\"product\":\"Stewing beef\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"10.57\", \"price2012\":\"11.33\", \"price2013\":\"11.64\", \"price2014\":\"14.4\", \"price2015\":\"17.12\", \"priceserving\":\"1.284\", \"usdaproduct\":\"Stewing beef, lean, simmered\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"183\", \"energykj\":\"765\", \"protein\":\"28\", \"carbohydrate\":\"0\", \"totalfat\":\"7\", \"saturatedfat\":\"2.9\", \"cholesterol\":\"68\"	, \"iron\":\"2.6\", \"sodium\":\"45\", \"potassium\":\"214\", \"magnesium\":\"19\", \"phosphorus\":\"150\", \"folate\":\"4\" , \"vitamina\":\"0\", \"vitamind\":\"0.5\", \"vitaminb12\":\"1.8\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"N/A\", \"polyunsaturatedfat3\":\"N/A\", \"saturatedfat3\":\"2.9\", \"monounsaturatedfat\":\"N/A\", \"monounsaturatedfat3\":\"N/A\" },"
                                            + "{\"product\":\"Ground beef, regular\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"8.23\", \"price2012\":\"9.24\", \"price2013\":\"9.53\", \"price2014\":\"11\", \"price2015\":\"12.75\", \"priceserving\":\"0.95625\", \"usdaproduct\":\"Ground, regular, crumbled, pan-fried\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"243\", \"energykj\":\"1016\", \"protein\":\"22\", \"carbohydrate\":\"0\", \"totalfat\":\"17\", \"saturatedfat\":\"7\", \"cholesterol\":\"63\"	, \"iron\":\"2.2\", \"sodium\":\"78\", \"potassium\":\"301\", \"magnesium\":\"21\", \"phosphorus\":\"178\", \"folate\":\"0\" , \"vitamina\":\"0\", \"vitamind\":\"0.8\", \"vitaminb12\":\"2.72\", \"vitamine\":\"N/A\"   , \"polyunsaturatedfatg\":\"0.4\", \"polyunsaturatedfat3\":\"0.4\", \"saturatedfat3\":\"7\", \"monounsaturatedfat\":\"7.9\", \"monounsaturatedfat3\":\"7.9\" },"
                                            + "{\"product\":\"Pork chops\",\"servings\":\"13.3333333333333\", \"unit\":\"1 kg\", \"price2011\":\"9.97\", \"price2012\":\"10.37\", \"price2013\":\"10.89\", \"price2014\":\"12.88\", \"price2015\":\"12.72\", \"priceserving\":\"0.954\", \"usdaproduct\":\"Loin, rib end, lean + fat, pan-fried\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"199\", \"energykj\":\"831\", \"protein\":\"20\", \"carbohydrate\":\"0\", \"totalfat\":\"13\", \"saturatedfat\":\"4.8\", \"cholesterol\":\"55\"	, \"iron\":\"0.5\", \"sodium\":\"38\", \"potassium\":\"323\", \"magnesium\":\"19\", \"phosphorus\":\"177\", \"folate\":\"2\" , \"vitamina\":\"2\", \"vitamind\":\"0.5\", \"vitaminb12\":\"0.53\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"1.4\", \"polyunsaturatedfat3\":\"1.4\", \"saturatedfat3\":\"4.8\", \"monounsaturatedfat\":\"5.6\", \"monounsaturatedfat3\":\"5.6\" },"
                                            + "{\"product\":\"Chicken\", \"unit\":\"1 kg\",\"servings\":\"13.3333333333333\", \"price2011\":\"6.7\", \"price2012\":\"7.04\", \"price2013\":\"7.02\", \"price2014\":\"7.09\", \"price2015\":\"7.58\", \"priceserving\":\"0.5685\", \"usdaproduct\":\"Chicken, broiler, flesh and skin, roasted\", \"servingmeasure\":\"75g\", \"servingweight\":\"75\", \"energykcal\":\"179\", \"energykj\":\"749\", \"protein\":\"20\", \"carbohydrate\":\"0\", \"totalfat\":\"10\", \"saturatedfat\":\"2.8\", \"cholesterol\":\"66\"	, \"iron\":\"0.9\", \"sodium\":\"62\", \"potassium\":\"167\", \"magnesium\":\"17\", \"phosphorus\":\"137\", \"folate\":\"4\" , \"vitamina\":\"35\", \"vitamind\":\"0.2\", \"vitaminb12\":\"0.23\", \"vitamine\":\"0.2\"   , \"polyunsaturatedfatg\":\"2.2\", \"polyunsaturatedfat3\":\"2.2\", \"saturatedfat3\":\"2.8\", \"monounsaturatedfat\":\"4\", \"monounsaturatedfat3\":\"4\" },"
                                            + "{\"product\":\"Bacon\", \"unit\":\"500 g\",\"servings\":\"20.8333333333333\", \"price2011\":\"5.11\", \"price2012\":\"5.08\", \"price2013\":\"5.19\", \"price2014\":\"6.58\", \"price2015\":\"6.41\", \"priceserving\":\"0.30768\", \"usdaproduct\":\"Bacon, pork, broiled, pan-fried or roasted\", \"servingmeasure\":\"3 slices\", \"servingweight\":\"24\", \"energykcal\":\"130\", \"energykj\":\"543\", \"protein\":\"9\", \"carbohydrate\":\"tr\", \"totalfat\":\"10\", \"saturatedfat\":\"3.3\", \"cholesterol\":\"26\"	, \"iron\":\"0.3\", \"sodium\":\"554\", \"potassium\":\"136\", \"magnesium\":\"8\", \"phosphorus\":\"128\", \"folate\":\"tr\" , \"vitamina\":\"3\", \"vitamind\":\"0.4\", \"vitaminb12\":\"0.3\", \"vitamine\":\"tr\"   , \"polyunsaturatedfatg\":\"1.1\", \"polyunsaturatedfat3\":\"1.1\", \"saturatedfat3\":\"3.3\", \"monounsaturatedfat\":\"4.4\", \"monounsaturatedfat3\":\"4.4\" },"
                                            + "{\"product\":\"Wieners\", \"unit\":\"450 g\",\"servings\":\"11.8421052631579\", \"price2011\":\"3.28\", \"price2012\":\"3.47\", \"price2013\":\"3.41\", \"price2014\":\"4.01\", \"price2015\":\"4.26\", \"priceserving\":\"0.359733333333333\", \"usdaproduct\":\"Wiener (frankfurter), beef\", \"servingmeasure\":\"1\", \"servingweight\":\"38\", \"energykcal\":\"104\", \"energykj\":\"436\", \"protein\":\"5\", \"carbohydrate\":\"2\", \"totalfat\":\"8\", \"saturatedfat\":\"3.3\", \"cholesterol\":\"23\"	, \"iron\":\"0.6\", \"sodium\":\"343\", \"potassium\":\"54\", \"magnesium\":\"5\", \"phosphorus\":\"60\", \"folate\":\"2\" , \"vitamina\":\"0\", \"vitamind\":\"0.3\", \"vitaminb12\":\"0.65\", \"vitamine\":\"0.1\"   , \"polyunsaturatedfatg\":\"0.5\", \"polyunsaturatedfat3\":\"0.5\", \"saturatedfat3\":\"3.3\", \"monounsaturatedfat\":\"4\", \"monounsaturatedfat3\":\"4\" },"
                                            + "{\"product\":\"Canned sockeye salmon\", \"unit\":\"213 g\",\"servings\":\"2.84\", \"price2011\":\"3.1\", \"price2012\":\"3.65\", \"price2013\":\"3.75\", \"price2014\":\"4.62\", \"price2015\":\"4.63\", \"priceserving\":\"1.63028169014085\", \"usdaproduct\":\"Salmon, pink, canned, drained with bones\", \"servingmeasure\":\"75 g\", \"servingweight\":\"75\", \"energykcal\":\"102\", \"energykj\":\"427\", \"protein\":\"17\", \"carbohydrate\":\"0\", \"totalfat\":\"4\", \"saturatedfat\":\"0.6\", \"cholesterol\":\"62\", \"calcium\":\"208\", \"iron\":\"0.7\", \"sodium\":\"299\", \"potassium\":\"233\"	, \"phosphorus\":\"274\"  , \"dha\":\"0.52\", \"epa\":\"0.27\", \"vitamina\":\"17\", \"vitamind\":\"N/A\", \"vitaminb12\":\"3.71\", \"vitamine\":\"1\"   , \"polyunsaturatedfatg\":\"1.1\", \"polyunsaturatedfat3\":\"1.1\", \"saturatedfat3\":\"0.6\"  },"
                                            + "{\"product\":\"Homogenized milk\", \"unit\":\"1 l\",\"servings\":\"4\", \"price2011\":\"2.27\", \"price2012\":\"2.41\", \"price2013\":\"2.44\", \"price2014\":\"2.47\", \"price2015\":\"2.49\", \"priceserving\":\"0.6225\", \"usdaproduct\":\"Milk, whole, 3.3% M.F.\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"258\", \"energykcal\":\"155\", \"energykj\":\"647\", \"protein\":\"8\", \"carbohydrate\":\"12\", \"totalsugar\":\"14\", \"saturatedfat\":\"5.4\"	, \"calcium\":\"291\", \"iron\":\"0.1\", \"sodium\":\"103\", \"potassium\":\"369\", \"magnesium\":\"26\", \"phosphorus\":\"235\"	, \"riboflavin\":\"0.47\"	, \"folate\":\"13\" , \"vitamina\":\"72\", \"vitamind\":\"2.7\", \"vitaminb12\":\"1.13\"  , \"cholesterol\":\"26\", \"totalfat\":\"8\", \"saturatedfat3\":\"5.4\"  },"
                                            + "{\"product\":\"Partly skimmed milk\", \"unit\":\"1 l\",\"servings\":\"4\", \"price2011\":\"2.2\", \"price2012\":\"2.3\", \"price2013\":\"2.29\", \"price2014\":\"2.31\", \"price2015\":\"2.33\", \"priceserving\":\"0.5825\", \"usdaproduct\":\"Milk, partly skimmed, 2% M.F.\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"258\", \"energykcal\":\"129\", \"energykj\":\"539\", \"protein\":\"9\", \"carbohydrate\":\"12\", \"totalsugar\":\"13\", \"saturatedfat\":\"3.3\"	, \"calcium\":\"302\", \"iron\":\"0.1\", \"sodium\":\"106\", \"potassium\":\"387\", \"magnesium\":\"28\", \"phosphorus\":\"242\"	, \"riboflavin\":\"0.48\"	, \"folate\":\"13\" , \"vitamina\":\"142\", \"vitamind\":\"2.8\", \"vitaminb12\":\"1.19\"  , \"cholesterol\":\"21\", \"totalfat\":\"5\", \"saturatedfat3\":\"3.3\"  },"
                                            + "{\"product\":\"Butter\", \"unit\":\"454 g\", \"price2011\":\"4.33\",\"servings\":\"90.8\", \"price2012\":\"4.37\", \"price2013\":\"4.39\", \"price2014\":\"4.51\", \"price2015\":\"4.54\", \"priceserving\":\"0.05\", \"usdaproduct\":\"Butter\", \"servingmeasure\":\"5 mL\", \"servingweight\":\"5\", \"energykcal\":\"34\", \"energykj\":\"144\", \"protein\":\"tr\", \"carbohydrate\":\"tr\", \"totalfat\":\"4\", \"saturatedfat\":\"2.5\", \"cholesterol\":\"10\", \"calcium\":\"1\", \"iron\":\"tr\", \"sodium\":\"28\", \"potassium\":\"1\", \"magnesium\":\"tr\", \"phosphorus\":\"1\"   , \"vitamina\":\"33\", \"vitamind\":\"tr\"	, \"vitamine\":\"0.1\"   , \"polyunsaturatedfatg\":\"0.1\", \"polyunsaturatedfat3\":\"0.1\", \"saturatedfat3\":\"2.5\", \"monounsaturatedfat\":\"1\", \"monounsaturatedfat3\":\"1\", \"transfat\":\"0.2\"	},"
                                            + "{\"product\":\"Processed cheese food slices\", \"unit\":\"250 g\",\"servings\":\"11.9047619047619\", \"price2011\":\"2.79\", \"price2012\":\"2.78\", \"price2013\":\"2.73\", \"price2014\":\"2.81\", \"price2015\":\"2.8\", \"priceserving\":\"0.2352\", \"usdaproduct\":\"Processed cheese food, thin slices\", \"servingmeasure\":\"1\", \"servingweight\":\"21\", \"energykcal\":\"78\", \"energykj\":\"327\", \"protein\":\"5\", \"carbohydrate\":\"tr\", \"totalsugar\":\"tr\", \"saturatedfat\":\"4.1\"	, \"calcium\":\"115\", \"iron\":\"tr\", \"sodium\":\"310\", \"potassium\":\"35\", \"magnesium\":\"6\", \"phosphorus\":\"107\"	, \"riboflavin\":\"0.07\"	, \"folate\":\"2\" , \"vitamina\":\"53\", \"vitamind\":\"tr\", \"vitaminb12\":\"0.15\"  , \"cholesterol\":\"20\", \"totalfat\":\"7\", \"saturatedfat3\":\"4.1\"  },"
                                            + "{\"product\":\"Evaporated milk\", \"unit\":\"385 ml\",\"servings\":\"25.6666666666667\", \"price2011\":\"1.99\", \"price2012\":\"1.93\", \"price2013\":\"1.93\", \"price2014\":\"1.87\", \"price2015\":\"1.91\", \"priceserving\":\"0.0744155844155844\", \"usdaproduct\":\"Milk, evaporated, whole, canned, undiluted, 7.8% M.F.\", \"servingmeasure\":\"15 mL\", \"servingweight\":\"16\", \"energykcal\":\"21\", \"energykj\":\"89\", \"protein\":\"1\", \"carbohydrate\":\"2\", \"totalsugar\":\"2\", \"saturatedfat\":\"0.8\"	, \"calcium\":\"42\", \"iron\":\"tr\", \"sodium\":\"17\", \"potassium\":\"48\", \"magnesium\":\"4\", \"phosphorus\":\"32\"	, \"riboflavin\":\"0.05\"	, \"folate\":\"1\" , \"vitamina\":\"10\", \"vitamind\":\"0.3\", \"vitaminb12\":\"0.03\"  , \"cholesterol\":\"5\", \"totalfat\":\"1\", \"saturatedfat3\":\"0.8\"  },"
                                            + "{\"product\":\"Eggs\", \"unit\":\"1 dz\",\"servings\":\"12\", \"price2011\":\"2.85\", \"price2012\":\"3.08\", \"price2013\":\"3.24\", \"price2014\":\"3.27\", \"price2015\":\"3.29\", \"priceserving\":\"0.274166666666667\", \"usdaproduct\":\"Egg, poached\", \"servingmeasure\":\"1 large\", \"servingweight\":\"50\", \"energykcal\":\"74\", \"energykj\":\"308\", \"protein\":\"6\", \"carbohydrate\":\"tr\", \"totalfat\":\"5\"	, \"cholesterol\":\"215\", \"calcium\":\"27\", \"iron\":\"0.9\", \"sodium\":\"147\", \"potassium\":\"67\"	, \"phosphorus\":\"95\", \"folate\":\"24\" , \"vitamina\":\"70\", \"vitamind\":\"0.4\", \"vitaminb12\":\"0.64\", \"vitamine\":\"0.5\"  , \"polyunsaturatedfat3\":\"0.7\", \"saturatedfat3\":\"1.5\"	, \"monounsaturatedfat3\":\"1.9\" },"
                                            + "{\"product\":\"Bread\", \"unit\":\"675 g\",\"servings\":\"19.2857142857143\", \"price2011\":\"2.85\", \"price2012\":\"2.77\", \"price2013\":\"2.87\", \"price2014\":\"2.84\", \"price2015\":\"2.96\", \"priceserving\":\"0.153481481481481\", \"usdaproduct\":\"Bread, whole wheat, commercial\", \"servingmeasure\":\"1 slice\", \"servingweight\":\"35\", \"energykcal\":\"86\", \"energykj\":\"360\", \"protein\":\"3\", \"carbohydrate\":\"16\", \"totalsugar\":\"7\", \"totaldietaryfibre\":\"2.4\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.3\", \"cholesterol\":\"0\", \"calcium\":\"25\", \"iron\":\"1.2\", \"sodium\":\"184\", \"potassium\":\"88\", \"magnesium\":\"30\", \"phosphorus\":\"80\", \"thiamin\":\"0.1\", \"riboflavin\":\"0.07\", \"niacin\":\"2.2\", \"folate\":\"18\"       , \"saturatedfat3\":\"0.3\"  },"
                                            + "{\"product\":\"Soda crackers\", \"unit\":\"450 g\",\"servings\":\"37.5\", \"price2011\":\"2.69\", \"price2012\":\"2.73\", \"price2013\":\"2.73\", \"price2014\":\"2.7\", \"price2015\":\"2.99\", \"priceserving\":\"0.0797333333333333\", \"usdaproduct\":\"Saltine (oyster, soda, soup)\", \"servingmeasure\":\"4\", \"servingweight\":\"12\", \"energykcal\":\"51\", \"energykj\":\"215\", \"protein\":\"1\", \"carbohydrate\":\"9\", \"totalsugar\":\"tr\", \"totaldietaryfibre\":\"0.4\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.2\", \"cholesterol\":\"0\", \"calcium\":\"8\", \"iron\":\"0.7\", \"sodium\":\"129\", \"potassium\":\"18\", \"magnesium\":\"3\", \"phosphorus\":\"12\", \"thiamin\":\"tr\", \"riboflavin\":\"0.05\", \"niacin\":\"0.9\", \"folate\":\"26\"       , \"saturatedfat3\":\"0.2\"  },"
                                            + "{\"product\":\"Macaroni\", \"unit\":\"500 g\",\"servings\":\"3.37837837837838\", \"price2011\":\"1.43\", \"price2012\":\"1.46\", \"price2013\":\"1.4\", \"price2014\":\"1.42\", \"price2015\":\"1.63\", \"priceserving\":\"0.48248\", \"usdaproduct\":\"Macaroni, cooked\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"148\", \"energykcal\":\"209\", \"energykj\":\"873\", \"protein\":\"7\", \"carbohydrate\":\"42\", \"totalsugar\":\"1\", \"totaldietaryfibre\":\"1.8\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.1\", \"cholesterol\":\"0\", \"calcium\":\"10\", \"iron\":\"2.1\", \"sodium\":\"1\", \"potassium\":\"46\", \"magnesium\":\"27\", \"phosphorus\":\"80\", \"thiamin\":\"0.3\", \"riboflavin\":\"0.14\", \"niacin\":\"4\", \"folate\":\"184\"       , \"saturatedfat3\":\"0.1\"  },"
                                            + "{\"product\":\"Flour\", \"unit\":\"2.5 kg\",\"servings\":\"20\", \"price2011\":\"5.15\", \"price2012\":\"5.19\", \"price2013\":\"5.09\", \"price2014\":\"4.95\", \"price2015\":\"5.13\", \"priceserving\":\"0.147744\", \"usdaproduct\":\"Wheat flour, bread\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"72\", \"energykcal\":\"261\", \"energykj\":\"1093\", \"protein\":\"9\", \"carbohydrate\":\"52\", \"totalsugar\":\"tr\", \"totaldietaryfibre\":\"1.7\"	, \"totalfat\":\"1\", \"saturatedfat\":\"0.2\", \"cholesterol\":\"0\", \"calcium\":\"11\", \"iron\":\"3.2\", \"sodium\":\"1\", \"potassium\":\"72\", \"magnesium\":\"18\", \"phosphorus\":\"70\", \"thiamin\":\"0.6\", \"riboflavin\":\"0.37\", \"niacin\":\"7.1\", \"folate\":\"208\"       , \"saturatedfat3\":\"0.2\"  },"
                                            + "{\"product\":\"Corn flakes\", \"unit\":\"675 g\",\"servings\":\"25.9615384615385\", \"price2011\":\"4.5\", \"price2012\":\"4.98\", \"price2013\":\"4.85\", \"price2014\":\"5.1\", \"price2015\":\"4.77\", \"priceserving\":\"0.183733333333333\", \"usdaproduct\":\"Corn Flakes, Kellogg's™\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"26\", \"energykcal\":\"103\", \"energykj\":\"430\", \"protein\":\"2\", \"carbohydrate\":\"23\", \"totalsugar\":\"2\", \"totaldietaryfibre\":\"0.7\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"1\", \"iron\":\"3.5\", \"sodium\":\"190\", \"potassium\":\"28\", \"magnesium\":\"2\", \"phosphorus\":\"12\", \"thiamin\":\"0.5\", \"riboflavin\":\"0.71\", \"niacin\":\"1.6\", \"folate\":\"32\"       , \"saturatedfat3\":\"0\"  },"
                                            + "{\"product\":\"Apples\", \"unit\":\"1 kg\",\"servings\":\"7.2463768115942\", \"price2011\":\"3.36\", \"price2012\":\"3.51\", \"price2013\":\"3.89\", \"price2014\":\"3.97\", \"price2015\":\"3.8\", \"priceserving\":\"0.5244\", \"usdaproduct\":\"Apple with skin (7 cm.diam)\", \"servingmeasure\":\"1\", \"servingweight\":\"138\", \"energykcal\":\"72\", \"energykj\":\"300\", \"protein\":\"tr\", \"carbohydrate\":\"19\", \"totalsugar\":\"14\", \"totaldietaryfibre\":\"2.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"8\", \"iron\":\"0.2\", \"sodium\":\"1\", \"potassium\":\"148\", \"magnesium\":\"7\", \"phosphorus\":\"15\", \"folate\":\"4\" , \"vitamina\":\"4\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"37\", \"lycopene\":\"0\", \"vitaminc\":\"6\"     },"
                                            + "{\"product\":\"Bananas\", \"unit\":\"1 kg\",\"servings\":\"8.47457627118644\", \"price2011\":\"1.72\", \"price2012\":\"1.7\", \"price2013\":\"1.66\", \"price2014\":\"1.67\", \"price2015\":\"1.72\", \"priceserving\":\"0.20296\", \"usdaproduct\":\"Banana\", \"servingmeasure\":\"1\", \"servingweight\":\"118\", \"energykcal\":\"105\", \"energykj\":\"439\", \"protein\":\"1\", \"carbohydrate\":\"27\", \"totalsugar\":\"14\", \"totaldietaryfibre\":\"2.1\"	, \"totalfat\":\"tr\" , \"calcium\":\"6\", \"iron\":\"0.3\", \"sodium\":\"1\", \"potassium\":\"422\", \"magnesium\":\"32\", \"phosphorus\":\"26\", \"folate\":\"24\" , \"vitamina\":\"4\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"31\", \"lycopene\":\"0\", \"vitaminc\":\"10\"     },"
                                            + "{\"product\":\"Grapefruits\", \"unit\":\"1 kg\",\"servings\":\"8.13008130081301\", \"price2011\":\"2.81\", \"price2012\":\"2.96\", \"price2013\":\"2.96\", \"price2014\":\"2.9\", \"price2015\":\"3.35\", \"priceserving\":\"0.41205\", \"usdaproduct\":\"Grapefruit, pink or red\", \"servingmeasure\":\"½\", \"servingweight\":\"123\", \"energykcal\":\"52\", \"energykj\":\"216\", \"protein\":\"1\", \"carbohydrate\":\"13\", \"totalsugar\":\"8\", \"totaldietaryfibre\":\"2\"	, \"totalfat\":\"tr\" , \"calcium\":\"27\", \"iron\":\"0.1\", \"sodium\":\"0\", \"potassium\":\"166\", \"magnesium\":\"11\", \"phosphorus\":\"22\", \"folate\":\"16\" , \"vitamina\":\"71\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"844\", \"lycopene\":\"1745\", \"vitaminc\":\"38\"     },"
                                            + "{\"product\":\"Oranges\", \"unit\":\"1 kg\",\"servings\":\"7.63358778625954\", \"price2011\":\"2.69\", \"price2012\":\"2.89\", \"price2013\":\"3.15\", \"price2014\":\"3.54\", \"price2015\":\"3.56\", \"priceserving\":\"0.46636\", \"usdaproduct\":\"Orange\", \"servingmeasure\":\"1\", \"servingweight\":\"131\", \"energykcal\":\"62\", \"energykj\":\"258\", \"protein\":\"1\", \"carbohydrate\":\"15\", \"totalsugar\":\"12\", \"totaldietaryfibre\":\"2.3\"	, \"totalfat\":\"tr\" , \"calcium\":\"52\", \"iron\":\"0.1\", \"sodium\":\"0\", \"potassium\":\"237\", \"magnesium\":\"13\", \"phosphorus\":\"18\", \"folate\":\"39\" , \"vitamina\":\"8\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"93\", \"lycopene\":\"0\", \"vitaminc\":\"70\"     },"
                                            + "{\"product\":\"Apple juice, canned\", \"unit\":\"1.36 l\",\"servings\":\"10.88\", \"price2011\":\"2.06\", \"price2012\":\"2.13\", \"price2013\":\"2.1\", \"price2014\":\"2.07\", \"price2015\":\"2.08\", \"priceserving\":\"0.191176470588235\", \"usdaproduct\":\"Apple juice, ready-to-drink, vitamin C added\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"126\", \"energykcal\":\"59\", \"energykj\":\"249\", \"protein\":\"tr\", \"carbohydrate\":\"15\", \"totalsugar\":\"14\", \"totaldietaryfibre\":\"0.1\"	, \"totalfat\":\"tr\" , \"calcium\":\"9\", \"iron\":\"0.5\", \"sodium\":\"4\", \"potassium\":\"150\", \"magnesium\":\"4\", \"phosphorus\":\"9\", \"folate\":\"tr\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"0\", \"lycopene\":\"0\", \"vitaminc\":\"52\"     },"
                                            + "{\"product\":\"Orange juice, tetra-brick\", \"unit\":\"1 l\",\"servings\":\"8\", \"price2011\":\"4.01\", \"price2012\":\"3.94\", \"price2013\":\"3.95\", \"price2014\":\"3.97\", \"price2015\":\"4.2\", \"priceserving\":\"0.525\", \"usdaproduct\":\"Orange juice, ready-to-drink\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"132\", \"energykcal\":\"58\", \"energykj\":\"242\", \"protein\":\"1\", \"carbohydrate\":\"13\", \"totalsugar\":\"N/A\", \"totaldietaryfibre\":\"0.3\"	, \"totalfat\":\"tr\" , \"calcium\":\"13\", \"iron\":\"0.2\", \"sodium\":\"1\", \"potassium\":\"250\", \"magnesium\":\"14\", \"phosphorus\":\"14\", \"folate\":\"24\" , \"vitamina\":\"5\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"61\", \"lycopene\":\"0\", \"vitaminc\":\"43\"     },"
                                            + "{\"product\":\"Carrots\", \"unit\":\"1 kg\", \"price2011\":\"2.29\",\"servings\":\"12.5\", \"price2012\":\"1.86\", \"price2013\":\"1.84\", \"price2014\":\"2.02\", \"price2015\":\"2.17\", \"priceserving\":\"0.1736\", \"usdaproduct\":\"Carrots, baby, raw\", \"servingmeasure\":\"8\", \"servingweight\":\"80\", \"energykcal\":\"28\", \"energykj\":\"117\", \"protein\":\"1\", \"carbohydrate\":\"7\", \"totalsugar\":\"4\", \"totaldietaryfibre\":\"1.4\"	, \"totalfat\":\"tr\" , \"calcium\":\"26\", \"iron\":\"0.7\", \"sodium\":\"62\", \"potassium\":\"190\", \"magnesium\":\"8\", \"phosphorus\":\"22\", \"folate\":\"26\" , \"vitamina\":\"552\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"5113\", \"lycopene\":\"0\", \"vitaminc\":\"7\"     },"
                                            + "{\"product\":\"Celery\", \"unit\":\"1 kg\",\"servings\":\"25\", \"price2011\":\"2.85\", \"price2012\":\"2.28\", \"price2013\":\"2.99\", \"price2014\":\"2.37\", \"price2015\":\"2.79\", \"priceserving\":\"0.1116\", \"usdaproduct\":\"Celery, raw\", \"servingmeasure\":\"1 stalk\", \"servingweight\":\"40\", \"energykcal\":\"6\", \"energykj\":\"24\", \"protein\":\"tr\", \"carbohydrate\":\"1\", \"totalsugar\":\"1\", \"totaldietaryfibre\":\"0.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"16\", \"iron\":\"0.1\", \"sodium\":\"32\", \"potassium\":\"104\", \"magnesium\":\"4\", \"phosphorus\":\"10\", \"folate\":\"14\" , \"vitamina\":\"9\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"108\", \"lycopene\":\"0\", \"vitaminc\":\"1\"     },"
                                            + "{\"product\":\"Mushrooms\", \"unit\":\"1 kg\",\"servings\":\"18.5185185185185\", \"price2011\":\"8.09\", \"price2012\":\"8.13\", \"price2013\":\"8.04\", \"price2014\":\"8.15\", \"price2015\":\"8.75\", \"priceserving\":\"0.4725\", \"usdaproduct\":\"Mushrooms, raw\", \"servingmeasure\":\"3 medium\", \"servingweight\":\"54\", \"energykcal\":\"12\", \"energykj\":\"50\", \"protein\":\"2\", \"carbohydrate\":\"2\", \"totalsugar\":\"1\", \"totaldietaryfibre\":\"0.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"2\", \"iron\":\"0.3\", \"sodium\":\"2\", \"potassium\":\"170\", \"magnesium\":\"5\", \"phosphorus\":\"46\", \"folate\":\"9\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0.02\"	, \"betacarotene\":\"0\", \"lycopene\":\"0\", \"vitaminc\":\"1\"     },"
                                            + "{\"product\":\"Onions\", \"unit\":\"1 kg\",\"servings\":\"16.6666666666667\", \"price2011\":\"1.71\", \"price2012\":\"1.53\", \"price2013\":\"1.88\", \"price2014\":\"2.21\", \"price2015\":\"1.7\", \"priceserving\":\"0.0697\", \"usdaproduct\":\"Onions, yellow, chopped, raw\", \"servingmeasure\":\"60 mL\", \"servingweight\":\"41\", \"energykcal\":\"17\", \"energykj\":\"71\", \"protein\":\"tr\", \"carbohydrate\":\"4\", \"totalsugar\":\"2\", \"totaldietaryfibre\":\"0.6\"	, \"totalfat\":\"tr\" , \"calcium\":\"9\", \"iron\":\"0.1\", \"sodium\":\"1\", \"potassium\":\"58\", \"magnesium\":\"4\", \"phosphorus\":\"11\", \"folate\":\"8\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"tr\", \"lycopene\":\"0\", \"vitaminc\":\"3\"     },"
                                            + "{\"product\":\"Potatoes\", \"unit\":\"4.54 kg\",\"servings\":\"26.242774566474\", \"price2011\":\"5.24\", \"price2012\":\"5.65\", \"price2013\":\"5.52\", \"price2014\":\"5.74\", \"price2015\":\"5.66\", \"priceserving\":\"0.215678414096916\", \"usdaproduct\":\"Potato, baked, flesh and skin\", \"servingmeasure\":\"1\", \"servingweight\":\"173\", \"energykcal\":\"161\", \"energykj\":\"673\", \"protein\":\"4\", \"carbohydrate\":\"37\", \"totalsugar\":\"2\", \"totaldietaryfibre\":\"3.8\"	, \"totalfat\":\"tr\" , \"calcium\":\"26\", \"iron\":\"1.9\", \"sodium\":\"17\", \"potassium\":\"926\", \"magnesium\":\"48\", \"phosphorus\":\"121\", \"folate\":\"48\" , \"vitamina\":\"2\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"10\", \"lycopene\":\"0\", \"vitaminc\":\"17\"     },"
                                            + "{\"product\":\"French fried potatoes, frozen\", \"unit\":\"1 kg\",\"servings\":\"20.8333333333333\", \"price2011\":\"2.24\", \"price2012\":\"2.47\", \"price2013\":\"2.43\", \"price2014\":\"2.5\", \"price2015\":\"2.71\", \"priceserving\":\"0.13008\", \"usdaproduct\":\"Potatoes, French fried, frozen, home-prepared in oven\", \"servingmeasure\":\"20 strips\", \"servingweight\":\"48\", \"energykcal\":\"96\", \"energykj\":\"403\", \"protein\":\"2\", \"carbohydrate\":\"15\", \"totalsugar\":\"tr\", \"totaldietaryfibre\":\"1.6\"	, \"totalfat\":\"4\" , \"calcium\":\"4\", \"iron\":\"0.6\", \"sodium\":\"14\", \"potassium\":\"201\", \"magnesium\":\"11\", \"phosphorus\":\"40\", \"folate\":\"6\" , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"1\", \"lycopene\":\"0\", \"vitaminc\":\"5\"     },"
                                            + "{\"product\":\"Olives\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Olives, pickled, canned or bottled\"	,\"servingmeasure\":\"4\"	,\"servingweight\":\"16\"	,\"energykcal\":\"23\"	,\"energykj\":\"97\"	,\"protein\":\"tr\"	,\"carbohydrate\":\"1\"	,\"totalsugar\":\"tr\"	,\"totaldietaryfibre\":\"2\"	,\"totaldaietaryfibre\":\"0.3\"	,\"totalfat\":\"1.8\"	,\"saturatedfat\":\"0.2\"	,\"cholesterol\":\"0\"	,\"calcium\":\"8\"	,\"iron\":\"0.1\"	,\"sodium\":\"249\"	,\"potassium\":\"7\"	,\"magnesium\":\"2\"	,\"phosphorus\":\"1\"	,\"thiamin\":\"3\"	,\"riboflavin\":\"0\"	,\"niacin\":\"tr\"																				},"
                                            + "{\"product\":\"Corned beef\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Corned beef, brisket, cooked\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"101\"	,\"energykj\":\"422\"	,\"protein\":\"11\"	,\"carbohydrate\":\"1\"	,\"totalsugar\":\"6\"	,\"totaldietaryfibre\":\"2\"	,\"totaldaietaryfibre\":\"2.9\"	,\"totalfat\":\"0.2\"	,\"saturatedfat\":\"55\"	,\"cholesterol\":\"1\"	,\"calcium\":\"653\"	,\"iron\":\"112\"	,\"sodium\":\"7\"	,\"potassium\":\"70\"	,\"magnesium\":\"0\"	,\"phosphorus\":\"0.2\"	,\"thiamin\":\"0.91\"	,\"riboflavin\":\"3\"	,\"niacin\":\"0.1\"																				},"
                                            + "{\"product\":\"Bologna\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Bologna (baloney), beef and pork\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"153\"	,\"energykj\":\"640\"	,\"protein\":\"7\"	,\"carbohydrate\":\"3\"	,\"totalsugar\":\"13\"	,\"totaldietaryfibre\":\"4.7\"	,\"totaldaietaryfibre\":\"5.9\"	,\"totalfat\":\"1.1\"	,\"saturatedfat\":\"31\"	,\"cholesterol\":\"0.8\"	,\"calcium\":\"549\"	,\"iron\":\"132\"	,\"sodium\":\"6\"	,\"potassium\":\"51\"	,\"magnesium\":\"14\"	,\"phosphorus\":\"0.6\"	,\"thiamin\":\"0.74\"	,\"riboflavin\":\"3\"	,\"niacin\":\"0.2\"																				},"
                                            + "{\"product\":\"Deli meat, ham\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Deli meat, ham, regular (11% fat)\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"91\"	,\"energykj\":\"382\"	,\"protein\":\"9\"	,\"carbohydrate\":\"2\"	,\"totalsugar\":\"5\"	,\"totaldietaryfibre\":\"1.6\"	,\"totaldaietaryfibre\":\"2.4\"	,\"totalfat\":\"0.4\"	,\"saturatedfat\":\"32\"	,\"cholesterol\":\"0.6\"	,\"calcium\":\"730\"	,\"iron\":\"161\"	,\"sodium\":\"12\"	,\"potassium\":\"86\"	,\"magnesium\":\"0\"	,\"phosphorus\":\"0.1\"	,\"thiamin\":\"0.24\"	,\"riboflavin\":\"4\"	,\"niacin\":\"tr\"																				},"
                                            + "{\"product\":\"Deli meat, turkey\"	,\"unit\":\"\"	,\"price2011\":\"\"	,\"price2012\":\"\"	,\"price2013\":\"\"	,\"price2014\":\"\"	,\"price2015\":\"\"	,\"priceserving\":\"\"	,\"usdaproduct\":\"Deli meat, turkey breast\"	,\"servingmeasure\":\"2 slices\"	,\"servingweight\":\"56\"	,\"energykcal\":\"63\"	,\"energykj\":\"262\"	,\"protein\":\"8\"	,\"carbohydrate\":\"4\"	,\"totalsugar\":\"2\"	,\"totaldietaryfibre\":\"0.1\"	,\"totaldaietaryfibre\":\"0.3\"	,\"totalfat\":\"0.2\"	,\"saturatedfat\":\"31\"	,\"cholesterol\":\"1.2\"	,\"calcium\":\"672\"	,\"iron\":\"195\"	,\"sodium\":\"11\"	,\"potassium\":\"88\"	,\"magnesium\":\"0\"	,\"phosphorus\":\"0\"	,\"thiamin\":\"0.12\"	,\"riboflavin\":\"2\"	,\"niacin\":\"0.1\"																				},"
                                            + "{\"product\":\"Baked beans, canned\",\"servings\":\"2.27428571428571\", \"unit\":\"398 ml\", \"price2011\":\"1.11\", \"price2012\":\"1.23\", \"price2013\":\"1.19\", \"price2014\":\"1.27\", \"price2015\":\"1.33\", \"priceserving\":\"0.584798994974874\", \"usdaproduct\":\"Beans, baked, with pork, canned\", \"servingmeasure\":\"175 mL\", \"servingweight\":\"187\", \"energykcal\":\"198\", \"energykj\":\"829\", \"protein\":\"10\", \"carbohydrate\":\"37\", \"totalsugar\":\"N/A\"	, \"totaldaietaryfibre\":\"10.4\", \"totalfat\":\"3\", \"saturatedfat\":\"1.1\"	, \"calcium\":\"99\", \"iron\":\"3.2\", \"sodium\":\"775\", \"potassium\":\"578\", \"magnesium\":\"64\", \"phosphorus\":\"202\", \"folate\":\"67\"  , \"vitaminb12\":\"0\", \"vitamine\":\"N/A\"   , \"polyunsaturatedfatg\":\"0.4\", \"polyunsaturatedfat3\":\"0.4\", \"saturatedfat3\":\"1.1\", \"monounsaturatedfat\":\"1.3\", \"monounsaturatedfat3\":\"1.3\" },"
                                            + "{\"product\":\"Tomatoes, canned\", \"unit\":\"796 ml\",\"servings\":\"6.368\", \"price2011\":\"1.58\", \"price2012\":\"1.52\", \"price2013\":\"1.49\", \"price2014\":\"1.54\", \"price2015\":\"1.64\", \"priceserving\":\"0.257537688442211\", \"usdaproduct\":\"Tomatoes, canned, stewed\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"135\", \"energykcal\":\"35\", \"energykj\":\"147\", \"protein\":\"1\", \"carbohydrate\":\"8\", \"totalsugar\":\"6\", \"totaldietaryfibre\":\"1.4\"	, \"totalfat\":\"tr\" , \"calcium\":\"46\", \"iron\":\"1.8\", \"sodium\":\"298\", \"potassium\":\"279\", \"magnesium\":\"16\", \"phosphorus\":\"27\", \"folate\":\"7\" , \"vitamina\":\"12\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"140\", \"lycopene\":\"5436\", \"vitaminc\":\"11\"     },"
                                            + "{\"product\":\"Tomato juice, canned\", \"unit\":\"1.36 l\",\"servings\":\"10.88\", \"price2011\":\"2.16\", \"price2012\":\"2.41\", \"price2013\":\"2.42\", \"price2014\":\"2.49\", \"price2015\":\"2.54\", \"priceserving\":\"0.233455882352941\", \"usdaproduct\":\"Tomato juice\", \"servingmeasure\":\"125 mL\", \"servingweight\":\"128\", \"energykcal\":\"22\", \"energykj\":\"91\", \"protein\":\"1\", \"carbohydrate\":\"5\", \"totalsugar\":\"5\", \"totaldietaryfibre\":\"0.9\"	, \"totalfat\":\"tr\" , \"calcium\":\"13\", \"iron\":\"0.6\", \"sodium\":\"345\", \"potassium\":\"294\", \"magnesium\":\"14\", \"phosphorus\":\"23\", \"folate\":\"26\" , \"vitamina\":\"30\"	, \"vitaminb12\":\"0\"	, \"betacarotene\":\"347\", \"lycopene\":\"11602\", \"vitaminc\":\"10\"     },"
                                            + "{\"product\":\"Ketchup\", \"unit\":\"1 l\",\"servings\":\"66.6666666666667\", \"price2011\":\"3\", \"price2012\":\"3.3\", \"price2013\":\"3.09\", \"price2014\":\"3.19\", \"price2015\":\"3.32\", \"priceserving\":\"0.0498\", \"usdaproduct\":\"Ketchup\", \"servingmeasure\":\"15 mL\", \"servingweight\":\"15\", \"energykcal\":\"15\", \"energykj\":\"64\", \"protein\":\"tr\", \"carbohydrate\":\"4\", \"totalsugar\":\"3\" , \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"3\", \"iron\":\"0.1\", \"sodium\":\"169\", \"potassium\":\"57\", \"magnesium\":\"3\", \"phosphorus\":\"5\", \"folate\":\"2\" , \"vitamina\":\"7\"  , \"lycopene\":\"2586\", \"polyunsaturatedfat\":\"tr\" , \"saturatedfat3\":\"tr\", \"monounsaturatedfat\":\"tr\", \"monounsaturatedfat3\":\"tr\" },"
                                            + "{\"product\":\"Sugar, white\", \"unit\":\"2 kg\",\"servings\":\"400\", \"price2011\":\"3.19\", \"price2012\":\"3.16\", \"price2013\":\"3.03\", \"price2014\":\"2.76\", \"price2015\":\"2.79\", \"priceserving\":\"0.00558\", \"usdaproduct\":\"White sugar (granulated)\", \"servingmeasure\":\"5 mL\", \"servingweight\":\"4\", \"energykcal\":\"16\", \"energykj\":\"68\", \"protein\":\"0\", \"carbohydrate\":\"4\", \"totalsugar\":\"4\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"0\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"tr\", \"iron\":\"0\", \"sodium\":\"0\", \"potassium\":\"0\", \"magnesium\":\"0\", \"phosphorus\":\"0\"   , \"vitamina\":\"0\"	, \"vitaminb12\":\"0\", \"vitaminc\":\"0\" , \"saturatedfat3\":\"0\"  },"
                                            + "{\"product\":\"Coffee, roasted\", \"unit\":\"300 g\",\"servings\":\"60\", \"price2011\":\"5.79\", \"price2012\":\"6.12\", \"price2013\":\"5.88\", \"price2014\":\"5.56\", \"price2015\":\"6.53\", \"priceserving\":\"5.44166666666667\", \"usdaproduct\":\"Coffee, brewed\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"250\", \"energykcal\":\"3\", \"energykj\":\"10\", \"protein\":\"tr\", \"carbohydrate\":\"0\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"5\", \"iron\":\"tr\", \"sodium\":\"5\", \"potassium\":\"123\", \"magnesium\":\"8\", \"phosphorus\":\"8\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                                            + "{\"product\":\"Coffee, instant\", \"unit\":\"200 g\",\"servings\":\"25\", \"price2011\":\"6.26\", \"price2012\":\"6.6\", \"price2013\":\"6.62\", \"price2014\":\"6.26\", \"price2015\":\"6.93\", \"priceserving\":\"8.76645\", \"usdaproduct\":\"Coffee, instant, regular, powder + water\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"253\", \"energykcal\":\"5\", \"energykj\":\"20\", \"protein\":\"tr\", \"carbohydrate\":\"1\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"10\", \"iron\":\"0.1\", \"sodium\":\"5\", \"potassium\":\"76\", \"magnesium\":\"8\", \"phosphorus\":\"8\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                                            + "{\"product\":\"Tea (bags)\", \"unit\":\"72\",\"servings\":\"72\", \"price2011\":\"4.4\", \"price2012\":\"4.42\", \"price2013\":\"4.32\", \"price2014\":\"4.4\", \"price2015\":\"4.51\", \"priceserving\":\"0.0626388888888889\", \"usdaproduct\":\"Tea, brewed\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"250\", \"energykcal\":\"3\", \"energykj\":\"10\", \"protein\":\"0\", \"carbohydrate\":\"1\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"0\", \"iron\":\"0.1\", \"sodium\":\"8\", \"potassium\":\"93\", \"magnesium\":\"8\", \"phosphorus\":\"3\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                                            + "{\"product\":\"Cooking or salad oil\", \"unit\":\"1 l\",\"servings\":\"66.6666666666667\", \"price2011\":\"4.14\", \"price2012\":\"4.17\", \"price2013\":\"4.16\", \"price2014\":\"4.07\", \"price2015\":\"4.18\", \"priceserving\":\"0.0627\", \"usdaproduct\":\"Canola\", \"servingmeasure\":\"15 mL\", \"servingweight\":\"14\", \"energykcal\":\"125\", \"energykj\":\"525\", \"protein\":\"0\", \"carbohydrate\":\"0\", \"totalfat\":\"14\", \"saturatedfat\":\"1\", \"cholesterol\":\"0\", \"calcium\":\"0\", \"iron\":\"0\", \"sodium\":\"0\", \"potassium\":\"0\", \"magnesium\":\"0\", \"phosphorus\":\"0\"   , \"vitamina\":\"0\", \"vitamind\":\"0\"	, \"vitamine\":\"2.4\"   , \"polyunsaturatedfatg\":\"4.2\", \"polyunsaturatedfat3\":\"4.2\", \"saturatedfat3\":\"1\", \"monounsaturatedfat\":\"8.4\", \"monounsaturatedfat3\":\"8.4\", \"transfat\":\"0.3\"	},"
                                            + "{\"product\":\"Soup, canned\", \"unit\":\"284 ml\",\"servings\":\"1.136\", \"price2011\":\"1.03\", \"price2012\":\"1.07\", \"price2013\":\"1.04\", \"price2014\":\"1.03\", \"price2015\":\"1.13\", \"priceserving\":\"0.994718309859155\", \"usdaproduct\":\"Tomato\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"258\", \"energykcal\":\"90\", \"energykj\":\"376\", \"protein\":\"2\", \"carbohydrate\":\"18\", \"totalsugar\":\"9\", \"totaldietaryfibre\":\"1.2\"	, \"totalfat\":\"2\", \"saturatedfat\":\"0.4\", \"cholesterol\":\"0\", \"calcium\":\"13\", \"iron\":\"1.9\", \"sodium\":\"735\", \"potassium\":\"278\", \"magnesium\":\"8\", \"phosphorus\":\"36\", \"folate\":\"15\" , \"vitamina\":\"26\"	, \"vitaminb12\":\"0\", \"vitaminc\":\"3\" , \"saturatedfat3\":\"0.4\"  },"
                                            + "{\"product\":\"Baby food\", \"unit\":\"128 ml\", \"price2011\":\"0.8\", \"price2012\":\"0.85\", \"price2013\":\"0.83\", \"price2014\":\"0.92\", \"price2015\":\"0.94\", \"priceserving\":\"\"},"
                                            + "{\"product\":\"Peanut butter\", \"unit\":\"500 g\",\"servings\":\"16.6666666666667\", \"price2011\":\"3\", \"price2012\":\"3.84\", \"price2013\":\"3.59\", \"price2014\":\"3.48\", \"price2015\":\"3.57\", \"priceserving\":\"0.22848\", \"usdaproduct\":\"Peanut butter, chunk type, fat, sugar and salt added\", \"servingmeasure\":\"30 mL\", \"servingweight\":\"32\", \"energykcal\":\"191\", \"energykj\":\"799\", \"protein\":\"8\", \"carbohydrate\":\"7\", \"totalsugar\":\"3\"	, \"totaldaietaryfibre\":\"2.6\", \"totalfat\":\"16\", \"saturatedfat\":\"2.6\"	, \"calcium\":\"15\", \"iron\":\"0.6\", \"sodium\":\"158\", \"potassium\":\"242\", \"magnesium\":\"52\", \"phosphorus\":\"103\", \"folate\":\"30\"  , \"vitaminb12\":\"0\", \"vitamine\":\"2\"   , \"polyunsaturatedfatg\":\"4.8\", \"polyunsaturatedfat3\":\"4.8\", \"saturatedfat3\":\"2.6\", \"monounsaturatedfat\":\"8\", \"monounsaturatedfat3\":\"8\" },"
                                            + "{\"product\":\"Fruit flavoured crystals\", \"unit\":\"2.25 l\",\"servings\":\"9\", \"price2011\":\"1.42\", \"price2012\":\"1.44\", \"price2013\":\"1.47\", \"price2014\":\"1.57\", \"price2015\":\"1.74\", \"priceserving\":\"0.193333333333333\", \"usdaproduct\":\"Fruit punch flavour drink, powder (Kool-Aid™) + water\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"276\", \"energykcal\":\"102\", \"energykj\":\"427\", \"protein\":\"0\", \"carbohydrate\":\"26\", \"totalsugar\":\"N/A\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"tr\", \"saturatedfat\":\"tr\", \"cholesterol\":\"0\", \"calcium\":\"44\", \"iron\":\"0.1\", \"sodium\":\"39\", \"potassium\":\"3\", \"magnesium\":\"3\", \"phosphorus\":\"55\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"tr\"  },"
                                            + "{\"product\":\"Soft drinks, cola type\", \"unit\":\"2 l\", \"price2011\":\"1.66\",\"servings\":\"8\", \"price2012\":\"1.91\", \"price2013\":\"1.98\", \"price2014\":\"1.99\", \"price2015\":\"1.98\", \"priceserving\":\"0.2475\", \"usdaproduct\":\"Cola, aspartame sweetened\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"250\", \"energykcal\":\"3\", \"energykj\":\"10\", \"protein\":\"tr\", \"carbohydrate\":\"tr\", \"totalsugar\":\"0\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"0\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"8\", \"iron\":\"0.1\", \"sodium\":\"13\", \"potassium\":\"15\", \"magnesium\":\"3\", \"phosphorus\":\"28\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"0\"  },"
                                            + "{\"product\":\"Soft drinks, lemon-lime type\", \"unit\":\"2 l\", \"price2011\":\"1.62\",\"servings\":\"8\", \"price2012\":\"1.82\", \"price2013\":\"1.86\", \"price2014\":\"1.92\", \"price2015\":\"1.91\", \"priceserving\":\"0.23875\", \"usdaproduct\":\"Lemon-lime soda\", \"servingmeasure\":\"250 mL\", \"servingweight\":\"260\", \"energykcal\":\"104\", \"energykj\":\"433\", \"protein\":\"0\", \"carbohydrate\":\"27\", \"totalsugar\":\"23\", \"totaldietaryfibre\":\"0\"	, \"totalfat\":\"0\", \"saturatedfat\":\"0\", \"cholesterol\":\"0\", \"calcium\":\"5\", \"iron\":\"0.2\", \"sodium\":\"29\", \"potassium\":\"3\", \"magnesium\":\"3\", \"phosphorus\":\"0\"   , \"vitamina\":\"0\" , \"vitaminc\":\"0\" , \"saturatedfat3\":\"0\"  }]}";
                            try {
                                JSONObject jsonObject1 = new JSONObject(strFoodNutrients);
                                JSONObject jsonObject2 = null;
                                Boolean blnFound = false;
                                // look up picked product and report calories, proteins, carbs, fat
                                JSONArray jsonArray1 = jsonObject1.getJSONArray(NUTRIENT_LIST_KEY);
                                Integer listLength = jsonArray1.length();
                                if( listLength>0 ){
                                    for (Integer intI = 0 ; intI < listLength; intI++ ){
                                        JSONObject objElement = jsonArray1.optJSONObject(intI);
                                        if(Objects.equals(objElement.getString(NUTRIENT_PRODUCT_KEY).toLowerCase(), strProduct.toLowerCase())) {
                                            // Toast.makeText( getContext(), "Product: " + objElement.getString(NUTRIENT_PRODUCT_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Price per " + objElement.getString(NUTRIENT_PRICE_PER_KEY) +":" + objElement.getString(NUTRIENT_PRICE_KEY),Toast.LENGTH_LONG).show();
                                            // Toast.makeText( getContext(), "Serving size: " + objElement.getString(NUTRIENT_SERVING_SIZE_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Servings per " + objElement.getString(NUTRIENT_PRICE_PER_KEY) +":" + objElement.getString(NUTRIENT_SERVING_PER_KEY),Toast.LENGTH_LONG).show();
                                            // Toast.makeText( getContext(), "Calories: " + objElement.getString(NUTRIENT_CALORIE_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Carbs: " + objElement.getString(NUTRIENT_CARBS_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Protein: " + objElement.getString(NUTRIENT_PROTEIN_KEY),Toast.LENGTH_SHORT).show();
                                            // Toast.makeText( getContext(), "Fat: " + objElement.getString(NUTRIENT_FATS_KEY),Toast.LENGTH_SHORT).show();
                                            jsonObject2 = jsonArray1.optJSONObject(intI); blnFound = true;
                                        }
                                    }
                                }
                                //  append the product to JSON data
                                // TODO: 2016-04-03 make a function to return JSON object from SP
                                // TODO: 2016-04-03 make a function to add product to JSON object
                                // TODO: 2016-04-03 make a function to save JSON object to SP ???
                                if( blnFound ) {
                                    // Have Department and Product, need purchase size (quantity) and number of servings
                                    final String unitPurchased  = String.valueOf(jsonObject2.getString(NUTRIENT_PRICE_PER_KEY));
                                    final Double servings = Double.valueOf(jsonObject2.getString(NUTRIENT_SERVING_PER_KEY));
                                    final JSONObject jsonItem = new JSONObject();
                                    jsonItem.put(NUTRIENT_LIST_DEPARTMENT_KEY, strDepartment);
                                    jsonItem.put(NUTRIENT_LIST_PRODUCT_KEY, strProduct);
                                    jsonItem.put(NUTRIENT_LIST_QUANTITY_KEY, unitPurchased);
                                    jsonItem.put(NUTRIENT_LIST_SERVING_KEY, servings);
                                    // Toast.makeText( getContext(), jsonItem.toString(), Toast.LENGTH_LONG).show();
                                    //
                                    final SharedPreferences preferences = getActivity().getSharedPreferences(ApplicationActivity.NAME, Context.MODE_PRIVATE);
                                    final String strJprod = preferences.getString(ApplicationActivity.NUTRIENT_PAGE_KEY,
                                            "{\"productlist\":[]}");
                                    final JSONObject jsonObject3 = new JSONObject(strJprod);
                                    // Toast.makeText( getContext(), "SharedPreferences: " +jsonObject3.toString(), Toast.LENGTH_LONG).show();
                                    final JSONArray jsonArray2 = jsonObject3.getJSONArray(NUTRIENT_LIST_FOOD_KEY);
                                    jsonArray2.put(jsonItem);
                                    jsonObject3.put(NUTRIENT_LIST_FOOD_KEY,jsonArray2);
                                    // Toast.makeText( getContext(), "SharedPreferences: " +jsonObject3.toString(), Toast.LENGTH_LONG).show();
                                    /*
                                    //final String
                                    final JSONArray jsonArray2 = jsonObject3.getJSONArray(ApplicationActivity.NUTRIENT_LIST_FOOD_KEY);
                                    if( jsonArray2 != null ) {
                                        //
                                    }
                                            */
                                    getActivity().getSharedPreferences(ApplicationActivity.NAME, Context.MODE_PRIVATE)
                                            .edit()
                                            .putString(ApplicationActivity.NUTRIENT_PAGE_KEY, jsonObject3.toString())
                                            .apply();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // TODO: 2016-04-03 retrieve the JSON data and display
                            final SharedPreferences preferences = getActivity().getSharedPreferences(ApplicationActivity.NAME, Context.MODE_PRIVATE);
                            final String strJproduct = preferences.getString(ApplicationActivity.NUTRIENT_PAGE_KEY,
                                    "{\"productlist\":[]}");
                            // Toast.makeText( getContext(), "SharedPreferences: " +strJproduct, Toast.LENGTH_LONG).show();
                            try {
                                final JSONObject objJOGroceryList = new JSONObject( strJproduct );
                                final JSONArray arrJAGroceryList = objJOGroceryList.getJSONArray(NUTRIENT_LIST_FOOD_KEY);
                                final Integer intGLlength = arrJAGroceryList.length();
                                final TextView foodDescription = (TextView) v.getRootView().findViewById(R.id.pickpage_food_description);
                                foodDescription.setText("");
                                // Toast.makeText( getContext(), "SharedPreferences: " +strJproduct, Toast.LENGTH_LONG).show();
                                for( int i = 0 ; i < intGLlength ; i++ ){
                                    foodDescription.setText(
                                            getString(R.string.pickpage_food_display_description,
                                                    foodDescription.getText(),
                                                    (!Objects.equals(foodDescription.getText(), "") ? "\n" : "" ),
                                                    arrJAGroceryList.getJSONObject(i).getString(NUTRIENT_LIST_QUANTITY_KEY ),
                                                    arrJAGroceryList.getJSONObject(i).getString(NUTRIENT_PRODUCT_KEY),
                                                    Double.valueOf( arrJAGroceryList.getJSONObject(i).getString(NUTRIENT_LIST_SERVING_KEY) )
                                            )
                                    );
//                                    Toast.makeText(getContext(), "Food" + arrJAGroceryList.getJSONObject(i).getString(NUTRIENT_LIST_DEPARTMENT_KEY),Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        /*

                                                @Override
                        public void onClick(View v) {

                         */
                    });
                    // TODO: 2016-04-03 retrieve the JSON data and display
                    final SharedPreferences shPreferences = getActivity().getSharedPreferences(ApplicationActivity.NAME, Context.MODE_PRIVATE);
                    final String strJproduct = shPreferences.getString(ApplicationActivity.NUTRIENT_PAGE_KEY,
                            "{\"productlist\":[]}");
                    // Toast.makeText( getContext(), "SharedPreferences: " +strJproduct, Toast.LENGTH_LONG).show();
                    //  ((PickpageFoodElement) update instanceof  ? (() ((PickpageFoodElement) update) : null;
                    final JSONObject jsonObject4;
                    try {
                        jsonObject4 = new JSONObject(strJproduct);
                        final JSONArray jsonArray3 = jsonObject4.getJSONArray(NUTRIENT_LIST_FOOD_KEY);
                        final Integer arrLength = jsonArray3.length();
                        final TextView foodDescription = (TextView) rootView.findViewById(R.id.pickpage_food_description);
                        foodDescription.setText("");
                        if( arrLength > 0 ) {
                            // There is something to show.
                            // Is there anything on display?
                            for( int i = 0 ; i < arrLength ; i++){
                                foodDescription.setText(
                                        getString(R.string.pickpage_food_display_description,
                                        foodDescription.getText(),
                                        (!Objects.equals(foodDescription.getText(), "") ? "\n" : "" ),
                                        jsonArray3.getJSONObject(i).getString(NUTRIENT_LIST_QUANTITY_KEY ),
                                        jsonArray3.getJSONObject(i).getString(NUTRIENT_PRODUCT_KEY),
                                        Double.valueOf( jsonArray3.getJSONObject(i).getString(NUTRIENT_LIST_SERVING_KEY))
                                        )
                                );
//                                Toast.makeText(getContext(), "Food : " + jsonArray3.getJSONObject(i).getString(NUTRIENT_LIST_DEPARTMENT_KEY),Toast.LENGTH_SHORT).show();
                            }
                            //final ListView listview = (ListView) rootView.findViewById(R.id.pickpage_food_listview);
                            //                            if( listview.getCount() > 0 ) {
                                // burn it - to better next time // TODO do better
                                // Toast.makeText( getContext(),
                                //        listview.getChildAt(0).toString(), Toast.LENGTH_SHORT)
                                //        .show();
                                // listview.removeAllViews();}
                            /*
                            else {
                                for (Integer intI = 0 ; intI < arrLength; intI++ ){
                                    JSONObject objElement2 = jsonArray3.optJSONObject(intI);
                                    // create child view for food model
                                    final View newFood = new PickpageFoodAdapter( objElement2 );
                                    listview.addView( newFood );
                                }
                            }
                        */

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            //        Toast.makeText(getActivity(),"Fragment open",Toast.LENGTH_SHORT).show();
                case 5:
                case 6:
                case 7:
                    rootView = inflater.inflate(R.layout.applicationfragment, container, false);
                    TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                    textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                    break;
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3, no, 7 total pages. No wait, just 4
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.intropage_title);
                case 1:
                    return getString(R.string.pickpage_person_title);
                case 2:
                    return getString(R.string.pickpage_food_title);
                case 3:
                    return getString(R.string.nutrition_facts_title);
                case 4:
                    return getString(R.string.nutrition_details_title);
                case 5:
                    return getString(R.string.shoppinglist_title);
                case 6:
                    return getString(R.string.source_data_title);
            }
            return null;
        }
    }

    public class ListViewFood extends ListActivity {
        // from StackOverflow.com/questions/4540754/dynamically-add-elements-to-a-listview-android
        ArrayList<String> listItems = new ArrayList<String>();

        ArrayAdapter<String> adapter;

        @Override
        public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
            super.onCreate(savedInstanceState, persistentState);

            setContentView(R.layout.pickpage_food);
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    listItems);
            setListAdapter(adapter);
        }

        public void addItems( View view ) {
            listItems.add("Food");
            adapter.notifyDataSetChanged();
        }
    }
}

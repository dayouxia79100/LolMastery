package com.example.app;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
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
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            final int currentTab = getArguments().getInt(ARG_SECTION_NUMBER);

            if(currentTab == 1){
                rootView.setBackgroundResource(R.drawable.offense_back);
            } else if (currentTab == 2){
                rootView.setBackgroundResource(R.drawable.defense_back);
            } else if (currentTab == 3){
                rootView.setBackgroundResource(R.drawable.utility_back);
            }


            GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
            gridview.setAdapter(new ImageAdapter(getActivity()));

            final Integer[] maxOffense = {
                1,4,4,1,
                1,3,3,1,
                1,1,1,3,
                1,3,3,1,
                1,3,0,1,
                0,1
            };

            final Integer[] maxDefence = {
                    2,2,2,2,
                    1,3,0,1,
                    1,1,3,3,
                    3,1,1,1,
                    1,4,1,0,
                    0,1
            };

            final Integer[] maxUtility = {
                    1,3,3,1,
                    0,3,1,1,
                    3,1,3,1,
                    1,1,3,2,
                    0,1,3,0,
                    0,1
            };



            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    TextView count = ((TextView)((RelativeLayout)v).getChildAt(1));
                    char first = count.getText().charAt(0);
                    char second = count.getText().charAt(2);

                    int firstNumber = Character.getNumericValue(first);
                    int secondNumber = Character.getNumericValue(second);

                    if(firstNumber < secondNumber){
                        firstNumber++;
                        count.setText(firstNumber+"/"+secondNumber);
                    }


                }
            });


            return rootView;
        }

        public class ImageAdapter extends BaseAdapter {
            private Context mContext;

            public ImageAdapter(Context c) {
                mContext = c;
            }

            public int getCount() {
                return mOffenseMasteries.length;
            }

            public Object getItem(int position) {
                return null;
            }

            public long getItemId(int position) {
                return 0;
            }

            // create a new ImageView for each item referenced by the Adapter
            public View getView(int position, View convertView, ViewGroup parent) {


                Map<Integer,Integer> masteryMap = new HashMap<Integer, Integer>();
                masteryMap.put(1,1);
                masteryMap.put(2,1);
                masteryMap.put(12,1);




                RelativeLayout relativeLayout = new RelativeLayout(mContext);
                ImageView imageView;
                TextView textView;
                if (convertView == null) {  // if it's not recycled, initialize some attributes
                    imageView = new ImageView(mContext);
                    imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(8, 8, 8, 8);
                    textView = new TextView(mContext);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    lp.addRule(relativeLayout.ALIGN_PARENT_BOTTOM);
                    lp.addRule(relativeLayout.CENTER_HORIZONTAL);

                    textView.setTextColor(Color.GREEN);
                    relativeLayout.addView(imageView);
                    relativeLayout.addView(textView,lp);
                    textView.setBackgroundColor(Color.BLACK);
                    imageView.setBackgroundColor(Color.YELLOW);
                } else {
                    return convertView;
                }


                if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                textView.setText("0/"+maxOffense[position]);
                imageView.setImageResource(mOffenseMasteries[position]);
                    if(position == 18 || position == 20){
                        relativeLayout.setVisibility(View.INVISIBLE);
                    }
                } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                    textView.setText("0/"+maxDefence[position]);
                    imageView.setImageResource(mDefenseMasteries[position]);
                    if(position == 6 || position == 19 || position == 20){
                        relativeLayout.setVisibility(View.INVISIBLE);
                    }
                } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                    textView.setText("0/"+maxUtility[position]);
                    imageView.setImageResource(mUtilityMasteries[position]);
                    if(position == 4 || position == 16 || position == 19 || position==20){
                        relativeLayout.setVisibility(View.INVISIBLE);
                    }
                }

                if(masteryMap.containsKey(position)){
                    textView.setText(masteryMap.get(position)+"/"+textView.getText().charAt(2));
                    relativeLayout.setAlpha(1);
                } else{
                    relativeLayout.setAlpha(0.45f);
                }

                return relativeLayout;


            }
            private Integer[] maxOffense = {
                    1,4,4,1,
                    1,3,3,1,
                    1,1,1,3,
                    1,3,3,1,
                    1,3,0,1,
                    0,1
            };

            private Integer[] maxDefence = {
                    2,2,2,2,
                    1,3,0,1,
                    1,1,3,3,
                    3,1,1,1,
                    1,4,1,0,
                    0,1
            };

            private Integer[] maxUtility = {
                    1,3,3,1,
                    0,3,1,1,
                    3,1,3,1,
                    1,1,3,2,
                    0,1,3,0,
                    0,1
            };



            // references to our images
            private Integer[] mOffenseMasteries = {
                R.drawable.offense_11, R.drawable.offense_12,
                R.drawable.offense_13, R.drawable.offense_14,

                R.drawable.offense_21, R.drawable.offense_22,
                R.drawable.offense_23, R.drawable.offense_24,

                R.drawable.offense_31, R.drawable.offense_32,
                R.drawable.offense_33, R.drawable.offense_34,

                R.drawable.offense_41, R.drawable.offense_42,
                R.drawable.offense_43, R.drawable.offense_44,

                R.drawable.offense_51, R.drawable.offense_52,
                R.drawable.offense_54, R.drawable.offense_54,

                R.drawable.offense_54, R.drawable.offense_62

            };

            private Integer[] mDefenseMasteries = {
                    R.drawable.defense_11, R.drawable.defense_12,
                    R.drawable.defense_13, R.drawable.defense_14,

                    R.drawable.defense_21, R.drawable.defense_22,
                    R.drawable.defense_22, R.drawable.defense_24,

                    R.drawable.defense_31, R.drawable.defense_32,
                    R.drawable.defense_33, R.drawable.defense_34,

                    R.drawable.defense_41, R.drawable.defense_42,
                    R.drawable.defense_43, R.drawable.defense_44,

                    R.drawable.defense_51, R.drawable.defense_52,
                    R.drawable.defense_53, R.drawable.defense_53,

                    R.drawable.defense_62, R.drawable.defense_62
            };

            private  Integer[] mUtilityMasteries = {
                    R.drawable.utility_11, R.drawable.utility_12,
                    R.drawable.utility_13, R.drawable.utility_14,

                    R.drawable.utility_22, R.drawable.utility_22,
                    R.drawable.utility_23, R.drawable.utility_24,

                    R.drawable.utility_31, R.drawable.utility_32,
                    R.drawable.utility_33, R.drawable.utility_34,

                    R.drawable.utility_41, R.drawable.utility_42,
                    R.drawable.utility_43, R.drawable.utility_44,

                    R.drawable.utility_52, R.drawable.utility_52,
                    R.drawable.utility_53, R.drawable.utility_53,

                    R.drawable.utility_62, R.drawable.utility_62

            };
        }
    }

}

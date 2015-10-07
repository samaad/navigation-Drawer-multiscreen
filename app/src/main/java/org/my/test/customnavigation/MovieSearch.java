package org.my.test.customnavigation;

import android.os.Bundle;

/**
 * Created by Dell on 10/8/2015.
 */
public class MovieSearch extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.movie_search, frameLayout);
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);
    }
}

package org.my.test.customnavigation;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Dell on 10/8/2015.
 */
public class CricketApi extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.cricket_api, frameLayout);
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);

        TextView tv = (TextView) findViewById(R.id.txt_cricket_search);
    }
}
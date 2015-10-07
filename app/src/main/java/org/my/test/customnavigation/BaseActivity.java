package org.my.test.customnavigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;


public class BaseActivity extends AppCompatActivity {

	protected FrameLayout frameLayout;
	protected ListView mDrawerList;
	protected String[] listArray = { "Movie", "Cricket" };
	protected static int position;
	private static boolean isLaunch = true;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_drawer_base_layout);
		
		frameLayout = (FrameLayout)findViewById(R.id.content_frame);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, listArray));
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				openActivity(position);
			}
		});


		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		

		actionBarDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.string.open_drawer,
				R.string.close_drawer)
		{ 
			@Override
			public void onDrawerClosed(View drawerView) {
				getSupportActionBar().setTitle(listArray[position]);
                invalidateOptionsMenu();
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(getString(R.string.app_name));
                invalidateOptionsMenu();
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				super.onDrawerStateChanged(newState);
			}
		};
		mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
		
		if(isLaunch){

			isLaunch = false;
			openActivity(0);
		}
	}
	
	protected void openActivity(int position) {
		
		mDrawerLayout.closeDrawer(mDrawerList);
		BaseActivity.position = position;
		
		switch (position) {
		case 0:
			startActivity(new Intent(this, MovieSearch.class));
			break;
		case 1:
			startActivity(new Intent(this, CricketApi.class));
			break;
		default:
			break;
		}
		
		Toast.makeText(this, "Selected Item Position::"+position, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
		
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
  	@Override
	public void onBackPressed() {
		if(mDrawerLayout.isDrawerOpen(mDrawerList)){
			mDrawerLayout.closeDrawer(mDrawerList);
		}else {
			mDrawerLayout.openDrawer(mDrawerList);
		}
	}
}

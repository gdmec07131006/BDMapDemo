package cn.due.gdmec.c07131006.bdmapdemo;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MainActivity extends Activity {
	Double Nlat = 23.387;
	Double Nlng = 113.453;
	MapView myMV = null;
	BaiduMap myBDM = null;
	LocationManager lm = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		myMV = (MapView) findViewById(R.id.bmapView);
		myBDM = myMV.getMap();
		myBDM.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		myBDM.setMyLocationEnabled(true);
		
		MyLocationData locdata = new MyLocationData.Builder()
		.latitude(Nlat).longitude(Nlng).build();
		
		myBDM.setMyLocationData(locdata);
		
		LatLng ll = new LatLng(Nlat,Nlng);
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll,18);
		myBDM.animateMapStatus(u);
		
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(lm.GPS_PROVIDER, 1000, 10, lis);
		
	}
	LocationListener lis = new LocationListener(){

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
		
			
			MyLocationData locdata = new MyLocationData.Builder()
			.latitude(location.getLatitude()).longitude(location.getLongitude()).build();
			
			myBDM.setMyLocationData(locdata);
			
			LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll,18);
			myBDM.animateMapStatus(u);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

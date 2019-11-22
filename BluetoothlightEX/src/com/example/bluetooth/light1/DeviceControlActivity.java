/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bluetooth.light1;


import java.util.UUID;

import com.example.bluetooth.light1.R;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * For a given BLE device, this Activity provides the user interface to connect,
 * display data, and display GATT services and characteristics supported by the
 * device. The Activity communicates with {@code BluetoothLeService}, which in
 * turn interacts with the Bluetooth LE API.
 */
public class DeviceControlActivity extends Activity {
	private final static String TAG = DeviceControlActivity.class
			.getSimpleName();

	public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
	public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

	private TextView mConnectionState;
	private EditText mDataField;
	private EditText mDataField2;
	private String mDeviceName;
	private String mDeviceAddress;
	private ExpandableListView mGattServicesList;
	private BluetoothLeService mBluetoothLeService;
	private BluetoothGattService mReadBluetoothLeService;
	private BluetoothGattService mWriteBluetoothLeService;
	private boolean mConnected = false;
	private BluetoothGattCharacteristic mNotifyCharacteristic;
	private BluetoothGattCharacteristic mReadCharacteristic;
	private BluetoothGattCharacteristic mWriteCharacteristic;
	private Button button1;
	private Button buttoncancel;
	private Spinner spinner1;
	private Spinner spinner2;
	private Spinner spinner3;
	private Spinner spinner4;
	private Spinner spinner5;
	 byte[] WriteBytes = new byte[20];
	private String order ="FE0100000c810145FDDF";
	private String mode = "41";
	private String point = "00";
	private String point1 = "00";
	private String point2 = "0c";
	private String speed = "01";

	// Code to manage Service lifecycle.
	private final ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName componentName,
				IBinder service) {
			mBluetoothLeService = ((BluetoothLeService.LocalBinder) service)
					.getService();
			if (!mBluetoothLeService.initialize()) {
				Log.e(TAG, "Unable to initialize Bluetooth");
				finish();
			}
			// Automatically connects to the device upon successful start-up
			// initialization.
			mBluetoothLeService.connect(mDeviceAddress);
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			mBluetoothLeService = null;
		}
	};

	// Handles various events fired by the Service.
	// ACTION_GATT_CONNECTED: connected to a GATT server.
	// ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
	// ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
	// ACTION_DATA_AVAILABLE: received data from the device. This can be a
	// result of read
	// or notification operations.
	private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
				mConnected = true;
				updateConnectionState(R.string.connected);
				invalidateOptionsMenu();
			} else if (BluetoothLeService.ACTION_GATT_DISCONNECTED
					.equals(action)) {
				mConnected = false;
				updateConnectionState(R.string.disconnected);
				invalidateOptionsMenu();
				clearUI();
			} else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED
					.equals(action)) {
				// Show all the supported services and characteristics on the
				// user interface.
				mReadBluetoothLeService = mBluetoothLeService.getSupportedGattServices(UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb"));
				mReadCharacteristic = mReadBluetoothLeService.getCharacteristic(UUID.fromString("0000ffe4-0000-1000-8000-00805f9b34fb"));
				mWriteBluetoothLeService = mBluetoothLeService.getSupportedGattServices(UUID.fromString("0000ffe5-0000-1000-8000-00805f9b34fb"));
				mWriteCharacteristic = mWriteBluetoothLeService.getCharacteristic(UUID.fromString("0000ffe9-0000-1000-8000-00805f9b34fb"));
			} else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
				displayData(intent
						.getStringExtra(BluetoothLeService.EXTRA_DATA));

			}
		}
	};

	// If a given GATT characteristic is selected, check for supported features.
	// This sample
	// demonstrates 'Read' and 'Notify' features. See
	// http://d.android.com/reference/android/bluetooth/BluetoothGatt.html for
	// the complete
	// list of supported characteristic features.


        
	private void read() {  
    	//mBluetoothLeService.readCharacteristic(readCharacteristic);
    	//readCharacteristic的数据发生变化，发出通知
    	mBluetoothLeService.setCharacteristicNotification(mReadCharacteristic, true);
    	//Toast.makeText(this, "读成功", Toast.LENGTH_SHORT).show();
    } 
            
	private void clearUI() {
		mGattServicesList.setAdapter((SimpleExpandableListAdapter) null);
		mDataField.setText(R.string.no_data);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gatt_services_characteristics);

		final Intent intent = getIntent();
		mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
		mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

		// Sets up UI references.
		((TextView) findViewById(R.id.device_address)).setText(mDeviceAddress);
		mConnectionState = (TextView) findViewById(R.id.connection_state);
		mDataField = (EditText) findViewById(R.id.data_value);
		mDataField2 = (EditText) findViewById(R.id.data_write);
		button1 = (Button)findViewById(R.id.buttonsend);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				read();
                final int charaProp = mWriteCharacteristic.getProperties();
                
                //如果该char可写
                if ((charaProp | BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
                     order ="FE01"+point+point1+point2+mode+speed+"45FDDF";
                     //将输入的字符串转化成byte型数组，这样才可以被单片机识别
                    byte[] bytes = hexStringToBytes(order);
                    mDataField2.setText(order);
                    mWriteCharacteristic.setValue(new byte[]{(byte) 0xFE,0x01,0x00,0x00,0x0C,(byte) 0x81,0x01,0x45,(byte) 0xfd,(byte) 0xdf});
                    mWriteCharacteristic.setValue(bytes);
                  mBluetoothLeService.writeCharacteristic(mWriteCharacteristic);
                        Toast.makeText(getApplicationContext(), "写入成功！", Toast.LENGTH_SHORT).show();
			}
			}
		});
		buttoncancel = (Button)findViewById(R.id.buttoncancel);
		buttoncancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				read();
                final int charaProp = mWriteCharacteristic.getProperties();
                
                //如果该char可写
                if ((charaProp | BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
                     order ="FE0100000C810045FDDF";
                    byte[] bytes = hexStringToBytes(order);
                    mDataField2.setText(order);
                    mWriteCharacteristic.setValue(new byte[]{(byte) 0xFE,0x01,0x00,0x00,0x0C,(byte) 0x81,0x01,0x45,(byte) 0xfd,(byte) 0xdf});
                    mWriteCharacteristic.setValue(bytes);
                  mBluetoothLeService.writeCharacteristic(mWriteCharacteristic);
                        Toast.makeText(getApplicationContext(), "写入成功！", Toast.LENGTH_SHORT).show();
			}
			}
		});
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件  
            public void onItemSelected(AdapterView<?> arg0, View arg1,  
                    int arg2, long arg3) {  
                // TODO Auto-generated method stub  
                // 将所选mySpinner 的值带入myTextView 中   

            	if(spinner1.getSelectedItemPosition()==0){
            		mode="41";
            	}
            	else if(spinner1.getSelectedItemPosition()==1){
            		mode="81";
            	}
            	else if(spinner1.getSelectedItemPosition()==2){
            		mode="42";
            	}
            	else if(spinner1.getSelectedItemPosition()==3){
            		mode="82";
            	}
            }  

            public void onNothingSelected(AdapterView<?> arg0) {  
                // TODO Auto-generated method stub  
              
            }  
        }); 
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件  
            public void onItemSelected(AdapterView<?> arg0, View arg1,  
                    int arg2, long arg3) {  
                // TODO Auto-generated method stub  
                // 将所选mySpinner 的值带入myTextView 中   

            	if(spinner2.getSelectedItemPosition()==0){
            		point="00";
            	}
            	else if(spinner2.getSelectedItemPosition()==1){
            		point="01";
            	}
            	else if(spinner2.getSelectedItemPosition()==2){
            		point="02";
            	}
            	else if(spinner2.getSelectedItemPosition()==3){
            		point="03";
            	}
            	else if(spinner2.getSelectedItemPosition()==4){
            		point="04";
            	}
            	else if(spinner2.getSelectedItemPosition()==5){
            		point="05";
            	}
            	else if(spinner2.getSelectedItemPosition()==6){
            		point="06";
            	}
            	else if(spinner2.getSelectedItemPosition()==7){
            		point="07";
            	}
            	else if(spinner2.getSelectedItemPosition()==8){
            		point="08";
            	}
            	else if(spinner2.getSelectedItemPosition()==9){
            		point="09";
            	}
            	else if(spinner2.getSelectedItemPosition()==10){
            		point="0a";
            	}
            	else if(spinner2.getSelectedItemPosition()==11){
            		point="0b";
            	}
            	else if(spinner2.getSelectedItemPosition()==12){
            		point="0c";
            	}
              	else if(spinner2.getSelectedItemPosition()==13){
            		point="0d";
            	}
            	else if(spinner2.getSelectedItemPosition()==14){
            		point="0e";
            	}
            	else if(spinner2.getSelectedItemPosition()==15){
            		point="0f";
            	}
            	else if(spinner2.getSelectedItemPosition()==16){
            		point="10";
            	}
            	else if(spinner2.getSelectedItemPosition()==17){
            		point="11";
            	}
            	else if(spinner2.getSelectedItemPosition()==18){
            		point="12";
            	}
            	else if(spinner2.getSelectedItemPosition()==19){
            		point="13";
            	}
            	else if(spinner2.getSelectedItemPosition()==20){
            		point="14";
            	}
            	else if(spinner2.getSelectedItemPosition()==21){
            		point="15";
            	}
            	else if(spinner2.getSelectedItemPosition()==22){
            		point="16";
            	}
            	else if(spinner2.getSelectedItemPosition()==23){
            		point="17";
            	}
            	else if(spinner2.getSelectedItemPosition()==24){
            		point="18";
            	}
            	else if(spinner2.getSelectedItemPosition()==25){
            		point="19";
            	}
            	else if(spinner2.getSelectedItemPosition()==26){
            		point="1a";
            	}
            }  

            public void onNothingSelected(AdapterView<?> arg0) {  
                // TODO Auto-generated method stub  
              
            }  
        });
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		spinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件  
            public void onItemSelected(AdapterView<?> arg0, View arg1,  
                    int arg2, long arg3) {  
                // TODO Auto-generated method stub  
                // 将所选mySpinner 的值带入myTextView 中   

            	if(spinner3.getSelectedItemPosition()==0){
            		point1="00";
            	}
            	else if(spinner3.getSelectedItemPosition()==1){
            		point1="01";
            	}
            	else if(spinner3.getSelectedItemPosition()==2){
            		point1="02";
            	}
            	else if(spinner3.getSelectedItemPosition()==3){
            		point1="03";
            	}
            	else if(spinner3.getSelectedItemPosition()==4){
            		point1="04";
            	}
            	else if(spinner3.getSelectedItemPosition()==5){
            		point1="05";
            	}
            	else if(spinner3.getSelectedItemPosition()==6){
            		point1="06";
            	}
            	else if(spinner3.getSelectedItemPosition()==7){
            		point1="07";
            	}
            	else if(spinner3.getSelectedItemPosition()==8){
            		point1="08";
            	}
            	else if(spinner3.getSelectedItemPosition()==9){
            		point1="09";
            	}
            	else if(spinner3.getSelectedItemPosition()==10){
            		point1="0a";
            	}
            	else if(spinner3.getSelectedItemPosition()==11){
            		point1="0b";
            	}
            	else if(spinner3.getSelectedItemPosition()==12){
            		point1="0c";
            	}
              	else if(spinner2.getSelectedItemPosition()==13){
            		point="0d";
            	}
            	else if(spinner2.getSelectedItemPosition()==14){
            		point="0e";
            	}
            	else if(spinner2.getSelectedItemPosition()==15){
            		point="0f";
            	}
            	else if(spinner2.getSelectedItemPosition()==16){
            		point="0010";
            	}
            	else if(spinner2.getSelectedItemPosition()==17){
            		point="0011";
            	}
            	else if(spinner2.getSelectedItemPosition()==18){
            		point="0012";
            	}
            	else if(spinner2.getSelectedItemPosition()==19){
            		point="0013";
            	}
            	else if(spinner2.getSelectedItemPosition()==20){
            		point="0014";
            	}
            	else if(spinner2.getSelectedItemPosition()==21){
            		point="0015";
            	}
            	else if(spinner2.getSelectedItemPosition()==22){
            		point="0016";
            	}
            	else if(spinner2.getSelectedItemPosition()==23){
            		point="0017";
            	}
            	else if(spinner2.getSelectedItemPosition()==24){
            		point="0018";
            	}
            	else if(spinner2.getSelectedItemPosition()==25){
            		point="0019";
            	}
            	else if(spinner2.getSelectedItemPosition()==26){
            		point="001a";
            	}
            }  

            public void onNothingSelected(AdapterView<?> arg0) {  
                // TODO Auto-generated method stub  
              
            }  
        });
		spinner4 = (Spinner) findViewById(R.id.spinner4);
		spinner4.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件  
            public void onItemSelected(AdapterView<?> arg0, View arg1,  
                    int arg2, long arg3) {  
                // TODO Auto-generated method stub  
                // 将所选mySpinner 的值带入myTextView 中   

            	if(spinner4.getSelectedItemPosition()==0){
            		point2="00";
            	}
            	else if(spinner4.getSelectedItemPosition()==1){
            		point2="01";
            	}
            	else if(spinner4.getSelectedItemPosition()==2){
            		point2="02";
            	}
            	else if(spinner4.getSelectedItemPosition()==3){
            		point2="03";
            	}
            	else if(spinner4.getSelectedItemPosition()==4){
            		point2="04";
            	}
            	else if(spinner4.getSelectedItemPosition()==5){
            		point2="05";
            	}
            	else if(spinner4.getSelectedItemPosition()==6){
            		point2="06";
            	}
            	else if(spinner4.getSelectedItemPosition()==7){
            		point2="07";
            	}
            	else if(spinner4.getSelectedItemPosition()==8){
            		point2="08";
            	}
            	else if(spinner4.getSelectedItemPosition()==9){
            		point2="09";
            	}
            	else if(spinner4.getSelectedItemPosition()==10){
            		point2="0a";
            	}
            	else if(spinner4.getSelectedItemPosition()==11){
            		point2="0b";
            	}
            	else if(spinner4.getSelectedItemPosition()==12){
            		point2="0c";
            	}
              	else if(spinner2.getSelectedItemPosition()==13){
            		point="0d";
            	}
            	else if(spinner2.getSelectedItemPosition()==14){
            		point="0e";
            	}
            	else if(spinner2.getSelectedItemPosition()==15){
            		point="0f";
            	}
            	else if(spinner2.getSelectedItemPosition()==16){
            		point="0010";
            	}
            	else if(spinner2.getSelectedItemPosition()==17){
            		point="0011";
            	}
            	else if(spinner2.getSelectedItemPosition()==18){
            		point="0012";
            	}
            	else if(spinner2.getSelectedItemPosition()==19){
            		point="0013";
            	}
            	else if(spinner2.getSelectedItemPosition()==20){
            		point="0014";
            	}
            	else if(spinner2.getSelectedItemPosition()==21){
            		point="0015";
            	}
            	else if(spinner2.getSelectedItemPosition()==22){
            		point="0016";
            	}
            	else if(spinner2.getSelectedItemPosition()==23){
            		point="0017";
            	}
            	else if(spinner2.getSelectedItemPosition()==24){
            		point="0018";
            	}
            	else if(spinner2.getSelectedItemPosition()==25){
            		point="0019";
            	}
            	else if(spinner2.getSelectedItemPosition()==26){
            		point="001a";
            	}
            	
            }  

            public void onNothingSelected(AdapterView<?> arg0) {  
                // TODO Auto-generated method stub  
              
            }  
        });
		spinner5 = (Spinner) findViewById(R.id.spinner5);
		spinner5.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件  
            public void onItemSelected(AdapterView<?> arg0, View arg1,  
                    int arg2, long arg3) {  
                // TODO Auto-generated method stub  
                // 将所选mySpinner 的值带入myTextView 中   
           int hex = spinner5.getSelectedItemPosition()+1;
           String hex1=Integer.toHexString(hex);
            	if(spinner5.getSelectedItemPosition()<15)
            	{
            		speed="0"+hex1;
            	}
            	else
            	{
            		speed=hex1;
            	}
  
            	
        	}  

            public void onNothingSelected(AdapterView<?> arg0) {  
                // TODO Auto-generated method stub  
              
            }  
        });
		getActionBar().setTitle(mDeviceName);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
		bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
		if (mBluetoothLeService != null) {
			final boolean result = mBluetoothLeService.connect(mDeviceAddress);
			Log.d(TAG, "Connect request result=" + result);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mGattUpdateReceiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mServiceConnection);
		mBluetoothLeService = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gatt_services, menu);
		if (mConnected) {
			menu.findItem(R.id.menu_connect).setVisible(false);
			menu.findItem(R.id.menu_disconnect).setVisible(true);
		} else {
			menu.findItem(R.id.menu_connect).setVisible(true);
			menu.findItem(R.id.menu_disconnect).setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_connect:
			mBluetoothLeService.connect(mDeviceAddress);
			return true;
		case R.id.menu_disconnect:
			mBluetoothLeService.disconnect();
			return true;
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void updateConnectionState(final int resourceId) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mConnectionState.setText(resourceId);
			}
		});
	}

	private void displayData(String data) {
		if (data != null) {
			mDataField.append(data+"\n");
		}
	}
	//这是一个进制转化的方法，输入String，返回 byte型数组
	   public static byte[] hexStringToBytes(String hexString) {
	        if (hexString == null || hexString.equals("")) {
	            return null;
	        }
	        hexString = hexString.toUpperCase();
	        int length = hexString.length() / 2;
	        char[] hexChars = hexString.toCharArray();
	        byte[] d = new byte[length];
	        for (int i = 0; i < length; i++) {
	            int pos = i * 2;
	            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	            
	        }
	        return d;
	    }
	    private static byte charToByte(char c) {
	        return (byte) "0123456789ABCDEF".indexOf(c);
	    }

	// Demonstrates how to iterate through the supported GATT
	// Services/Characteristics.
	// In this sample, we populate the data structure that is bound to the
	// ExpandableListView
	// on the UI.


	private static IntentFilter makeGattUpdateIntentFilter() {
		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
		intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
		intentFilter
				.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
		intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
		return intentFilter;
	}
}

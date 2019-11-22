package com.prolific.pl2303hxdsimpletest;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import tw.com.prolific.driver.pl2303.PL2303Driver;
import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class PL2303HXDSimpleTest extends Activity {

	private static final boolean SHOW_DEBUG = true;
	
	PL2303Driver mSerial;

	String TAG = "PL2303HXD_APLog";

	private Button btBrodecastLighting;
	private Button btSlaveLighting;
	private Button btBrodecastOff;
	private Button btSlaveOff;
	private Button btclear;
	private EditText etWrite;
	private Button btRead;
	private EditText etRead;
	private Timer timer;
	private ToggleButton button2;
	private Spinner spinner;
	private Spinner spinner2;
	private PL2303Driver.BaudRate mBaudrate = PL2303Driver.BaudRate.B9600;
	private byte slave;
	private byte color;

	private static final String ACTION_USB_PERMISSION = "com.prolific.pl2303hxdsimpletest.USB_PERMISSION";

	public int PL2303HXD_BaudRate;
	public String PL2303HXD_BaudRate_str="B4800";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "Enter onCreate");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pl2303_hxdsimple_test);



		Button mButton01 = (Button)findViewById(R.id.button1);
		mButton01.setOnClickListener(new Button.OnClickListener() {		
			public void onClick(View v) {
				openUsbSerial();
			}
		});

		btBrodecastLighting= (Button) findViewById(R.id.button2);   
		btSlaveLighting= (Button) findViewById(R.id.button4);   
		btBrodecastOff= (Button) findViewById(R.id.button5);   
		btSlaveOff= (Button) findViewById(R.id.button6);   
		btclear= (Button) findViewById(R.id.buttonclear);   
		etWrite = (EditText) findViewById(R.id.editText1);
		btRead = (Button) findViewById(R.id.button3);   
		etRead = (EditText) findViewById(R.id.editText2);
		button2 = (ToggleButton)findViewById(R.id.toggleButton1);
		spinner = (Spinner)findViewById(R.id.spinner1);
		spinner2 = (Spinner)findViewById(R.id.spinner2);
		btBrodecastLighting.setOnClickListener(new Button.OnClickListener() {		
			public void onClick(View v) {
				switch(spinner2.getSelectedItem().toString()){
			    case "红" :
			    	color = 1;
			    	break; 
			    case "蓝" :
			    	color = 2;
				       break; 
			    case "绿" :
			    	color = 3;
				       break; 
			    case "粉红" :
			    	color = 4;
				       break; 
			    case "黄" :
			    	color = 5;
				       break; 			    
			}
				byte[] orders = {-2,6,-2,color,14,-1,45,69};
				writeDataToSerial(orders);
			}
		});
		btSlaveLighting.setOnClickListener(new Button.OnClickListener() {		
			public void onClick(View v) {	
				switch(spinner.getSelectedItem().toString()){
			    case "1" :
			       slave = 1;
			       break; 
			    case "2" :
				       slave = 2;
				       break; 
			    case "3" :
				       slave = 3;
				       break; 
			    case "4" :
				       slave = 4;
				       break; 
			    case "5" :
				       slave = 5;
				       break; 
			    case "6" :
				       slave = 6;
				       break; 
			    case "7" :
				       slave = 7;
				       break; 
			    case "8" :
				       slave = 8;
				       break; 
			    case "9" :
				       slave = 9;
				       break; 
			    case "10" :
				       slave = 10;
				       break; 
			    case "11" :
				       slave = 11;
				       break; 
			    case "12" :
				       slave = 12;
				       break; 
			    case "13" :
				       slave = 13;
				       break; 
			    case "14" :
				       slave = 14;
				       break; 
			    case "15" :
				       slave = 15;
				       break; 
			}
				switch(spinner2.getSelectedItem().toString()){
			    case "红" :
			    	color = 1;
			    	break; 
			    case "蓝" :
			    	color = 2;
				       break; 
			    case "绿" :
			    	color = 3;
				       break; 
			    case "粉红" :
			    	color = 4;
				       break; 
			    case "黄" :
			    	color = 5;
				       break; 			    
			}
				byte[] orders = {-2,6,-2,color,14,slave,45,69};
				writeDataToSerial(orders);
			}
		});
		btclear.setOnClickListener(new Button.OnClickListener() {		
			public void onClick(View v) {
				etRead.setText("");
			}
		});
		btBrodecastOff.setOnClickListener(new Button.OnClickListener() {		
			public void onClick(View v) {
				byte[] orders = {-2,6,-2,0,16,-1,45,69};
				writeDataToSerial(orders);
			}
		});
		btSlaveOff.setOnClickListener(new Button.OnClickListener() {		
			public void onClick(View v) {
				switch(spinner.getSelectedItem().toString()){
			    case "1" :
			       slave = 1;
			       break; 
			    case "2" :
				       slave = 2;
				       break; 
			    case "3" :
				       slave = 3;
				       break; 
			    case "4" :
				       slave = 4;
				       break; 
			    case "5" :
				       slave = 5;
				       break; 
			    case "6" :
				       slave = 6;
				       break; 
			    case "7" :
				       slave = 7;
				       break; 
			    case "8" :
				       slave = 8;
				       break; 
			    case "9" :
				       slave = 9;
				       break; 
			    case "10" :
				       slave = 10;
				       break; 
			    case "11" :
				       slave = 11;
				       break; 
			    case "12" :
				       slave = 12;
				       break; 
			    case "13" :
				       slave = 13;
				       break; 
			    case "14" :
				       slave = 14;
				       break; 
			    case "15" :
				       slave = 15;
				       break; 
			}
				byte[] orders = {-2,6,-2,0,16,slave,45,69};
				writeDataToSerial(orders);
			}
		});

		btRead.setOnClickListener(new Button.OnClickListener() {		
			public void onClick(View v) {
				    	readDataFromSerial(); 
			}
		});
		button2.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			 @Override  
			 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { 
				 Message message = new Message();
	             if(isChecked){  
	            	 timer = new Timer();  
	            	 btRead.setEnabled(false);
	            	
	     	    final Handler handler = new Handler(){  
	    	        public void handleMessage(Message msg) {  
	    	        	
	    	            switch (msg.what) {      
	    	            case 1:      
	    	                btRead.performClick();  
	    	                break;      
	    	            }      
	    	            super.handleMessage(msg);  
	    	        }  
	    	           
	    	    };
	    	    TimerTask task = new TimerTask(){  
	    	   
	    	        public void run() {  
	    	            Message message = new Message();      
	    	            message.what = 1;      
	    	            handler.sendMessage(message);    
	    	        }  
	    	           
	    	    }; 
	    	    timer.schedule(task,0, 1*1000);
	                
	             }else{  
	            	 timer.cancel();
	            	 timer=null;
	            	 btRead.setEnabled(true);
	             }  
	         }  
	     });  


	 
		// get service
		mSerial = new PL2303Driver((UsbManager) getSystemService(Context.USB_SERVICE),
				this, ACTION_USB_PERMISSION); 

		// check USB host function.
		if (!mSerial.PL2303USBFeatureSupported()) {

			Toast.makeText(this, "No Support USB host API", Toast.LENGTH_SHORT)
			.show();

			Log.d(TAG, "No Support USB host API");

			mSerial = null;

		}

		Log.d(TAG, "Leave onCreate");
	}//onCreate

	protected void onStop() {
		Log.d(TAG, "Enter onStop");
		super.onStop();        
		Log.d(TAG, "Leave onStop");
	}    

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Enter onDestroy");   

		if(mSerial!=null) {
			mSerial.end();
			mSerial = null;
		}    	

		super.onDestroy();        
		Log.d(TAG, "Leave onDestroy");
	}    

	public void onStart() {
		Log.d(TAG, "Enter onStart");
		super.onStart();
		Log.d(TAG, "Leave onStart");
	}

	public void onResume() {
		Log.d(TAG, "Enter onResume"); 
		super.onResume();
		String action =  getIntent().getAction();
		Log.d(TAG, "onResume:"+action);

		//if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action))        
		if(!mSerial.isConnected()) {
			if (SHOW_DEBUG) {
				Log.d(TAG, "New instance : " + mSerial);
			}

			if( !mSerial.enumerate() ) {

				Toast.makeText(this, "no more devices found", Toast.LENGTH_SHORT).show();     
				return;
			} else {
				Log.d(TAG, "onResume:enumerate succeeded!");
			}    		 
		}//if isConnected  
		Toast.makeText(this, "attached", Toast.LENGTH_SHORT).show();

		Log.d(TAG, "Leave onResume"); 
	}        

	private void openUsbSerial() {
		Log.d(TAG, "Enter  openUsbSerial");


		if(null==mSerial)
			return;   	 

		if (mSerial.isConnected()) {
			if (SHOW_DEBUG) {
				Log.d(TAG, "openUsbSerial : isConnected ");
			}
		
				mBaudrate = PL2303Driver.BaudRate.B9600;
						
			// if (!mSerial.InitByBaudRate(mBaudrate)) {
				if (!mSerial.InitByBaudRate(mBaudrate,700)) {
					if(!mSerial.PL2303Device_IsHasPermission()) {
						Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();		
					}

					if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
						Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
					}
				} else {        	
					
						Toast.makeText(this, "connected : " , Toast.LENGTH_SHORT).show(); 	
					
				}
		}//isConnected

		Log.d(TAG, "Leave openUsbSerial");
	}//openUsbSerial


	private void readDataFromSerial() {

		int len;
		// byte[] rbuf = new byte[4096];
		byte[] rbuf = new byte[20];
		final StringBuffer sbHex=new StringBuffer();

		Log.d(TAG, "Enter readDataFromSerial");

		if(null==mSerial)
			return;        

		if(!mSerial.isConnected()) 
			return;

		len = mSerial.read(rbuf);
		if(len<0) {
			Log.d(TAG, "Fail to bulkTransfer(read data)");
			return;
		}

		if (len > 0) {        	
			if (SHOW_DEBUG) {
				Log.d(TAG, "read len : " + len);
			}                
			//rbuf[len] = 0;		
			for (int j = 0; j < len; j++) {            	   
				//String temp=Integer.toHexString(rbuf[j]&0x000000FF);
				//Log.i(TAG, "str_rbuf["+j+"]="+temp);
				//int decimal = Integer.parseInt(temp, 16);
				//Log.i(TAG, "dec["+j+"]="+decimal);
				//sbHex.append((char)decimal);
				//sbHex.append(temp);
				if(rbuf[j]<16&rbuf[j]>=0){
					sbHex.append("0"+Integer.toHexString(rbuf[j])+" "); 
				}
				else if(rbuf[j]>=16){
					sbHex.append(Integer.toHexString(rbuf[j])+" "); 
				}
				else{
					sbHex.append(Integer.toHexString(rbuf[j]).substring(6)+" "); 
				}
	     	}		
					etRead.append(sbHex.toString()+";"+"\n");

			Toast.makeText(this, "len="+len, Toast.LENGTH_SHORT).show();
		}
		
		else {     	
			if (SHOW_DEBUG) {
				Log.d(TAG, "read len : 0 ");
			}
		
			return;
		}

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Log.d(TAG, "Leave readDataFromSerial");	
	}//readDataFromSerial

	private void writeDataToSerial(byte[]test) {

		Log.d(TAG, "Enter writeDataToSerial");

		if(null==mSerial)
			return;

		if(!mSerial.isConnected()) 
			return;

		String strWrite = etWrite.getText().toString();
		/*
        //strWrite="012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
       // strWrite = changeLinefeedcode(strWrite);
         strWrite="012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
         if (SHOW_DEBUG) {
            Log.d(TAG, "PL2303Driver Write(" + strWrite.length() + ") : " + strWrite);
        }
        int res = mSerial.write(strWrite.getBytes(), strWrite.length());
		if( res<0 ) {
			Log.d(TAG, "setup: fail to controlTransfer: "+ res);
			return;
		} 

		Toast.makeText(this, "Write length: "+strWrite.length()+" bytes", Toast.LENGTH_SHORT).show();  
		 */
		// test data: 600 byte
		//strWrite="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		if (SHOW_DEBUG) {
			Log.d(TAG, "PL2303Driver Write 2(" + strWrite.length() + ") : " + strWrite);
		}
		
		int res = mSerial.write(test, 8);
		if( res<0 ) {
			Log.d(TAG, "setup2: fail to controlTransfer: "+ res);
			return;
		} 

		Toast.makeText(this, "Write length: "+strWrite.length()+" bytes", Toast.LENGTH_SHORT).show(); 

		Log.d(TAG, "Leave writeDataToSerial");
	}//writeDataToSerial
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
	/**  
	 * Convert char to byte  
	 * @param c char  
	 * @return byte  
	 */  
	 private static byte charToByte(char c) {   
	    return (byte) "0123456789ABCDEF".indexOf(c);   
	}  
}

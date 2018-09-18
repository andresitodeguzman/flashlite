package com.andresitodeguzman.flashlite;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.content.pm.*;
import android.hardware.*;
import android.view.View.*;
import android.view.*;
import android.widget.CompoundButton.*;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		final Context context = getApplicationContext();
		
		final ToggleButton toggleButton = findViewById(R.id.toggleButton);
		
		Boolean hasFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		
		if(hasFlash){
			
			final Camera cam = Camera.open();
			Camera.Parameters p = cam.getParameters();
			p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			cam.setParameters(p);
			
			toggleButton.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					if(toggleButton.isChecked()){
						Toast.makeText(context,"on",Toast.LENGTH_LONG).show();
						cam.startPreview();
					} else {
						Toast.makeText(context,"off",Toast.LENGTH_LONG).show();
						cam.stopPreview();
						cam.release();
					}
				}
			});
			
		} else {
			Toast.makeText(context,"Sorry, your phone doesn't have flash",Toast.LENGTH_LONG);
		}
		
    }
}

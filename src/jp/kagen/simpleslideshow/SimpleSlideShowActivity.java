package jp.kagen.simpleslideshow;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class SimpleSlideShowActivity extends Activity {
	/** Called when the activity is first created. */
	Spinner spinnerFile;
	
	int currentPosition = 0;
	
	int interval = 3000;
	
	Runnable runnableChangeImage;
	
	Handler handler = new Handler();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ImageView imageViewMain =
		// (ImageView)findViewById(R.id.imageViewMain);
		// imageViewMain.setImageResource(R.drawable.sample);

		spinnerFile = (Spinner) findViewById(R.id.spinnerFile);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);

		File directory = Environment.getExternalStorageDirectory();

		File[] files = directory.listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(".jpg")) {
				adapter.add(files[i].getPath());
			}
		}

		spinnerFile.setAdapter(adapter);
		
		spinnerFile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Log.d("Spinner", "Position" + arg2);
				
				showImageAtPosition(arg2);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				Log.d("Spinner", "Position" + arg0);
			}
		});
		
		runnableChangeImage = new Runnable() {
			
			@Override
			public void run() {
				currentPosition++;
				
				if (currentPosition == spinnerFile.getCount()) {
					currentPosition = 0;
				}
				
				showImageAtPosition(currentPosition);
			}
		};
		
		Thread threadTimer = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(interval);
					} catch (Exception e) {}
					
					handler.post(runnableChangeImage);
				}
			}
		};
		
		threadTimer.start();
	}

	public void showImage(View view) {
		
		ImageView imageViewMain = (ImageView) findViewById(R.id.imageViewMain);

		if (view.getId() == R.id.buttonShow) {
			imageViewMain.setImageResource(R.drawable.sample);
		} else {
			Bitmap bitmap = BitmapFactory
					.decodeFile("/mnt/sdcard/sample_sd.jpg");
			imageViewMain.setImageBitmap(bitmap);
		}
	}
	
	private void showImageAtPosition(int position) {
		String selectedFile = (String)spinnerFile.getItemAtPosition(position);
		ImageView imageViewMain = (ImageView)findViewById(R.id.imageViewMain);
		
		Bitmap bitmap = BitmapFactory.decodeFile(selectedFile);
		imageViewMain.setImageBitmap(bitmap);
	}
}
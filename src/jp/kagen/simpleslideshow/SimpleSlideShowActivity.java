package jp.kagen.simpleslideshow;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class SimpleSlideShowActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ImageView imageViewMain =
		// (ImageView)findViewById(R.id.imageViewMain);
		// imageViewMain.setImageResource(R.drawable.sample);

		Spinner spinnerFile = (Spinner) findViewById(R.id.spinnerFile);
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
}
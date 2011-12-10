package jp.kagen.simpleslideshow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SimpleSlideShowActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ImageView imageViewMain =
		// (ImageView)findViewById(R.id.imageViewMain);
		// imageViewMain.setImageResource(R.drawable.sample);
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
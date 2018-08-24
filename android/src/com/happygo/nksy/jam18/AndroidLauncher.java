package com.happygo.nksy.jam18;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements IPlatformService {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Main(this), config);
	}

	@Override
	public void share() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Icebergs - Color Scheme");
		intent.putExtra(Intent.EXTRA_TEXT,
				Html.fromHtml(new StringBuilder()
					.append("$color-0: <font color='#").append(Main.leadColor.toString()).append("'>").append('#').append(Main.leadColor.toString()).append("</font><br>")
					.append("$color-1: <font color='#").append(Main.darkerColor.toString()).append("'>").append('#').append(Main.darkerColor.toString()).append("</font><br>")
					.append("$color-2: <font color='#").append(Main.lighterColor.toString()).append("'>").append('#').append(Main.lighterColor.toString()).append("</font><br>")
					.append("$color-3: <font color='#").append(Main.waterColor.toString()).append("'>").append('#').append(Main.waterColor.toString()).append("</font><br>")
					.toString()
				));
		startActivity(Intent.createChooser(intent, "Share Colors"));
	}
}

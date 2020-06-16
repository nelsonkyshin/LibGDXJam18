package com.happygo.nksy.jam18;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.SignInAccount;

public class AndroidLauncher extends AndroidApplication implements IPlatformService {

    public static GoogleSignInAccount SignInAccount;
    private static int SIGN_IN_RESULT_CODE = 69;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Main(this), config);
	}

	@Override
	public void SignInGoogleGames() {
	    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null)
            return;

        // otherwise must sign in manually
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestIdToken("989109665227-juk034devss96o9ol0b7k4vk9dontbe7.apps.googleusercontent.com")
				.build();
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this, options);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, SIGN_IN_RESULT_CODE);
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_RESULT_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                SignInAccount = result.getSignInAccount();
            }
            else {
                System.out.println(result.getStatus().getStatusMessage());
            }
        }
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

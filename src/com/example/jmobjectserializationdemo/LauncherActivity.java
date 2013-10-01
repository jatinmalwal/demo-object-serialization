package com.example.jmobjectserializationdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LauncherActivity extends Activity {

	Mario mario;
	String root;
	private File myDir;
	private File file;
	public static final String FILE_NAME = "mario.obj"; // File name could be
														// anything

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		mario = new Mario();
		setText();

		root = Environment.getExternalStorageDirectory().toString();
		// root = Environment.getRootDirectory().toString(); //For getting the
		// root path
		Toast.makeText(this, root, Toast.LENGTH_SHORT).show();
		// myDir = new File(root + "/.mario"); //Making hidden folder
		myDir = new File(root + "/mario");
		myDir.mkdirs();

		file = new File(myDir, FILE_NAME);

		// Deleting the file
		// if (file.exists()) {
		// file.delete();
		// }

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonS:
			mario.setName("Mario");
			mario.setState(3);
			mario.setIsPowerUpActivated(true);
			mario.setToatlLife(4);
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				// FileOutputStream fileOutputStream =
				// this.openFileOutput("Mario",Context.MODE_PRIVATE);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(mario);
				objectOutputStream.close();
				Toast.makeText(this, "Serialized", Toast.LENGTH_SHORT).show();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.buttonD:

			try {
				// FileInputStream fileInputStream =
				// this.openFileInput("Mario");
				FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

				mario = (Mario) objectInputStream.readObject();
				objectInputStream.close();
				Toast.makeText(this, "Deserialized", Toast.LENGTH_SHORT).show();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setText();

			break;

		}
	}

	private void setText() {
		TextView tv = ((TextView) findViewById(R.id.tv));
		tv.setText("Name : " + mario.getName() + "\nTotalLife : " + mario.getToatlLife() + "\nisPowerUpActivated : "
				+ mario.getIsPowerUpActivated() + "\nState : " + mario.getState());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}

}

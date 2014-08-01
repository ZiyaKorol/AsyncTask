package com.sems.asynctask;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private TextView tvSonuc;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Button btnStart = (Button) findViewById(R.id.btnBasla);
		tvSonuc=(TextView) findViewById(R.id.tvSonuc);
		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ArkaPlan islem=new ArkaPlan();
				islem.execute((Void)null);
			}
		});
	}

	public class ArkaPlan extends AsyncTask<Void, Integer, Integer> {

		
		public Integer sayi = null;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setMax(100);
			progressDialog.setProgress(0);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.show();
		}

		@Override
		protected Integer doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			for (int i = 0; i < 101; i++) {
				
				try{
					publishProgress(i);
					Thread.sleep(100);
					sayi=i;
				}
				
				catch(InterruptedException e){
					
				}
			}

			return sayi;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			Toast.makeText(getApplicationContext(), "Sonuç : "+result, Toast.LENGTH_LONG).show();
			tvSonuc.setText(tvSonuc.getText()+" "+result);
			
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			Integer gelen=values[0];
			progressDialog.setProgress(gelen);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			progressDialog.dismiss();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

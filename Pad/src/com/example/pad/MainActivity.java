
package com.example.pad;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int nb = 9;
	private Button[] tabB = new Button[nb];
	private RadioGroup[] tabRb = new RadioGroup[nb];
	private int[] refSon = new int[nb];
	private MediaPlayer[] mp = new MediaPlayer[nb];
	private boolean edit  = false;

	public final static String EXTRA_MESSAGE ="com.ltm.ltmactionbar.MESSAGE";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initPad();

		for(int x = 0;x<nb;x++){
			mp[x] = new MediaPlayer();
		}

		assigner();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menupad, menu);


		MenuItem editr = menu.getItem(1);
		editr.setOnMenuItemClickListener(new OnMenuItemClickListener() {


			@Override
			public boolean onMenuItemClick(MenuItem item) {
				if(edit == false){
					edit = true;
					Toast.makeText(MainActivity.this, "Choisir bouton", Toast.LENGTH_SHORT).show();
					for(int x = 0;x<nb;x++){
						if(mp[x].isPlaying()){
							mp[x].pause();
						}
					}	
				}else{
					edit = false;
					Toast.makeText(MainActivity.this, "edit = false", Toast.LENGTH_SHORT).show();
					for(int x = 0;x<nb;x++){
						if(mp[x].isPlaying()){
							mp[x].pause();
						}
					}	
				}
				return true;
			}
		});	

		MenuItem stop = menu.getItem(0);
		stop.setOnMenuItemClickListener(new OnMenuItemClickListener() {
	
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(MainActivity.this, "Et je coupe le son ...", Toast.LENGTH_SHORT).show();	
				for(int x = 0;x<nb;x++){
					if(mp[x].isPlaying()){
						mp[x].pause();
					}
				}	
				return true;
			}
		});

		
		MenuItem apropos = menu.getItem(2);
		apropos.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent intent = new Intent(MainActivity.this, Apropos.class);
				startActivity(intent);
				
				return true;
			}
			
		});
		

		return true;
	}

	
	
	private void initPad() {
		tabB[0] = (Button)findViewById(R.id.Button1);
		tabB[1] = (Button)findViewById(R.id.Button2);
		tabB[2] = (Button)findViewById(R.id.Button3);
		tabB[3] = (Button)findViewById(R.id.Button4);
		tabB[4] = (Button)findViewById(R.id.Button5);
		tabB[5] = (Button)findViewById(R.id.Button6);
		tabB[6] = (Button)findViewById(R.id.Button7);
		tabB[7] = (Button)findViewById(R.id.Button8);
		tabB[8] = (Button)findViewById(R.id.Button9);

		tabRb[0] = (RadioGroup)findViewById(R.id.RadioGroup1);
		tabRb[1] = (RadioGroup)findViewById(R.id.RadioGroup2);
		tabRb[2] = (RadioGroup)findViewById(R.id.RadioGroup3);
		tabRb[3] = (RadioGroup)findViewById(R.id.RadioGroup4);
		tabRb[4] = (RadioGroup)findViewById(R.id.RadioGroup5);
		tabRb[5] = (RadioGroup)findViewById(R.id.RadioGroup6);
		tabRb[6] = (RadioGroup)findViewById(R.id.RadioGroup7);
		tabRb[7] = (RadioGroup)findViewById(R.id.RadioGroup8);
		tabRb[8] = (RadioGroup)findViewById(R.id.RadioGroup9);

		refSon[0] = R.raw.caisseclaire;
		refSon[1] = R.raw.grossecaisse;
		refSon[2] = R.raw.drop;
		refSon[3] = R.raw.bim;
		refSon[4] = R.raw.zbre;
		refSon[5] = R.raw.coin;
		refSon[6] = R.raw.izi;
		refSon[7] = R.raw.up;
		refSon[8] = R.raw.zbra;
	}

	private void playSound( MediaPlayer mp,Boolean loop) {
		mp.setLooping(loop);
		mp.start();
	}

	public void assigner(){
		for(int x = 0;x<tabB.length;x++){
			final RadioGroup gr = tabRb[x];
			mp[x] = MediaPlayer.create(MainActivity.this, refSon[x]);
			final MediaPlayer mediaP = mp[x];


			tabB[x].setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					if(edit == false){
						
					
					boolean loop;
					int selectedId = gr.getCheckedRadioButtonId();
					RadioButton rb = (RadioButton) findViewById(selectedId);
					int idx = gr.indexOfChild(rb);

					if(idx == 0){
						loop = false;
					}else{
						loop = true;
					}
					playSound(mediaP,loop);	
					
					}else {
						Intent i1 = new Intent( MainActivity.this, Editer.class );
					    i1.putExtra(EXTRA_MESSAGE, "ID du bouton ici");
					    startActivityForResult(i1,0);
					}
				}			
			});
		}
	}
	

	//Méthode pour récup le son choisi de EDITER 
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	      if( resultCode==1 ) {
	        String s = data.getStringExtra(EXTRA_MESSAGE);
	        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	       
	      }
	      
	      super.onActivityResult(requestCode, resultCode, data);
	      edit=false;
	    }

	
}
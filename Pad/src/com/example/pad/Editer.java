package com.example.pad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Editer extends Activity{

	private EditText ed;
	private ListView lv1;
	private StableArrayAdapter adapter;
	static final String[] SONS = new String[] {

		"Afghanistan", "Albania", "Algeria", "American Samoa",
		"Andorra", "Angola", "Anguilla", "Antarctica",
		"Antigua and Barbuda", "Argentina", "Armenia", "Aruba",
		"Australia", "Austria", "Azerbaijan", "Bahrain",
		"Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
		"Benin", "Bermuda", "Bhutan", "Bolivia",
		"Bosnia and Herzegovina", "Botswana", "Bouvet Island",
		"Brazil", "British Indian Ocean Territory"
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.editer_activity);
		lv1 = (ListView) findViewById(R.id.list);
		lv1.setClickable(true);

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < SONS.length; ++i) {
			list.add(SONS[i]);
		}

		adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, list);
		lv1.setAdapter(adapter);


		lv1.setOnItemClickListener(new OnItemClickListener(){


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String item = (String) parent.getItemAtPosition(position);
				Intent i2 = new Intent();
				i2.putExtra(MainActivity.EXTRA_MESSAGE, item);
				Editer.this.setResult(1, i2);
				Editer.this.finish();


			}


		});


		ed=(EditText)findViewById(R.id.editText1);



		ed.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				Editer.this.adapter.getFilter().filter(cs);  
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {                       
			}
		});


	}


	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}



}

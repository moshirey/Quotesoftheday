package com.example.quotesoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class QuoteList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView quotelistview;
    ArrayAdapter Adapter;
    ArrayList<String> list0quoes;
    ArrayList<String> list1quoes;
    ArrayList<String> list2quoes;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private int positions;
    private String country;
    private int positionspclist;
    private AdView mAdView;
    Sqlite sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        quotelistview = findViewById(R.id.quotelistview);
        sqlite=new Sqlite(this);
        list0quoes = new ArrayList<>();
        list1quoes = new ArrayList<>();
        list2quoes = new ArrayList<>();
        Bundle inttentlistofquotes = getIntent().getExtras();
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (inttentlistofquotes != null) {
            list0quoes = (inttentlistofquotes.getStringArrayList("list0"));
            if (list0quoes!=null){
                // Create an ArrayAdapter from List
                ArrayAdapter<String> Adapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_list_item_1, list0quoes){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);

                        // Initialize a TextView for ListView each Item
                        TextView tv = (TextView) view.findViewById(android.R.id.text1);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.WHITE);

                        // Generate ListView Item using TextView
                        return view;
                    }
                };
               quotelistview.setDivider(new ColorDrawable(Color.GRAY));
                quotelistview.setDividerHeight(1);
                quotelistview.setAdapter(Adapter);
            }

        }



   quotelistview.setOnItemClickListener(QuoteList.this);
    }

    @Override
    protected void onResume() {
        registerForContextMenu(quotelistview);
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){

            case R.id.quotelistview:

                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) menuInfo;
                country = ((TextView) info.targetView).getText().toString();
                menu.setHeaderTitle(country);

                String [] actions = getResources().getStringArray(R.array.context_menu);
                for (int i=0;i<actions.length;i++){
                    menu.add(Menu.NONE, i, i, actions[i]);
                }
                break;

        }

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuItemIndex = item.getItemId();
        String [] menuItems = getResources().getStringArray(R.array.context_menu);
        String menuItemName = menuItems[menuItemIndex];

        switch (menuItemName) {

            case "COPY":
                String text;
                text = country;

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

                break;
            case "SAVE":
            Quote quote=new Quote(country);
            sqlite.addFavourate(quote);
                break;
        }

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        positionspclist=position;
        moveSpclist(list0quoes);


    }
    private void moveSpclist(ArrayList<String>spclist){
        if (spclist!=null){
            Intent dataquotes = new Intent(QuoteList.this,QuoteActivity.class);
            String spc=spclist.get(positionspclist);
            dataquotes.putExtra("spc",spc);
            dataquotes.putExtra("list",spclist);
            startActivity(dataquotes);

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(QuoteList.this,"backed",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}

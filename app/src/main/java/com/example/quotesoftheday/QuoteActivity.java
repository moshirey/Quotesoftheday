package com.example.quotesoftheday;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class QuoteActivity extends AppCompatActivity implements config {
    private InterstitialAd mInterstitialAd;
    TextView quoteview;
    Button btnnext, btnpreview,btnsaved;
    Stack<String>preview;
    boolean previewyes=false;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private int index;
    private String spc1;
    private ArrayList<String> list1;
    private int count=1;
    private AdView mAdView;
    private List<Quote> favlist;
Sqlite sqlite;
    private ArrayList<String> favouratequotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        list1=new ArrayList<>();
        quoteview = findViewById(R.id.quotetextview);
        btnnext=findViewById(R.id.btnnext);
        btnsaved=findViewById(R.id.btnsaved);
        btnpreview=findViewById(R.id.btnprevious);
        preview=new Stack<>();
        favouratequotes=new ArrayList<>();
        sqlite=new Sqlite(this);
        favlist=sqlite.getAllfavourates();
        if (favlist.size() > 0) {
            for (int i = 0; i <favlist.size(); i++) {
               Quote objquote=favlist.get(i);
                favouratequotes.add(objquote.getFavourate());

            }
        }
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        //incoming quotes
        Bundle extra=getIntent().getExtras();
        if (extra!=null){
           spc1= extra.get("spc").toString();
           list1=extra.getStringArrayList("list");
        }

         //RANDOMIZE THE QUOTES
        quoteview.setText(spc1);
        quoteview.setTypeface(quoteview.getTypeface(), Typeface.BOLD);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                if (count==showads) {

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();

                    }
                    count=0;
                }else {count++;}

                if (list1!=null){
                   addtotextview(list1);
                }

            }
        });

        btnsaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dataquotes = new Intent(QuoteActivity.this, Favourate_quotes.class);
                dataquotes.putExtra("list0", favouratequotes);
                startActivity(dataquotes);
            }
        });
        btnpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!previewyes){
                    preview.pop();
                    previewyes=true;
                }
                if (preview.size()>0&& previewyes){
                    quoteview.setText(preview.pop().toString());
                }else {Toast.makeText(getApplicationContext(), "press next for more quotes",
                        Toast.LENGTH_SHORT).show();}

            }
        });
        //end of next and previous
        //copyquotes
        quoteview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text;
                text = quoteview.getText().toString();

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }

    public void addtotextview(ArrayList<String>category1quotesList3){
        int numberofquotesforcat2=category1quotesList3.size();
        index=getrandomnumbers(numberofquotesforcat2-1);
        quoteview.setText(category1quotesList3.get(index));
        quoteview.setTypeface(quoteview.getTypeface(), Typeface.BOLD);
        preview.push(category1quotesList3.get(index));
    }
    //getting random numbers
    private int getrandomnumbers(int lenth) {
        return (int)( Math.random()*lenth+1);
    }
    public void quoteoncreate(ArrayList<String>mix){
        int numberofquotesforcat1=mix.size();
        index=getrandomnumbers(numberofquotesforcat1-1);
        quoteview.setText(mix.get(index));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(QuoteActivity.this,"backed",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}



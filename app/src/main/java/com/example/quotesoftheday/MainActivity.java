package com.example.quotesoftheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements config{
    private InterstitialAd mInterstitialAd;
    ListView CategoryListview;
    ArrayAdapter adapter;

    ArrayList<String>quotecat0;
    ArrayList<String>quotecat1;
    ArrayList<String>quoteList2;
    ArrayList<String>quoteList3;
    ArrayList<String>quoteList4;
    ArrayList<String>quoteList5;
    ArrayList<String>quoteList6;
    ArrayList<String>quoteList7;
    ArrayList<String>quoteList8;
    ArrayList<String>quoteList9;
    ArrayList<String>quoteList10;
    ArrayList<String>quoteList11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryListview=findViewById(R.id.CategoryListview);
        quotecat0=new ArrayList<>();
        quoteList2=new ArrayList<>();
        quotecat1=new ArrayList<>();
        quoteList3=new ArrayList<>();
        quoteList4=new ArrayList<>();
        quoteList5=new ArrayList<>();
        quoteList6=new ArrayList<>();
        quoteList7=new ArrayList<>();
        quoteList8=new ArrayList<>();
        quoteList9=new ArrayList<>();
        quoteList10=new ArrayList<>();
        quoteList11=new ArrayList<>();
        //all local quotes
        Resources resources = getResources();
        String[] List0 =resources.getStringArray(R.array.List0);
        String[] List1 =resources.getStringArray(R.array.List1);
        String[] List2 =resources.getStringArray(R.array.List2);
        String[] List3 =resources.getStringArray(R.array.List3);
        String[] List4 =resources.getStringArray(R.array.List4);
        String[] List5 =resources.getStringArray(R.array.List5);
        String[] List6 =resources.getStringArray(R.array.List6);
        String[] List7 =resources.getStringArray(R.array.List7);
        String[] List8 =resources.getStringArray(R.array.List8);
        String[] List9 =resources.getStringArray(R.array.List9);
        String[] List10 =resources.getStringArray(R.array.List10);
        String[] List11 =resources.getStringArray(R.array.List11);
        quoteCatergoryonemethod(List0,List1,List2,List3,List4,List5,List6,List7,List8,List9,List10,List11);

        final String[]names={No0,No1,No2,No3,No4,No5,No6,No7,No8,No9,No10,No11};
        // Create an ArrayAdapter from List
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, names){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                // Set the item text style to bold
                tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                // Generate ListView Item using TextView
                return view;
            }
        };
        CategoryListview.setAdapter(adapter);

        CategoryListview.setDivider(new ColorDrawable(Color.GRAY));
        CategoryListview.setDividerHeight(1);

        CategoryListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0 && categotyNO1==true){
                    movetoposition(quotecat0,"list0");
                }
                if(position==1){
                    movetoposition(quotecat1,"list0");
                }
                if(position==2) {
                   movetoposition(quoteList2,"list0");
                }
                if(position==3) {
                    movetoposition(quoteList3,"list0");
                }
                if(position==4) {
                    movetoposition(quoteList4,"list0");
                }
                if(position==5) {
                    movetoposition(quoteList5,"list0");
                }
                if(position==6) {
                    movetoposition(quoteList6,"list0");
                }
                if(position==7) {
                    movetoposition(quoteList7,"list0");
                }
                if(position==8) {
                    movetoposition(quoteList8,"list0");
                }
                if(position==9) {
                    movetoposition(quoteList9,"list0");
                }
                if(position==10) {
                    movetoposition(quoteList10,"list0");
                }
                if(position==11) {
                    movetoposition(quoteList11,"list0");
                }


            }
        });
    }
    // putting quotes INTO the database
    private void quoteCatergoryonemethod (String[]localcat1,String[]localcat2,String[]List2,String[]List3,String[]List4,String[]List5,String[]List6
            ,String[]List7,String[]List8,String[]List9,String[]List10,String[]List11){
        if (localcat1!=null && categotyNO1==true){
        for (int i = 0; i < localcat1.length; i++) {
            String qoutesStringloop =localcat1[i];

            quotecat0.add(qoutesStringloop);

        }}
        if (localcat2!=null) {
            for (int i = 0; i < localcat2.length; i++) {
                String qoutesStringloop = localcat2[i];

                quotecat1.add(qoutesStringloop);
            }
        }
        if (List2!=null){
        for (int i = 0; i < List2.length; i++) {
            String qoutesStringloop = List2[i];

            quoteList2.add(qoutesStringloop);
        }}
        if (List3!=null){
        for (int i = 0; i < List3.length; i++) {
            String qoutesStringloop = List3[i];

            quoteList3.add(qoutesStringloop);
        }}if (List4!=null){
        for (int i = 0; i < List4.length; i++) {
            String qoutesStringloop = List4[i];

            quoteList4.add(qoutesStringloop);
        }}
        if (List5!=null){
        for (int i = 0; i < List5.length; i++) {
            String qoutesStringloop = List5[i];

            quoteList5.add(qoutesStringloop);
        }}
        if (List6!=null){
        for (int i = 0; i < List6.length; i++) {
            String qoutesStringloop = List6[i];

            quoteList6.add(qoutesStringloop);
        }}
        if (List7!=null){
        for (int i = 0; i < List7.length; i++) {
            String qoutesStringloop = List7[i];

            quoteList7.add(qoutesStringloop);
        }}
        if (List8!=null){
        for (int i = 0; i < List8.length; i++) {
            String qoutesStringloop = List8[i];

            quoteList8.add(qoutesStringloop);
        }}
        if (List9!=null){
        for (int i = 0; i < List9.length; i++) {
            String qoutesStringloop = List9[i];

            quoteList9.add(qoutesStringloop);
        }}
        if (List10!=null){
        for (int i = 0; i < List10.length; i++) {
            String qoutesStringloop = List10[i];

            quoteList10.add(qoutesStringloop);
        }}
        if (List11!=null){
        for (int i = 0; i < List11.length; i++) {
            String qoutesStringloop = List11[i];

            quoteList11.add(qoutesStringloop);
        }}

    }

    private void movetoposition(ArrayList<String>quot,String list){
        if (quot!=null) {
            Intent dataquotes = new Intent(MainActivity.this, QuoteList.class);
            dataquotes.putExtra(list, quot);
            startActivity(dataquotes);
        }
    }




}

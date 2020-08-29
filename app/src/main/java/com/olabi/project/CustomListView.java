package com.olabi.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListView extends BaseAdapter {
    Context mContext;
    ArrayList<Book> books = new ArrayList<>();
    int lengthAsIndex;
    Dialog popup;
    TextView textViewPopUp;
    ImageView imageViewPopUp;

    public CustomListView(Context mContext, ArrayList<Book> books) {
        this.mContext = mContext;
        this.books = books;
        lengthAsIndex = books.size() - 1;
    }

    @Override
    public int getCount() {
        if(books.size()%2==0)
            return books.size()/2;
        else
            return (books.size()/2) + 1;



    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_custom_list_view, parent, false);
        }
        popup = new Dialog(view.getContext());
        popup.setContentView(R.layout.book_popup);
        popup.setCanceledOnTouchOutside(false);
        popup.setCancelable(true);
        final Book t = (Book) getItem(i);
        ImageButton Image = (ImageButton) view.findViewById(R.id.ImageButton1);
        ImageButton Image2 = (ImageButton) view.findViewById(R.id.ImageButton2);
        //first element of list view
        Image.setImageResource(t.getImage());
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewPopUp = popup.findViewById(R.id.title);
                imageViewPopUp = popup.findViewById(R.id.cover);
                textViewPopUp.setText(t.getTitle());
                imageViewPopUp.setImageResource(t.getImage());
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popup.show();
            }
        });
        //to make sure that not add two element of the same book when number
        //of book is odd




        //second element of list view
        Image2.setVisibility(View.VISIBLE);
        if (getItem(lengthAsIndex - i) != null){
            Image2.setImageResource(((Book) getItem(lengthAsIndex - i)).getImage());
        Image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewPopUp = popup.findViewById(R.id.title);
                imageViewPopUp = popup.findViewById(R.id.cover);
                textViewPopUp.setText(((Book) getItem(lengthAsIndex - i)).getTitle());
                imageViewPopUp.setImageResource(((Book) getItem(lengthAsIndex - i)).getImage());
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popup.show();
            }
        });

        }

        if(i==(lengthAsIndex-i) && i==books.size()/2) {
            System.out.println("************   "+i+"  len"+(lengthAsIndex-i));
            Image2.setVisibility(View.INVISIBLE);
        }
        return view;
    }


}
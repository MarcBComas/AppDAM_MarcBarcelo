package com.example.appdam;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView wordItemView;
        WordListAdapter mAdapter;
        public WordViewHolder(View itemView, WordListAdapter adapter) {
                super(itemView);
                wordItemView = (TextView) itemView.findViewById(R.id.word);
                this.mAdapter = adapter;
                itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                wordItemView.setText ("Clicked! "+ wordItemView.getText());
        }
}

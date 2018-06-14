package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Checking if convertView exist
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list, parent, false);
        }
        Article currentArticle = getItem(position);
        String artName = currentArticle.getaName();
        String artDate = currentArticle.getaDate();
        String artSection = currentArticle.getSection();
        String artAuthor = currentArticle.getmAuthor();
        String[] stringsTouse = {getContext().getResources().getString(R.string.published),
                getContext().getResources().getString(R.string.section),
                getContext().getResources().getString(R.string.written)};
        String[] separator = {getContext().getResources().getString(R.string.separator0),
                getContext().getResources().getString(R.string.separator1),
                getContext().getResources().getString(R.string.separator2)};
        TextView sectionView = (TextView) listItemView.findViewById(R.id.aSection);
        TextView titleView = (TextView) listItemView.findViewById(R.id.aTitle);
        titleView.setText(artName);
        TextView dateView = (TextView) listItemView.findViewById(R.id.aTime);
        if (artDate.contains(separator[0])) {
            String[] parts = artDate.split(separator[0]);
            String dayTime = parts[0];
            String hourTime = parts[1];
            if (hourTime.contains(separator[1])) {
                String[] partsofHour = hourTime.split(separator[1]);
                String hourConverted = partsofHour[0];
                dateView.setText(stringsTouse[0] + dayTime + " " + hourConverted);
            }
        } else {
            dateView.setText(artDate);
        }
        if (artAuthor.contains(separator[2])) {
            String[] locationRemover = artAuthor.split(separator[2]);
            sectionView.setText(stringsTouse[1] + artSection + stringsTouse[2] + locationRemover[0]);
        } else {
            sectionView.setText(stringsTouse[1] + artSection + stringsTouse[2] + artAuthor);
        }
        return listItemView;
    }
}

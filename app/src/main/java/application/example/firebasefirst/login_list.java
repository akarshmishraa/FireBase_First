package application.example.firebasefirst;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class login_list extends ArrayAdapter<Artist> {
Activity context;
List<Artist> arrayList;


    public login_list(Activity context,List<Artist> login_list) {                  //Inside <> a class is mentioned
        super(context,R.layout.list_layout,login_list);
        this.context = context;
        this.arrayList = login_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Artist artist= new Artist();
        LayoutInflater inflater= context.getLayoutInflater();
        View ListViewItem= inflater.inflate(R.layout.list_layout,null,true);
        TextView textView=(TextView) ListViewItem.findViewById(R.id.textView);
        TextView textView2=(TextView) ListViewItem.findViewById(R.id.textView2);

        artist=arrayList.get(position);
        textView.setText(artist.getEmail());
        textView2.setText(artist.getPassword());

        return ListViewItem;
    }
}

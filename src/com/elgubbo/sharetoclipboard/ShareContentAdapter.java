package com.elgubbo.sharetoclipboard;

import java.util.ArrayList;

import com.elgubbo.sharetoclipboarddatabase.ShareDataSource;
import com.elgubbo.sharetoclipboardonclicklisteners.OnItemClickCopyListener;
import com.elgubbo.sharetoclipboardonclicklisteners.OnItemDeleteClickListener;
import com.elgubbo.sharetoclipboardonclicklisteners.OnItemShareClickListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareContentAdapter extends ArrayAdapter<ShareContent> {
	private ArrayList<ShareContent> contents;
	private Context cont;
	private ShareDataSource datasource;

	public ShareContentAdapter(Context context, int textViewResourceId,
			ArrayList<ShareContent> contents, ShareDataSource ds) {
		super(context, textViewResourceId, contents);
		this.contents = contents;
		this.cont = context;
		this.datasource = ds;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_content, null);
		}

		ShareContent content = contents.get(position);
		if (content != null) {
			TextView description = (TextView) v.findViewById(R.id.description);
			TextView actualcontent = (TextView) v
					.findViewById(R.id.actualcontent);
			//Set the OnClickListener for the delete Button and the Share button
			ImageView delbtn = (ImageView) v.findViewById(R.id.deletebtn);
			delbtn.setOnClickListener(new OnItemDeleteClickListener(position, datasource, this));
			//Set the ClickListener for the share Button
			ImageView sharebtn = (ImageView) v.findViewById(R.id.sharebtn);
			sharebtn.setOnClickListener(new OnItemShareClickListener(position, this, cont));
			//Set the ClickListener for the copy button
			ImageView copybtn = (ImageView) v.findViewById(R.id.copybtn);
			copybtn.setOnClickListener(new OnItemClickCopyListener(this, position, cont));
			if (description != null) {
				description.setText(content.getDescription());
			}

			if (actualcontent != null) {
				actualcontent.setText("Link: " + content.getContent());
			}
		}
		return v;
	}
	

}
package com.elgubbo.sharetoclipboard;

import java.util.ArrayList;
import com.elgubbo.sharetoclipboard.db.ShareDataSource;
import com.elgubbo.sharetoclipboard.listeners.OnItemClickCopyListener;
import com.elgubbo.sharetoclipboard.listeners.OnItemDeleteClickListener;
import com.elgubbo.sharetoclipboard.listeners.OnItemShareClickListener;
import com.elgubbo.sharetoclipboard.listeners.OnItemTouchListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ShareContentAdapter extends ArrayAdapter<ShareContent> {
	private ArrayList<ShareContent> contents;
	private Context cont;
	private ShareDataSource datasource;
	View.OnTouchListener gestureListener;

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
		ViewHolder holder;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) cont
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_content, null);

			holder = new ViewHolder();
			holder.contentText = (TextView) v.findViewById(R.id.actualcontent);
			holder.descriptionText = (TextView) v
					.findViewById(R.id.description);
			holder.copybtn = (ImageView) v.findViewById(R.id.copybtn);
			holder.delbtn = (ImageView) v.findViewById(R.id.deletebtn);
			holder.sharebtn = (ImageView) v.findViewById(R.id.sharebtn);
			holder.flipper = (ViewFlipper) v.findViewById(R.id.flipper);
			v.setTag(holder);

		} else {
			// Get the ViewHolder back to get fast access to the TextView
			// and the ImageView.
			holder = (ViewHolder) v.getTag();
		}

		ShareContent content = contents.get(position);
		if (content != null) {
			// Set the OnClickListeners for the Buttons
			holder.delbtn.setOnClickListener(new OnItemDeleteClickListener(
					position, datasource, this));
			holder.sharebtn.setOnClickListener(new OnItemShareClickListener(
					position, this, cont));
			holder.copybtn.setOnClickListener(new OnItemClickCopyListener(this,
					position, cont));
			holder.descriptionText.setText(content.getDescription());
			holder.contentText.setText("Link: " + content.getContent());
			holder.flipper.setOnTouchListener(new OnItemTouchListener());
		}
		return v;
	}

	static class ViewHolder {
		ViewFlipper flipper;
		TextView descriptionText;
		TextView contentText;
		ImageView delbtn;
		ImageView sharebtn;
		ImageView copybtn;
	}

}
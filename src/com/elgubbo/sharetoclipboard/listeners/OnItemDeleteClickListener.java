package com.elgubbo.sharetoclipboard.listeners;

import com.elgubbo.sharetoclipboard.ShareContentAdapter;
import com.elgubbo.sharetoclipboard.db.ShareDataSource;

import android.view.View;
import android.view.View.OnClickListener;

/*
 * This handles the clicks on each "delete" button in the list
 */
public class OnItemDeleteClickListener implements OnClickListener{

	private int mPosition;
	private ShareDataSource datasource;
	private ShareContentAdapter shareContAdap;
    public OnItemDeleteClickListener(int position, ShareDataSource ds, ShareContentAdapter shareContentAdapter){
            this.mPosition = position;
            this.datasource = ds;
            this.shareContAdap = shareContentAdapter;
    }
	@Override
	public void onClick(View v) {
		datasource.deleteContent((shareContAdap.getItem(mPosition)));
		shareContAdap.remove(shareContAdap.getItem(mPosition));
		shareContAdap.notifyDataSetChanged();
	}
}

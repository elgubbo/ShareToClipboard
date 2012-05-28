package com.elgubbo.sharetoclipboard.onclicklisteners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.elgubbo.sharetoclipboard.ShareContent;
import com.elgubbo.sharetoclipboard.ShareContentAdapter;

/*
 * This handles the clicks on each "share" button in the list
 */
public class OnItemShareClickListener implements OnClickListener{

	private int mPosition;
	private ShareContentAdapter shareContAdap;
	private Context cont;

	public OnItemShareClickListener(int position, ShareContentAdapter sca, Context cont){
		this.mPosition = position;
		this.shareContAdap = sca;
		this.cont = cont;
	}

	@Override
	public void onClick(View v) {
		ShareContent sc = shareContAdap.getItem(mPosition);
		Intent i = new Intent(Intent.ACTION_SEND);
		i.putExtra(Intent.EXTRA_SUBJECT, sc.getDescription());
		i.putExtra(Intent.EXTRA_TEXT, sc.getContent());
		i.setType("text/*");
		cont.startActivity(Intent.createChooser(i, "Use Link in: "));
	}
	
	
	
}

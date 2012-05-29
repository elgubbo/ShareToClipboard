package com.elgubbo.sharetoclipboard.listeners;

import com.elgubbo.sharetoclipboard.ShareContentAdapter;

import android.content.Context;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/*
 * This handles the clicks on each "copy" button in the list
 */
public class OnItemClickCopyListener implements OnClickListener{

	
	private int mPosition;
	private ShareContentAdapter shareContAdap;
	private Context cont;
	public OnItemClickCopyListener(ShareContentAdapter sca, int position, Context cont){
		this.mPosition = position;
		this.shareContAdap = sca;
		this.cont = cont;
	}
	
	@Override
	public void onClick(View v) {
		ClipboardManager cm = (ClipboardManager) cont.getSystemService(Context.CLIPBOARD_SERVICE);
		cm.setText(shareContAdap.getItem(mPosition).getContent());
		// TODO add string resource
		Toast t = Toast.makeText(cont, "Added share to clipboard", 3000);
		t.show();
	}

}

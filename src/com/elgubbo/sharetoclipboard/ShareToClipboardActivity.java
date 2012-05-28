package com.elgubbo.sharetoclipboard;

import java.util.ArrayList;
import java.util.List;

import com.elgubbo.sharetoclipboard.db.ShareDataSource;

import greendroid.app.GDListActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Toast;

public class ShareToClipboardActivity extends GDListActivity {

	private ShareDataSource datasource;
	private static final int ACTION_BAR_EDIT = 0;
	private QuickActionWidget mBar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActionBarItem(Type.Edit, ACTION_BAR_EDIT);
		// create the datasource responsible for maintaining ShareContent
		// objects
		datasource = new ShareDataSource(this);
		datasource.open();
		// This part consumes the Share intent
		// Should be replaced by something like a "intentHandler"
		// TODO add class IntentHandler
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String action = intent.getAction();
		String content = null, description = null;
		if (Intent.ACTION_SEND.equals(action)) {
			if (extras.containsKey(Intent.EXTRA_TEXT)) {
				// Use contents of Bundle
				content = extras.getString(Intent.EXTRA_TEXT);
				if (extras.containsKey(Intent.EXTRA_SUBJECT)) {
					description = extras.getString(Intent.EXTRA_SUBJECT);
				} else
					description = content;
				if (content != null) {
					datasource.createContent(content, description, "text");
				}
			}
			// Copy current Link/Text/Whatever to clipboard
			ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			cm.setText(content);
			// TODO add string resource
			Toast t = Toast.makeText(this, "Added share to clipboard", 3000);
			t.show();
		}
		buildQuickActionsBar();
		//TODO add actionlistener

		// A list of all Contents is created
		List<ShareContent> values = datasource.getAllContents();
		// Show database elements in a ListView
		ShareContentAdapter adapter = new ShareContentAdapter(this,
				R.layout.list_content, (ArrayList<ShareContent>) values, datasource);
		setListAdapter(adapter);
	}

	@Override
	public int createLayout() {
		return R.layout.listlayout;
	}

	public void onShowBar(View v) {
		mBar.show(v);
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	public void buildQuickActionsBar() {
		mBar = new QuickActionBar(this);
		mBar.addQuickAction(new QuickAction(this,
				R.drawable.gd_action_bar_sort_by_size, R.string.gd_sort_by_size));
		mBar.addQuickAction(new QuickAction(this,
				R.drawable.gd_action_bar_settings, R.string.gd_settings));
	}

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
		switch (item.getItemId()) {
		case ACTION_BAR_EDIT:
			onShowBar(this.getActionBar().getItem(ACTION_BAR_EDIT)
					.getItemView());
			break;
		default:
			return super.onHandleActionBarItemClick(item, position);
		}
		return true;
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}
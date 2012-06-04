/*
 * Copyright 2012 Alexander Reichert

This file is part of ShareToClipboard.
ShareToClipboard is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
ShareToClipboard is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with Foobar. If not, see http://www.gnu.org/licenses/.
 */
package com.elgubbo.sharetoclipboard;

import greendroid.app.GDListActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;

import com.elgubbo.sharetoclipboard.db.ShareDataSource;
import com.elgubbo.sharetoclipboard.handlers.IntentHandler;

/**
 * The Class ShareToClipboardActivity is the Main Activity
 */
public class ShareToClipboardActivity extends GDListActivity {

	/** The datasource. */
	private ShareDataSource datasource;

	/** The Constant ACTION_BAR_EDIT. */
	private static final int ACTION_BAR_EDIT = 0;

	/** The m bar. */
	private QuickActionWidget mBar;

	/**
	 * Builds the quick actions bar.
	 */
	public void buildQuickActionsBar() {
		mBar = new QuickActionBar(this);
		mBar.addQuickAction(new QuickAction(this,
				R.drawable.gd_action_bar_sort_by_size, R.string.gd_sort_by_size));
		mBar.addQuickAction(new QuickAction(this,
				R.drawable.gd_action_bar_settings, R.string.gd_settings));
	}

	// @Override
	// public int createLayout() {
	// return R.layout.listlayout;
	// }

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            the saved instance state
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setBackgroundResource(R.id.backgroundifempty);
		addActionBarItem(Type.Edit, ACTION_BAR_EDIT);
		// create the datasource responsible for maintaining ShareContent
		// objects
		datasource = new ShareDataSource(this);
		datasource.open();
		// This part consumes the Share intent
		// Should be replaced by something like a "intentHandler"
		final IntentHandler ih = new IntentHandler(getIntent(), datasource,
				this, (ClipboardManager) getSystemService(CLIPBOARD_SERVICE));
		ih.handleIntent();

		buildQuickActionsBar();
		// TODO add actionlistener

		// A list of all Contents is created
		final List<ShareContent> values = datasource.getAllContents();
		// Show database elements in a ListView
		final ShareContentAdapter adapter = new ShareContentAdapter(this,
				R.layout.list_content, (ArrayList<ShareContent>) values,
				datasource);
		setListAdapter(adapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * greendroid.app.GDActivity#onHandleActionBarItemClick(greendroid.widget
	 * .ActionBarItem, int)
	 */
	@Override
	public boolean onHandleActionBarItemClick(final ActionBarItem item,
			final int position) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	/**
	 * On show bar.
	 * 
	 * @param v
	 *            the v
	 */
	public void onShowBar(final View v) {
		mBar.show(v);
	}
}
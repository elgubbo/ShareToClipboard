package com.elgubbo.sharetoclipboard.handlers;

import java.util.HashMap;

import android.os.Bundle;

public abstract class ContentCreator {

	final Bundle extras;

	ContentCreator(final Bundle extras) {
		this.extras = extras;
	}

	public abstract HashMap<String, String> createContentSet();
}

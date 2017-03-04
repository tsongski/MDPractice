package com.tsongski.mdpractice.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsongski.mdpractice.R;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by abc on 2016/5/20.
 */
public class MSTestFragment extends Fragment implements View.OnClickListener {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.page_ms, container, false);
		root.findViewById(R.id.excel).setOnClickListener(this);
		root.findViewById(R.id.word).setOnClickListener(this);
		root.findViewById(R.id.power_point).setOnClickListener(this);

		Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		toolbar.setTitle(R.string.app_name);
		return root;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.excel:
				break;
			case R.id.word:
				break;
			case R.id.power_point:
				gotoPowerPoint();
				break;
		}
	}

	private void gotoPowerPoint() {
		String scheme = "ms-word::ofv|u|" + formatEncode("http://res1.xdf100.com/media/pad_import_document/7d/04/7d04af6595f723f58bd483f1e1d3e2ff.docx");
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
		startActivity(intent);
	}

	public String formatEncode(String originUrl) {
		if (TextUtils.isEmpty(originUrl)) {
			return "";
		}
		try {
			URL url = new URL(originUrl);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			return uri.toURL().toString();
		} catch (MalformedURLException e) {
		} catch (URISyntaxException e) {
		}
		return originUrl;
	}
}

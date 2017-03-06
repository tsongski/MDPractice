package com.tsongski.mdpractice.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsongski.mdpractice.R;
import com.tsongski.mdpractice.ui.Utils;

import java.io.File;

/**
 * Created by abc on 2016/5/23.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.page_screenshots, container, false);
		root.findViewById(R.id.screenshots).setOnClickListener(this);
		return root;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.screenshots:
				File file = Utils.getScreenShot(getContext());
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				startActivity(intent);
				break;
		}
	}
}

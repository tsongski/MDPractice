package com.tsongski.mdpractice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsongski.mdpractice.R;

/**
 * Created by tsongski on 2017/3/13.
 */

public class DrawerFragment extends Fragment {
	public static final String TAG = DrawerFragment.class.getSimpleName();

	private View mDrawer;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.page_drawer, container, false);
		mDrawer = root.findViewById(R.id.drawer);
		Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		toolbar.setTitle(R.string.app_name);

		DataAdapter adapter = new DataAdapter();
		RecyclerView rv1 = (RecyclerView) root.findViewById(R.id.rv1);
		rv1.setLayoutManager(new LinearLayoutManager(getContext()));
		rv1.setAdapter(adapter);

		RecyclerView rv2 = (RecyclerView) root.findViewById(R.id.rv2);
		rv2.setLayoutManager(new LinearLayoutManager(getContext()));
		rv2.setAdapter(adapter);
		return root;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_page_drawer, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.switch_item) {
			switchDrawer();
			return true;
		}
		return false;
	}

	private boolean isOpen;

	private void switchDrawer() {
		if (isOpen) {
			closeDrawer();
		} else {
			openDrawer();
		}
	}


	private void closeDrawer() {
		isOpen = false;
		mDrawer.animate()
				.setDuration(300)
				.translationXBy(-getResources().getDimensionPixelOffset(R.dimen.drawer_width))
				.start();
	}

	private void openDrawer() {
		isOpen = true;
		mDrawer.animate()
				.translationXBy(getResources().getDimensionPixelOffset(R.dimen.drawer_width))
				.setDuration(300)
				.start();
	}

	private class DataAdapter extends RecyclerView.Adapter<DataHolder> {

		@Override
		public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new DataHolder(LayoutInflater.from(getContext()).inflate(R.layout.page_drawer_item, parent, false));
		}

		@Override
		public void onBindViewHolder(DataHolder holder, int position) {
			holder.tv.setText(position + "");
		}

		@Override
		public int getItemCount() {
			return 100;
		}
	}

	private class DataHolder extends RecyclerView.ViewHolder {
		TextView tv;

		public DataHolder(View itemView) {
			super(itemView);
			tv = (TextView) itemView;
		}
	}

}

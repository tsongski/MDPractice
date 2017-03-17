package com.tsongski.mdpractice.ui.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.tsongski.mdpractice.R;

import java.io.File;

/**
 * Created by tsongski on 2017/3/17.
 */

public class PdfFragment extends Fragment {
	public static final String TAG = PdfFragment.class.getSimpleName();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private PDFView mPdfView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page_pdf_view, container, false);
		mPdfView = (PDFView) view.findViewById(R.id.pdfView);
		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "java.pdf");
		mPdfView.fromFile(file)
				.enableSwipe(true)
				.swipeHorizontal(false)
				.enableDoubletap(true)
				.defaultPage(0)
				.scrollHandle(new DefaultScrollHandle(getContext()))
				.load();
		return view;
	}
}

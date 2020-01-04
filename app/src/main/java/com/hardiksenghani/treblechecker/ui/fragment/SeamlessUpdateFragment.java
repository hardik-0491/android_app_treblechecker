package com.hardiksenghani.treblechecker.ui.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ImageViewCompat;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardiksenghani.treblechecker.R;
import com.hardiksenghani.treblechecker.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeamlessUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeamlessUpdateFragment extends Fragment {

    private TextView resultTextView, infoTextView;
    private ImageView resultImageView;

    public SeamlessUpdateFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SealessUpdateFragment.
     */
    public static SeamlessUpdateFragment newInstance() {
        SeamlessUpdateFragment fragment = new SeamlessUpdateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seamless_update, container, false);

        resultTextView = view.findViewById(R.id.fragment_seamless_result_textView);
        infoTextView = view.findViewById(R.id.fragment_seamless_info_textview);
        resultImageView = view.findViewById(R.id.fragment_seamless_result_imageView);

        infoTextView.setMovementMethod(LinkMovementMethod.getInstance());

        if (Utils.isSeamlessUpdatesSupported()) {
            resultTextView.setText(R.string.support_seamless_yes);
            resultImageView.setImageResource(R.drawable.ic_check_circle_24dp);
            ImageViewCompat.setImageTintList(resultImageView, ColorStateList.valueOf(Color.GREEN));
            view.setBackgroundColor(Color.parseColor("#EEFFEE"));
        } else {
            resultTextView.setText(R.string.support_seamless_no);
            resultImageView.setImageResource(R.drawable.ic_cancel_circle_24dp);
            ImageViewCompat.setImageTintList(resultImageView, ColorStateList.valueOf(Color.RED));
            view.setBackgroundColor(Color.parseColor("#FFEEEE"));
        }

        return view;
    }

}

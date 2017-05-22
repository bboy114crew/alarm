package com.thangnv.fu.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thangnv.fu.R;
import com.thangnv.fu.base.BaseFragment;


public class ClockFragment extends BaseFragment {



    private OnFragmentInteractionListener mListener;

    public ClockFragment() {
        // Required empty public constructor
    }

    public static ClockFragment newInstance() {
        ClockFragment fragment = new ClockFragment();
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

        View rootView =inflater.inflate(R.layout.fragment_clock, container, false);
        return rootView;
    }

    public void changeTitle(String title) {
        if (mListener != null) {
            mListener.changeTitle(title);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void changeTitle(String title);
    }


}

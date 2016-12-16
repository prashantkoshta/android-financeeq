package com.devcli.finance_eq.core;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by prashantkoshta on 12/15/16.
 */

public class CoreFragment extends Fragment {
    private static final String TAG = CoreFragment.class.getName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"onActivityCreated()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"onDetach()");
    }
}

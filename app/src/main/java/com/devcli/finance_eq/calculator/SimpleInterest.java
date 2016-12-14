package com.devcli.finance_eq.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devcli.finance_eq.R;

/**
 * Created by prashantkoshta on 12/13/16.
 */

public class SimpleInterest extends Fragment {

    private static final String TAG = SimpleInterest.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_interest, container,false);
    }
}

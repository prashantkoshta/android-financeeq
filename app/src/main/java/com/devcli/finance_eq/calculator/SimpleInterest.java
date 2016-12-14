package com.devcli.finance_eq.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devcli.finance_eq.R;

import java.text.NumberFormat;
import java.util.logging.SimpleFormatter;

/**
 * Created by prashantkoshta on 12/13/16.
 */

public class SimpleInterest extends Fragment {

    private static final String TAG = SimpleInterest.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_interest, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText txAmount = (EditText) getView().findViewById(R.id.txtEditAmount);
        final EditText txRate = (EditText) getView().findViewById(R.id.txtEditRate);
        final EditText txYear = (EditText) getView().findViewById(R.id.txtEditYear);
        final TextView txvResult = (TextView) getView().findViewById(R.id.txtViewResult);
        Button btnCalculate = (Button) getView().findViewById(R.id.btnCalculate);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double p = Double.parseDouble(txAmount.getText().toString());
                double r = Double.parseDouble(txRate.getText().toString());
                double t = Double.parseDouble(txYear.getText().toString());
                double result = (p * r * t) /100;
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                txvResult.setText(numberFormat.format(result));
            }
        });



    }
}

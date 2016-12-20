package com.devcli.finance_eq.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.devcli.finance_eq.R;
import com.devcli.finance_eq.core.CoreFragment;
import com.devcli.finance_eq.utils.Calculation;
import com.devcli.finance_eq.vo.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashantkoshta on 12/13/16.
 */

public class SimpleInterest extends CoreFragment {

    private static final String TAG = SimpleInterest.class.getName();
    private TextView txvResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_interest, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView txtViewCalType = (TextView) getView().findViewById(R.id.txtViewCalType);
        final EditText txtAmount = (EditText) getView().findViewById(R.id.txtEditAmount);
        final EditText txtRate = (EditText) getView().findViewById(R.id.txtEditRate);
        final EditText txtYear = (EditText) getView().findViewById(R.id.txtEditYear);
        txvResult = (TextView) getView().findViewById(R.id.txtViewResult);
        final Button btnCalculate = (Button) getView().findViewById(R.id.btnCalculate);
        final TextView txtViewDesc = (TextView) getView().findViewById(R.id.txtViewDesc);
        final Spinner spinnerFreq = (Spinner) getView().findViewById(R.id.spinnerFreq);


        Calculator calc  = (Calculator) getArguments().getParcelable("objCalculator");

        txtViewDesc.setText(Html.fromHtml(calc.getDescriptions()));
        txtViewCalType.setText(calc.getType());

        List<String> list =  (ArrayList<String>)calc.getFrequency();

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFreq.setAdapter(ad);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCalculate.requestFocus();
                double p = Calculation.parseFromString(String.valueOf(txtAmount.getText()));
                double r = Calculation.parseFromString(String.valueOf(txtRate.getText()));
                double t = Calculation.parseFromString(String.valueOf(txtYear.getText()));
                String R = (String) spinnerFreq.getSelectedItem()!=null ? (String) spinnerFreq.getSelectedItem(): "Year";
                double result = Calculation.simpleInterest(p,r,t,R);
                txvResult.setText(Calculation.formatNumberToString(result));
                txtAmount.clearFocus();
                txtRate.clearFocus();
                txtYear.clearFocus();
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null) {
            txvResult.setText(savedInstanceState.getString("finalResult"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("finalResult", String.valueOf(txvResult.getText()));
    }
}

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
 * Created by prashantkoshta on 12/15/16.
 */

public class LoanCalculator extends CoreFragment {
    private static final String TAG = LoanCalculator.class.getName();
    private TextView txvResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.compound_interest, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView txtViewCalType = (TextView) getView().findViewById(R.id.txtViewCalType);
        final EditText txt_p = (EditText) getView().findViewById(R.id.txt_p);
        final EditText txt_r = (EditText) getView().findViewById(R.id.txt_r);
        final EditText txt_t = (EditText) getView().findViewById(R.id.txt_t);
        txvResult = (TextView) getView().findViewById(R.id.txtViewResult);
        final Button btnCalculate = (Button) getView().findViewById(R.id.btnCalculate);
        final TextView txtViewDesc = (TextView) getView().findViewById(R.id.txtViewDesc);
        final Spinner spinner_n = (Spinner) getView().findViewById(R.id.spinner_n);


        Calculator calc  = (Calculator) getArguments().getSerializable("objCalculator");

        txtViewDesc.setText(Html.fromHtml(calc.descriptions));
        txtViewCalType.setText(calc.type);

        List<String> list =  (ArrayList<String>)calc.frequency;

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_n.setAdapter(ad);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCalculate.requestFocus();
                double p = Calculation.parseFromString(String.valueOf(txt_p.getText()));
                double r = Calculation.parseFromString(String.valueOf(txt_r.getText()));
                double t = Calculation.parseFromString(String.valueOf(txt_t.getText()));
                String R = (String) spinner_n.getSelectedItem()!=null ? (String) spinner_n.getSelectedItem(): "Months";
                double result = Calculation.homeLoan(p,r,t,R);
                txvResult.setText(Calculation.formatNumberToString(result));
                txt_p.clearFocus();
                txt_r.clearFocus();
                txt_t.clearFocus();
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

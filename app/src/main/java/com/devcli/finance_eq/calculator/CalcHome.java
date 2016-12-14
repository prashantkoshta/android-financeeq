package com.devcli.finance_eq.calculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.devcli.finance_eq.R;
import com.devcli.finance_eq.service.ServiceHandler;
import com.devcli.finance_eq.vo.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashantkoshta on 12/13/16.
 */

public class CalcHome extends Fragment {

    private static final String TAG = CalcHome.class.getName();
    private ListView listView;
    private List<Calculator> list;
    private ArrayAdapter<Calculator> adapter;
    private CalcHome.ResultReceiver receiver;
    private ProgressBar _progressBar;
    private FragmentManager _fragmentManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calc_home, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _fragmentManager = getFragmentManager();

        _progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        listView = (ListView) getView().findViewById(R.id.listViewEq);
        this.list = new ArrayList<Calculator>();
        adapter = new ArrayAdapter<Calculator>(this.getActivity(), R.layout.list_row_item, R.id.lsRowText, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Calculator calc = (Calculator) adapterView.getSelectedItem();
                addCalcFragment();
                Log.i("####", adapterView.toString());
            }
        });

        if (savedInstanceState != null) {
            adapter.clear();
            list = (ArrayList<Calculator>) savedInstanceState.getSerializable("list");
            adapter.addAll(list);
            _progressBar.setVisibility(View.INVISIBLE);
        } else {
            ServiceHandler sHandler = new ServiceHandler();
            Intent intent = new Intent(this.getActivity(), ServiceHandler.class);
            intent.putExtra("url", "https://app-service-fn.herokuapp.com/fneqapi/listofequation");
            this.getActivity().startService(intent);
            _progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        receiver = new ResultReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("GET_LIST");
        getActivity().registerReceiver(receiver, intentFilter);
        super.onStart();
    }

    @Override
    public void onStop() {
        getActivity().unregisterReceiver(receiver);
        super.onStop();;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", (ArrayList<Calculator>) list);
    }

    private void addCalcFragment(){
        SimpleInterest simpleInterest = new SimpleInterest();
        FragmentTransaction fragmentTransaction = _fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.calc_container, simpleInterest, "Calc");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack("Calc");
        fragmentTransaction.commit();
        _fragmentManager.executePendingTransactions();

    }


    private class ResultReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.clear();
            list = (ArrayList<Calculator>) intent.getSerializableExtra("result");
            adapter.addAll(list);
            _progressBar.setVisibility(View.INVISIBLE);
        }
    }
}

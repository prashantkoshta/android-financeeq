package com.devcli.finance_eq.service;

import com.devcli.finance_eq.utils.Constants;
import com.devcli.finance_eq.vo.Calculator;
import com.devcli.finance_eq.vo.Calculators;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by prashantkoshta on 12/18/16.
 */

public class CalculatorClient {
    private static CalculatorClient _calculatorClient;
    private CalculatorAPIService _calculatorAPIServiceService;

    private void CalculatorClient(){
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         _calculatorAPIServiceService = retrofit.create(CalculatorAPIService.class);
    }

    public  static CalculatorClient getInstance(){
        if(_calculatorClient == null){
            _calculatorClient = new CalculatorClient();
        }
        return _calculatorClient;
    }

    public Observable<Calculators> getCalcData(){
        return Observable.create(new OnSubscribe<Calculators>() {
            @Override
            public void call(Subscriber<? super Calculators> subscriber) {
                //subscriber.onError(new RuntimeException("boo"));
                _calculatorAPIServiceService.loadCalculators();
               // subscriber.onNext();
                subscriber.onCompleted();

            }


        });

        //return  _calculatorAPIServiceService.loadCalculators();
    }
}

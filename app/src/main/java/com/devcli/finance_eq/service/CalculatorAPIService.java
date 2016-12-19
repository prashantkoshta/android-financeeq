package com.devcli.finance_eq.service;

import com.devcli.finance_eq.utils.Constants;
import com.devcli.finance_eq.vo.Calculators;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;

/**
 * Created by prashantkoshta on 12/18/16.
 */

public interface CalculatorAPIService {
    @GET(Constants.CALC_URL)
    Observable<Calculators> loadCalculators();
}

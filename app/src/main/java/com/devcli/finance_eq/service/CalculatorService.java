package com.devcli.finance_eq.service;

import com.devcli.finance_eq.utils.Constants;
import com.devcli.finance_eq.vo.Calculators;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by prashantkoshta on 12/17/16.
 */

public interface CalculatorService {
    @GET(Constants.CALC_URL)
    Call<Calculators> loadCalculators();
    //@Query("tags") String tags
}

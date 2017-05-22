package com.lks.converters;

import org.springframework.core.convert.support.DefaultConversionService;

/**
 * Created by lokkur on 22/05/17.
 */
public class MRVConversionServiceUtil extends DefaultConversionService {

    public MRVConversionServiceUtil() {
        super();
        super.addConverter(new BhavDOToBhavQOConverter());
    }
}

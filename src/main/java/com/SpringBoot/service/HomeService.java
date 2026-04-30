package com.SpringBoot.service;

import com.SpringBoot.model.Home;
import com.SpringBoot.model.HomeCategory;

import java.util.List;

public interface HomeService {

    public Home createHomePageData(List<HomeCategory> allCategories);

}

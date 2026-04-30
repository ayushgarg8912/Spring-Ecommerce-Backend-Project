package com.SpringBoot.model;

import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Data


public class Home {

    private List<HomeCategory> grid;

    private List<HomeCategory> shopByCategories;

    private List<HomeCategory> electricCategories;

    private List<HomeCategory> dealCategories;

    private List<Deal> deals;

}

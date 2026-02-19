package com.practice;

import java.util.ArrayList;

public class Emplist extends ArrayList<Employee>{

@Override
public boolean isEmpty()  {
    // return true ONLY if size >= 5
    return this.size() >= 5;
}
}

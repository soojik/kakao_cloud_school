package com.example.javaweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class PageServiceImpl implements PageService {

    @Override
    public int add(int first, int second) {
        return first+second;
    }
}

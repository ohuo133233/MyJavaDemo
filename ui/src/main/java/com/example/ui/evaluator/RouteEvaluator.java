package com.example.ui.evaluator;

import android.animation.TypeEvaluator;
import android.util.Log;

import com.example.ui.game.map.Route;

public class RouteEvaluator implements TypeEvaluator<Route> {

    private String TAG = "RouteEvaluator";
    private   Route route = new Route();
    @Override
    public Route evaluate(float fraction, Route startRoute, Route endRoute) {

        int x= (int) (startRoute.getX() + fraction * (endRoute.getX() - startRoute.getX()));
        int y= (int) (startRoute.getY() + fraction * (endRoute.getY() - startRoute.getY()));

        Log.d(TAG, "startRoute :" + startRoute);
        Log.d(TAG, "endRoute :" + endRoute);
        Log.d(TAG, "x :" + x);
        Log.d(TAG, "y :" + y);
        route.setX(x);
        route.setY(y);

        return route;

    }
}

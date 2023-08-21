package com.example.ui.evaluator;

import android.animation.TypeEvaluator;
import android.util.Log;

import com.example.ui.game.map.Route;

public class RouteEvaluator implements TypeEvaluator<Route> {

    private String TAG = "RouteEvaluator";

    @Override
    public Route evaluate(float fraction, Route startRoute, Route endRoute) {
        Route route1 = new Route();
//        int x= (int) (route.getX() + fraction * (t1.getX() - route.getX()));
//        int y= (int) (route.getY() + fraction * (t1.getY() - route.getY()));

        Log.d(TAG, "startRoute :" + startRoute);
        Log.d(TAG, "endRoute :" + endRoute);
        route1.setX(100);
        route1.setY(100);

        return route1;

    }
}

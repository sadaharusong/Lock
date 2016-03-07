package com.example.lock;

import android.R.integer;

public class Point {
	public static int STATE_NORMAL = 0;
	public static int STATE_PRESS = 1;
	public static int STATE_ERROR = 2;
	float x,y;
	int state = STATE_NORMAL;
	
	public Point(float x,float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float distance(Point a)
	{
		float distance = (float)Math.sqrt((x - a.x) * (x - a.x) + (y - a.y) * (y - a.y));
		return distance;
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands;

import org.usfirst.frc330.Robot;
 
/**
 * Turn in place towards a waypoint using the gyro.
 */
public class TurnGyroWaypoint extends TurnGyroAbs {
    double x, y;
    public TurnGyroWaypoint(double x, double y, double tolerance)
    {
        super(0,0,tolerance,true,true);
        this.x=x;
        this.y=y;
        
    }

    protected void initialize() {
        calcAngle(x, y);
        super.initialize();
    }

    protected void calcAngle(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcAngle = Math.toDegrees(Math.atan2(deltaX, deltaY));
        
        if (Double.isNaN(calcAngle) || Double.isInfinite(calcAngle))
        {
        	Robot.logger.println("Infinite calcAngle in TurnGyroWaypoint");
            calcAngle = 0;
        }
        
        robotAngle = Robot.chassis.getAngle();
        
        if (Double.isNaN(robotAngle) || Double.isInfinite(robotAngle))
        {
        	Robot.logger.println("Infinite robotAngle in TurnWaypoint");
            robotAngle = 0;
        }
        
        if (Math.abs(robotAngle-calcAngle)<180)
        {
            //do nothing
        }
        else if (robotAngle > calcAngle)
        {
            while (robotAngle > calcAngle)
                calcAngle += 360;
        }
        else 
        {
            while (robotAngle < calcAngle)
                calcAngle -= 360;
        }
//        System.out.println("angle: " + calcAngle);
        
        angle = calcAngle;
    }
}

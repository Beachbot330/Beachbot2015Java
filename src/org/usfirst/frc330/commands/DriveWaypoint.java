/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc330.commands;

import org.usfirst.frc330.Robot;
/*
 * This will drive the robot forwards to a waypoint on the field based on its 
 * original starting position.
 */
public class DriveWaypoint extends DriveDistanceAtAbsAngle {
    double x,y;

    public DriveWaypoint(double x, double y, double tolerance, double timeout, boolean stopAtEnd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(0,0,tolerance,timeout, stopAtEnd);
    }

    protected void initialize() {
        calcXY(x,y);
        super.initialize();
    }
    
    protected void calcXY(double x, double y) {
        double curX, curY, deltaX, deltaY, calcAngle, calcDistance, robotAngle;
        
        curX = Robot.chassis.getX();
        curY = Robot.chassis.getY();
        
        deltaX = x - curX;
        deltaY = y - curY;
        
        calcDistance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        calcAngle = Math.toDegrees(Math.atan2(deltaX, deltaY));
        
        if (Double.isNaN(calcAngle) || Double.isInfinite(calcAngle))
        {
        	Robot.logger.println("Infinite calcAngle in DriveWaypoint");
            calcAngle = 0;
        }
        
        robotAngle = Robot.chassis.getAngle();
        
        if (Double.isNaN(robotAngle) || Double.isInfinite(robotAngle))
        {
        	Robot.logger.println("Infinite robotAngle in DriveWaypoint");
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
        Robot.logger.println("DriveWaypoint distance: " + calcDistance + " angle: " + calcAngle);
    }

}
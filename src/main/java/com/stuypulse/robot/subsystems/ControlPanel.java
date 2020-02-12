package com.stuypulse.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanel extends SubsystemBase {

    public ControlPanel() {

    }

    public void turn(double speed) {
        System.out.println("turning control panel at " + speed);
    }

    public void stop() {
        System.out.println("stopping control panel");    
    }
}


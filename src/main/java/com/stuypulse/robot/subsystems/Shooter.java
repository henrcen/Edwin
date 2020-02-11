package com.stuypulse.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    public Shooter() {
    }

    public void runShooter(double speed) {
        System.out.println("running shooter at " + speed);
    }

    public void reverseShooter() {
        System.out.println("reversing shooter");
    }

    public void stopShooter() {
        System.out.println("stopping shooter");
    }

    public void runFeeder() {
        System.out.println("running feeder");
    }

    public void reverseFeed() {
        System.out.println("reverse feeder");
    }

    public void stopFeeder() {
        System.out.println("stopping feeder motor");
    }
    
    public void extendHoodSolenoid() {
        System.out.println("extending hood solenoid");
    }

    public void retractHoodSolenoid() {
        System.out.println("retracting hood solenoid");
    }

    public void setDefaultSolenoidPosition() {
        retractHoodSolenoid();
    }
}
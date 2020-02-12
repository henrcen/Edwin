package com.stuypulse.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

    public Climber() {
    }

    public void setNeutralMode(IdleMode mode) {
        System.out.println("setting neutral mode");
    }

    public void moveLiftDown() {
        System.out.println("moving climber lift down");
    }

    public void moveLiftUp() {
        System.out.println("moving climber lift up");
    }

    public void moveLift(double speed) {
        System.out.println("moving lift at " + speed);
    }

    public void moveYoyo(double speed) {
        System.out.println("moving yoyo at " + speed);
    }

    public void stopClimber() {
        System.out.println("stopping lift climber");
    }

    public void stopYoyo() {
        System.out.println("stopping yoyo");
    }

    public void toggleLiftBrake() {
        System.out.println("toggling lift brake");
    }

    public void enableLiftBrake() {
        System.out.println("enabling lift brake");
    }

    public void releaseLiftBrake() {
        System.out.println("release lift brake");
    }
}
package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Shooter;

public class ShooterShootRpmCommand {
    public Shooter shooter;
    public double targetVelocity;
    public ShooterShootRpmCommand(Shooter shooter, double targetVelocity) {
        this.shooter = shooter;
        this.targetVelocity = targetVelocity;        
    }
}
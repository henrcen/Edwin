package com.stuypulse.robot.commands;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.subsystems.Shooter;
import com.stuypulse.robot.subsystems.Shooter.Mode;
import com.stuypulse.stuylib.input.WPIGamepad;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterControlCommand extends CommandBase {
    public Shooter shooter;
    public WPIGamepad gamepad;
    public double targetVelocity;

    public ShooterControlCommand(Shooter shooter, WPIGamepad gamepad) {
        this.shooter = shooter;
        this.gamepad = gamepad;
        addRequirements(shooter);
    }

    @Override
    public void execute() {

        System.out.println("Running ShooterControlCommand");

        // If up dpad button is pressed, shoot from far (Steph Curry)
        if (gamepad.getRawDPadUp()) {
            targetVelocity = Constants.SHOOT_FROM_FAR_RPM;
            System.out.println("shooting from far");
            shooter.retractHoodSolenoid();  
            shooter.runShooter(targetVelocity);
            shooter.runFeeder();
            shooter.setMode(Mode.SHOOT_FROM_FAR);
        }
        
        // If down dpad button is pressed, shoot from initiation line
        if (gamepad.getRawDPadDown()) {
            targetVelocity = Constants.SHOOT_FROM_INITATION_LINE_RPM;
            System.out.println("shooting from initiation line");
            shooter.retractHoodSolenoid();
            shooter.runShooter(targetVelocity);
            shooter.runFeeder();
            shooter.setMode(Mode.SHOOT_FROM_INITIATION_LINE);
        }

        // If left dpad button is pressed, shoot from trench
        if (gamepad.getRawDPadLeft()) {
            targetVelocity = Constants.SHOOT_FROM_TRENCH_RPM;
            System.out.println("shooting from trench");
            shooter.extendHoodSolenoid();
            shooter.runShooter(targetVelocity);
            shooter.runFeeder();
            shooter.setMode(Mode.SHOOT_FROM_TRENCH);
        }

        // If right dpad button is pressed, stop shooter
        if (gamepad.getRawDPadRight()) {
            shooter.stopShooter();
        }
    } 

    @Override
    public boolean isFinished() {
        return false;
    }
}
package com.stuypulse.robot.commands;

import com.stuypulse.robot.Constants.Shooting;
import com.stuypulse.robot.subsystems.Shooter;
import com.stuypulse.stuylib.control.Controller;
import com.stuypulse.stuylib.control.PIDCalculator;
import com.stuypulse.stuylib.control.PIDController;
import com.stuypulse.stuylib.input.WPIGamepad;
import com.stuypulse.stuylib.math.SLMath;
import com.stuypulse.stuylib.streams.filters.IStreamFilter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterDefaultCommand extends CommandBase {
    public static final IStreamFilter INTEGRAL_FILTER = (x) -> SLMath.limit(x, Shooting.I_LIMIT.doubleValue());
    public static final IStreamFilter RESET_FILTER = (x) -> 0;

    public Shooter shooter;
    public WPIGamepad gamepad;
    public Controller shootController;
    public Controller feedController;

    public ShooterDefaultCommand(Shooter shooter, WPIGamepad gamepad, Controller shootController,
        Controller feedController) {
        this.shooter = shooter;
        this.gamepad = gamepad;
        this.shootController = shootController;
        this.feedController = feedController;

        addRequirements(this.shooter);
    }

    public ShooterDefaultCommand(Shooter shooter, WPIGamepad gamepad) {
        this(shooter, gamepad, new PIDController(), new PIDController());
    }

    public void updatePID() {
        if (shootController instanceof PIDController) {
            PIDController controller = (PIDController) shootController;

            controller.setP(Shooting.Shooter.P.get());
            controller.setI(Shooting.Shooter.I.get());
            controller.setD(Shooting.Shooter.D.get());

            if(controller.isDone(Shooting.I_RANGE.doubleValue())) {
                controller.setIntegratorFilter(INTEGRAL_FILTER);
            } else {
                controller.setIntegratorFilter(RESET_FILTER);
            }
        }

        if (feedController instanceof PIDController) {
            PIDController controller = (PIDController) feedController;

            controller.setP(Shooting.Feeder.P.get());
            controller.setI(Shooting.Feeder.I.get());
            controller.setD(Shooting.Feeder.D.get());

            if(controller.isDone(Shooting.I_RANGE.doubleValue())) {
                controller.setIntegratorFilter(INTEGRAL_FILTER);
            } else {
                controller.setIntegratorFilter(RESET_FILTER);
            }
        }
    }

    public void updateAutoPID() {
        if (shootController instanceof PIDCalculator) {

            PIDCalculator calculator = (PIDCalculator) shootController;
            PIDController controller = (PIDController) calculator.getPIDController();

            Shooting.Shooter.P.set(controller.getP());
            Shooting.Shooter.I.set(controller.getI());
            Shooting.Shooter.D.set(controller.getD());
        }

        if (feedController instanceof PIDCalculator) {

            PIDCalculator calculator = (PIDCalculator) feedController;
            PIDController controller = (PIDController) calculator.getPIDController();

            Shooting.Feeder.P.set(controller.getP());
            Shooting.Feeder.I.set(controller.getI());
            Shooting.Feeder.D.set(controller.getD());
        }
    }

    public void updateShooter() {
        // Target speed to go at
        double speed = shooter.getCurrentShooterVelocityInRPM();
        double target = shooter.getTargetVelocity();

        // The error from current speed to target
        double error = target - speed;

        // Speed to set the motor plus feed forward
        double output = shootController.update(error);
        output += target * Shooting.Shooter.FF.get();

        // Set the shooter to that
        shooter.setShooterSpeed(output);

        if(gamepad != null) {
            double rumbleMag = Math.abs(speed - target);
            rumbleMag = Shooting.TOLERANCE - rumbleMag;
            rumbleMag = Math.max(error, 0.0);
            rumbleMag /= Shooting.TOLERANCE;

            gamepad.setRumble(rumbleMag);
        }
    }

    public void updateFeeder() {
        // Target speed to go at
        double speed = shooter.getCurrentFeederVelocityInRPM();
        double target = shooter.getTargetVelocity() * Shooting.Feeder.SPEED_MUL;

        // The error from current speed to target
        double error = target - speed;

        // Speed to set the motor plus feed forward
        double output = feedController.update(error);
        output += target * Shooting.Feeder.FF.get();

        // Set the shooter to that
        shooter.setFeederSpeed(output);
    }

    @Override
    public void execute() {

        // If using PID, get its PID values
        updatePID();

        // If using AutoPID, report its values
        updateAutoPID();

        // Update shooter and feeder with controller
        updateShooter();
        updateFeeder();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

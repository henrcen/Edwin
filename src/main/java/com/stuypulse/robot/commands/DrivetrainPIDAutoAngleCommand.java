package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.Constants.Alignment;

import com.stuypulse.stuylib.control.Controller;
import com.stuypulse.stuylib.control.PIDCalculator;
import com.stuypulse.stuylib.control.PIDController;
/**
 * This class will move the drivetrain and make measurements so that you can
 * calculate optimal P I and D values for the angle controller.
 */
public class DrivetrainPIDAutoAngleCommand extends DrivetrainAlignmentCommand {

    private static Controller getNewSpeedController() {
        PIDController speed = new PIDController(-1, -1, -1);
        return speed;
    }

    private static Controller getNewAngleController() {
        return new PIDCalculator(Alignment.Angle.BANGBANG_SPEED);
    }

    /**
     * This creates a command that aligns the robot
     * 
     * @param drivetrain Drivetrain used by command to move
     * @param distance   target distance for robot to drive to
     */
    public DrivetrainPIDAutoAngleCommand(Drivetrain drivetrain, DrivetrainAlignmentCommand.Aligner aligner) {
        super(drivetrain, aligner, getNewSpeedController(), getNewAngleController());
    }

    // This command never really finishes
    public boolean isFinished() {
        return false;
    }

    // Report value to smart dashboard
    public void execute() {
        super.execute();
        
        if (getAngleController() instanceof PIDCalculator) {
            PIDController calulated = ((PIDCalculator) getAngleController()).getPIDController(
                    Alignment.AUTOTUNE_P.doubleValue(), 
                    Alignment.AUTOTUNE_I.doubleValue(),
                    Alignment.AUTOTUNE_D.doubleValue()
                );
            
            Alignment.Angle.P.set(calulated.getP());
            Alignment.Angle.I.set(calulated.getI());
            Alignment.Angle.D.set(calulated.getD());
        }
    }
}
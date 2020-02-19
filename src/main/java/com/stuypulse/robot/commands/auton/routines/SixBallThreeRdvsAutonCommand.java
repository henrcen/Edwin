package com.stuypulse.robot.commands.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.stuypulse.robot.commands.DrivetrainGoalAligner;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.TurnCommand;
import com.stuypulse.robot.commands.DrivetrainAlignmentCommand;
import com.stuypulse.robot.commands.DrivetrainStopCommand;
import com.stuypulse.robot.commands.IntakeAcquireCommand;
import com.stuypulse.robot.commands.command.groups.AcquireNewBallCommandGroup;
import com.stuypulse.robot.commands.command.groups.AcquireThreeBallFromRdvsCommandGroup;
import com.stuypulse.robot.commands.command.groups.AlignAtStartAndShootThreeCommandGroup;
import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;
import com.stuypulse.robot.Constants;


public class SixBallThreeRdvsAutonCommand extends SequentialCommandGroup {
    public SixBallThreeRdvsAutonCommand(Drivetrain drivetrain, Intake intake, Funnel funnel, Chimney chimney) {
        addCommands(
            new AlignAtStartAndShootThreeCommandGroup(drivetrain),
            new AcquireThreeBallFromRdvsCommandGroup(drivetrain, intake, funnel, chimney),
            new TurnCommand(drivetrain, -Constants.ANGLE_FROM_START_POINT_TO_THREE_BALL),
            new AlignAtStartAndShootThreeCommandGroup(drivetrain),
            new DrivetrainStopCommand(drivetrain)
            //Shoot 3  
            );
    }
}
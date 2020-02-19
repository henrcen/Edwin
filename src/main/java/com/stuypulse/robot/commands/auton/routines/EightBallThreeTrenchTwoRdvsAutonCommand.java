package com.stuypulse.robot.commands.auton.routines;

import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;
import com.stuypulse.robot.commands.DrivetrainStopCommand;
import com.stuypulse.robot.commands.command.groups.ShootThreeAtStartThreeTrenchTwoRdvsCommandGroup;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class EightBallThreeTrenchTwoRdvsAutonCommand extends SequentialCommandGroup {
    public EightBallThreeTrenchTwoRdvsAutonCommand(Drivetrain drivetrain, Intake intake, Funnel funnel, Chimney chimney) {
        addCommands(
            new ShootThreeAtStartThreeTrenchTwoRdvsCommandGroup(drivetrain, intake, funnel, chimney),
            new DrivetrainStopCommand(drivetrain)
        );
    }
}
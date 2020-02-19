package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class FeedAndShootOneBallAtTargetVelocityCommand extends CommandGroupBase {

    public FeedAndShootOneBallAtTargetVelocityCommand(Funnel funnel, Chimney chimney, Shooter shooter) {
        addCommands(
            new ParallelRaceGroup(
                new ShooterReachTargetVelocityCommand(shooter), 
                new ShooterDefaultCommand(shooter, null)
            ),
            new ParallelRaceGroup(
                new FeedBallsCommand(shooter, funnel, chimney),
                new ShooterDetectBallShotCommand(shooter),
                new ShooterDefaultCommand(shooter, null)
            )
        );
    }

    @Override
    public void addCommands(Command... commands) {
        // TODO Auto-generated method stub

    }



}
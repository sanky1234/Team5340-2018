package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by RobotAdmin on 11/16/2017.
 */

public class CommonActuatorClass extends CommonDriving {

    private int DistanceToClicksConverterForLift(double DistanceToTravel) {


        int ClicksNeeded = (int) (DistanceToTravel * LIFT_CLICKS);

        return ClicksNeeded;
    }//end Distatnce to clicks Converter

    public void openArms(){
        leftGrabber.setPosition(OPEN_GRABBER);
        rightGrabber.setPosition(OPEN_GRABBER);
        sleep(250);
    }

    public void closeArms(){
        leftGrabber.setPosition(CLOSE_GRABBER);
        rightGrabber.setPosition(CLOSE_GRABBER);
        sleep(250);
    }

    public void liftUp(double DistanceToTravel, double Speed) throws InterruptedException {
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int ClicksNeeded = DistanceToClicksConverterForLift(DistanceToTravel);

        lift.setTargetPosition(ClicksNeeded);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(Speed);


        while (opModeIsActive() &&  lift.isBusy()) {

        }

        lift.setPower(0);

        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void liftDown(double DistanceToTravel, double Speed) throws InterruptedException {
        liftUp(-DistanceToTravel, -Speed);
    }

}

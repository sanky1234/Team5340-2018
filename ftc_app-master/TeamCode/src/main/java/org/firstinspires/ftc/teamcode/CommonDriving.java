package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by RobotAdmin on 11/8/2017.
 */

public class CommonDriving extends CommonVariables {
    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////                                     Formulas                                         ////
    //////////////////////////////////////////////////////////////////////////////////////////////
    private int DistanceToClicksConverter(double DistanceToTravel) {

        double DistancePerRotation = WHEEL_DIAMETER * Math.PI;
        double RotationsNeeded = (DistanceToTravel / DistancePerRotation);
        int ClicksNeeded = (int) (RotationsNeeded * CLICKS_PER_ROTATION);

        return ClicksNeeded;
    }//end Distatnce to clicks Converter

    public void keepStraight(int degreeToStayAt, double speed){
        int currentDegrees = gyro.getIntegratedZValue();
        telemetry.addData("gyro", currentDegrees);
        telemetry.addData("gyro", degreeToStayAt);

        if (gyroTolerance(currentDegrees, degreeToStayAt).equalsIgnoreCase("Left")){
            left.setPower(speed + (speed * SPEED_ADDITION));
            right.setPower(speed - (speed * SPEED_ADDITION ));
            telemetry.addData("Veer", "Left");
        }else if (gyroTolerance(currentDegrees, degreeToStayAt).equalsIgnoreCase("Right")){
            telemetry.addData("veer", "Right");
            left.setPower(speed - (speed * SPEED_ADDITION));
            right.setPower(speed +(speed * SPEED_ADDITION));
        }else{
            left.setPower(speed);
            right.setPower(speed);
        }
       telemetry.update();
    }

    public String gyroTolerance(int currentDegree, int degreesToStayAt){
        if((degreesToStayAt + 3) < currentDegree ){
            return "Left";
        }else if((degreesToStayAt - 3) > currentDegree){
            return "Right";
        }else{
            return "Good";
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Drive Forward / Backward /////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public void driveForwardWithEncoders(double DistanceToTravel, double Speed) throws InterruptedException {
        int degreesToStayAt = gyro.getIntegratedZValue();
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int ClicksNeeded = DistanceToClicksConverter(DistanceToTravel);

        left.setTargetPosition(ClicksNeeded);
        right.setTargetPosition(ClicksNeeded);

        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setPower(Speed);
        right.setPower(Speed);


        while (opModeIsActive() && right.isBusy() && left.isBusy()) {
            keepStraight(degreesToStayAt,Speed);
            telemetry.update();
        }

        left.setPower(0);
        right.setPower(0);

        //reset mode as a safety
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveBackwardsWithEncoders(double distanceToTravel, double speed) throws InterruptedException {
        driveForwardWithEncoders(-distanceToTravel,-speed);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// Turns //////////// /////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void turn(int degreesDesired, int direction){
        degreesDesired = degreesDesired - 10;
        gyro.resetZAxisIntegrator();

        int currentDegrees = gyro.getIntegratedZValue();
        int initialDegrees = gyro.getIntegratedZValue();
        telemetry.addData("Gyro",currentDegrees + "hi");

        while (Math.abs(currentDegrees) < degreesDesired + initialDegrees){
            if(Math.abs(currentDegrees) < (degreesDesired - 35)) {
                left.setPower(FAST_TURNSPEED * direction);
                right.setPower(FAST_TURNSPEED * (-direction));
            }else{
                left.setPower(SLOW_TURNSPEED * direction);
                right.setPower(SLOW_TURNSPEED * (-direction));
            }
            currentDegrees = gyro.getIntegratedZValue();
            telemetry.addData("Gyro",currentDegrees + "hi");
            telemetry.update();
        }

        left.setPower(0);
        right.setPower(0);
    }

    public void turnRight(int desiredDegrees){
        turn(desiredDegrees, 1);
    }

    public void turnLeft(int desiredDegrees){
        turn(desiredDegrees, -1);
    }


}

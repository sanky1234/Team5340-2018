package org.firstinspires.ftc.teamcode;

/**
 * Created by RobotAdmin on 11/1/2017.
 */
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "TeleopMain", group= "Concept")
public class Teleop extends CommonVariables {

    @Override
    public void runOpMode() throws InterruptedException {
        initTeleop();
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y){
                if(!MotorSlowing){
                    MotorSlowing = true;
                }else{
                    MotorSlowing = false;
                }
                sleep(250);
            }

            if(gamepad1.right_bumper){
                leftGrabber.setPosition(CLOSE_GRABBER);
                //rightGrabber.setPosition(CLOSE_GRABBER);
                sleep(250);
              //  leftGrabber.setPosition(.2);
              //  rightGrabber.setPosition(.2);
              //  sleep(500);
            }

            if (gamepad1.left_bumper){
                leftGrabber.setPosition(OPEN_GRABBER);
                //rightGrabber.setPosition(OPEN_GRABBER);
                sleep(250);
            }

            if(MotorSlowing){
                motorSlowingFactor = MOTOR_SLOW;
            }else{
                motorSlowingFactor = MOTOR_FAST;
            }

            if(gamepad1.right_trigger != 0 && lift.getCurrentPosition() < LIFT_MAX_CLICKS) {                     //Flapper control - toggle using A
                lift.setPower(gamepad1.right_trigger * LIFT_SLOW);
                telemetry.addData("lift current position ", lift.getCurrentPosition());
            }else if(gamepad1.left_trigger != 0 && lift.getCurrentPosition() >= -500){
                lift.setPower(-gamepad1.left_trigger * LIFT_SLOW);
                telemetry.addData("lift current position ", lift.getCurrentPosition());
            }else{
                lift.setPower(0);
                telemetry.addData("lift current position ", lift.getCurrentPosition());
            }
                telemetry.update();

            double RightGamepadVal = gamepad1.right_stick_y * motorSlowingFactor; //Get Joystick values
            double LeftGamepadVal = gamepad1.left_stick_y * motorSlowingFactor;

            left.setPower(LeftGamepadVal);
            right.setPower(RightGamepadVal);


        }

    }

}

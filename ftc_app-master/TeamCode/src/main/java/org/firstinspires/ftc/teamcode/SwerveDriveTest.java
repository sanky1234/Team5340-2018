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

@TeleOp(name = "TeleopMainSwerve", group= "Concept")

public class SwerveDriveTest extends LinearOpMode{
    public DcMotor left;
    public DcMotor backLeft;
    public DcMotor right;
    public DcMotor backRight;
    public Servo leftServo;
    public Servo backLeftServo;
    public Servo rightServo;
    public Servo backRightServo;

    public double A = 0;
    public double B = 0;
    public double C = 0;
    public double D = 0;

    public double length = 18;
    public double width = 18;

    public double vehicleTranslationx = 0;
    public double vehicleTranslationy = 0;
    public double vehicleRotation = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("left");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        right = hardwareMap.dcMotor.get("right");
        backRight = hardwareMap.dcMotor.get("backRight");

        leftServo = hardwareMap.servo.get("leftS");
        rightServo = hardwareMap.servo.get("rightS");
        backLeftServo = hardwareMap.servo.get("backLeftS");
        backRightServo = hardwareMap.servo.get("backRightS");

        left.setPower(0);
        backLeft.setPower(0);
        right.setPower(0);
        backRight.setPower(0);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        left.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        vehicleTranslationx = gamepad1.right_stick_x;
        vehicleTranslationy = gamepad1.right_stick_y;

        if(gamepad1.left_stick_x < -0.1){
            vehicleRotation = -1;
        }else if(gamepad1.left_stick_x > 0.1){
            vehicleRotation = 1;
        }else{
            vehicleRotation = 0;
        }



        A = vehicleTranslationx - vehicleRotation*(length /2);
        B = vehicleTranslationx + vehicleRotation*(length/2);
        C = vehicleTranslationy - vehicleRotation*(width / 2);
        D = vehicleTranslationy + vehicleRotation*(width / 2);

        double wheelOneSpeed = Math.sqrt((Math.pow(B,2) + Math.pow(C,2)));
        double wheelOneAngle = (((Math.atan2(B,C) * (180 / Math.PI)) + 180) / 360);
        double wheelTwoSpeed = Math.sqrt((Math.pow(B,2) + Math.pow(D,2)));
        double wheelTwoAngle = (((Math.atan2(B,D) * (180 / Math.PI))+ 180) / 360);
        double wheelThreeSpeed = Math.sqrt((Math.pow(A,2) + Math.pow(D,2)));
        double wheelThreeAngle = (((Math.atan2(A,D) * (180 / Math.PI))+ 180) / 360);
        double wheelFourSpeed = Math.sqrt((Math.pow(A,2) + Math.pow(C,2)));
        double wheelFourAngle = (((Math.atan2(A,C) * (180 / Math.PI)) + 180) / 360);

        right.setPower(wheelOneSpeed);
        rightServo.setPosition(wheelOneAngle);
        left.setPower(wheelTwoSpeed);
        leftServo.setPosition(wheelTwoAngle);
        backRight.setPower(wheelFourSpeed);
        backRightServo.setPosition(wheelFourAngle);
        backLeft.setPower(wheelThreeSpeed);
        backLeftServo.setPosition(wheelThreeAngle);



    }
}

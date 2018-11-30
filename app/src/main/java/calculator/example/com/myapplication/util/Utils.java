package calculator.example.com.myapplication.util;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @Author YangYong
 * @Date 2018/11/30 14:43
 * @Description
 */
public class Utils {
    public static String keepTwoDecimal(double value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setGroupingUsed(false);
        return nf.format(value);
    }

    public static double parseString (String str){
        double temp;
        try{
            temp =  Double.parseDouble(str);
        }catch (NumberFormatException e){
            temp = 0;
        }
        return temp;
    }
}

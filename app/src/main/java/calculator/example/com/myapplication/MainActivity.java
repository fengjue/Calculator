package calculator.example.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import calculator.example.com.myapplication.R;
import calculator.example.com.myapplication.util.Utils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.express)
    TextView express;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.button_division)
    Button buttonDivision;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button_multiplication)
    Button buttonMultiplication;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button_subtraction)
    Button buttonSubtraction;
    @BindView(R.id.button_point)
    Button buttonPoint;
    @BindView(R.id.button0)
    Button button0;
    @BindView(R.id.button_equal)
    Button buttonEqual;
    @BindView(R.id.button_addition)
    Button buttonAddition;
    @BindView(R.id.delete)
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button7, R.id.button8, R.id.button9, R.id.button_division, R.id.button4, R.id
            .button5, R.id.button6, R.id.button_multiplication, R.id.button1, R.id.button2, R.id
            .button3, R.id.button_subtraction, R.id.button_point, R.id.button0, R.id
            .button_equal, R.id.button_addition, R.id.delete})
    public void onViewClicked(View view) {
        String preStr = express.getText().toString();
        switch (view.getId()) {
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button0:
                express.setText(preStr + ((Button) view).getText().toString());
                break;
            case R.id.button_division://  除
            case R.id.button_multiplication://乘
            case R.id.button_subtraction://减
            case R.id.button_addition://加
            case R.id.button_point://小数点
                express.setText(preStr + ((Button) view).getText().toString());
                break;
            case R.id.button_equal://等号
                result.setText(Utils.keepTwoDecimal(getResult()));
                break;
            case R.id.delete:
                express.setText("");
                result.setText("");
                break;
        }

    }

    private double getResult() {
        String input = express.getText().toString();
        if (input.contains("+")) {
            return getInputResult("\\+", input);
        } else if (input.contains("-")) {
            return getInputResult("-", input);
        } else if (input.contains("*")) {
            return getInputResult("\\*", input);
        } else if (input.contains("/")) {
            return getInputResult("/", input);
        }
        return 0;

    }

    private double getInputResult(String reg, String input) {
        String[] split = input.split(reg);
        double temp = 0;
        for (int j = 0; j < split.length; j++) {
            String aSplit = split[j];
            double temp1 = 0;
            if (aSplit.contains("+")) {
                String[] split1 = aSplit.split("\\+");
                for (String aSplit1 : split1) {
                    if (aSplit1.contains("+")) {
                        temp1 += getInputResult("\\+", aSplit1);
                    } else if (aSplit1.contains("-")) {
                        temp1 += getInputResult("-", aSplit1);
                    } else if (aSplit1.contains("*")) {
                        temp1 += getInputResult("\\*", aSplit1);
                    } else if (aSplit1.contains("/")) {
                        temp1 += getInputResult("/", aSplit1);
                    } else {
                        temp1 += Utils.parseString(aSplit1);
                    }
                }
            } else if (aSplit.contains("-")) {
                String[] split1 = aSplit.split("-");
                for (String aSplit1 : split1) {
                    if (aSplit1.contains("+")) {
                        if (temp1 == 0) {
                            temp1 = getInputResult("\\+", aSplit1);
                        } else {
                            temp1 -= getInputResult("\\+", aSplit1);
                        }
                    } else if (aSplit1.contains("-")) {
                        if (temp1 == 0) {
                            temp1 = getInputResult("-", aSplit1);
                        } else {
                            temp1 -= getInputResult("-", aSplit1);
                        }
                    } else if (aSplit1.contains("*")) {
                        if (temp1 == 0) {
                            temp1 = getInputResult("\\*", aSplit1);
                        } else {
                            temp1 -= getInputResult("\\*", aSplit1);
                        }
                    } else if (aSplit1.contains("/")) {
                        if (temp1 == 0) {
                            temp1 = getInputResult("/", aSplit1);
                        } else {
                            temp1 -= getInputResult("/", aSplit1);
                        }
                    } else {
                        if (temp1 == 0) {
                            temp1 = Utils.parseString(aSplit1);
                        } else {
                            temp1 -= Utils.parseString(aSplit1);
                        }
                    }
                }
            } else if (aSplit.contains("*")) {
                String[] split1 = aSplit.split("\\*");
                double temp2 = 1;
                for (String aSplit1 : split1) {
                    if (aSplit1.contains("/")) {
                        temp2 *= getInputResult("/", aSplit1);
                    } else {
                        temp2 *= Utils.parseString(aSplit1);
                    }

                }
                temp1 = temp2;
            } else if (aSplit.contains("/")) {
                String[] split1 = aSplit.split("/");
                double temp2 = 0;
                for (int i = 0; i < split1.length; i++) {
                    if (Utils.parseString(split1[i]) != 0) {
                        if (i == 0) {
                            temp2 = Utils.parseString(split1[i]);
                        } else {
                            temp2 = temp2 / Utils.parseString(split1[i]);
                        }
                    }
                }
                temp1 = temp2;
            } else {
                temp1 = Utils.parseString(aSplit);
            }
            if ("\\+".equals(reg)) {
                temp += temp1;
            } else if ("-".equals(reg)) {
                if (temp1 == 0) {
                    if (j == 0) {
                        temp1 = Utils.parseString(aSplit);
                    } else {
                        temp1 = temp - temp1;
                    }
                } else {
                    if (j != 0) {
                        temp1 = temp - temp1;
                    }
                }
                temp = temp1;
            } else if ("\\*".equals(reg)) {
                if (temp1 == 0) {
                    if (j == 0) {
                        temp1 = Utils.parseString(aSplit);
                    } else {
                        temp1 = temp * Utils.parseString(aSplit);
                    }
                } else {
                    if (j != 0) {
                        temp1 = temp * temp1;
                    }
                }
                temp = temp1;
            } else if ("/".equals(reg)) {
                if (temp1 == 0) {
                    if (j == 0) {
                        temp1 = Utils.parseString(aSplit);
                    } else {
                        temp1 = temp / Utils.parseString(aSplit);
                    }
                } else {
                    if (j != 0) {
                        temp1 = temp / temp1;
                    }
                }
                temp = temp1;
            }
        }
        return temp;
    }
}

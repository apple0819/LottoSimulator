package com.example.lottosimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lottosimulator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    List<TextView> winNumTextList = new ArrayList<>();
    int[] winLottoNumArr = new int[6];
    int bonusNum = 0;
    long useMoneyAmount = 0;
    int[] myLottoNumArr = {5,19,24,27,35,42};

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.buyOneLottoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                당첨번호를 생성 => 텍스트뷰에 반영
            makeWinLottoNum();
            checkLottoRank();
            }
        });

    }

    @Override
    public void setValues() {

        winNumTextList.add(binding.winLottoNumTxt01);
        winNumTextList.add(binding.winLottoNumTxt02);
        winNumTextList.add(binding.winLottoNumTxt03);
        winNumTextList.add(binding.winLottoNumTxt04);
        winNumTextList.add(binding.winLottoNumTxt05);
        winNumTextList.add(binding.winLottoNumTxt06);

    }

    void makeWinLottoNum() {
//        6개의 숫자(배열) + 보너스번호 1개 int 변수
//        => 이 함수에서만이 아니라, 다른곳에서도 쓸 예정.
//        => 당첨 등수 확인떄도 사용. => 맴버변수로 배열 / 변수 생성

//        당첨번호+보너스번호 모두 0으로 초기화.
//        (이미 뽑은 번호가 있다면 모두 날리자)

        for(int i=0; i<winLottoNumArr.length; i++) {
            winLottoNumArr[i]=0;
        }
        bonusNum = 0;

//        로또번호 6개 생성.
//        1~45여야 함+중복 허용 X.

        for(int i=0; i<winLottoNumArr.length; i++) {
//            1~45d의 숫자를 랜덥으로 뽑고
//            중복이 아니라면 => 당첨번호로 선정.
//            중복이라면? => 다시뽑자. => 중복이 아닐때까지 계속 뽑자.

            while(true) {
//                1<=(int) (Math.random()*45+1)<46

//                1~45의 정수를 뽑아서 임시 저장.
//                이 숫자가 중복검사를 통과하면 사용, 아니면 다시검사.
                int randomNum = (int) (Math.random()*45+1);

//                중복검사? 당첨번호 전부와 randomNum을 비교.
//                하나라도 같으면 탈락.

                boolean isDuplOk = true; //중복검사 변수
                for(int winNum : winLottoNumArr) {
                    if(winNum==randomNum) {
                        isDuplOk = false;
                        break;
                    }
                }

                if(isDuplOk) {
                    winLottoNumArr[i] = randomNum;
                    Log.i("당첨번호", randomNum+"");
                    break;
                }
            }
        }

//        6개의 당첨번호를 작은 숫자부터 정렬.
        Arrays.sort(winLottoNumArr);

        for(int i=0; i<winLottoNumArr.length; i++) {

            winNumTextList.get(i).setText(winLottoNumArr[i]+"");
        }

//        보너스번호 생성 => 1~45, 당첨번호 중복 X.

        while (true) {
            int randomNum = (int) (Math.random()*45+1);

            boolean isDuplOk = true;
            for (int winNum : winLottoNumArr) {
                if (winNum == randomNum) {
                    isDuplOk = false;
                    break;
                }
            }

            if (isDuplOk) {
                bonusNum = randomNum;
                break;
            }
        }

//        보너스넘버 생성됨.
        binding.bonusNumTxt.setText(bonusNum+"");

    }

    void checkLottoRank() {
//        돈을 천원 지불+등수 확인.

        useMoneyAmount+=1000;

        binding.useMoneyTxt.setText(String.format("사용 금액 : %,d원",useMoneyAmount));

//        몇등인지?
//        내 번호를 하나 들고 => 당첨번호 6개를 돌아본다
//        얻어 낼것? 몇개의 숫자를 뭦췄는지.
//        맞춘갯수를 담아줄 변수

        int correctCount = 0;
        for (int myNum : myLottoNumArr) {
            for (int winNum : winLottoNumArr) {
                if(myNum == winNum) {
                    correctCount++;
                }
            }
        }

//        correctCount의 값에 따라 등수를 판정.
        if (correctCount == 6) {
//            1등
        }
        else if (correctCount == 5) {
//            2등 / 3등 재검사 필요 => 보너스번호를 맞췄는지?
        }
        else if (correctCount == 4) {
//            4등
        }
        else if (correctCount == 3) {
//            5등
        }
        else {
//            꽝!
        }


    }

}

package com.example.animation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Animation - 변화하는 효과를 주는 기법
        //     - Frame Animation : 한장한장 바뀌는 이미지를 교체하는 방식
        //                        고전적으로 많이 사용되온 방식
        //  - Tween Animation : 움직이는 방법을 설정해서
        //                        계산결과로 중간이미지를 표현하는 방식
        //            - 투명도 변경, 회전, 크기변경, 위치변경
        val b = findViewById<View>(R.id.button1) as Button
        val iv = findViewById<View>(R.id.imageView1) as ImageView
        val drawable = iv.background as AnimationDrawable
        // ImageView 의 배경으로 지정한 animation 설정파일을 객체로 얻어옴
        b.setOnClickListener {
            if (drawable.isRunning) { // 동작중일 경우
                // 멈추기
                drawable.stop()
                b.text = "에니메이션 시작"
            } else { // 멈춰있는 경우
                // 동작 개시하기
                drawable.start() // 애니메이션 동작 개시
                b.text = "에니메이션 중지"
            }
        }
    } // end of onCreate
} // end of class

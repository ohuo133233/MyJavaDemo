package com.wang.game.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.wang.game.R;

/**
 * 控制按钮
 * 1、攻击和对话UI显示
 */
public class OperateController extends ConstraintLayout {
    private View mRoot;
    private ImageButton mOperate;

    public OperateController(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public OperateController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public OperateController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public OperateController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        mRoot = LayoutInflater.from(context).inflate(R.layout.operate_controller, this);
        mOperate = mRoot.findViewById(R.id.operate);
    }


    public void showAttackButton() {
        mOperate.setBackgroundResource(R.mipmap.attack_button);
    }

    public void showDialogueButton() {
        mOperate.setBackgroundResource(R.mipmap.dialogue_button);
    }
}

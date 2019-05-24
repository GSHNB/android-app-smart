package com.nsxz.smart.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.Html;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nsxz.smart.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoldTextView extends LinearLayout {
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvButton)
    TextView tvButton;

    ValueAnimator foldAnim;
    ValueAnimator openAnim;

    int foldHeight;
    int realHeight;
    boolean isFirstOpen = true;
    int foldLines = 2;
    int duration = 400;


    public FoldTextView(Context context) {
        this(context, null);
    }

    public FoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_foldtext, this, true);
        ButterKnife.bind(this);
        final LayoutParams layoutParams = (LayoutParams) tvContent.getLayoutParams();
        openAnim = ValueAnimator.ofInt();
        openAnim.setDuration(duration);
        openAnim.setRepeatCount(0);
        openAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                layoutParams.height = value;
                tvContent.requestLayout();
            }
        });

        foldAnim = ValueAnimator.ofInt();
        foldAnim.setDuration(duration);
        foldAnim.setRepeatCount(0);
        foldAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                layoutParams.height = value;
                tvContent.requestLayout();
            }
        });


    }

    public void setFoldText(String html) {
        tvContent.setMaxLines(foldLines);
        LayoutParams lp = (LayoutParams) tvContent.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        tvContent.requestLayout();
        isFirstOpen = true;
        tvContent.setText(Html.fromHtml(html));
        tvContent.post(new Runnable() {
            @Override
            public void run() {
                realHeight = measureRealHeight();
                foldHeight = tvContent.getHeight();
                openAnim.setIntValues(foldHeight, realHeight);
                foldAnim.setIntValues(realHeight, foldHeight);

            }
        });
    }

    public void setFoldLines(int foldLines) {
        this.foldLines = foldLines;
    }

    @OnClick(R.id.tvButton)
    public void onViewClicked() {
        if (isFirstOpen) {
            tvContent.setMaxLines(Integer.MAX_VALUE);
            isFirstOpen = false;
        }
        if (tvContent.getHeight() == realHeight) {
            foldAnim.start();
        } else {
            openAnim.start();
        }
    }

    private int measureRealHeight() {
        Layout layout = tvContent.getLayout();
        int desired = layout.getLineTop(tvContent.getLineCount());
        int padding = tvContent.getCompoundPaddingTop() + tvContent.getCompoundPaddingBottom();
        return desired + padding;
    }
}

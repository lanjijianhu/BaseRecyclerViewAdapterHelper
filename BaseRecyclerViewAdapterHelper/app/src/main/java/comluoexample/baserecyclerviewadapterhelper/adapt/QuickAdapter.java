package comluoexample.baserecyclerviewadapterhelper.adapt;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import comluoexample.baserecyclerviewadapterhelper.R;
import comluoexample.baserecyclerviewadapterhelper.javaBean.MultyItemBean;

/**
 * Created by DQ on 2017/6/17.
 * BaseQuickAdapter<MultyItemBean, BaseViewHolder>,
 */
public class QuickAdapter extends BaseQuickAdapter<MultyItemBean, BaseViewHolder> {
    private Context context;
    public interface onSwipeListener{
        void onDel(int pos);
    }
    private onSwipeListener mOnSwipeListener;
    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }
    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    public QuickAdapter(Context context,List<MultyItemBean> data) {
        super(R.layout.item_layout, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder viewHolder, MultyItemBean bean) {
        viewHolder.setText(R.id.content_tv, bean.getName());
//                .setText(R.id.tweetText, item.getText())
//                .setText(R.id.tweetDate, item.getCreatedAt())
//                .setVisible(R.id.tweetRT, item.isRetweet())
//                .linkify(R.id.tweetText);
        viewHolder.getView(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnSwipeListener){
                    mOnSwipeListener.onDel(viewHolder.getAdapterPosition());
                }
            }
        });

    }

//    ClickableSpan clickableSpan = new ClickableSpan() {
//        @Override
//        public void onClick(View widget) {
//            ToastUtils.showShortToast("事件触发了 landscapes and nedes");
//        }
//
//        @Override
//        public void updateDrawState(TextPaint ds) {
//            ds.setColor(Utils.getContext().getResources().getColor(R.color.clickspan_color));
//            ds.setUnderlineText(true);
//        }
//    };


}

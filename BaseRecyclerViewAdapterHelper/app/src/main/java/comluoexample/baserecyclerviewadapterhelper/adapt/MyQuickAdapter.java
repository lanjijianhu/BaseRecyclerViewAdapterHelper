//package comluoexample.baserecyclerviewadapterhelper.adapt;
//
//import android.content.Context;
//import android.view.View;
//import android.widget.Toast;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//
//import java.util.List;
//
//import comluoexample.baserecyclerviewadapterhelper.R;
//import comluoexample.baserecyclerviewadapterhelper.javaBean.MultyItemBean;
//
///**
// * Created by DQ on 2017/6/17.
// */
//public class MyQuickAdapter extends BaseQuickAdapter<MultyItemBean,BaseViewHolder> implements View.OnClickListener,View.OnLongClickListener {
//private Context context;
//
//    public MyQuickAdapter( Context context,int layoutResId, List<MultyItemBean> data) {
//        super(layoutResId, data);
//        this.context=context;
//    }
//
//
//    @Override
//    protected void convert(BaseViewHolder baseViewHolder, MultyItemBean multyItemBean) {
//        baseViewHolder.setText(R.id.content_tv, multyItemBean.getName())
////                .setChecked(R.id.checkbox_content,multyItemBean.checked)
//                .setOnClickListener(R.id.content_tv,this)
//                //设置item中某个控件的长点击事件监听,而.setOnItemClickListener()是设置整个条目的点击监听的
//                .setOnLongClickListener(R.id.content_tv,this);
////        this.setOnRecyclerViewItemClickListener();
////        this.setOnRecyclerViewItemChildClickListener(listener);
////        this.setOnRecyclerViewItemLongClickListener(listener2);
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.content_tv) {
//            Toast.makeText(context, "Item中某个控件被点击了", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public boolean onLongClick(View view) {
//        if (view.getId() == R.id.content_tv) {
//            Toast.makeText(context, "Item中某个控件被长按了", Toast.LENGTH_SHORT).show();
//        }
//        return true;
//    }
//}
